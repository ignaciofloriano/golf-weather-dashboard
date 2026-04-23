package com.ignacio;

import com.ignacio.logica.AnalizadorGolf;
import com.ignacio.modelo.DatosMeteoHoy;
import com.ignacio.modelo.PrediccionDia;
import com.ignacio.servicio.ServicioMeteo;
import io.javalin.Javalin;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        // Probamos la API antes de levantar el servidor
        ServicioMeteo servicio = new ServicioMeteo();
        AnalizadorGolf analizador = new AnalizadorGolf();

        DatosMeteoHoy hoy = servicio.obtenerDatosHoy();
        List<PrediccionDia> pronostico = servicio.obtenerPronostico();

        System.out.println("=== DATOS DE HOY ===");
        System.out.println("Temperatura: " + hoy.getTemperatura() + "°C");
        System.out.println("Viento: " + hoy.getViento() + " km/h");
        System.out.println("Lluvia: " + hoy.getLluvia() + "%");
        System.out.println("Puntuación: " + analizador.calcular(hoy));

        System.out.println("\n=== PRONÓSTICO 7 DÍAS ===");
        for (PrediccionDia dia : pronostico) {
            System.out.println(dia.getNombreDia() + " → " +
                    dia.getTemperaturaMax() + "°C · " +
                    dia.getProbabilidadLluvia() + "% lluvia · " +
                    analizador.calcular(dia));
        }

        // Servidor Javalin
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> {
            ctx.contentType("text/plain; charset=utf-8");
            ctx.result("⛳ Golf Weather Dashboard — OK");
        });
    }
}
