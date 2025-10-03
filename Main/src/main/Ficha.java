package main;

/**
 *
 * @author Toto
 */
public class Ficha {
    private char color;
    private char orientacion;

    public char getColor(){
        return color;
    }

    public void setColor(char unColor){
        this.color = unColor;
    }

    public char getOrientacion(){
        return orientacion;
    }

    public void setOrientacion(char UnaOrientacion){
        this.orientacion = UnaOrientacion;
    }
    
    //////
    public void invertirOrientacion(){
        if(this.getOrientacion()=='C'){
            this.setOrientacion('D');
        }else{
            this.setOrientacion('C');
        }
    }
    
}
