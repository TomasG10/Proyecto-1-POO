package proyecto_1;

import javax.swing.ImageIcon;
import java.awt.Color;

public class AgenteBase {
    int fila;
    int columna;
    boolean estadoRecurso;
    ImageIcon tipoHormiga;
    Color fondoHormiga;

    public AgenteBase(){

    }

    public void pintarAgente(int fila, int columna, ImageIcon tipoHormiga, Color fondoHormiga){
        Ventana.tablero[fila][columna].setBackground(fondoHormiga);
        Ventana.tablero[fila][columna].setIcon(tipoHormiga);
        
    }

    public void moverAgente(AgenteBase hormiga, int movimiento){
        int fila = hormiga.fila;
        int columna = hormiga.columna;
        int direccion;
        
        boolean realizado = false;
        
        while (realizado == false){
            if (movimiento == 0){
                direccion = (int) (Math.random()*(5-1) + 1);
            }
            else{
                direccion = movimiento;
            }
            
            switch(direccion){
                case 1: // Arriba
                    if (fila != 0){
                        
                        if (verificarEspacio(fila-1,columna)){
                            Ventana.tablero[fila][columna].setBackground(Ventana.colorTablero);
                            Ventana.tablero[fila][columna].setIcon(Ventana.fondo);

                            hormiga.fila = fila - 1;
                            Ventana.tablero[fila-1][columna].setBackground(hormiga.fondoHormiga);
                            Ventana.tablero[fila-1][columna].setIcon(hormiga.tipoHormiga);
                            realizado = true;
                        }
                    }
                    break;
                
                case 2: // Abajo
                    if (fila != 49){
                        if (verificarEspacio(fila+1,columna)){
                            Ventana.tablero[fila][columna].setBackground(Ventana.colorTablero);
                            Ventana.tablero[fila][columna].setIcon(Ventana.fondo);

                            hormiga.fila = fila + 1;
                            Ventana.tablero[fila+1][columna].setBackground(hormiga.fondoHormiga);
                            Ventana.tablero[fila+1][columna].setIcon(hormiga.tipoHormiga);
                            realizado = true;
                        }
                    }
                    break;
                
                case 3: // Derecha
                    if (columna != 49){
                        if (verificarEspacio(fila,columna+1)){
                            Ventana.tablero[fila][columna].setBackground(Ventana.colorTablero);
                            Ventana.tablero[fila][columna].setIcon(Ventana.fondo);

                            hormiga.columna = columna + 1;
                            Ventana.tablero[fila][columna+1].setBackground(hormiga.fondoHormiga);
                            Ventana.tablero[fila][columna+1].setIcon(hormiga.tipoHormiga);
                            realizado = true;
                        }
                    }
                    break;
                
                case 4: // Izquierda
                    if (columna != 0){
                        if (verificarEspacio(fila,columna-1)){
                            Ventana.tablero[fila][columna].setBackground(Ventana.colorTablero);
                            Ventana.tablero[fila][columna].setIcon(Ventana.fondo);

                            hormiga.columna = columna - 1;
                            Ventana.tablero[fila][columna-1].setBackground(hormiga.fondoHormiga);
                            Ventana.tablero[fila][columna-1].setIcon(hormiga.tipoHormiga);
                            realizado = true;
                        }
                    }
                    break;
            }
        }
    }
    
            
    public boolean verificarEspacio(int fila, int columna){
        return Ventana.tablero[fila][columna].getBackground().equals(Ventana.colorTablero) || Ventana.tablero[fila][columna].getBackground().equals(Recursos.fondoRecurso);
    }    
    
    public static int volverABase(AgenteBase hormiga){
        return 0;
    }

    
    public static int comparacionPorArriba(int filaHormiga, int columnaHormiga){
        if (filaHormiga > 0){
            if (Ventana.tablero[filaHormiga-1][columnaHormiga].getBackground().equals(Ventana.colorTablero)){
                return 1;
            }
        }
        return 0;
    }
    
    public static int comparacionPorAbajo(int filaHormiga, int columnaHormiga){
        if (filaHormiga < 49){
            if (Ventana.tablero[filaHormiga+1][columnaHormiga].getBackground().equals(Ventana.colorTablero)){
                return 2;
            }
        }
        return 0;     
    }
    
    public static int comparacionPorDerecha(int filaHormiga, int columnaHormiga){
        if (columnaHormiga < 49){
            if (Ventana.tablero[filaHormiga][columnaHormiga+1].getBackground().equals(Ventana.colorTablero)){
                return 3;
            }
        }
        return 0;
    }
    
    public static int comparacionPorIzquierda(int filaHormiga, int columnaHormiga){
        if (columnaHormiga > 0){
            if (Ventana.tablero[filaHormiga][columnaHormiga-1].getBackground().equals(Ventana.colorTablero)){
                return 4;
            }
        }
        return 0;
    }

    public static int hayAmenazaCercaRecolectores(int fila, int columna, int radio){
        int contador;
        int direccion = 0;
           
        // Detectar una amenaza
        
        // Revision hacia Arriba
        for (contador = 1; contador <= radio; contador++){
            if (fila - contador >= 0){
               if (Ventana.tablero[fila-contador][columna].getBackground().equals(Amenazas.fondoAmenaza)){
                    direccion = 1;
               }
            }
        }

        // Revision hacia abajo (Revisar que no se este saliendo de los margenes del tablero)
        for (contador = 1; contador <= radio; contador++){
            if (fila + contador <= 49){
               if (Ventana.tablero[fila+contador][columna].getBackground().equals(Amenazas.fondoAmenaza)){
                    direccion = 2;
               }
            }
        }

        // Revision hacia derecha (Revisar que no se este saliendo de los margenes del tablero)
        for (contador = 1; contador <= radio; contador++){
            if (columna + contador <= 49){
               if (Ventana.tablero[fila][columna+contador].getBackground().equals(Amenazas.fondoAmenaza)){
                    direccion = 3;
               }
            }
        }

        // Revision hacia izquierda (Revisar que no se este saliendo de los margenes del tablero)
        for (contador = 1; contador <= radio; contador++){
            if (columna - contador >= 0){
               if (Ventana.tablero[fila][columna-contador].getBackground().equals(Amenazas.fondoAmenaza)){
                    direccion = 4;
               }
            }
        }     
        
        // Logica con orden para el movimiento (Recolectores) (Se busca que la direccion sea la inversa a la de la amenaza)
        switch (direccion){
            case 1: // Amenaza arriba
                if (comparacionPorDerecha(fila, columna) != 0)// Caso especifico
                    direccion = 3; 
                if (comparacionPorIzquierda(fila, columna) != 0) // Caso especifico
                    direccion = 4; 
                if (comparacionPorAbajo(fila, columna) != 0) // Contraria a la direccion donde esta la amenaza
                    direccion = 2;
                break;
            
            case 2: // Amenaza abajo
                if (comparacionPorDerecha(fila, columna) != 0)// Caso especifico
                     direccion = 3; 
                if (comparacionPorIzquierda(fila, columna) != 0) // Caso especifico
                    direccion = 4; 
                if (comparacionPorArriba(fila, columna) != 0) // Contraria
                    direccion = 1;
                break;
            
            case 3: // Amenaza derecha
                if (comparacionPorArriba(fila, columna) != 0) // Caso especifico
                    direccion = 1;
                if (comparacionPorAbajo(fila, columna) != 0) // Caso especifico
                    direccion = 2;
                if (comparacionPorIzquierda(fila, columna) != 0) // Contraria
                    direccion = 4;
                break;
            
            case 4: // Amenaza izquierda
                if (comparacionPorArriba(fila, columna) != 0) // Caso especifico
                    direccion = 1;
                if (comparacionPorAbajo(fila, columna) != 0) // Caso especifico
                    direccion = 2;
                if (comparacionPorDerecha(fila, columna) != 0) // Contraria
                    direccion = 3;
        }
        return direccion;
    }

    public static boolean llegoABase(int filaHormiga, int columnaHormiga){
        
        int base = Base.conocerBase();
        
        switch (base){
        
            case 1:
                if ((filaHormiga == 0 && columnaHormiga == 1) || (filaHormiga == 1 && columnaHormiga == 0))
                    return true;
            
            case 2:
                if ((filaHormiga == 0 && columnaHormiga == 48) || (filaHormiga == 1 && columnaHormiga == 48))
                    return true;
                
            case 3:
                if ((filaHormiga == 48 && columnaHormiga == 0) || (filaHormiga == 49 && columnaHormiga == 1))
                    return true;
            
            case 4:
                if ((filaHormiga == 48 && columnaHormiga == 49) || (filaHormiga == 49 && columnaHormiga == 48))
                    return true;
        }
        return false;
    }
    
    public static boolean llevaRecurso(AgenteBase hormiga, int direccion){
        int fila = hormiga.fila;
        int columna = hormiga.columna;
        
        switch (direccion) {
            case 1: // Arriba
                return Ventana.tablero[fila-1][columna].getBackground().equals(Recursos.fondoRecurso);
             
            case 2: // Abajo
                return Ventana.tablero[fila+1][columna].getBackground().equals(Recursos.fondoRecurso);
                
            case 3: // Derecha
                return Ventana.tablero[fila][columna+1].getBackground().equals(Recursos.fondoRecurso);
                
            case 4: // Izquierda
                return Ventana.tablero[fila][columna-1].getBackground().equals(Recursos.fondoRecurso);
        
        }
        return false; 
    } 
}