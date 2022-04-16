package proyecto_1;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Amenazas extends Objetos{
    int fila;
    int columna;
    int vida;
    ImageIcon imagenAmenaza;
    public static Color fondoAmenaza;

    public Amenazas() {
        this.vida = 10;
        imagenAmenaza = new ImageIcon("fuego.jpg");
        fondoAmenaza = new java.awt.Color(255,128,0);
        generar();
    }

    public void generar() {
        int contador = 0;
        while (contador < 7){
            fila = (int) (Math.random() * (42-4) + 4);
            columna= (int) (Math.random() * (47-4) + 4);

            if (hayEspacio(fila,columna)){
                pintar(fila,columna);
                contador ++;
            }
        }
    }

    public boolean hayEspacio(int fila, int columna) {
        return Ventana.tablero[fila][columna].getBackground().equals(Ventana.colorTablero);
    }

    public void pintar(int fila, int columna) {
        Ventana.tablero[fila][columna].setBackground(fondoAmenaza);
        Ventana.tablero[fila][columna].setIcon(imagenAmenaza);
    }
}