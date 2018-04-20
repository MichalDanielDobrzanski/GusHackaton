package com.gus.hackaton;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.github.mikephil.charting.charts.RadarChart;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.ar.core.Session;
import com.gus.hackaton.ar.ARActivity;
import com.gus.hackaton.db.preferences.Storage;
import com.gus.hackaton.db.preferences.StorageImpl;
import com.gus.hackaton.fridge.FridgeAdapter;
import com.gus.hackaton.fridge.FridgeItem;
import com.gus.hackaton.model.Option;
import com.gus.hackaton.model.Points;
import com.gus.hackaton.model.Quiz;
import com.gus.hackaton.net.Api;
import com.gus.hackaton.net.ApiService;
import com.gus.hackaton.ranking.RankingActivity;
import com.gus.hackaton.shared.FlowManager;
import com.gus.hackaton.utils.Utils;
import com.gus.hackaton.utils.ZoomAnimator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.graphics.Typeface.BOLD;
import static com.gus.hackaton.utils.Utils.COLUMNS_COUNT;

/**
 * https://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
 */
public class MainActivity extends AppCompatActivity implements AndroidFragmentApplication.Callbacks {

    public static final int CAMERA_PERMISSION = 101;

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.badgesRecyclerView)
    RecyclerView badgesRecyclerView;

    @BindView(R.id.questsRecyclerView)
    RecyclerView questsRecyclerView;

	@BindView(R.id.scanBarcodeButton)
	Button scanBarcode;
	
    @BindView(R.id.showAr)
    Button showAr;

	@BindView(R.id.expanded_fridge_item)
    View expandedFridgeItem;

	@BindView(R.id.mainContainer)
    View mainContainer;

	@BindView(R.id.points)
    TextView pointsTextView;

	@BindView(R.id.quiz_button)
    Button quizButton;

    private FridgeAdapter badgesAdapter;
    private FridgeAdapter questsAdapter;

    private Storage storage;

    private SharedPreferences prefs;

    @Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        }

		setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

        quizButton.setOnClickListener(v -> prepareQuiz());

		scanBarcode.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, ScanActivity.class);
            startActivity(myIntent);
        });

        showAr.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, ARActivity.class);
            startActivity(myIntent);
        });

		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.heroContainer, new HeroFragment())
				.commit();

		setupRecyclerViews();

		try {
            Session session = new Session(/* context= */ this);
        } catch (Exception e) {
		    e.printStackTrace();
		    showAr.setVisibility(View.INVISIBLE);
        }
		storage = new StorageImpl(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
	}

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).apply();

            storage.putQuestList(FlowManager.createInitialQuestList(this));
            storage.putBadgeList(null);
        }

        List<FridgeItem> quests = storage.getQuestList();
        List<FridgeItem> badges = storage.getBadgeList();

        badgesAdapter.invalidateData(badges);
        questsAdapter.invalidateData(quests);

        refreshPoints();
    }

    private void refreshPoints()
    {
        ApiService api = Api.getApi();
        api.getPoints().enqueue(new Callback<Points>()
        {
            @Override
            public void onResponse(@NonNull Call<Points> call, @NonNull Response<Points> response)
            {
                HeroGame.score = response.body().points;

                if (BuildConfig.DEBUG) Log.d(TAG, "refreshPoints() onResponse: " + response.body().toString());

                updatePointsTextView(response);
            }

            @Override
            public void onFailure(Call<Points> call, Throwable t) {
                Utils.showError(MainActivity.this, t);
            }
        });
    }

    private void updatePointsTextView(Response<Points> response) {
        int points = response.body().points;
        String text = String.format(getString(R.string.points), points);
        pointsTextView.setText(text);
    }

    private void prepareQuiz()
    {
        ApiService ap = Api.getApi();
        ap.getQuiz().enqueue(new Callback<Quiz>()
        {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response)
            {
                Quiz quiz = response.body();
                if (BuildConfig.DEBUG) Log.d(TAG, "onResponse: " + quiz.question);
                CharSequence [] optionsChars = new CharSequence[4];
                boolean [] corectness = new boolean[4];
                List<Option> options = quiz.options;
                for(int i = 0; i < options.size(); ++i) {
                    if (BuildConfig.DEBUG) Log.d(TAG, "onResponse: " + options.get(i).value);
                    optionsChars[i] = options.get(i).value;
                    corectness[i] = options.get(i).isCorrect;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(quiz.question);
                TextView textView = new TextView(MainActivity.this);
                textView.setText(quiz.question);
                textView.setPadding(32,32,32,32);
                textView.setTypeface(null, BOLD);
                builder.setCustomTitle(textView);
                builder.setItems(optionsChars, (dialog, which) -> {

                    if (corectness[which]) {

                        addPoints(10);

                        Toast.makeText(MainActivity.this, R.string.rightAnswer, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }

    private void addPoints(int pnts)
    {
        ApiService api = Api.getApi();
        Points p = new Points(pnts);
        api.addPoints(p).enqueue(new Callback<Points>()
        {
            @Override
            public void onResponse(Call<Points> call, Response<Points> response)
            {
                updatePointsTextView(response);

                HeroGame.score = response.body().points;
            }

            @Override
            public void onFailure(Call<Points> call, Throwable t) {
                Utils.showError(MainActivity.this, t);
            }
        });
    }

    private void setupRecyclerViews() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        badgesRecyclerView.setHasFixedSize(true);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
        layoutManager.setFlexWrap(FlexWrap.WRAP);

        badgesRecyclerView.setLayoutManager(layoutManager);


        FridgeAdapter.OnFridgeItemClicked onFridgeItemClicked = createFridgeItemHandler();


        badgesAdapter = new FridgeAdapter(onFridgeItemClicked);

        badgesRecyclerView.setAdapter(badgesAdapter);


        questsRecyclerView.setHasFixedSize(true);
        questsRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS_COUNT, LinearLayoutManager.VERTICAL, false));
        questsAdapter = new FridgeAdapter(onFridgeItemClicked);

        questsRecyclerView.setAdapter(questsAdapter);

    }

    private FridgeAdapter.OnFridgeItemClicked createFridgeItemHandler() {
        return (fridgeItem, view) -> {

            TextView tvType = expandedFridgeItem.findViewById(R.id.typeFridgeItem);

            RadarChart radarChart = expandedFridgeItem.findViewById(R.id.typeFridgeChart);

            ImageView imageView = expandedFridgeItem.findViewById(R.id.typeFridgeImage);
            imageView.setImageResource(fridgeItem.getDrawableRes());

            ZoomAnimator.zoomImageFromThumb(view, expandedFridgeItem, mainContainer);

            String res = "";
            switch (fridgeItem.getFridgeType()) {
                case Badge:
                    res = getString(R.string.badge);
                    radarChart.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.INVISIBLE);
                    if (fridgeItem.eurostatData != null) {
                        Utils.invalidateChart(fridgeItem.eurostatData, radarChart);
                    }
                    break;
                case Quest:
                    radarChart.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                    res = getString(R.string.quest);
            }

            tvType.setText(res);

            TextView tvDescr = expandedFridgeItem.findViewById(R.id.typeFridgeDescr);
            tvDescr.setText(fridgeItem.getDescription());


        };
    }

    @Override
    public void exit() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, getString(R.string.successCameraPermission), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, getString(R.string.errorNoCameraPermission), Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.rankingButton)
    public void launchRanking(View view) {
        if (BuildConfig.DEBUG) Log.d(TAG, "launchRanking()");

        startActivity(new Intent(MainActivity.this, RankingActivity.class));
    }
}
