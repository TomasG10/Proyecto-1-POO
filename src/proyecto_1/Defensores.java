package proyecto_1;

import java.awt.Color;
import javax.swing.ImageIcon;

public class Defensores extends AgenteBase{

    public Defensores(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        estadoRecurso = false;
        tipoHormiga = new ImageIcon("defensora.jpg");
        fondoHormiga = new Color(255,0,255);
        super.pintarAgente(fila,columna, tipoHormiga, fondoHormiga);
    }

    public static int rangoDefensor(Defensores hormiga ,int radio){
        int fila = hormiga.fila;
        int columna= hormiga.columna;
        int contador;

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
    
    public static int volverABase(Defensores hormiga){
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
    
    public static int hayAmenazaCercaDefensores(int fila, int columna, int radio){
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
        
        // Logica con orden para el movimiento (Defensores) (Se busca que se acerque a la de la amenaza)
        switch (direccion){
            case 1: // Amenaza arriba
                if (comparacionPorDerecha(fila, columna) != 0)// Caso especifico
                    direccion = 3; 
                if (comparacionPorIzquierda(fila, columna) != 0) // Caso especifico
                    direccion = 4; 
                if (comparacionPorArriba(fila, columna) != 0) // Acercarse
                    direccion = 1;
                break;
            
            case 2: // Amenaza abajo
                if (comparacionPorDerecha(fila, columna) != 0)// Caso especifico
                     direccion = 3; 
                if (comparacionPorIzquierda(fila, columna) != 0) // Caso especifico
                    direccion = 4; 
                if (comparacionPorAbajo(fila, columna) != 0) // Acercarse
                    direccion = 2;
                break;
            
            case 3: // Amenaza derecha
                if (comparacionPorArriba(fila, columna) != 0) // Caso especifico
                    direccion = 1;
                if (comparacionPorAbajo(fila, columna) != 0) // Caso especifico
                    direccion = 2;
                if (comparacionPorDerecha(fila, columna) != 0) // Acercarse
                    direccion = 3;
                break;
            
            case 4: // Amenaza izquierda
                if (comparacionPorArriba(fila, columna) != 0) // Caso especifico
                    direccion = 1;
                if (comparacionPorAbajo(fila, columna) != 0) // Caso especifico
                    direccion = 2;
                if (comparacionPorIzquierda(fila, columna) != 0) // Acercarse
                    direccion = 4;
        }
        return direccion;
    }
    
    public static int dirDeAmenaza(int filaHormiga, int columnaHormiga){
        int direccion = 0;
    
        // Revision hacia arriba
        if (filaHormiga != 0){
            if (Ventana.tablero[filaHormiga-1][columnaHormiga].getBackground().equals(Amenazas.fondoAmenaza)){
                direccion = 1;
            }
        }
        
        // Revision hacia abajo
        if (filaHormiga != 49){
            if (Ventana.tablero[filaHormiga+1][columnaHormiga].getBackground().equals(Amenazas.fondoAmenaza)){
                direccion = 2;
            }
        }
        
        // Revision hacia derecha
        if (columnaHormiga != 49){
            if (Ventana.tablero[filaHormiga][columnaHormiga+1].getBackground().equals(Amenazas.fondoAmenaza)){
                direccion = 3;
            }
        }
        
        // Revision hacia izquierda
        if (filaHormiga != 0){
            if (Ventana.tablero[filaHormiga][columnaHormiga-1].getBackground().equals(Amenazas.fondoAmenaza)){
                direccion = 4;
            }
        }
        return direccion;
    }
    
    public static void atacandoAmenaza(int filaHormiga, int columnaHormiga, int direccionDeAtaque){
        
        for (int i=0; i < 7; i++){
            Amenazas amenazaActual = Simulacion.GetlistaAmenazas()[i];
            
            switch(direccionDeAtaque){
                case 1:
                    if (filaHormiga - 1 == amenazaActual.fila && columnaHormiga == amenazaActual.columna){
                        amenazaActual.vida -= 1;
                    }
                    break;
                case 2:
                    if (filaHormiga + 1 == amenazaActual.fila && columnaHormiga == amenazaActual.columna){
                        amenazaActual.vida -= 1;
                    }
                    break;
                case 3:
                    if (filaHormiga == amenazaActual.fila && columnaHormiga + 1 == amenazaActual.columna){
                        amenazaActual.vida -= 1;
                    }
                    break;
                case 4:
                    if (filaHormiga == amenazaActual.fila && columnaHormiga - 1 == amenazaActual.columna){
                        amenazaActual.vida -= 1;
                    }
                    break;
            }
            
            // Eliminar y Regenerar una nueva amenaza
            if (Amenazas.amenazaEliminada(amenazaActual)){
                int fila = 0;
                int columna = 0;
                
                while(true){
                    fila = (int) (Math.random() * (42-4) + 4);
                    columna= (int) (Math.random() * (47-4) + 4);

                    if (casillaVacia(fila,columna)){
                        Simulacion.GetlistaAmenazas()[i] = new Amenazas(fila, columna);
                        break;
                    }
                }
            }
        }
    }
    
    public static boolean casillaVacia(int fila, int columna){
        return Ventana.tablero[fila][columna].getBackground().equals(Ventana.colorTablero);
    }
}

