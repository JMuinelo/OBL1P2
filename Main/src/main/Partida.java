package main;

public class Partida {
    private String[][]tablero; //Celdas del tipo DB, CN
    private boolean partidaFinalizada;
    private Jugador jugadorBlanco;
    private Jugador jugadorNegro;
    private char turno;
    private boolean mostrarBordes;

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
        }else{
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
        //tablero artificial
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
        boolean hayGanador = false;
        //vertical
        for(int j=0; j < tablero[0].length; j=j+2){
            int cantB = 0;
            int cantN = 0;
            boolean diagonalNegro=false;
            boolean diagonalBlanco=false;
            
            for(int i=0; i < tablero.length; i++){
                //los circulos primero (blanco)
                    //Vertical
                    if(controlBlanco){
                        if(tablero[i][j].charAt(0) == 'C' && tablero[i][j+1].charAt(0) == 'D'){
                            cantB++;
                        }
                        diagonalBlanco = enDiagonal(true);
                        
                    }
                if(controlNegro){
                   if(tablero[i][j].charAt(0) == 'D' && tablero[i][j+1].charAt(0) == 'C'){
                        cantN++;
                   }
                   diagonalNegro = enDiagonal(false);
                }
            }
            
            boolean resultadoBlanco = cantB == 3 ||diagonalBlanco;
            boolean resultadoNegro = cantN == 3 || diagonalNegro;
            if(resultadoBlanco && resultadoNegro){
                if(this.getTurno() == 'B'){
                    ganador = "Blanco";
                    sumar1NB('B');
                }
                else{
                    ganador = "Negro";
                    sumar1NB('N');
                }
                hayGanador = true;
            }
            else{
                if(resultadoBlanco){
                    ganador = "Blanco";
                    hayGanador = true;
                    sumar1NB('B');
                }
                if(resultadoNegro){
                    ganador = "Negro";
                    hayGanador = true;
                    sumar1NB('N');
                }
            }
        }
        //Si no hubo ganador, recorre de forma horizontal
        if(!hayGanador){
            
            int cantB = 0;
            int cantN = 0;    
                for(int i = 0 ; i < tablero.length; i++){
                    
                    for(int j=0; j < tablero[0].length; j=j+2){
                        if(controlBlanco){
                            if(tablero[i][j].charAt(0) == 'C' && tablero[i][j+1].charAt(0) == 'D'){
                                cantB++;
                            }
                        }
                        if(controlNegro){
                            if(tablero[i][j].charAt(0) == 'D' && tablero[i][j+1].charAt(0) == 'C'){
                                cantN++;
                            }
                        }
                    }
                }
            if(cantB == 3 && cantN == 3){
                if(this.getTurno() == 'B'){
                    ganador = "Blanco";
                    hayGanador = true;
                    sumar1NB('B');
                }
            }
            else{
                if(cantB == 3){
                    ganador = "Blanco";
                    hayGanador = true;
                    sumar1NB('B');
                }
                if(cantN == 3){
                    ganador = "Negro";
                    hayGanador = true;
                    sumar1NB('N');
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
            //funcion random
            int col = Character.getNumericValue(jugada.charAt(1)) - 1;
            int fila = 2;

            if (jugada.charAt(0) == 'A') {
                fila = 0;
            } else if (jugada.charAt(0) == 'B') {
                fila = 1;

            } else {
                fila = 2;
            }
            if (jugada.charAt(2) != 'I') {
                ficha += jugada.charAt(2);
                ficha += (this.getTurno() + "");
                this.agregarFicha(ficha, fila, col);

            } else {
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
    
    public void mostrarTablero(String[][] tablero, boolean mostrar){
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
                System.out.println(fila);
            }
            System.out.println(linea);
        }

    }
    
    public boolean enDiagonal(boolean controlBlanco){
        boolean hayDiagonal=false;
        String[][] tablero = this.getTablero();
        
        int cantBDS = 0; //Blanco Diagonal desde Superior
        int cantNDS  =0;
        int cantBDI = 0;
        int cantNDI = 0;
        
        
        if(controlBlanco){
            //Blanca, superior
            for(int z=0; z<3&&cantBDS!=3;z++){
                int j=0;
                for(int i=0;i<tablero.length; i++,j++){
                    if(tablero[i][j+z].charAt(0)  == 'C' && tablero[i][j+z+1].charAt(0)  == 'D'){
                        cantBDS++;
                    }
                    if(cantBDS==3){
                        hayDiagonal=true;  
                    }
                }
            }
            //Blanca Inferior
            for(int z=0; z<3&&cantBDI!=3;z++){
                int j=0;
                for(int i=0;i<tablero.length; i++,j++){
                    if(tablero[2-i][j+z].charAt(0)  == 'C' && tablero[2-i][j+z+1].charAt(0)  == 'D'){
                        cantBDI++;
                    }
                    if(cantBDI==3){
                        hayDiagonal=true;  
                    }
                }
            }
            
            
        }else{
            //Negra Superior
            for(int z=0; z<3&&cantNDS!=3;z++){
                int j=0;
                for(int i=0;i<tablero.length; i++,j++){
                    if(tablero[i][j+z].charAt(0)  == 'D' && tablero[i][j+z+1].charAt(0)  == 'C'){
                        cantNDS++;
                    }
                    if(cantNDS==3){
                        hayDiagonal=true;  
                    }
                }
            }
            
            //Negra Inferior
            for(int z=0; z<3&&cantNDI!=3;z++){
                int j=0;
                for(int i=0;i<tablero.length; i++,j++){
                    if(tablero[2-i][j+z].charAt(0)  == 'D' && tablero[2-i][j+z+1].charAt(0)  == 'C'){
                        cantNDI++;
                    }
                    if(cantNDI==3){
                        hayDiagonal=true;  
                    }
                }
            }
        }
        
        
        return hayDiagonal;
    }
    
}   
