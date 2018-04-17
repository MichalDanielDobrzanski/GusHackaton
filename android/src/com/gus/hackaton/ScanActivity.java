package com.gus.hackaton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.github.mikephil.charting.charts.RadarChart;
import com.google.android.cameraview.CameraView;
import com.gus.hackaton.model.NutritionInfo;
import com.gus.hackaton.model.Product;
import com.gus.hackaton.repository.Repository;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;


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

    @Inject
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainApplication) getApplication()).getAppComponent().inject(this);

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
        tryToFindNutritionInformation(rawResult.getContents());
    }

    private void tryToFindNutritionInformation(String id) {
        Log.d(TAG, "tryToFindNutritionInformation: id = " + id);


        Optional<NutritionInfo> optional = Stream.of(repository.getNutritionInfoList())
                .filter(nInfo -> Objects.equals(nInfo.getId(), id)).findFirst();

        if (optional.isPresent()) {

            Optional<Product> optionalProduct = Stream.of(repository.getCurrentProductList())
                    .filter(p -> p.id.equals(optional.get().getId()))
                    .findFirst();

            if (optionalProduct.isPresent()) {

                toggleVisibility(true);

                NutritionInfo nutritionInfo = optional.get();
                Product product = optionalProduct.get();

                name.setText(product.name);
                calories.setText(String.valueOf(nutritionInfo.calories));
                fat.setText(String.valueOf(nutritionInfo.fat));
                carbohydrate.setText(String.valueOf(nutritionInfo.carbohydrate));
                sugar.setText(String.valueOf(nutritionInfo.sugar));
                protein.setText(String.valueOf(nutritionInfo.protein));
            }


        } else {
            toggleVisibility(false);
            Toast.makeText(ScanActivity.this, "Nie znaleziono produktu, spróbuj ponownie!", Toast.LENGTH_SHORT).show();
        }

//        ApiService api = Api.getEurostatApi();
//        api.getProductInfo(id).enqueue(new Callback<Product>()
//        {
//            @Override
//            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response)
//            {
//                Log.d(TAG, "onResponse: " + response.body().toString());
//
//
//                Product product = response.body();
//                if (product != null && product.nutritionInfo != null) {
//
//                    toggleVisibility(true);
//
//                    Log.d(TAG, "onResponse: " + product.name);
//                    name.setText(product.name);
//                    health_indicator.setText(String.valueOf(product.health_indicator));
//                    calories.setText(String.valueOf(product.nutritionInfo.calories));
//                    fat.setText(String.valueOf(product.nutritionInfo.fat));
//                    carbohydrate.setText(String.valueOf(product.nutritionInfo.carbohydrate));
//                    sugar.setText(String.valueOf(product.nutritionInfo.sugar));
//                    protein.setText(String.valueOf(product.nutritionInfo.protein));
//
//                    Utils.invalidateChart(product.eurostatDataList, radarChart);
//
//                    // global update
//                    repository.markScanned(product.name, product.eurostatDataList);
//
//                    api.addPoints(new Points(product.points));
//
//                } else {
//                    Log.d(TAG, "onResponse: product is NULL");
//                    Toast.makeText(ScanActivity.this, "Nie znaleziono produktu, spróbuj ponownie!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Product> call, Throwable t)
//            {
//                Toast.makeText(ScanActivity.this, "Problem z siecią!", Toast.LENGTH_SHORT).show();
//                t.printStackTrace();
//
//                scanChartProgressBar.setVisibility(View.GONE);
//            }
//        });
    }

    @OnClick(R.id.scanTryButton)
    public void tryAgainButton() {
        // https://stackoverflow.com/questions/1898886/removing-an-activity-from-the-history-stack
        Intent intent = new Intent(this, ScanActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}