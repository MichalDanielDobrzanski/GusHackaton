package com.gus.hackaton.ranking;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gus.hackaton.BuildConfig;
import com.gus.hackaton.R;
import com.gus.hackaton.db.AppDatabase;
import com.gus.hackaton.db.entity.Ranking;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gus.hackaton.MainActivity.POINTS_KEY;

public class RankingActivity extends AppCompatActivity {

    private static final String TAG = RankingAdapter.class.getSimpleName();

    @BindView(R.id.rankingRecyclerView)
    RecyclerView recyclerView;

    private RankingAdapter rankingAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate: ");
        setContentView(R.layout.ranking_activity);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rankingAdapter = new RankingAdapter();
        recyclerView.setAdapter(rankingAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        new UpdateRanking(this).execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private static class UpdateRanking extends AsyncTask<Void, Void, List<Ranking>>
    {
        private WeakReference<RankingActivity> activityReference;
        UpdateRanking(RankingActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Ranking> doInBackground(Void... voids)
        {
            return AppDatabase.getsInstance(activityReference.get()).rankingDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Ranking> r)
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activityReference.get());
            r.add(new Ranking(3121212, activityReference.get().getString(R.string.me), prefs.getInt(POINTS_KEY, 0)));
            r.sort((o1, o2) -> o2.getPoints() - o1.getPoints());
            activityReference.get().rankingAdapter.setData(r);
        }
    }
}
