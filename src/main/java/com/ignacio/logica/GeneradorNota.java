package com.ignacio.logica;

import com.ignacio.modelo.PrediccionDia;

import java.util.List;

public class GeneradorNota {

    private static final AnalizadorGolf analizador = new AnalizadorGolf();

    public static String generar(List<PrediccionDia> pronostico) {
        String mejorDia = null;
        String peorDia = null;
        int diasBogey = 0;

        for (PrediccionDia dia : pronostico) {
            AnalizadorGolf.Puntuacion p = analizador.calcular(dia);

            if (p == AnalizadorGolf.Puntuacion.BIRDIE && mejorDia == null) {
                mejorDia = dia.getNombreDia();
            }
            if (p == AnalizadorGolf.Puntuacion.BOGEY) {
                diasBogey++;
                if (peorDia == null) peorDia = dia.getNombreDia();
            }
        }

        StringBuilder nota = new StringBuilder();

        // el mejor dia

        if (mejorDia != null) {
            if (mejorDia.equals("Hoy")) {
                nota.append("Hoy es tu mejor oportunidad, las condiciones son excelentes. ");
            } else {
                nota.append(nombreCompleto(mejorDia)).append(" es tu mejor ventana de la semana. ");
            }
        } else {
            nota.append("No hay días ideales esta semana, elige el menos malo. ");
        }

        // advertencia para dias malos

        if (peorDia != null) {
            nota.append("<strong>").append(nombreCompleto(peorDia))
                    .append(" tiene malas condiciones, mejor evítalo.</strong> ");
        }

        // resumen ggeneral

        if (diasBogey == 0) {
            nota.append("La semana tiene buenas condiciones en general.");
        } else if (diasBogey <= 2) {
            nota.append("El resto de la semana es aceptable para salir al campo.");
        } else {
            nota.append("La semana viene complicada, elige bien el día.");
        }

        return nota.toString();
    }

    // conversion de abreviaciones

    private static String nombreCompleto(String abreviacion) {
        return switch (abreviacion) {
            case "Hoy" -> "Hoy";
            case "Lun" -> "El lunes";
            case "Mar" -> "El martes";
            case "Mié" -> "El miércoles";
            case "Jue" -> "El jueves";
            case "Vie" -> "El viernes";
            case "Sáb" -> "El sábado";
            case "Dom" -> "El domingo";
            default -> abreviacion;
        };
    }
}
