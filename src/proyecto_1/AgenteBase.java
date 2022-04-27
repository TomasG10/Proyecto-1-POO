package proyecto_1;

import javax.swing.ImageIcon;
import java.awt.Color;

public class AgenteBase {
    int fila;
    int columna;
    int filaRecurso;
    int columnaRecurso;
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
                        else{
                            movimiento = 0;
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
                        else{
                            movimiento = 0;
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
                        else{
                            movimiento = 0;
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
                        else{
                            movimiento = 0;
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
    
    public void comportamientoHormiga(){
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

    public int irHaciaRecurso(){
        
        int direccion = 0;
        
        if (filaRecurso > fila){
            if (comparacionPorAbajo(fila, columna) != 0)
                direccion = 2;
            else{
                filaRecurso = 0;
                columnaRecurso = 0;
            }
        }


        else if (filaRecurso < fila){
            if (comparacionPorArriba(fila, columna) != 0)
                direccion = 1;
            else{
                filaRecurso = 0;
                columnaRecurso = 0;
            }
        }
        
        else if (columnaRecurso > columna){
            if (comparacionPorDerecha(fila, columna) != 0)
                direccion = 3;
            else{
                filaRecurso = 0;
                columnaRecurso = 0;
            }
        }

        else if (columnaRecurso < columna){
            if (comparacionPorIzquierda(fila, columna) != 0)
                direccion = 4; 
            else{
                filaRecurso = 0;
                columnaRecurso = 0;
            }
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
    
    public void detectarHormigaConRecurso(int radio){
 
        int contador;
        
        if (filaRecurso == 0 && columnaRecurso == 0){
            
            // Revision hacia Arriba
            for (contador = 1; contador <= radio; contador++){
                if (fila - contador >= 0){
                   if (Ventana.tablero[fila-contador][columna].getBackground().equals(fondoHormiga)){
                       llevaHormigaRecurso(fila - contador, columna);
                   }
                }
            }

            // Revision hacia abajo (Revisar que no se este saliendo de los margenes del tablero)
            for (contador = 1; contador <= radio; contador++){
                if (fila + contador <= 49){
                   if (Ventana.tablero[fila+contador][columna].getBackground().equals(fondoHormiga)){
                       llevaHormigaRecurso(fila + contador, columna);
                   }
                }
            }

            // Revision hacia derecha (Revisar que no se este saliendo de los margenes del tablero)
            for (contador = 1; contador <= radio; contador++){
                if (columna + contador <= 49){
                   if (Ventana.tablero[fila][columna+contador].getBackground().equals(fondoHormiga)){
                       llevaHormigaRecurso(fila, columna + contador);      
                   }
                }
            }

            // Revision hacia izquierda (Revisar que no se este saliendo de los margenes del tablero)
            for (contador = 1; contador <= radio; contador++){
                if (columna - contador >= 0){
                   if (Ventana.tablero[fila][columna-contador].getBackground().equals(fondoHormiga)){
                       llevaHormigaRecurso(fila, columna - contador);

                   }
                }
            }
        }

    }
    
    public void llevaHormigaRecurso(int fila, int columna){
        
       AgenteBase[] hormigas = Simulacion.GetlistaHormigas();
       AgenteBase hormigaActual;
       
        for (int i=0; i < 16; i++){
            hormigaActual = hormigas[i];
             if (hormigaActual.fila == fila  && hormigaActual.columna == columna){
                 if (hormigaActual.estadoRecurso){
                     this.filaRecurso = hormigaActual.filaRecurso;
                     this.columnaRecurso = hormigaActual.columnaRecurso;
                 }
             }
        }
    }
    
    public void llegoAPosicionRecurso(){
    
        if (fila == filaRecurso && columna == columnaRecurso){
            filaRecurso = 0;
            columnaRecurso = 0;
        }
    }
    
    public int llevaRecurso(int direccion){
        
     
        switch (direccion) {
            case 1: // Arriba
                if (Ventana.tablero[fila-1][columna].getBackground().equals(Recursos.fondoRecurso)){
                    return direccion;
                }
                break;
             
            case 2: // Abajo
                if (Ventana.tablero[fila+1][columna].getBackground().equals(Recursos.fondoRecurso)){
                    return direccion;
                }
                break;
                
            case 3: // Derecha
                if (Ventana.tablero[fila][columna+1].getBackground().equals(Recursos.fondoRecurso)){
                    return direccion;
                }
                break;
                
            case 4: // Izquierda
                if (Ventana.tablero[fila][columna-1].getBackground().equals(Recursos.fondoRecurso)){
                    return direccion;
                }
                break;
        }
        return 0; 
    } 
}