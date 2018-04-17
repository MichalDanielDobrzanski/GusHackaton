package com.gus.hackaton;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.google.android.cameraview.CameraView;
import com.gus.hackaton.model.EurostatData;
import com.gus.hackaton.model.Points;
import com.gus.hackaton.model.Product;
import com.gus.hackaton.net.Api;
import com.gus.hackaton.net.ApiService;
import com.gus.hackaton.shared.FlowManager;
import com.gus.hackaton.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler
{
    private static final String TAG = ScanActivity.class.getSimpleName();
    private ZBarScannerView mScannerView;

    @BindView(R.id.display_view)
    CameraView mCameraView;

    @BindView(R.id.name_l)
    TextView name_l;
    @BindView(R.id.health_indicator_l)
    TextView health_indicator_l;
    @BindView(R.id.calories_l)
    TextView calories_l;
    @BindView(R.id.fat_l)
    TextView fat_l;
    @BindView(R.id.carbohydrate_l)
    TextView carbohydrate_l;
    @BindView(R.id.protein_l)
    TextView protein_l;
    @BindView(R.id.sugar_l)
    TextView sugar_l;

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.health_indicator)
    TextView health_indicator;
    @BindView(R.id.calories)
    TextView calories;
    @BindView(R.id.carbohydrate)
    TextView carbohydrate;
    @BindView(R.id.protein)
    TextView protein;
    @BindView(R.id.sugar)
    TextView sugar;
    @BindView(R.id.fat)
    TextView fat;

    @BindView(R.id.scanChart)
    RadarChart radarChart;

    @BindView(R.id.scanChartProgressBar)
    ProgressBar scanChartProgressBar;

    private boolean scanned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }


    @Override
    public void onResume() {
        super.onResume();
        if(!scanned) {
            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
            mScannerView.startCamera();          // Start camera on resume
        } else {
            mCameraView.start();
        }
    }

    private void toggleVisibility(boolean visible) {
        name_l.setVisibility(visible ? View.VISIBLE : View.GONE);

        health_indicator_l.setVisibility(visible ? View.VISIBLE : View.GONE);
        calories_l.setVisibility(visible ? View.VISIBLE : View.GONE);
        fat_l.setVisibility(visible ? View.VISIBLE : View.GONE);

        carbohydrate_l.setVisibility(visible ? View.VISIBLE : View.GONE);
        protein_l.setVisibility(visible ? View.VISIBLE : View.GONE);
        sugar_l.setVisibility(visible ? View.VISIBLE : View.GONE);


        name.setVisibility(visible ? View.VISIBLE : View.GONE);

        health_indicator.setVisibility(visible ? View.VISIBLE : View.GONE);
        calories.setVisibility(visible ? View.VISIBLE : View.GONE);
        fat.setVisibility(visible ? View.VISIBLE : View.GONE);

        carbohydrate.setVisibility(visible ? View.VISIBLE : View.GONE);
        protein.setVisibility(visible ? View.VISIBLE : View.GONE);
        sugar.setVisibility(visible ? View.VISIBLE : View.GONE);

        radarChart.setVisibility(visible ? View.VISIBLE : View.GONE);
        scanChartProgressBar.setVisibility(visible ? View.GONE : View.VISIBLE);

    }

    @Override
    public void onPause() {
        if(!scanned)
            mScannerView.stopCamera();           // Stop camera on pause
        else {
            mCameraView.stop();
        }
        super.onPause();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.d(TAG, rawResult.getContents()); // Prints scan results

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
        mScannerView.stopCamera();
        scanned = true;
        setContentView(R.layout.scan_activity);

        ButterKnife.bind(this);

        toggleVisibility(false);

        mCameraView.start();
        sendRequest(rawResult.getContents());
    }

    private void sendRequest(String id)
    {
        ApiService api = Api.getEurostatApi();

        Log.d(TAG, "sendRequest: " + id);


        api.getProductInfo(id).enqueue(new Callback<Product>()
        {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response)
            {
                Log.d(TAG, "onResponse: " + response.body().toString());


                Product product = response.body();
                if (product != null && product.nutricalInfo != null) {

                    toggleVisibility(true);

                    Log.d(TAG, "onResponse: " + product.name);
                    name.setText(product.name);
                    health_indicator.setText(String.valueOf(product.health_indicator));
                    calories.setText(String.valueOf(product.nutricalInfo.calories));
                    fat.setText(String.valueOf(product.nutricalInfo.fat));
                    carbohydrate.setText(String.valueOf(product.nutricalInfo.carbohydrate));
                    sugar.setText(String.valueOf(product.nutricalInfo.sugar));
                    protein.setText(String.valueOf(product.nutricalInfo.protein));

                    Utils.invalidateChart(product.eurostatDataList, radarChart);

                    // global update
                    FlowManager.getInstance().markScanned(ScanActivity.this, product.name, product.eurostatDataList);

                    api.addPoints(new Points(product.points));

                } else {
                    Log.d(TAG, "onResponse: product is NULL");
                    Toast.makeText(ScanActivity.this, "Nie znaleziono produktu, spróbuj ponownie!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t)
            {
                Toast.makeText(ScanActivity.this, "Problem z siecią!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();

                scanChartProgressBar.setVisibility(View.GONE);
            }
        });
    }



    @OnClick(R.id.scanTryButton)
    public void tryAgainButton() {
        // https://stackoverflow.com/questions/1898886/removing-an-activity-from-the-history-stack
        Intent intent = new Intent(this, ScanActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}