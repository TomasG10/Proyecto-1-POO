package proyecto_1;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Recursos extends Objetos{

    private ImageIcon imagenRecurso;
    public static Color fondoRecurso = new Color(0,255,128);
    
    public Recursos(int cantidadRecursos){
        imagenRecurso = new ImageIcon("recurso.jpg");
        generar(cantidadRecursos);
    }
    
    public void generar(int cantidad){
        int contador = 0;
        while (contador < cantidad){
            fila = (int) (Math.random()*(42-4) + 4);
            columna= (int) (Math.random()*(47-4) + 4);

            if (hayEspacio(fila,columna)){
                pintar(fila,columna);
                contador ++;
            }
        }
    }

    @Override
    public boolean hayEspacio(int fila, int columna) {
        for (int i=0; i<5; i++){
            if( !((Ventana.tablero[fila+i][columna].getBackground().equals(Ventana.colorTablero)) && Ventana.tablero[fila+i][columna+1].getBackground().equals(Ventana.colorTablero))){ // Revisar fila 
                return false;
            }
        }
        return true;
    }
    
    public void pintar(int fila, int columna) {
        for (int i=0; i<5; i++){
            Ventana.tablero[fila+i][columna].setBackground(fondoRecurso);
            Ventana.tablero[fila+i][columna].setIcon(imagenRecurso);  
            
            Ventana.tablero[fila+i][columna+1].setBackground(fondoRecurso);
            Ventana.tablero[fila+i][columna+1].setIcon(imagenRecurso); 
         }
    }
    
}


