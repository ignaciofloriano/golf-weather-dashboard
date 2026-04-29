package logica;

import com.ignacio.logica.AnalizadorGolf;
import com.ignacio.modelo.DatosMeteoHoy;
import com.ignacio.modelo.PrediccionDia;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalizadorGolfTest {

    private final AnalizadorGolf analizador = new AnalizadorGolf();

    @Test
    void diaIdeal_debeSerBirdie() {
        DatosMeteoHoy datos = new DatosMeteoHoy(17, 15, 4, 12, 3, 62, "NE", "Excelente");
        assertEquals(AnalizadorGolf.Puntuacion.BIRDIE, analizador.calcular(datos));
    }

    @Test
    void lluviaIntensa_debeSerBogey() {
        DatosMeteoHoy datos = new DatosMeteoHoy(17, 15, 78, 12, 3, 62, "NE", "Reducida");
        assertEquals(AnalizadorGolf.Puntuacion.BOGEY, analizador.calcular(datos));
    }

    @Test
    void temperaturaFria_debeSerBogey() {
        DatosMeteoHoy datos = new DatosMeteoHoy(5, 3, 4, 12, 1, 70, "N", "Buena");
        assertEquals(AnalizadorGolf.Puntuacion.BOGEY, analizador.calcular(datos));
    }

    @Test
    void vientoFuerte_debeSerBogey() {
        DatosMeteoHoy datos = new DatosMeteoHoy(17, 15, 10, 45, 3, 62, "NE", "Buena");
        assertEquals(AnalizadorGolf.Puntuacion.BOGEY, analizador.calcular(datos));
    }

    @Test
    void condicionesMedias_debeSerPar() {
        DatosMeteoHoy datos = new DatosMeteoHoy(17, 15, 35, 25, 3, 62, "NE", "Buena");
        assertEquals(AnalizadorGolf.Puntuacion.PAR, analizador.calcular(datos));
    }

    @Test
    void prediccionIdeal_debeSerBirdie() {
        PrediccionDia dia = new PrediccionDia("Sáb", 21, 3, 10, 5);
        assertEquals(AnalizadorGolf.Puntuacion.BIRDIE, analizador.calcular(dia));
    }

    @Test
    void prediccionLluvia_debeSerBogey() {
        PrediccionDia dia = new PrediccionDia("Mié", 14, 75, 15, 2);
        assertEquals(AnalizadorGolf.Puntuacion.BOGEY, analizador.calcular(dia));
    }
}
