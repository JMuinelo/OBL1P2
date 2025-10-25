package main;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 *
 * @author Toto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        
        Sistema sistema = new Sistema();

        Jugador j1 = new Jugador("Pedro", 33);
        Jugador j2 = new Jugador("Nombre2", 20);
        sistema.agregarJugador(j1);
        sistema.agregarJugador(j2);
        
        Interfaz.menuPrincipal(sistema);
        
    }
    
}