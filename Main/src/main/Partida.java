package main;

/**
 *
 * 
 * @author Toto
 */
public class Partida {
    private String[][]tablero; //Celdas del tipo BD, NC
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
    
    //procesa la jugada y agrega la ficha solo si es una jugada de ficha, si es un comando (1 letra) no hace nada, se hace en interfaz
    public void procesarJugada(String jugada){
        //la funcion asume datos validos
        if (jugada.length() == 3) {
            String ficha = "";
            int col = (int)jugada.charAt(1) - 1;
            int fila = 0;

            if (jugada.charAt(0) == 'A') {
                fila = 0;
            } else if (jugada.charAt(0) == 'B') {
                fila = 1;

            } else {
                fila = 2;

            }
            if (!(jugada.charAt(2) == 'I')) {
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
    
    
    /*
        String[][] tableroStr = {
    {" "," "," ","1"," "," ","2"," "," ","3"," "," ","4"," "," ","5"," "," ","6"," "},
    {" ","+","-","-","+","-","-","+","-","-","+","-","-","+","-","-","+","-","-","+"},
    {" ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"},
    {"A","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"},
    {" ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"},
    {" ","+","-","-","+","-","-","+","-","-","+","-","-","+","-","-","+","-","-","+"},
    {" ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"},
    {"B","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"},
    {" ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"},
    {" ","+","-","-","+","-","-","+","-","-","+","-","-","+","-","-","+","-","-","+"},
    {" ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"},
    {"C","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"},
    {" ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"," "," ","|"},
    {" ","+","-","-","+","-","-","+","-","-","+","-","-","+","-","-","+","-","-","+"},
    }; */
    
    
    /*
    public void sacarBordes(){
        String[] caracteres = {"A","B","C","1","2","3","4","5","6"};
        
        for(int i=0;i<caracteres.length;i++){
            for(int j=0;j<this.getTableroStr()[0].length;j++){
                if(caracteres[i] == this.getTableroStr()[0][j]){
                    this.getTableroStr()[0][j] = " ";
                }
            }
            
        }
        for(int i=0;i<caracteres.length;i++){
            for(int j=0;j<this.getTableroStr().length;j++){
                if(caracteres[i] == this.getTableroStr()[j][0]){
                    this.getTableroStr()[j][0] = " ";
                }
            }
        }
    }
    
    public void incluirBordes(){
      int[] fila = {3,7,11};
      
      String[] dato = {"A","B","C"};
     
      int[] colNum = {3,6,9,12,15,18};
      
      String[] datoNum = {"1","2","3","4","5","6"};
      
      for(int i=0;i<fila.length;i++){
          this.getTableroStr()[fila[i]][0]=dato[i];
      }
      
      for(int j=0;j<colNum.length;j++){
          this.getTableroStr()[0][colNum[j]]=datoNum[j];
      }
    }*/
}   
