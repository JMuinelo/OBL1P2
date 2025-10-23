
package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Toto
 */
public class Interfaz {
    
    public static void menuPrincipal(Sistema sistema){
        ;
        Scanner in = new Scanner(System.in);
        System.out.println("Bienvenido al Medio Tateti!");
         
        
        
        int opcion = 0;
        
        
        while(opcion!=5){
            System.out.println("Elija una opcion para continuar:");
            System.out.println("1. Ingresar un Jugador");
            System.out.println("2. Comenzar Partida");
            System.out.println("3. Continuacion de Partida");
            System.out.println("4. Ranking & Invictos");
            System.out.println("5. Salir");
            
            opcion = pedirNumero("Ingrese numero a continuacion: ", 1, 5);

            switch (opcion){
                case 1:
                        ingresarJugadorInterfaz(sistema);
                        System.out.println(sistema.getListaJugadores());
                        break;
                case 2:
                        comenzarPartida(sistema, false);
                        break;
                case 3:
                        comenzarPartida(sistema, true);
                        break;
                case 4:
                        mostrarRanking(sistema);
                case 5:
                    break;
            }
        }
            
        
        
        
        
    }
    public static void ingresarJugadorInterfaz(Sistema sistema){
        Scanner in = new Scanner(System.in);
        
        System.out.println("Ingrese el Nombre");
        String nombre = in.nextLine();
        
        while(!sistema.nombreDisponible(nombre)){
            System.out.println("El nombre no esta disponible, Ingrese otro por favor");
            nombre = in.nextLine();
            
            
        }
        int edad = pedirNumero("Ingrese edad", 0, 100);
        sistema.agregarJugador((new Jugador(nombre, edad)));
    }
    
    public static void mostrarRanking(Sistema sistema){
        //1 ganadores
        Collections.sort(sistema.getListaJugadores(),
                new Comparator<Jugador>(){
                    public int compare(Jugador j1, Jugador j2){
                        return j2.getPartidasGanadas()-j1.getPartidasGanadas();
                    }
                }
                );
        System.out.println("***** RANKING DE VICTORIAS *****");
        for(int i=0; i < sistema.getListaJugadores().size(); i++){
            Jugador jug = sistema.getListaJugadores().get(i);
                System.out.println((i+1)+". "+jug.getNombre());
        }
        Collections.sort(sistema.getListaJugadores());
        //2 invivtos
        System.out.println("***** RANKING INVICTOS *****");
        for(int i=0; i < sistema.getListaJugadores().size(); i++){
            Jugador jug = sistema.getListaJugadores().get(i);
            if(jug.estaInvicto() || jug.getPartidasJugadas() == 0){
                System.out.println((i+1)+". "+jug.getNombre());
            }
        }
    }
    
    public static void seleccionarJugadores(Sistema sistema){
        int opcion = 0;
        int max = sistema.getListaJugadores().size();
        int jug1 = 0;
        int jug2 = -1;
        boolean loop = true;
        /////////////////////////////////////////////////////////
        
        System.out.println("***** Lista de Jugadores *****");
        for(int i=0; i < sistema.getListaJugadores().size(); i++){
            System.out.println("\n"+(i+1)+". "+sistema.getListaJugadores().get(i).getNombre());
        }
        jug1 = pedirNumero("Seleccione el 1er jugador: ", 1, max) - 1;
            
        while(loop){
            System.out.println("***** Lista de Jugadores *****");
            for(int i=0; i < sistema.getListaJugadores().size(); i++){
                System.out.println("\n"+(i+1)+". "+sistema.getListaJugadores().get(i).getNombre());
            }
            jug2 = pedirNumero("Seleccione el 2do jugador: ", 1, max) - 1;
            loop = false;
                
            if(jug2 == jug1){
                System.out.println("\nADVERTENCIA: Los jugadores no pueden ser iguales");
                loop = true;
            }
        }//En este punto jug1 y jug2 tienen los valores de los indices del respectivo jugador en el arraylist de jugadores
        sistema.setPartidaActual(new Partida(sistema.getListaJugadores().get(jug1), sistema.getListaJugadores().get(jug2)));
    }
    
    public static void agregarSecuencias(Sistema sistema){
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese la partida: ");
        String partida = in.nextLine().toUpperCase();
        for(int i=0; i < partida.length(); i=i+4){
            sistema.getPartidaActual().procesarJugada(partida.substring(i, i+3).trim());
            sistema.getPartidaActual().cambiarTurno();
        }
    }
    
    public static void comenzarPartida(Sistema sistema, boolean continuaPartida){
        if(sistema.getListaJugadores().size() > 1){
            seleccionarJugadores(sistema);
            if(continuaPartida){
                 agregarSecuencias(sistema);
            }
            while(!sistema.getPartidaActual().getPartidaFinalizada()){
                String turno = "TURNO DE: " + sistema.getPartidaActual().getTurno() + " - ";
                if(sistema.getPartidaActual().getTurno() == 'B'){
                    turno += " " + sistema.getPartidaActual().getJugadorBlanco().getNombre();
                }
                else{
                    turno += sistema.getPartidaActual().getJugadorNegro().getNombre();
                }
                System.out.println(turno);
                sistema.getPartidaActual().mostrarTablero(sistema.getPartidaActual().getTablero(), sistema.getPartidaActual().getMostrarBordes());
                String jugada = pedirJugada(sistema);
                sistema.getPartidaActual().procesarJugada(jugada);
                if(jugada.length() == 3){
                    String jugador = sistema.getPartidaActual().hayGanador(sistema.getPartidaActual().getTablero(), true, true);
                    if(!jugador.equals("")){
                        sistema.getPartidaActual().setPartidaFinalizada(true);
                        System.out.println("\n***** GANADOR: "+jugador+" *****");
                        //prueba
                        //sistema.getPartidaActual().mostrarTablero(sistema.getPartidaActual().getTablero(), sistema.getPartidaActual().getMostrarBordes());
                    }
                    sistema.getPartidaActual().cambiarTurno();
                }
                
            }
        }else{
            System.out.println("No hay suficientes jugadores para comenzar una nueva partida.");
        }
        
        
        
    }
    
    public static int pedirNumero(String texto, int min, int max){
        Scanner in = new Scanner(System.in);
        boolean valido=false;
        
        int numero=0;
        while(!valido){
            try{
            System.out.println(texto);
            numero = in.nextInt();
            in.nextLine();
            if(numero >= min && numero <= max){
                valido = true;
            }
            else{
                System.out.println("Valor Fuera de Rango"+"("+min+"-"+max+"), reingrese:");
                //numero = in.nextInt();
            }
            }catch(InputMismatchException e){
                System.out.println("Ingrese solo numeros");
            }
        }
        return numero;
        
        
    }
    public static String pedirJugada(Sistema sistema){
        String retorno ="";
        boolean valida =true;
        Scanner input = new Scanner(System.in);
        char[] fila = {'A','B','C'};
        char[] columna = {'1','2','3','4','5','6'};
        System.out.println("Ingrese una jugada: ");
        while(valida){
            String jugada = input.next().toUpperCase();
            if(jugada.length()==1){
                switch(jugada){
                    case "X":
                        
                        
                        valida = false;
                    break;
                    
                    case "B":
                        sistema.getPartidaActual().setMostrarBordes(true);
                        valida = false;
                        break;
                    case "T":
                        
                        
                        valida = false;
                        break;
                    
                    case "H":
                        System.out.println("Entro el H");
                        boolean controlBlanco = true;
                        if(sistema.getPartidaActual().getTurno() == 'N'){
                            controlBlanco = false;
                        }
                        System.out.println(sistema.getPartidaActual().hayJugadaGanadora(controlBlanco));
                        valida = false;
                        break;
                    case "N":
                        sistema.getPartidaActual().setMostrarBordes(false);
                        valida = false;
                        break;
                    
                    default: 
                        System.out.println("Opcion invalida, reingrese:");
                        
                }
            }else{
                if(jugada.length()==3){
                    boolean filaValida=false;
                    boolean columnaValida=false;
                    int columnaValor = 0;
                    int filaValor = 0;
                    for(int i = 0; i < fila.length && !filaValida; i++){
                        if(fila[i] == jugada.charAt(0)){
                            filaValida = true;
                        }
                        else{
                            filaValor++;
                        }
                    }
                    for(int i = 0; i < columna.length && !columnaValida; i++){
                        if(columna[i] == jugada.charAt(1)){
                            columnaValida = true;
                        }
                        else{
                            columnaValor++;
                        }
                        
                    }
                    if(filaValida && columnaValida){
                        if(jugada.charAt(2) == 'C' || jugada.charAt(2) == 'D'){
                            retorno = jugada;
                            valida = false;
                        }
                        else{
                            //revisa si la pieza es invertible para el jugador actual
                            if(jugada.charAt(2) == 'I'){
                                if(sistema.getPartidaActual().getTablero()[filaValor][columnaValor].charAt(1) == sistema.getPartidaActual().getTurno()){
                                //if((sistema.getPartidaActual().getTurno()+"" ).equals(sistema.getPartidaActual().getTablero()[((int)jugada.charAt(0))-65][Character.getNumericValue(jugada.charAt(1))])){
                                    valida = false;
                                    retorno = jugada;
                                }
                                else{
                                    System.out.println("Solo se pueden invertir las fichas de tu propio color");
                                }
                            }
                            else{
                                 filaValida = false;
                            }
                        }
                    }
                    if(!filaValida || !columnaValida){
                        System.out.println("Jugada invalida. Reingrese: ");
                    }
                }
                else{
                    System.out.println("Jugada invalida. Reingrese: ");
                }
            }
        }
        return retorno;
    }
}
