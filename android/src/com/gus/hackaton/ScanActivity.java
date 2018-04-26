package com.gus.hackaton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.google.android.cameraview.CameraView;
import com.google.gson.JsonObject;
import com.gus.hackaton.db.AppDatabase;
import com.gus.hackaton.db.entity.Product;
import com.gus.hackaton.net.Api;
import com.gus.hackaton.net.ApiService;
import com.gus.hackaton.utils.Utils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gus.hackaton.MainActivity.POINTS_KEY;


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
        if (BuildConfig.DEBUG) Log.d(TAG, rawResult.getContents()); // Prints scan results

        new ReadProduct(this).execute(rawResult.getContents());
    }

    private void showScannedProductInfo(int id, String eurostat_id) {
        ApiService api = Api.getEurostatApi();
        api.getEurostatData(eurostat_id).enqueue(new Callback<JsonObject>()
        {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
            {
                JsonObject eurostatData = response.body();
                Log.d(TAG, "onResponse: " + eurostatData.toString());
                JsonObject countryInfo = eurostatData.getAsJsonObject("value");

                new ScanProduct(ScanActivity.this).execute(String.valueOf(id),
                        countryInfo.toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t)
            {
                Toast.makeText(ScanActivity.this, R.string.internet_error, Toast.LENGTH_SHORT).show();
            }
        });
        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
        mScannerView.stopCamera();
        scanned = true;
        setContentView(R.layout.scan_activity);
        ButterKnife.bind(this);

        toggleVisibility(false);
        mCameraView.start();
    }


    @OnClick(R.id.scanTryButton)
    public void tryAgainButton() {
        // https://stackoverflow.com/questions/1898886/removing-an-activity-from-the-history-stack
        Intent intent = new Intent(this, ScanActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private static class ReadProduct extends AsyncTask<String, Void, Product> {

        private WeakReference<ScanActivity> activityReference;
        ReadProduct(ScanActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Product doInBackground(String... id)
        {
            AppDatabase appDatabase = AppDatabase.getsInstance(activityReference.get());
            return appDatabase.productDao().getProduct(id[0]);
        }

        @Override
        protected void onPostExecute(Product product)
        {
            super.onPostExecute(product);
            if (product != null)
                activityReference.get().showScannedProductInfo(product.getId(), product.getEurostat_id());
            else
                Toast.makeText(activityReference.get(), R.string.no_barcode_database, Toast.LENGTH_SHORT).show();
        }
    }

    private static class ScanProduct extends AsyncTask<String, Void, Product> {

        private WeakReference<ScanActivity> activityReference;
        ScanProduct(ScanActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Product doInBackground(String... id)
        {
            AppDatabase appDatabase = AppDatabase.getsInstance(activityReference.get());
            Product product = appDatabase.productDao().getProduct(id[0]);
            if (!product.isScanned()) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activityReference.get());
                int oldPoints = prefs.getInt(POINTS_KEY, 0);
                prefs.edit().putInt(POINTS_KEY, oldPoints + product.getPoints()).apply();
                appDatabase.productDao().productWasScanned(id[0], id[1]);
                product = appDatabase.productDao().getProduct(id[0]);
            }

            return product;
        }

        @Override
        protected void onPostExecute(Product product)
        {
            ScanActivity scanActivity = activityReference.get();
            if (product != null) {
                scanActivity.toggleVisibility(true);

                if (BuildConfig.DEBUG) Log.d(TAG, "onResponse: " + product.getName());
                scanActivity.name.setText(product.getName());
                scanActivity.health_indicator.setText(String.valueOf(product.getHealth_indicator()));
                scanActivity.calories.setText(String.valueOf(product.getCalories()));
                scanActivity.fat.setText(String.valueOf(product.getFat()));
                scanActivity.carbohydrate.setText(String.valueOf(product.getCarbohydrate()));
                scanActivity.sugar.setText(String.valueOf(product.getSugar()));
                scanActivity.protein.setText(String.valueOf(product.getProtein()));

                Utils.invalidateChart(product.getEurostatData(), scanActivity.radarChart, scanActivity);

            } else {
                if (BuildConfig.DEBUG) Log.d(TAG, "onResponse: productInfo is NULL");
                Toast.makeText(scanActivity, scanActivity.getString(R.string.errorNoScanResult), Toast.LENGTH_SHORT).show();
            }
        }
    }
}