package sistema;

import interfaz.Sistema;
import interfaz.Retorno;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02_RegistrarJugadorTest {
    Retorno retorno;

    @Test
    void registrarJugadorError01(){
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarJugador("Upset","",cat.PROFESIONAL);      
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());   

        retorno = sistema.registrarJugador("","Raphael",cat.ESTANDARD);      
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado()); 

        retorno = sistema.registrarJugador("Yamato","Jakob");      
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarJugadorError02(){
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarJugador("Flakked","Víctor", cat.PROFESIONAL);     
        assertEquals(Retorno.Resultado.OK, retorno.getResultado()); 

        retorno = sistema.registrarJugador("Flakked","Víctor", cat.PROFESIONAL);     
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());        
    }

    @Test
    void registrarJugadorOK(){
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        retorno = sistema.registrarJugador("Caps","Rasmus", cat.PROFESIONAL); 
        assertEquals(Retorno.Resultado.OK, retorno.getResultado()); 

        retorno = sistema.registrarJugador("BrokenBlade","Sergen", cat.PROFESIONAL); 
        assertEquals(Retorno.Resultado.OK, retorno.getResultado()); 

        retorno = sistema.registrarJugador("Viper","Park", cat.PROFESIONAL); 
        assertEquals(Retorno.Resultado.OK, retorno.getResultado()); 
    }
}
