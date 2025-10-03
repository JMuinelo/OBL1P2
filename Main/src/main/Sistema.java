package main;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Toto
 */
public class Sistema {
    private ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private Partida partidaActual;
    
    public void agregarJugador(Jugador jugador){//getsetters
        this.listaJugadores.add(jugador);
    }
    public Jugador buscarJugador(String nombre){
        Jugador retorno =new Jugador("",0);//cuando se usa el metodo listaJugadores nunca esta vacia, no devuelve null
        Iterator<Jugador> it = listaJugadores.iterator();
         
        while(it.hasNext()){
         String nombreActual = it.next().getNombre();
         if(nombreActual.equals(nombre)){
             retorno = it.next();
         }
        }
        return retorno;
    }
    public boolean jugadorDisponible(String nombre){
        boolean esta=true;
        
        Jugador jugador = this.buscarJugador(nombre);
        if(jugador.getNombre().equals("")){
            esta = false;
        }
        return esta;
    }
    //JUGADORES GANADORES, pendiente hasta saber ordenar arraylist
}
