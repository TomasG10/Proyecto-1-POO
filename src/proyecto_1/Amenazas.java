package proyecto_1;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Amenazas extends Objetos{
    int fila;
    int columna;
    int vida;
    ImageIcon imagenAmenaza;
    public static Color fondoAmenaza;
    

    public Amenazas(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.vida = 10;
        imagenAmenaza = new ImageIcon("fuego.jpg");
        fondoAmenaza = new java.awt.Color(255,128,0);
        pintar(fila, columna);
    }

    public void pintar(int fila, int columna) {
        Ventana.tablero[fila][columna].setBackground(fondoAmenaza);
        Ventana.tablero[fila][columna].setIcon(imagenAmenaza);
    }
    
    public static boolean amenazaEliminada(Amenazas amenaza){
        
        if (amenaza.vida == 0){
            int fila = amenaza.fila;
            int columna = amenaza.columna;
            
            Ventana.tablero[fila][columna].setBackground(Ventana.colorTablero);
            Ventana.tablero[fila][columna].setIcon(Ventana.fondo);
            
            return true;
        }
        
        return false;
    }
}