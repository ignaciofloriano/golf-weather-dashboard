package com.ignacio.servicio;

import com.google.gson.Gson;
import com.ignacio.api.DailyMeteo;
import com.ignacio.api.RespuestaMeteo;
import com.ignacio.modelo.DatosMeteoHoy;
import com.ignacio.modelo.PrediccionDia;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ServicioMeteo {

    private static final String URL = "https://api.open-meteo.com/v1/forecast"
            + "?latitude=43.36&longitude=-5.84"
            + "&current=temperature_2m,apparent_temperature,precipitation_probability,"
            + "wind_speed_10m,uv_index,relative_humidity_2m"
            + "&daily=temperature_2m_max,precipitation_probability_max,"
            + "wind_speed_10m_max,uv_index_max"
            + "&wind_speed_unit=kmh&timezone=Europe%2FMadrid&forecast_days=7";

    // devuelve objetos java desde json para prediccion diaria

    public DatosMeteoHoy obtenerDatosHoy() throws Exception {
        String json = hacerPeticion();
        RespuestaMeteo respuesta = new Gson().fromJson(json, RespuestaMeteo.class);

        return new DatosMeteoHoy(
                respuesta.current.temperatura,
                respuesta.current.sensacionTermica,
                respuesta.current.lluvia,
                respuesta.current.viento,
                (int) respuesta.current.uv,
                respuesta.current.humedad,
                calcularDireccionViento(),
                calcularVisibilidad(respuesta.current.lluvia)
        );
    }

    // devuelve objetos java desde json para prediccion 7 dias

    public List<PrediccionDia> obtenerPronostico() throws Exception {
        String json = hacerPeticion();
        RespuestaMeteo respuesta = new Gson().fromJson(json, RespuestaMeteo.class);
        DailyMeteo daily = respuesta.daily;

        // ajustado para dias de la semana correctos partiendo de "hoy"

        List<PrediccionDia> predicciones = new ArrayList<>();

        String[] diasSemana = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};

        for (int i = 0; i < 7; i++) {

            // api devuelve fechas exactas y se parsean

            java.time.LocalDate fecha = java.time.LocalDate.parse(daily.time.get(i));

            // día 0 siempre es hoy

            String nombreDia = (i == 0) ? "Hoy" : diasSemana[fecha.getDayOfWeek().getValue() % 7];

            predicciones.add(new PrediccionDia(
                    nombreDia,
                    daily.temperaturaMax.get(i),
                    daily.probabilidadLluvia.get(i),
                    daily.vientoMax.get(i),
                    daily.uvMax.get(i).intValue()
            ));
        }

        return predicciones;
    }

    // peticion http para devolver json

    private String hacerPeticion() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String calcularDireccionViento() {
        return "NE";
    }

    private String calcularVisibilidad(double lluvia) {
        if (lluvia < 20) return "Excelente";
        if (lluvia < 50) return "Buena";
        return "Reducida";
    }
}
