package com.gus.hackaton.ranking;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gus.hackaton.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gus.hackaton.utils.Utils.DUMMY_RANKING_LIST;

public class RankingActivity extends AppCompatActivity {

    @BindView(R.id.rankingRecyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("RankingActivity", "onCreate: ");

        setContentView(R.layout.ranking_activity);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RankingAdapter rankingAdapter = new RankingAdapter();

        recyclerView.setAdapter(rankingAdapter);

        rankingAdapter.setData(DUMMY_RANKING_LIST);
    }

}
