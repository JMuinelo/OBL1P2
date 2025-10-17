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

        Jugador j1 = new Jugador("Nombre", 18);
        Jugador j2 = new Jugador("Nombre2", 18);
        Partida part = new Partida(j1, j2);
        
        Interfaz.menuPrincipal(sistema);
        String[][] tablero = new String[3][6];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = "  ";
            }
        }
        tablero[0][1] = "CB";
        tablero[0][2] = "CN";
        tablero[0][3] = "DB";
        tablero[0][4] = "DN";
        
        tablero[1][1] = "CB";
        tablero[1][2] = "CN";
        tablero[1][3] = "DB";
        tablero[1][4] = "DN";
        
        tablero[2][1] = "CB";
        tablero[2][2] = "CN";
        tablero[2][3] = "DB";
        tablero[2][4] = "DN";
        /*
        part.setTablero(tablero);
        part.mostrarTablero(tablero,true);*/
    }
    
}