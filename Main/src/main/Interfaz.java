
package main;

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
                        comenzarPartida(sistema);
                        break;
                case 3:
                
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
                        return j1.getPartidasGanadas()-j2.getPartidasGanadas();
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
    
    public static void comenzarPartida(Sistema sistema){
        if(sistema.getListaJugadores().size() > 1){
            seleccionarJugadores(sistema);
            while(!sistema.getPartidaActual().getPartidaFinalizada()){
                System.out.println("TURNO DE: "+sistema.getPartidaActual().getTurno());
                sistema.getPartidaActual().mostrarTablero(sistema.getPartidaActual().getTablero(), sistema.getPartidaActual().getMostrarBordes());
                String jugada = pedirJugada(sistema);
                sistema.getPartidaActual().procesarJugada(jugada);
                if(jugada.length() == 3){
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
                    for(char carac: fila){
                        if(carac==jugada.charAt(0)){
                            filaValida = true;
                            System.out.println("fila valida");
                        }
                    }
                    for(int num: columna){
                        if(num==jugada.charAt(1)){
                            columnaValida = true;
                             System.out.println("columna valida");

                        }
                    }
                    if(filaValida && columnaValida){
                        if(jugada.charAt(2) == 'C' || jugada.charAt(2) == 'D' || jugada.charAt(2) == 'I'){
                            System.out.println("jugada valida");
                            retorno = jugada;
                            valida = false;
                        }
                        else{
                            filaValida = false;
                        }
                    }
                    if(!filaValida){
                        System.out.println("(no es CDI)Jugada invalida. Reingrese: ");
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
