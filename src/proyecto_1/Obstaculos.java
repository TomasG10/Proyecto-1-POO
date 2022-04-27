package proyecto_1;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Obstaculos extends Objetos{
    private ImageIcon imagenObstaculo;
    public static Color fondoObstaculo = new Color(192,192,192);
    
    public Obstaculos(int cantidad){
        imagenObstaculo = new ImageIcon("Imagenes/piedra.jpg");
        generar(cantidad);
    }
    
    @Override
    public void generar(int cantidad){
        int contador = 0;
        while (contador < cantidad){
            fila = (int) (Math.random()*(42-2) + 2);
            columna= (int) (Math.random()*(42-2) + 2);
            
            if (hayEspacio()){ 
                pintar(fila,columna);
                contador ++;
            }
        }
    }

    @Override
    public boolean hayEspacio() {
        
        // Revisando fila de arriba (Esto con el fin de que no queden pegados objetos)
        for (int pos=-1; pos<4; pos++){
            if (!Ventana.tablero[fila-1][columna+pos].getBackground().equals(Ventana.colorTablero)){
                return false;
            }
        }
        
        for (int f=0; f<4; f++){
            if (!Ventana.tablero[fila+f][columna-1].getBackground().equals(Ventana.colorTablero) || !Ventana.tablero[fila+f][columna+3].getBackground().equals(Ventana.colorTablero))
            {
            return false;
            }
            
            for (int c = 0; c<3; c++){
                if (!Ventana.tablero[fila+f][columna+c].getBackground().equals(Ventana.colorTablero)){
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
