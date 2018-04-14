package com.gus.hackaton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Option
{
    @SerializedName("value")
    @Expose
    public String value;

    @SerializedName("_correct")
    @Expose
    public boolean isCorrect;
}
