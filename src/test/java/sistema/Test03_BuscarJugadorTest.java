package sistema;

import interfaz.Sistema;
import interfaz.Retorno;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test03_BuscarJugadorTest {
	Retorno retorno;

  	@Test
	void buscarJugadorError01(){
    	Sistema sistema = new ImplementacionSistema();
    	sistema.inicializarSistema(10);

    	retorno = sistema.buscarJugador("");      
    	assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());    

    	retorno = sistema.buscarJugador();      
    	assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());    
  	}

  	@Test
  	void buscarJugadorError02(){
    	Sistema sistema = new ImplementacionSistema();
    	sistema.inicializarSistema(10);

    	retorno = sistema.registrarJugador("Zeka","Kim ", cat.PROFESIONAL); 
    	assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    	retorno = sistema.buscarJugador("Jankos");      
    	assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
  	} 

 	@Test
  	void buscarJugadorOK(){
    	Sistema sistema = new ImplementacionSistema();
    	sistema.inicializarSistema(10);

    	retorno = sistema.registrarJugador("Mikyx","Mihael", cat.PROFESIONAL); 
    	assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    	retorno = sistema.buscarJugador("Mikyx");      
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
  	}  
}
