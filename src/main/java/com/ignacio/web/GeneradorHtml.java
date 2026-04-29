package com.ignacio.web;

import com.ignacio.logica.AnalizadorGolf;
import com.ignacio.logica.AnalizadorGolf.Puntuacion;
import com.ignacio.modelo.DatosMeteoHoy;
import com.ignacio.modelo.PrediccionDia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class GeneradorHtml {

    private final AnalizadorGolf analizador = new AnalizadorGolf();

    public String generar(String plantilla, DatosMeteoHoy hoy, List<PrediccionDia> pronostico, String notaCaddy) {

        Puntuacion puntuacion = analizador.calcular(hoy);

        return plantilla
                .replace("{{FECHA}}", generarFecha())
                .replace("{{PUNTUACION_HOY}}", formatearPuntuacion(puntuacion))
                .replace("{{TEMPERATURA}}", String.valueOf((int) hoy.getTemperatura()))
                .replace("{{LLUVIA}}", String.valueOf((int) hoy.getLluvia()))
                .replace("{{VIENTO}}", String.valueOf((int) hoy.getViento()))
                .replace("{{UV}}", String.valueOf(hoy.getUv()))
                .replace("{{SENSACION}}", String.valueOf((int) hoy.getSensacionTermica()))
                .replace("{{HUMEDAD}}", String.valueOf(hoy.getHumedad()))
                .replace("{{DIRECCION_VIENTO}}", hoy.getDireccionViento())
                .replace("{{VISIBILIDAD}}", hoy.getVisibilidad())
                .replace("{{FORECAST}}", generarForecast(pronostico))
                .replace("{{NOTA_CADDY}}", notaCaddy);
    }

    private String generarFecha() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEEE, d 'de' MMMM · HH:mm", new Locale("es", "ES")
        );

        // primera letra en mayuscula

        String fecha = ahora.format(formatter);
        return fecha.substring(0, 1).toUpperCase() + fecha.substring(1);
    }

    private String generarForecast(List<PrediccionDia> pronostico) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pronostico.size(); i++) {
            PrediccionDia dia = pronostico.get(i);
            Puntuacion p = analizador.calcular(dia);
            String cssClase = p.name().toLowerCase();
            String hoy = i == 0 ? " today" : "";

            sb.append(String.format(
                    """
                            <div class="gm-day%s" 
                                 data-temp="%d"
                                 data-lluvia="%d"
                                 data-viento="%.1f"
                                 data-uv="%d"
                                 data-nombre="%s"
                                 data-puntuacion="%s"
                                 style="cursor:pointer">
                              <p class="gm-day-name">%s</p>
                              <p class="gm-day-temp">%d°</p>
                              <p class="gm-day-rain">%d%%</p>
                              <span class="gm-badge %s">%s</span>
                            </div>
                            """,
                    hoy,
                    (int) dia.getTemperaturaMax(),
                    (int) dia.getProbabilidadLluvia(),
                    dia.getVientoMax(),
                    dia.getUv(),
                    dia.getNombreDia(),
                    formatearPuntuacion(p),
                    dia.getNombreDia(),
                    (int) dia.getTemperaturaMax(),
                    (int) dia.getProbabilidadLluvia(),
                    cssClase,
                    formatearPuntuacion(p)
            ));
        }
        return sb.toString();
    }

    private String formatearPuntuacion(Puntuacion p) {
        return switch (p) {
            case BIRDIE -> "Birdie";
            case PAR -> "Par";
            case BOGEY -> "Bogey";
        };
    }
}