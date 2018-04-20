package com.gus.hackaton.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.gus.hackaton.R;
import com.gus.hackaton.model.EurostatData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static final int COLUMNS_COUNT = 2;

    public static void invalidateChart(List<Float> eurostatDatas, RadarChart radarChart) {

        radarChart.setBackgroundColor(Color.argb(77, 60, 65, 82));

        List<RadarEntry> entries = new ArrayList<>();
        for (int i = 0; i < eurostatDatas.size(); ++i) {
            entries.add(new RadarEntry(eurostatDatas.get(i)));
        }

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        List<String> labels = Arrays.asList(new String[]{"ES", "HR", "IT", "PL", "FIN"});

        xAxis.setValueFormatter((value, axis) -> labels.get(((int) value) % labels.size()));
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setLabelCount(labels.size(), false);

        yAxis.setTextSize(11f);
        yAxis.setDrawLabels(false);
        yAxis.setTextColor(Color.WHITE);

        //TODO translate this
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

    public static void showError(Context context, Throwable t) {
        Toast.makeText(context, R.string.errorNetwork, Toast.LENGTH_SHORT).show();
        t.printStackTrace();

    }

}
