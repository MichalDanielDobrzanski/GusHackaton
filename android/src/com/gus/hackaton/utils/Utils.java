package com.gus.hackaton.utils;

import android.graphics.Color;

import com.annimon.stream.Stream;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.gus.hackaton.R;
import com.gus.hackaton.model.EurostatData;
import com.gus.hackaton.model.Product;
import com.gus.hackaton.ranking.RankingItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static final int COLUMNS_COUNT = 2;

    public static List<RankingItem> DUMMY_RANKING_LIST = Arrays.asList(
            new RankingItem("Ryan gossling", 1000),
            new RankingItem("Konrad rap G", 900),
            new RankingItem("Darek The Dude", 760),
            new RankingItem("Roots reggae Radek", 160),
            new RankingItem("Michal D", 160),
            new RankingItem("Michal DD", 150),
            new RankingItem("Michal DDD", 140),
            new RankingItem("Michal DDDD", 120)
    );


    public static List<Product> INITIAL_PRODUCTS_LIST = Arrays.asList(
            new Product("000000000017", "Chleb", 3, 8, "CP01113A", R.drawable.bread),
            new Product("000000000024", "Jajko", 5, 7, "CP01147A", R.drawable.egg),
            new Product("000000000031", "Platki sniadaniowe", 15, 9, "CP01112B", R.drawable.cornflakes),
            new Product("000000000048", "Losos", 25, 7, "CP01131A", R.drawable.fish),
            new Product("000000000055", "Ryz", 5, 4, "CP01111A", R.drawable.rice),
            new Product("000000000062", "Oliwki", 15, 8, "CP01153A", R.drawable.olive),
            new Product("000000000079", "Ser", 8, 4, "CP01145A", R.drawable.cheese)
    );

    public static void invalidateChart(List<EurostatData> eurostatDatas, RadarChart radarChart) {

        radarChart.setBackgroundColor(Color.argb(77, 60, 65, 82));

        List<RadarEntry> entries = new ArrayList<>();
        Stream.of(eurostatDatas)
                .forEach(eurostatData -> entries.add(new RadarEntry((float)eurostatData.price)));

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        List<String> labels = new ArrayList<>();
        Stream.of(eurostatDatas)
                .forEach(eurostatData -> labels.add(EurostatData.Country.values()[eurostatData.country].name()));

        xAxis.setValueFormatter((value, axis) -> labels.get(((int) value) % labels.size()));
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setLabelCount(labels.size(), false);

        yAxis.setTextSize(11f);
        yAxis.setDrawLabels(false);
        yAxis.setTextColor(Color.WHITE);

        RadarDataSet radarDataSet = new RadarDataSet(entries, "średnia cena produktu w EUR");
        radarDataSet.setColor(Color.CYAN);

        radarDataSet.setValueTextColor(Color.WHITE);
        radarDataSet.setDrawFilled(true);

        RadarData radarData = new RadarData(radarDataSet);

        radarChart.getLegend().setTextColor(Color.WHITE);
        radarChart.setDescription(null);
        radarChart.setData(radarData);
        radarChart.invalidate();
    }

}
