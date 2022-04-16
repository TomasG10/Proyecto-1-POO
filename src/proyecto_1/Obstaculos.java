package proyecto_1;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Obstaculos extends Objetos{
    private int fila;
    private int columna;
    private ImageIcon imagenObstaculo;
    public static Color fondoObstaculo = new Color(192,192,192);
    
    public Obstaculos(){
        imagenObstaculo = new ImageIcon("piedra.jpg");
        generar();
    }
    
    public void generar(){
        int contador = 0;
        while (contador < 15){
            fila = (int) (Math.random()*(42-2) + 2);
            columna= (int) (Math.random()*(42-2) + 2);
            
            if (hayEspacio(fila,columna)){ 
                pintar(fila,columna);
                contador ++;
            }
        }
    }

    @Override
    public boolean hayEspacio(int fila, int columna) {
        for (int f=0; f<3; f++){
            for (int c = 0; c<3; c++){
                if (!((Ventana.tablero[fila+f][columna+c].getBackground().equals(Ventana.colorTablero)))){
                    return false;
                }
            }
        }
        return true;
    }
   
    @Override
    public void pintar(int fila, int columna) {
        
        for (int f = 0; f < 3; f++){
            for (int c = 0; c < 3; c++){
                Ventana.tablero[fila+f][columna+c].setBackground(fondoObstaculo);
                Ventana.tablero[fila+f][columna+c].setIcon(imagenObstaculo);
            }
        }
    }
}
