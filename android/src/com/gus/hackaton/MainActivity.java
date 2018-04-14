package com.gus.hackaton;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gus.hackaton.FridgeAdapter.COLUMNS_COUNT;

public class MainActivity extends AppCompatActivity implements AndroidFragmentApplication.Callbacks {


    @BindView(R.id.fridgeRecyclerView)
    RecyclerView recyclerView;

    private FridgeAdapter fridgeAdapter;

    @Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.heroContainer, new HeroFragment())
				.commit();

		setupRecyclerView();
	}

    private void setupRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS_COUNT));

        fridgeAdapter = new FridgeAdapter();
        recyclerView.setAdapter(fridgeAdapter);
    }

    @Override
    public void exit() {

    }
}
