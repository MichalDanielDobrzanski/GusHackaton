package com.gus.hackaton.ranking;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.gus.hackaton.R;
import com.gus.hackaton.net.Api;
import com.gus.hackaton.net.ApiService;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gus.hackaton.utils.Utils.DUMMY_RANKING_LIST;

public class RankingActivity extends AppCompatActivity {

    private static final String TAG = RankingAdapter.class.getSimpleName();

    @BindView(R.id.rankingRecyclerView)
    RecyclerView recyclerView;

    private RankingAdapter rankingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: ");

        setContentView(R.layout.ranking_activity);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rankingAdapter = new RankingAdapter();

        recyclerView.setAdapter(rankingAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        ApiService api = Api.getApi();

        api.getRanking().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject jsonObject = response.body();

                JsonElement yourJson = jsonObject.get("list");

                Type listType = new TypeToken<List<RankingItem>>() {}.getType();

                List<RankingItem> yourList = new Gson().fromJson(yourJson, listType);

                rankingAdapter.setData(yourList);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(RankingActivity.this, "Problem z siecią!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
