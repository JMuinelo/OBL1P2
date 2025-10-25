package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

// Autores: Dylan Escobar - 357026 & Juan Muinelo 350499
public class Sistema {
    private ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private Partida partidaActual;

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public Partida getPartidaActual() {
        return partidaActual;
    }
    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }
    
    public void agregarJugador(Jugador jugador){
        this.listaJugadores.add(jugador);
        Collections.sort(this.getListaJugadores());
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
    public boolean nombreDisponible(String nombre){ //Retorna true si esta disponible;
        boolean esta=true;
        for(int i=0;i<this.getListaJugadores().size();i++){
            if(nombre.toUpperCase().equals((this.getListaJugadores().get(i).getNombre()).toUpperCase())){
                esta = false;
            }
        }
        return esta;
    }
    
    
}
