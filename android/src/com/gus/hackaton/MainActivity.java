package com.gus.hackaton;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gus.hackaton.FridgeAdapter.COLUMNS_COUNT;

public class MainActivity extends AppCompatActivity implements AndroidFragmentApplication.Callbacks {

    public static final int CAMERA_PERMISSION = 101;

    @BindView(R.id.fridgeRecyclerView)
    RecyclerView recyclerView;

	@BindView(R.id.scan_barcode)
	Button scanBarcode;

    private FridgeAdapter fridgeAdapter;

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
}
