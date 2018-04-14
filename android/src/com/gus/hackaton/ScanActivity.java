package com.gus.hackaton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.cameraview.CameraView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;


public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler
{
    private static final String TAG = ScanActivity.class.getSimpleName();
    private ZBarScannerView mScannerView;

    @BindView(R.id.display_view)
    CameraView mCameraView;
    @BindView(R.id.textview)
    TextView textView;

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
        scanned = true;
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        mCameraView.start();
        textView.setText(rawResult.getContents());
    }
}