package com.gus.hackaton.fridge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.gus.hackaton.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * https://stackoverflow.com/questions/24471109/recyclerview-onclick
 *
 * https://blog.devcenter.co/unboxing-the-flexboxlayout-a7cfd125f023
 * 
 */
public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.ViewHolder>  {

    private List<FridgeItem> fridgeItemList;

    private final OnItemClicked onItemClicked;

    public FridgeAdapter(List<FridgeItem> fridgeItemList, OnFridgeItemClicked onFridgeItemClicked) {
        this.fridgeItemList = fridgeItemList;

        this.onItemClicked = (position, view) -> onFridgeItemClicked.onClick(fridgeItemList.get(position), view);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fridge_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ViewGroup.LayoutParams lp = holder.fridgeItemContainer.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams params = (FlexboxLayoutManager.LayoutParams)
                    holder.fridgeItemContainer.getLayoutParams();
            params.setFlexGrow(1.0f);
        }

        holder.bind(position, onItemClicked, fridgeItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return fridgeItemList.size();
    }


    public List<FridgeItem> getData() {
        return fridgeItemList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fridgeItemImage)
        ImageView imageView;

        @BindView(R.id.fridgeItemContainer)
        View fridgeItemContainer;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        void bind(int position, OnItemClicked onItemClicked, FridgeItem fridgeItem) {

            imageView.setPadding(20, 20, 20, 20);
            imageView.setImageResource(fridgeItem.getDrawableRes());

            imageView.setOnClickListener(v -> onItemClicked.onClick(position, v));


            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)imageView.getLayoutParams();

            if (position % 6 == 2) {
                params.setMargins(0, 20, 0, 0);

            } else if (position > 11 && position < 18) {
                params.setMargins(70, 0, 0, 0);

            }
        }
    }

    private interface OnItemClicked {

        void onClick(int position, View view);
    }

    public interface OnFridgeItemClicked {
        void onClick(FridgeItem fridgeItem, View view);
    }


}
