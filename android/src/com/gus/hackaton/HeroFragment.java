package com.gus.hackaton;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

/**
 * https://stackoverflow.com/questions/17966338/background-transparency-in-libgdx?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
 */
public class HeroFragment extends AndroidFragmentApplication {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        config.r = config.g = config.b = config.a = 8;

        View view = initializeForView(new HeroGame(), config);

        if (graphics.getView() instanceof SurfaceView) {
            SurfaceView glView = (SurfaceView) graphics.getView();
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderOnTop(true);
        }

        return view;

    }
}
