package com.gus.hackaton.fridge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gus.hackaton.R;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * https://stackoverflow.com/questions/24471109/recyclerview-onclick
 */
public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.ViewHolder>  {

    private List<FridgeItem> fridgeItemList;

    private final View.OnClickListener onClickListener;

    public FridgeAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        fridgeItemList = Collections.emptyList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fridge_item, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return fridgeItemList.size();
    }

    public void invalidateData(List<FridgeItem> fridgeItemList) {
        this.fridgeItemList = fridgeItemList;
        notifyDataSetChanged();
    }

    public List<FridgeItem> getData() {
        return fridgeItemList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fridgeItemImage)
        ImageView imageView;


        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

    }


}
