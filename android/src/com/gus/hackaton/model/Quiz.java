package com.gus.hackaton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quiz
{
    @SerializedName("question")
    @Expose
    public String question;

    @SerializedName("option")
    @Expose
    public List<Option> options;
}


