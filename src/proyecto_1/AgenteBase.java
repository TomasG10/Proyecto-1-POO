package proyecto_1;

import javax.swing.ImageIcon;
import java.awt.Color;

abstract class AgenteBase {
    int fila;
    int columna;
    int alejarHormiga;
    boolean estadoRecurso;
    ImageIcon tipoHormiga;
    Color fondoHormiga;

    public AgenteBase(){

    }

    public void pintarAgente(int fila, int columna, ImageIcon tipoHormiga, Color fondoHormiga){
        Ventana.tablero[fila][columna].setBackground(fondoHormiga);
        Ventana.tablero[fila][columna].setIcon(tipoHormiga);
    }

    public void moverAgente(int movimiento){
        int direccion;
      
        boolean realizado = false;
        
        while (realizado == false){
            
            if (comparacionPorArriba(fila, columna) == 0 && comparacionPorAbajo(fila,columna) == 0 && comparacionPorDerecha(fila, columna) == 0 && comparacionPorIzquierda(fila, columna) == 0){
                realizado = true;
            }
            
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

                            fila = fila - 1;
                            Ventana.tablero[fila][columna].setBackground(fondoHormiga);
                            Ventana.tablero[fila][columna].setIcon(tipoHormiga);
                            realizado = true;
                        }
                    }
                    break;
                
                case 2: // Abajo
                    if (fila != 49){
                        if (verificarEspacio(fila+1,columna)){
                            Ventana.tablero[fila][columna].setBackground(Ventana.colorTablero);
                            Ventana.tablero[fila][columna].setIcon(Ventana.fondo);

                            fila = fila + 1;
                            Ventana.tablero[fila][columna].setBackground(fondoHormiga);
                            Ventana.tablero[fila][columna].setIcon(tipoHormiga);
                            realizado = true;
                        }
                    }
                    break;
                
                case 3: // Derecha
                    if (columna != 49){
                        if (verificarEspacio(fila,columna+1)){
                            Ventana.tablero[fila][columna].setBackground(Ventana.colorTablero);
                            Ventana.tablero[fila][columna].setIcon(Ventana.fondo);

                            columna = columna + 1;
                            Ventana.tablero[fila][columna].setBackground(fondoHormiga);
                            Ventana.tablero[fila][columna].setIcon(tipoHormiga);
                            realizado = true;
                        }
                    }
                    break;
                
                case 4: // Izquierda
                    if (columna != 0){
                        if (verificarEspacio(fila,columna-1)){
                            Ventana.tablero[fila][columna].setBackground(Ventana.colorTablero);
                            Ventana.tablero[fila][columna].setIcon(Ventana.fondo);
 
                            columna = columna - 1;
                            Ventana.tablero[fila][columna].setBackground(fondoHormiga);
                            Ventana.tablero[fila][columna].setIcon(tipoHormiga);
                            realizado = true;

                        }
                    }
                    break;
            }
        }
    }
    
    public int rangoHormiga(int radio){
        return 0;
    }
    
    public int hayAmenazaCerca(int radio){
        return 0;
    }
    
    public void recorrerListaHormigas(){
    }
    
    public boolean verificarEspacio(int fila, int columna){
        return Ventana.tablero[fila][columna].getBackground().equals(Ventana.colorTablero) || Ventana.tablero[fila][columna].getBackground().equals(Recursos.fondoRecurso);
    }    
    
    public int volverABase(){
        
        int ladoBase =  Base.conocerBase();
        int direccion = 1000;

        switch (ladoBase){

            case 1:
                if (comparacionPorAbajo(fila, columna) != 0)
                    direccion = 2;

                if (comparacionPorDerecha(fila, columna) != 0)
                    direccion = 3;

                if (comparacionPorIzquierda(fila, columna) != 0)
                    direccion = 4;

                if (comparacionPorArriba(fila, columna) != 0)
                    direccion = 1;

                break;

            case 2:  
                if (comparacionPorAbajo(fila, columna) != 0)
                    direccion = 2;

                if (comparacionPorIzquierda(fila, columna) != 0)
                    direccion = 4;

                if (comparacionPorDerecha(fila, columna) != 0)
                    direccion = 3;

                if (comparacionPorArriba(fila, columna) != 0)
                    direccion = 1;

                break;

            case 3:
                if (comparacionPorArriba(fila, columna) != 0)
                    direccion = 1;

                if (comparacionPorDerecha(fila, columna) != 0)
                    direccion = 3;

                if (comparacionPorIzquierda(fila, columna) != 0)
                    direccion = 4;

                if (comparacionPorAbajo(fila, columna) != 0)
                    direccion = 2;

                break;

            case 4:
                if (comparacionPorArriba(fila, columna) != 0)
                    direccion = 1;

                if (comparacionPorIzquierda(fila, columna) != 0)
                    direccion = 4;

                if (comparacionPorDerecha(fila, columna) != 0)
                    direccion = 3;

                if (comparacionPorAbajo(fila, columna) != 0)
                    direccion = 2;

                break;
        }
        return direccion;
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
    
    public boolean llevaRecurso(int direccion){
     
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