package com.gus.hackaton;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AndroidFragmentApplication.Callbacks {

	@BindView(R.id.scan_barcode)
	Button scanBarcode;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

		scanBarcode.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent myIntent = new Intent(MainActivity.this, ScanActivity.class);
				startActivity(myIntent);
			}
		});
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.heroContainer, new HeroFragment())
				.commit();
	}

    @Override
    public void exit() {

    }
}
