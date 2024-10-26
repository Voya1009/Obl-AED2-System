package sistema;

import interfaz.Sistema;
import interfaz.Retorno;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test06_RegistrarEquipoTest {
    Retorno retorno;

    public void registrarEquipoError01(){
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarEquipo("","Cookie");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarEquipo("Zkittles","");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarEquipo("","");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    public void registrarEquipoError02(){
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarEquipo("Girl Scout","Cookie");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("Girl Scout","Cookie");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    public void registrarEquipoOK(){
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarEquipo("Tangie","Tino Mandarino");
        retorno = sistema.registrarEquipo("Tangie","Tino Mandarino");
        retorno = sistema.registrarEquipo("Tangie","Tino Mandarino");
    }
}
