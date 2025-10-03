
package main;

/**
 *
 * @author Toto
 */


//PREGUNTAR SI CONVIENE EL QUE HEREDE DE JUGADOR O QUE TENGA UN ATRIBUTO JUUGADOR
public class JugadorEnPartida{
    private boolean turno;
    private Jugador jugador;

    public boolean isTurno(){
        return turno;
    }
    public void setTurno(boolean turno){
        this.turno = turno;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public JugadorEnPartida(Jugador jugador,boolean turno){
        this.setJugador(jugador);
        this.setTurno(turno);
    }
    
}
