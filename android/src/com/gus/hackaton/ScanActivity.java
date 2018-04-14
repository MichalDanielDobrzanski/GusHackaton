package com.gus.hackaton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.cameraview.CameraView;
import com.gus.hackaton.model.ProductInfo;
import com.gus.hackaton.net.Api;
import com.gus.hackaton.net.ApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        mCameraView.start();
        sendRequest(rawResult.getContents());
    }

    private void sendRequest(String id)
    {
        ApiService api = Api.getApi();
        Log.d(TAG, "sendRequest: " + id);
        api.getProductInfo(id).enqueue(new Callback<ProductInfo>()
        {
            @Override
            public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response)
            {
                ProductInfo productInfo = response.body();
                Log.d(TAG, "onResponse: " + productInfo.name);
                name.setText(productInfo.name);
                health_indicator.setText(String.valueOf(productInfo.health_indicator));
                calories.setText(String.valueOf(productInfo.nutricalInfo.calories));
                fat.setText(String.valueOf(productInfo.nutricalInfo.fat));
                carbohydrate.setText(String.valueOf(productInfo.nutricalInfo.carbohydrate));
                sugar.setText(String.valueOf(productInfo.nutricalInfo.sugar));
                protein.setText(String.valueOf(productInfo.nutricalInfo.protein));

                //mCameraView.start();
            }

            @Override
            public void onFailure(Call<ProductInfo> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }
}