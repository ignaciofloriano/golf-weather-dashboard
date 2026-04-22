package com.ignacio.logica;

import com.ignacio.modelo.DatosMeteoHoy;
import com.ignacio.modelo.PrediccionDia;

public class AnalizadorGolf {

    // maximos y minimos para calcular la puntuación del dia

    private static final double LLUVIA_BIRDIE  = 15.0;
    private static final double LLUVIA_PAR     = 50.0;
    private static final double VIENTO_BIRDIE  = 20.0;
    private static final double VIENTO_PAR     = 40.0;
    private static final double TEMP_MIN       = 8.0;

    public enum Puntuacion {
        BIRDIE, PAR, BOGEY
    }

    // calcula puntuacion para hoy

    public Puntuacion calcular(DatosMeteoHoy datos) {
        return calcularPuntuacion(
                datos.getLluvia(),
                datos.getViento(),
                datos.getTemperatura()
        );
    }

    // calcula puntuacion para un dia futuro del pronóstico

    public Puntuacion calcular(PrediccionDia dia) {
        return calcularPuntuacion(
                dia.getProbabilidadLluvia(),
                dia.getVientoMax(),
                dia.getTemperaturaMax()
        );
    }

    // metodo compartido para devolver a los otros metodos birdie, par o bogey

    private Puntuacion calcularPuntuacion(double lluvia, double viento, double temp) {
        if (temp < TEMP_MIN || lluvia > LLUVIA_PAR || viento > VIENTO_PAR) {
            return Puntuacion.BOGEY;
        }
        if (lluvia <= LLUVIA_BIRDIE && viento <= VIENTO_BIRDIE) {
            return Puntuacion.BIRDIE;
        }
        return Puntuacion.PAR;
    }

}
