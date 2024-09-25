package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import interfaz.Retorno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02_RegistrarJugadorTest {
    Retorno retorno;

    @Test
    void registrarJugadorError01_Empty() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarJugador("", "Elias", "Lipp", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador("Targamas", "", "Crabbé", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador("Yamato", "Jakob", "", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarJugadorError01_Null() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarJugador(null, "Elias", "Lipp", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador("Targamas", null, "Crabbé", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador("Yamato", "Jakob", null, Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador("Hylissang", "Zdravets", "Iliev Galabov", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarJugadorError02() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarJugador("Flakked", "Víctor", "Lirola Tortosa", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarJugador("Flakked", "Víctor", "Lirola Tortosa", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarJugadorOK() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarJugador("Caps", "Rasmus", "Borregaard Winther", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarJugador("BrokenBlade", "Sergen", "Çelik", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarJugador("Viper", "Park", "Do-hyeon", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
}
