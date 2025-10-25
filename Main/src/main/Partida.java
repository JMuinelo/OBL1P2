package main;

import java.util.Arrays;
import java.util.Scanner;

// Autores: Dylan Escobar - 357026 & Juan Muinelo 350499
public class Partida {
    private String[][]tablero; //Celdas del tipo DB, CN
    private boolean partidaFinalizada;
    private Jugador jugadorBlanco;
    private Jugador jugadorNegro;
    private char turno;
    private boolean mostrarBordes;
    private int[] fichasGanadoras;

    public int[] getFichasGanadoras() {
        return fichasGanadoras;
    }
    
    public void setFichasGanadoras(int[] fichasGanadoras) {
        this.fichasGanadoras = fichasGanadoras;
    }
    
    public boolean getMostrarBordes() {
        return mostrarBordes;
    }

    public void setMostrarBordes(boolean mostrarBordes) {
        this.mostrarBordes = mostrarBordes;
    }

    public char getTurno() {
        return turno;
    }

    public void setTurno(char turno) {
        this.turno = turno;
    }
    
    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }
    public void agregarFicha(String ficha, int fila, int col){
        this.getTablero()[fila][col] = ficha;
    }
    public void invertirFicha(int fila, int col){
        String ficha = this.getTablero()[fila][col];
        String fichaInv;
        if(ficha.charAt(0)=='C'){
            fichaInv = "D";
        }
        else{
            fichaInv = "C";
        }
        fichaInv+=ficha.charAt(1);
        this.agregarFicha(fichaInv, fila, col);
    }

    public boolean getPartidaFinalizada() {
        return partidaFinalizada;
    }

    public void setPartidaFinalizada(boolean partidaFinalizada) {
        this.partidaFinalizada = partidaFinalizada;
    }

    public Jugador getJugadorBlanco() {
        return jugadorBlanco;
    }

    public void setJugadorBlanco(Jugador jugadorBlanco) {
        this.jugadorBlanco = jugadorBlanco;
    }

    public Jugador getJugadorNegro() {
        return jugadorNegro;
    }

    public void setJugadorNegro(Jugador jugadorNegro) {
        this.jugadorNegro = jugadorNegro;
    }
    public Partida(Jugador jb, Jugador jn){
        this.setJugadorBlanco(jb);
        this.setJugadorNegro(jn);
        this.setPartidaFinalizada(false);
        this.setTablero(new String[3][6]);
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = "  ";
            }
        }
        //Para que el tablero no comience en null
        this.setTurno('B');
        this.setMostrarBordes(true);
        fichasGanadoras = new int[12];
    }
    
    public String hayJugadaGanadora(boolean controlBlanco){
        String jugadaGanadora = "";
        //Segun el estado de controlBlanco, controla Circulo o Equis
        char primero  = 'C';
        char segundo  = 'D';
        if(!controlBlanco){
            primero  = 'D';
            segundo  = 'C';
        }
        int fila = 0;
        int columna = 0; 
        String[][] tablero = this.getTablero();

        for(int i = 0; i < tablero.length && !(jugadaGanadora.length() > 1); i++){
            for(int j=0; j < tablero[0].length && !(jugadaGanadora.length() > 1); j++){
                String fichaActual = tablero[i][j];
                fila = i;
                columna = j;
                if(fichaActual.equals("  ")){
                    tablero[i][j] = primero+"B";
                    //Como siempre analiza ganador por turno, uno es true, otro false
                    if(hayGanador(tablero, controlBlanco, !controlBlanco).length() > 0){
                        jugadaGanadora = "jugada ganadora: "+primero + " en ";
                    }
                    tablero[i][j] = segundo+"B";
                    if(hayGanador(tablero, controlBlanco, !controlBlanco).length() > 0){
                       jugadaGanadora = "jugada ganadora: "+ segundo + " en ";
                    }
                }
                else{
                    //Invertida
                    char aux = 'C';
                    if(fichaActual.charAt(0) == 'C'){
                        tablero[i][j] = "D"+fichaActual.charAt(1);
                        aux = 'D';
                    }
                    else{
                        tablero[i][j] = "C"+fichaActual.charAt(1);
                    }
                    if(hayGanador(tablero, controlBlanco, !controlBlanco).length() > 0){
                        jugadaGanadora = "jugada ganadora: "+aux + " en ";
                    }
                }
                tablero[i][j] = fichaActual;
            }
        }
        if(jugadaGanadora.length() > 1){
            switch(fila){
                case 0: jugadaGanadora+="A"; 
                    break;
                case 1: jugadaGanadora+="B"; 
                    break;
                case 2: jugadaGanadora+="C"; 
                    break;        
            }
            jugadaGanadora+=(columna+1);
        }
        else{
            jugadaGanadora = "No hay jugada ganadora";
        }
        return jugadaGanadora;
    }
    
    public String hayGanador(String[][] tablero, boolean controlBlanco, boolean controlNegro){
        String ganador = "";
        int[] posicionesGanadorasB = new int[12];
        int[] posicionesGanadorasN = new int[12];
        int cantB = 0;
        int cantN = 0;
        int contadorArrayB=0;
        int contadorArrayN=0;
        for(int j=0; j < tablero[0].length && cantB < 3 && cantN < 3; j = j+2){
            cantB = 0;
            cantN = 0;
            for(int i = 0; i < posicionesGanadorasB.length;i++){
                posicionesGanadorasB[i] = 0;
                posicionesGanadorasN[i] = 0;
            }
            for(int i=0; i < tablero.length && cantB < 3 && cantN < 3; i++){
                if(controlBlanco){
                    if(tablero[i][j].charAt(0) == 'C' && tablero[i][j+1].charAt(0) == 'D'){
                        cantB++;
                        posicionesGanadorasB[contadorArrayB]=i;
                        posicionesGanadorasB[contadorArrayB+1]=j;
                        posicionesGanadorasB[contadorArrayB+2]=i;
                        posicionesGanadorasB[contadorArrayB+3]=j+1;
                        contadorArrayB+=4;
                    }
                }
                if(controlNegro){
                   if(tablero[i][j].charAt(0) == 'D' && tablero[i][j+1].charAt(0) == 'C'){
                        cantN++;
                        posicionesGanadorasN[contadorArrayN]=i;
                        posicionesGanadorasN[contadorArrayN+1]=j;
                        posicionesGanadorasN[contadorArrayN+2]=i;
                        posicionesGanadorasN[contadorArrayN+3]=j+1;
                        contadorArrayN+=4;
                   }
                }
            }
        }
        if(cantN < 3 && cantB < 3){
            for(int i = 0 ; i < tablero.length && cantB < 3 && cantN < 3 ; i++){
                contadorArrayB=0;
                contadorArrayN=0;
                cantB = 0;
                cantN = 0;   
                for(int f = 0; f < posicionesGanadorasB.length;f++){
                    posicionesGanadorasB[f] = 0;
                    posicionesGanadorasN[f] = 0;
                }
                for(int j=0; j < tablero[0].length; j=j+2){
                    if(controlBlanco){
                        if(tablero[i][j].charAt(0) == 'C' && tablero[i][j+1].charAt(0) == 'D'){
                            cantB++;
                            posicionesGanadorasB[contadorArrayB]=i;
                            posicionesGanadorasB[contadorArrayB+1]=j;
                            posicionesGanadorasB[contadorArrayB+2]=i;
                            posicionesGanadorasB[contadorArrayB+3]=j+1;
                            contadorArrayB+=4;
                        }
                    }
                    if(controlNegro){
                        if(tablero[i][j].charAt(0) == 'D' && tablero[i][j+1].charAt(0) == 'C'){
                            cantN++;
                            posicionesGanadorasN[contadorArrayN]=i;
                            posicionesGanadorasN[contadorArrayN+1]=j;
                            posicionesGanadorasN[contadorArrayN+2]=i;
                            posicionesGanadorasN[contadorArrayN+3]=j+1;
                            contadorArrayN+=4;
                        }
                    }
                }
            }
        }
        if(cantN < 3 && cantB < 3){
            if(enDiagonal(true)){
                ganador = "Blanco";
            }
            else if(enDiagonal(false)){
                ganador = "Negro";
            }
        }
        else{
            if(cantB == 3 && cantN == 3){
                if(this.getTurno() == 'B'){
                    ganador = "Blanco";
                    sumar1NB('B');
                    this.setFichasGanadoras(posicionesGanadorasB);
                }
                else{
                    ganador = "Negro";
                    sumar1NB('N');
                    this.setFichasGanadoras(posicionesGanadorasN);
                }
            }
            else{
                if(cantB == 3){
                    ganador = "Blanco";
                    sumar1NB('B');
                    this.setFichasGanadoras(posicionesGanadorasB);
                }
                if(cantN == 3){
                    ganador = "Negro";
                    sumar1NB('N');
                    this.setFichasGanadoras(posicionesGanadorasN);
                }
            }
        }
        return ganador;
    }

    public void sumar1NB(char ganador){
        this.jugadorBlanco.setPartidasJugadas(this.jugadorBlanco.getPartidasJugadas()+1);
        this.jugadorNegro.setPartidasJugadas(this.jugadorNegro.getPartidasJugadas()+1);
        
        if(ganador == 'B'){
            this.jugadorBlanco.setPartidasGanadas(this.jugadorBlanco.getPartidasGanadas()+1);
        }
        else{
            this.jugadorNegro.setPartidasGanadas(this.jugadorNegro.getPartidasGanadas()+1);
        }
    }
    
    //procesa la jugada y agrega la ficha solo si es una jugada de ficha, si es un comando (1 letra) no hace nada, se hace en interfaz
    public void procesarJugada(String jugada){
        //la funcion asume datos validos
        if (jugada.length() == 3) {
            String ficha = "";
            int col = Character.getNumericValue(jugada.charAt(1)) - 1;
            int fila = 2;
            if (jugada.charAt(0) == 'A') {
                fila = 0;
            } 
            else if (jugada.charAt(0) == 'B') {
                fila = 1;
            }
            else{
                fila = 2;
            }
            if(jugada.charAt(2) != 'I') {
                ficha += jugada.charAt(2);
                ficha += (this.getTurno() + "");
                this.agregarFicha(ficha, fila, col);
            }
            else{
                this.invertirFicha(fila, col);
            }
        }
    }
    
    public void cambiarTurno(){
        if(this.getTurno() == 'B'){
            this.setTurno('N');
        }
        else{
            this.setTurno('B');
        }
    }
    
    public void mostrarTablero(String[][] tablero, boolean mostrar, boolean hayGanador){
        String[] letras ={"A","B","C"}; 
        if(mostrar){
            System.out.println("   1  2  3  4  5  6");
        }
        String linea = " +--+--+--+--+--+--+";
        System.out.println(linea);
        for (int i = 0; i < tablero.length; i++) {
            for (int z = 0; z < 3; z++) {
                String fila;
                if((z % 2 != 0) && mostrar){
                    fila = letras[i];
                }else{
                    fila = " ";
                }
                fila+="|";
                for (int j = 0; j < tablero[0].length; j++) {
                    boolean fichaGanadoraEncontrada = false;
                    if(hayGanador){
                        for(int k = 0; k < this.getFichasGanadoras().length; k=k+2){
                            if(this.getFichasGanadoras()[k] == i && this.getFichasGanadoras()[k+1] == j){
                                fichaGanadoraEncontrada = true;
                                if(this.hayGanador(this.getTablero(), true, false).equals("Blanco")){
                                    //ajuste manual, al llamar a hayGanador() se le suma una victoria al jugador blanco
                                    this.getJugadorBlanco().setPartidasGanadas(this.getJugadorBlanco().getPartidasGanadas()-1);
                                    fila += "OO|";
                                }
                                else{
                                    //ajuste manual, al llamar a hayGanador() se le suma una victoria al jugador Negro
                                    this.getJugadorNegro().setPartidasGanadas(this.getJugadorNegro().getPartidasGanadas()-1);
                                    fila += "XX|";
                                }
                            }
                        }
                    }
                    if(!fichaGanadoraEncontrada){
                        if ((tablero[i][j].charAt(1) + "").equals("N")) {
                            if ((tablero[i][j].charAt(0) + "").equals("C")) {
                                if (z % 2 == 0) {
                                    fila += " ●|";
                                } else {
                                    fila += "● |";
                                }
                            }
                            if ((tablero[i][j].charAt(0) + "").equals("D")) {
                                if (z % 2 == 0) {
                                    fila += "● |";
                                } else {
                                    fila += " ●|"; 
                                }
                            }
                        } else {
                            if ((tablero[i][j].charAt(1) + "").equals("B")) {
                                if ((tablero[i][j].charAt(0) + "").equals("C")) {
                                    if (z % 2 == 0) {
                                        fila += " ○|";
                                    } else {
                                        fila += "○ |";
                                    }
                                }
                                if ((tablero[i][j].charAt(0) + "").equals("D")) {
                                    if (z % 2 == 0) {
                                        fila += "○ |";
                                    } else {
                                        fila += " ○|";
                                    }
                                }
                            } else {
                                fila += "  |";
                            }
                        }    
                    }
                }
                System.out.println(fila);
            }
            System.out.println(linea);
        }
    }
    
    public boolean enDiagonal(boolean controlBlanco){
        boolean hayDiagonalB=false;
        boolean hayDiagonalN=false;
        String[][] tablero = this.getTablero();
        int cantBDS = 0; //Blanco Diagonal desde Superior
        int cantNDS  =0;
        int cantBDI = 0;
        int cantNDI = 0;
        int[] posicionesGanadorasB = new int[12];
        int[] posicionesGanadorasN = new int[12];
        int contadorArrayN = 0;
        int contadorArrayB = 0;
        for(int i = 0; i < posicionesGanadorasB.length;i++){
            posicionesGanadorasB[i] = 0;
            posicionesGanadorasN[i] = 0;
        }
        if(controlBlanco){
            //Blanca, superior
            for(int z=0; z<3&&cantBDS!=3;z++){
                cantBDS=0;
                int j=0;
                for(int i=0;i<tablero.length; i++,j++){
                    if(tablero[i][j+z].charAt(0)  == 'C' && tablero[i][j+z+1].charAt(0)  == 'D'){
                        cantBDS++;
                        posicionesGanadorasB[contadorArrayB]=i;
                        posicionesGanadorasB[contadorArrayB+1]=j+z;
                        posicionesGanadorasB[contadorArrayB+2]=i;
                        posicionesGanadorasB[contadorArrayB+3]=j+z+1;
                        contadorArrayB+=4;
                    }
                    if(cantBDS==3){
                        hayDiagonalB=true;  
                    }
                }
            }
            if(!hayDiagonalB){
                //Blanca Inferior
                contadorArrayB = 0;
                for(int i = 0; i < posicionesGanadorasB.length;i++){
                    posicionesGanadorasB[i] = 0;
                }
                for(int z=0; z < 3 && cantBDI != 3;z++){
                    int j=0;
                    for(int i=0;i<tablero.length; i++,j++){
                        if(tablero[2-i][j+z].charAt(0)  == 'C' && tablero[2-i][j+z+1].charAt(0)  == 'D'){
                            cantBDI++;
                            posicionesGanadorasB[contadorArrayB]=2-i;
                            posicionesGanadorasB[contadorArrayB+1]=j+z;
                            posicionesGanadorasB[contadorArrayB+2]=2-i;
                            posicionesGanadorasB[contadorArrayB+3]=j+z+1;
                            contadorArrayB+=4;
                        }
                        if(cantBDI==3){
                            hayDiagonalB=true;  
                        }
                    }
                }
            }
        }
        else{
            //Negra Superior
            for(int z=0; z<3&&cantNDS!=3;z++){
                cantNDS=0;
                int j=0;
                for(int i=0;i<tablero.length; i++,j++){
                    if(tablero[i][j+z].charAt(0)  == 'D' && tablero[i][j+z+1].charAt(0)  == 'C'){
                        cantNDS++;
                        posicionesGanadorasN[contadorArrayN]=i;
                        posicionesGanadorasN[contadorArrayN+1]=j+z;
                        posicionesGanadorasN[contadorArrayN+2]=i;
                        posicionesGanadorasN[contadorArrayN+3]=j+z+1;
                        contadorArrayN+=4;
                    }
                    if(cantNDS==3){
                        hayDiagonalN=true;  
                    }
                }
            }
            if(!hayDiagonalN){
                //Negra Inferior
                contadorArrayN = 0;
                for(int i = 0; i < posicionesGanadorasN.length;i++){
                    posicionesGanadorasN[i] = 0;
                }
                for(int z=0; z<3&&cantNDI!=3;z++){
                    int j=0;
                    for(int i=0;i<tablero.length; i++,j++){
                        if(tablero[2-i][j+z].charAt(0)  == 'D' && tablero[2-i][j+z+1].charAt(0)  == 'C'){
                            cantNDI++;
                            posicionesGanadorasN[contadorArrayN]=2-i;
                            posicionesGanadorasN[contadorArrayN+1]=j+z;
                            posicionesGanadorasN[contadorArrayN+2]=2-i;
                            posicionesGanadorasN[contadorArrayN+3]=j+z+1;
                            contadorArrayN+=4;
                        }
                        if(cantNDI==3){
                            hayDiagonalN=true;  
                        }
                    }
                }
            }
        }
        if(hayDiagonalB && hayDiagonalN){
            if(this.getTurno() == 'B'){
                this.setFichasGanadoras(posicionesGanadorasB);
                sumar1NB('B');
                hayDiagonalN = false;
            }
            else{
                hayDiagonalB = false;
                this.setFichasGanadoras(posicionesGanadorasN);
                sumar1NB('N');
            }
        }
        else{
            if(hayDiagonalB){
                this.setFichasGanadoras(posicionesGanadorasB);
                sumar1NB('B');
                hayDiagonalN = false;
            }
            if(hayDiagonalN){
                hayDiagonalB = false;
                this.setFichasGanadoras(posicionesGanadorasN);
                sumar1NB('N');
            }
        }
        return hayDiagonalB || hayDiagonalN;
    }
    
    public void rendicion(){
        char turno = this.getTurno();
        String jugador;
        String rendido;;
        
        if(turno == 'B'){
            sumar1NB('N');
            jugador = "Negro";
            rendido = "Blanco";
        }else{
            sumar1NB('B');
            jugador = "Blanco";
            rendido= "Negro";
        }
        this.setPartidaFinalizada(true);
        System.out.println(rendido + " se ha rendido.");
        System.out.println("\n***** GANADOR: "+jugador+" *****");
    }
    public void tablas(){
        Scanner in = new Scanner(System.in);
        char turno = this.getTurno();
        String ofreceEmpate;
        if(turno =='B'){
            ofreceEmpate="Blanco";
        }else{
            ofreceEmpate="Negro";
        }
        
        System.out.println("El jugador "+ ofreceEmpate + " esta ofreciendo un empate.");
        System.out.println("\n ¿Quiere aceptar el empate? (Y - Aceptar; Otro caracter - Declinar");
        String resp = in.nextLine();
        if(resp.toUpperCase().equals("Y")){
            System.out.println("\nLa partida Resulta en empate");
            this.setPartidaFinalizada(true);
        }
    }
    public void detectarTableroLleno(){
        boolean tableroLleno=true;
        String[][] tablero=this.getTablero();
        for(int i=0;i<tablero.length;i++){
            for(int j=0;j<tablero[0].length;j++){
                if(tablero[i][j].equals("  ")){
                    tableroLleno = false;
                }
            }
        }
        if(tableroLleno){
            System.out.println("\n El tablero se ha llenado, se finaliza la partida");
            System.out.println("\nLa partida Resulta en empate");
            this.setPartidaFinalizada(true);

        }
    }
}   
