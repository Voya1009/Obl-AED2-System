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

        retorno = sistema.registrarEquipo("T1","");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarEquipo("","");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    public void registrarEquipoError02(){
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarEquipo("BLG","Cookie");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("BLG","Cookie");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    public void registrarEquipoOK(){
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarEquipo("T1","T1Manager");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("G2","G2Manager");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("BLG","BLGManager");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("GEN","GENManager");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("WBG","WBGManager");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("EST","ESTManager");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("MDK","MDKManager");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("DWK","DWKManager");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("BLG","BLGManager");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarEquipo("BLG","BLGManager");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
}
