package com.gus.hackaton.ranking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gus.hackaton.R;
import com.gus.hackaton.db.entity.Ranking;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private List<Ranking> rankingList;

    RankingAdapter() {
        rankingList = Collections.emptyList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(rankingList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return rankingList.size();
    }

    public void setData(List<Ranking> rankingItemList) {
        this.rankingList = rankingItemList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rankingPoints)
        TextView tvPoints;

        @BindView(R.id.rankingUserName)
        TextView tvUserName;

        @BindView(R.id.rankingPosition)
        TextView tvPosition;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        void bind(Ranking ranking, int position) {
            tvPosition.setText(String.valueOf(position + 1));
            tvPoints.setText(String.valueOf(ranking.getPoints()));
            tvUserName.setText(ranking.getUserName());
        }
    }
}
