package com.ignacio.modelo;

public class PrediccionDia {

    private String nombreDia;
    private double temperaturaMax;
    private double probabilidadLluvia;
    private double vientoMax;
    private int uv;

    // constructor

    public PrediccionDia(String nombreDia, double temperaturaMax, double probabilidadLluvia, double vientoMax, int uv) {
        this.nombreDia = nombreDia;
        this.temperaturaMax = temperaturaMax;
        this.probabilidadLluvia = probabilidadLluvia;
        this.vientoMax = vientoMax;
        this.uv = uv;
    }

    // getters

    public String getNombreDia()           {
        return nombreDia; }

    public double getTemperaturaMax()      {
        return temperaturaMax; }

    public double getProbabilidadLluvia()  {
        return probabilidadLluvia; }

    public double getVientoMax()           {
        return vientoMax; }

    public int getUv()                     {
        return uv; }
}
