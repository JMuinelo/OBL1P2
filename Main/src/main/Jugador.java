package main;
/**
 *
 * @author Toto
 */
public class Jugador {
    private String nombre;
    private int edad;
    private int partidasJugadas;
    private int partidasGanadas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String unNombre){
        this.nombre = unNombre;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad(int unaEdad){
        this.edad = unaEdad;
    }

    public int getPartidasJugadas(){
        return partidasJugadas;
    }

    public void setPartidasJugadas(int cantPartidasJugadas){
        this.partidasJugadas = cantPartidasJugadas;
    }

    public int getPartidasGanadas(){
        return partidasGanadas;
    }

    public void setPartidasGanadas(int cantPartidasGanadas){
        this.partidasGanadas = cantPartidasGanadas;
    }
    Jugador(String nombre, int edad){
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setPartidasGanadas(0);
        this.setPartidasJugadas(0);
    }
    Jugador(){
        this.setNombre("Sin Nombre");
        this.setEdad(0);
        this.setPartidasGanadas(0);
        this.setPartidasJugadas(0);
        
    }
    @Override
    public String toString(){
        return this.getNombre() + "(" + this.getEdad() + ")";
    }
    ///////
    
    /*
    public boolean compararJugadorCon(Jugador otroJugador){ //para validar el nombre
            return this.getNombre().equals(otroJugador.getNombre());
    } 

    */
    public boolean estaInvicto(){
        return this.getPartidasGanadas() == this.getPartidasJugadas(); //cuando empatan restar uno a partidas jugadas
    }
    
    
}
