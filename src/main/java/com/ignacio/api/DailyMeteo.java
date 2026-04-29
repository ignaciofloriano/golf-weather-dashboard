package com.ignacio.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// clase para reflejar DailyMeteo

public class DailyMeteo {

    public List<String> time;

    @SerializedName("temperature_2m_max")
    public List<Double> temperaturaMax;

    @SerializedName("precipitation_probability_max")
    public List<Double> probabilidadLluvia;

    @SerializedName("wind_speed_10m_max")
    public List<Double> vientoMax;

    @SerializedName("uv_index_max")
    public List<Double> uvMax;
}