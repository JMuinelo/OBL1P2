package main;

/**
 *
 * @author Toto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
         
        
        
        
        
        Jugador j1 = new Jugador("Nombre", 18);
        Jugador j2 = new Jugador("Nombre2", 18);
        Partida part = new Partida(j1,j2);
        /*
        part.sacarBordes();
        part.mostrarMatriz(part.getTableroStr());
        part.incluirBordes();
        part.mostrarMatriz(part.getTableroStr());
        */
        Interfaz.menuPrincipal(sistema);
    }
            
}
