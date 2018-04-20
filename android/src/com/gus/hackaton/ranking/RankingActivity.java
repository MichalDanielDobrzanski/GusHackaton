package com.gus.hackaton.ranking;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gus.hackaton.BuildConfig;
import com.gus.hackaton.R;
import com.gus.hackaton.db.AppDatabase;
import com.gus.hackaton.db.entity.Ranking;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        new AsyncTask<Void, Void, List<Ranking>>() {

            @Override
            protected List<Ranking> doInBackground(Void... voids)
            {
                return AppDatabase.getsInstance(RankingActivity.this).rankingDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Ranking> r)
            {
                rankingAdapter.setData(r);
            }
        }.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
