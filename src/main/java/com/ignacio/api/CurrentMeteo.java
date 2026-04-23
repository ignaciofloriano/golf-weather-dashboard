package com.ignacio.api;

import com.google.gson.annotations.SerializedName;

// clase para reflejar CurrentMeteo

public class CurrentMeteo {

    @SerializedName("temperature_2m")
    public double temperatura;

    @SerializedName("apparent_temperature")
    public double sensacionTermica;

    @SerializedName("precipitation_probability")
    public double lluvia;

    @SerializedName("wind_speed_10m")
    public double viento;

    @SerializedName("uv_index")
    public double uv;

    @SerializedName("relative_humidity_2m")
    public int humedad;
}