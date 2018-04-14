package com.gus.hackaton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import com.google.android.cameraview.CameraView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayProductStatsActivity extends AppCompatActivity
{

    @BindView(R.id.display_view)
    CameraView mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product_stats);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    protected void onPause() {
        mCameraView.stop();
        super.onPause();
    }
}
