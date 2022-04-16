package proyecto_1;
import java.awt.Color;

import javax.swing.ImageIcon;

public class Recolectores extends AgenteBase{

    public Recolectores(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        estadoRecurso = false;
        tipoHormiga = new ImageIcon("recolectora.png");
        fondoHormiga = new Color(0,255,255);
        super.pintarAgente(fila,columna, tipoHormiga, fondoHormiga);
    } 

    public static int rangoRecolector(Recolectores hormiga ,int radio){
        int fila = hormiga.fila;
        int columna= hormiga.columna;
        int contador;
        
        if (hormiga.estadoRecurso == false){
            
            // Revision hacia Arriba
            for (contador = 1; contador <= radio; contador++){
                if (fila - contador >= 0){
                   if (Ventana.tablero[fila-contador][columna].getBackground().equals(Recursos.fondoRecurso)){
                        return 1;
                   }
                }
            }

            // Revision hacia abajo (Revisar que no se este saliendo de los margenes del tablero)
            for (contador = 1; contador <= radio; contador++){
                if (fila + contador <= 49){
                   if (Ventana.tablero[fila+contador][columna].getBackground().equals(Recursos.fondoRecurso)){
                        return 2;
                   }
                }
            }

            // Revision hacia derecha (Revisar que no se este saliendo de los margenes del tablero)
            for (contador = 1; contador <= radio; contador++){
                if (columna + contador <= 49){
                   if (Ventana.tablero[fila][columna+contador].getBackground().equals(Recursos.fondoRecurso)){
                        return 3;
                   }
                }
            }

            // Revision hacia izquierda (Revisar que no se este saliendo de los margenes del tablero)
            for (contador = 1; contador <= radio; contador++){
                if (columna - contador >= 0){
                   if (Ventana.tablero[fila][columna-contador].getBackground().equals(Recursos.fondoRecurso)){
                        return 4;
                   }
                }
            }

            return 0; // Caso en el que el movimiento no se ve afectado
        }
        else{
            return 0;
        }
    }
 
    public static int volverABase(Recolectores hormiga){
        int filaHormiga = hormiga.fila;
        int columnaHormiga = hormiga.columna;
        
        int filaBase = Base.fila;
        int columnaBase = Base.columna;
        
        int ladoBase =  Base.conocerBase();
        int direccion = 1000;
        
        switch (ladoBase){
              
            case 1:
                if (comparacionPorAbajo(filaHormiga, columnaHormiga) != 0)
                    direccion = 2;
                
                if (comparacionPorDerecha(filaHormiga, columnaHormiga) != 0)
                    direccion = 3;
                
                if (comparacionPorIzquierda(filaHormiga, columnaHormiga) != 0)
                    direccion = 4;
                
                if (comparacionPorArriba(filaHormiga, columnaHormiga) != 0)
                    direccion = 1;
                
                break;
                
            case 2:
                
                if (comparacionPorAbajo(filaHormiga, columnaHormiga) != 0)
                    direccion = 2;
                
                if (comparacionPorIzquierda(filaHormiga, columnaHormiga) != 0)
                    direccion = 4;
                
                if (comparacionPorDerecha(filaHormiga, columnaHormiga) != 0)
                    direccion = 3;
                
                if (comparacionPorArriba(filaHormiga, columnaHormiga) != 0)
                    direccion = 1;
                
                break;
                
            case 3:
                if (comparacionPorArriba(filaHormiga, columnaHormiga) != 0)
                    direccion = 1;
                
                if (comparacionPorDerecha(filaHormiga, columnaHormiga) != 0)
                    direccion = 3;
                
                if (comparacionPorIzquierda(filaHormiga, columnaHormiga) != 0)
                    direccion = 4;
                
                if (comparacionPorAbajo(filaHormiga, columnaHormiga) != 0)
                    direccion = 2;
                
                break;
            
            case 4:
                if (comparacionPorArriba(filaHormiga, columnaHormiga) != 0)
                    direccion = 1;
                
                if (comparacionPorIzquierda(filaHormiga, columnaHormiga) != 0)
                    direccion = 4;
                
                if (comparacionPorDerecha(filaHormiga, columnaHormiga) != 0)
                    direccion = 3;
                
                if (comparacionPorAbajo(filaHormiga, columnaHormiga) != 0)
                    direccion = 2;
                
                break;
        }
           
        return direccion;
    }
}