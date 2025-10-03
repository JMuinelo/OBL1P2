package main;

/**
 *
 * @author Toto
 */
public class Partida {
    private Ficha[][]tablero;
    private boolean partidaFinalizada;
    private JugadorEnPartida jugadorBlanco;
    private JugadorEnPartida jugadorNegro;
    
    public Ficha[][] getTablero() {
        return tablero;
    }

    public void setTablero(Ficha[][] tablero) {
        this.tablero = tablero;
    }

    public boolean isPartidaFinalizada() {
        return partidaFinalizada;
    }

    public void setPartidaFinalizada(boolean partidaFinalizada) {
        this.partidaFinalizada = partidaFinalizada;
    }

    public JugadorEnPartida getJugadorBlanco() {
        return jugadorBlanco;
    }

    public void setJugadorBlanco(JugadorEnPartida jugadorBlanco) {
        this.jugadorBlanco = jugadorBlanco;
    }

    public JugadorEnPartida getJugadorNegro() {
        return jugadorNegro;
    }

    public void setJugadorNegro(JugadorEnPartida jugadorNegro) {
        this.jugadorNegro = jugadorNegro;
    }
    public Partida(JugadorEnPartida jb, JugadorEnPartida jn){
        this.setJugadorBlanco(jb);
        this.setJugadorNegro(jn);
        this.setPartidaFinalizada(false);
        this.setTablero(new Ficha[3][6]);
        
    }
    
    ///////////////////////
    ///
    private String[][] tableroStr = {
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
    };

    public String[][] getTableroStr() {
        return tableroStr;
    }

    public void setTableroStr(String[][] tableroStr) {
        this.tableroStr = tableroStr;
    }
    
    
    
    public void mostrarMatriz(String[][] mat){
        
        
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                System.out.print(mat[i][j]+ " ");
            }
            System.out.println(" ");
        }
    }

    public void agregarFicha(Ficha ficha,int fila, int col){
        tablero[fila][col] = ficha;
    }
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
    }
}
