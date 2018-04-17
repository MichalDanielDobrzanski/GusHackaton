package com.gus.hackaton.net;

import android.content.Context;
import android.widget.Toast;

import com.gus.hackaton.R;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Utility for retrofit callbacks
 */
public class CallbackImpl<T> implements Callback<T> {

    private final Response<T> tResponse;
    private Context context;

    public CallbackImpl(Context context, Response<T> tResponse) {
        this.context = context;
        this.tResponse = tResponse;
    }

    @Override
    public void onResponse(Call<T> call, retrofit2.Response<T> response) {
        tResponse.onResult(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        showError(t);
    }

    private void showError(Throwable t) {
        t.printStackTrace();
        Toast.makeText(context, context.getString(R.string.networkError), Toast.LENGTH_SHORT).show();
    }

    public interface Response<T> {

        void onResult(T t);
    }
}
