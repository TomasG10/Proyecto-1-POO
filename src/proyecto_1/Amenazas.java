package proyecto_1;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Amenazas extends Objetos{
    int vida;
    private ImageIcon imagenAmenaza;
    public static Color fondoAmenaza;
    

    public Amenazas(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.vida = 10;
        imagenAmenaza = new ImageIcon("Imagenes/fuego.jpg");
        fondoAmenaza = new java.awt.Color(255,128,0);
        pintar(fila, columna);
    }

    @Override
    public void pintar(int fila, int columna) {
        Ventana.tablero[fila][columna].setBackground(fondoAmenaza);
        Ventana.tablero[fila][columna].setIcon(imagenAmenaza);
    }
    
    public  boolean amenazaEliminada(){
        
        if (vida == 0){     
            Ventana.tablero[fila][columna].setBackground(Ventana.colorTablero);
            Ventana.tablero[fila][columna].setIcon(Ventana.fondo);
            
            return true;
        }
        
        return false;
    }
    
    public static boolean hayEspacioAmenazas(int fila, int columna) {
        
        for (int f=0; f<8; f++){
            if (!Ventana.tablero[fila][columna-f].getBackground().equals(Ventana.colorTablero) || !Ventana.tablero[fila][columna+f].getBackground().equals(Ventana.colorTablero))
            {
            return false;
            }

            if (!Ventana.tablero[fila+f][columna].getBackground().equals(Ventana.colorTablero) || !Ventana.tablero[fila-f][columna].getBackground().equals(Ventana.colorTablero))
            {
            return false;
            }
        }
        return true;
    }
}