package com.ignacio;

import com.ignacio.logica.AnalizadorGolf;
import com.ignacio.modelo.DatosMeteoHoy;
import com.ignacio.modelo.PrediccionDia;
import com.ignacio.servicio.ServicioMeteo;
import com.ignacio.web.GeneradorHtml;
import io.javalin.Javalin;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        ServicioMeteo servicio = new ServicioMeteo();

        DatosMeteoHoy hoy = servicio.obtenerDatosHoy();
        List<PrediccionDia> pronostico = servicio.obtenerPronostico();

        // cargar html

        String plantilla = new String(
                Main.class.getResourceAsStream("/dashboard.html").readAllBytes(),
                StandardCharsets.UTF_8
        );

        // generador de nota de caddy con logica

        String notaCaddy = com.ignacio.logica.GeneradorNota.generar(pronostico);

        // generar html con datos reales

        GeneradorHtml generador = new GeneradorHtml();
        String html = generador.generar(plantilla, hoy, pronostico, notaCaddy);

        // servidor javalin

        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> {
            ctx.contentType("text/html; charset=utf-8");
            ctx.result(html);
        });


    }
}
