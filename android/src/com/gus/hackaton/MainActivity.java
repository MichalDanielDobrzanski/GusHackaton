package com.gus.hackaton;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.gus.hackaton.fridge.FridgeAdapter;
import com.gus.hackaton.utils.ZoomAnimator;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.gus.hackaton.fridge.FridgeUtils.COLUMNS_COUNT;
import static com.gus.hackaton.fridge.FridgeUtils.DUMMY_BADGE_LIST;
import static com.gus.hackaton.fridge.FridgeUtils.DUMMY_QUEST_LIST;

/**
 * https://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
 */
public class MainActivity extends AppCompatActivity implements AndroidFragmentApplication.Callbacks {

    public static final int CAMERA_PERMISSION = 101;

    @BindView(R.id.badgesRecyclerView)
    RecyclerView badgesRecyclerView;

    @BindView(R.id.questsRecyclerView)
    RecyclerView questsRecyclerView;

	@BindView(R.id.scan_barcode)
	Button scanBarcode;

	@BindView(R.id.expanded_fridge_item)
    View expandedFridgeItem;

	@BindView(R.id.mainContainer)
    View mainContainer;

    private FridgeAdapter badgesAdapter;
    private FridgeAdapter questsAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        }

		setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

		scanBarcode.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, ScanActivity.class);
            startActivity(myIntent);
        });

		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.heroContainer, new HeroFragment())
				.commit();

		setupRecyclerViews();
	}

    private void setupRecyclerViews() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        badgesRecyclerView.setHasFixedSize(true);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
        layoutManager.setFlexWrap(FlexWrap.WRAP);

        badgesRecyclerView.setLayoutManager(layoutManager);



        FridgeAdapter.OnFridgeItemClicked onFridgeItemClicked = createFridgeItemHandler();


        badgesAdapter = new FridgeAdapter(DUMMY_BADGE_LIST, onFridgeItemClicked);

        badgesRecyclerView.setAdapter(badgesAdapter);


        questsRecyclerView.setHasFixedSize(true);
        questsRecyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS_COUNT, LinearLayoutManager.VERTICAL, false));
        questsAdapter = new FridgeAdapter(DUMMY_QUEST_LIST, onFridgeItemClicked);

        questsRecyclerView.setAdapter(questsAdapter);

    }

    private FridgeAdapter.OnFridgeItemClicked createFridgeItemHandler() {
        return (fridgeItem, view) -> {

            TextView tvType = expandedFridgeItem.findViewById(R.id.typeFridgeItem);
            tvType.setText(fridgeItem.getFridgeType().name());

            TextView tvDescr = expandedFridgeItem.findViewById(R.id.typeFridgeDescr);
            tvDescr.setText(fridgeItem.getDescription());

            ZoomAnimator.zoomImageFromThumb(view, expandedFridgeItem, mainContainer);
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
                Toast.makeText(this, getString(R.string.success_permission), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, getString(R.string.error_no_permission), Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
