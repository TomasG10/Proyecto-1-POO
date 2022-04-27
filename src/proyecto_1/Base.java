package proyecto_1;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Base {
    private int randEsquina;
    public static Color fondoBase = new java.awt.Color(102,0,102);
    private ImageIcon imagenBase = new ImageIcon("Imagenes/base.jpg"); 
    public static int fila;
    public static int columna;
    
    
    public Base(){
        randEsquina = (int) (Math.random()*(5-1) + 1);
        generarBase(randEsquina);
    }
    
    private void generarBase(int randEsquina){
        switch(randEsquina){
            case 1:
                Ventana.tablero[0][0].setBackground(fondoBase);
                Ventana.tablero[0][0].setIcon(imagenBase);
                fila = 0;
                columna = 0;
                break;
            case 2:
                Ventana.tablero[0][49].setBackground(fondoBase);
                Ventana.tablero[0][49].setIcon(imagenBase);
                fila = 0;
                columna = 49;               
                break;
            case 3:
                Ventana.tablero[49][0].setBackground(fondoBase);
                Ventana.tablero[49][0].setIcon(imagenBase);
                fila = 49;
                columna = 0;                
                break;
            case 4:
                Ventana.tablero[49][49].setBackground(fondoBase);
                Ventana.tablero[49][49].setIcon(imagenBase);
                fila = 49;
                columna = 49;                
                break;
        } 
    }
    
    public static int conocerBase(){
        
        if (columna == 0 && fila == 0){
            return 1;
        }
        else if (fila == 0 && columna == 49){
            return 2;
        }
        else if (fila == 49 && columna == 0){
            return 3;
        }
        else{
            return 4;
        }
    }
}
