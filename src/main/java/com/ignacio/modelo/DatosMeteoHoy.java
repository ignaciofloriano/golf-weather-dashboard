package com.ignacio.modelo;

public class DatosMeteoHoy {

        private double temperatura;
        private double sensacionTermica;
        private double lluvia;
        private double viento;
        private int uv;
        private int humedad;
        private String direccionViento;
        private String visibilidad;

        // constructor

        public DatosMeteoHoy(double temperatura, double sensacionTermica, double lluvia, double viento, int uv, int humedad, String direccionViento, String visibilidad) {
            this.temperatura = temperatura;
            this.sensacionTermica = sensacionTermica;
            this.lluvia = lluvia;
            this.viento = viento;
            this.uv = uv;
            this.humedad = humedad;
            this.direccionViento = direccionViento;
            this.visibilidad = visibilidad;
        }

        // getters

        public double getTemperatura()       {
            return temperatura; }

        public double getSensacionTermica()  {
            return sensacionTermica; }

        public double getLluvia()            {
            return lluvia; }

        public double getViento()            {
            return viento; }

        public int getUv()                   {
            return uv; }

        public int getHumedad()              {
            return humedad; }

        public String getDireccionViento()   {
            return direccionViento; }

        public String getVisibilidad()       {
            return visibilidad; }
    }
