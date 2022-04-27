package proyecto_1;
import java.awt.Color;

import javax.swing.ImageIcon;

public class Recolectores extends AgenteBase{

    public Recolectores(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        filaRecurso = 0;
        columnaRecurso = 0;
        estadoRecurso = false;
        tipoHormiga = new ImageIcon("Imagenes/recolectora.png");
        fondoHormiga = new Color(0,255,255);
        super.pintarAgente(fila,columna, tipoHormiga, fondoHormiga);
    } 

    @Override
    public int rangoHormiga(int radio){
 
        int contador;

        // Revision hacia Arriba
        for (contador = 1; contador <= radio; contador++){
            if (fila - contador >= 0){
               if (Ventana.tablero[fila-contador][columna].getBackground().equals(Recursos.fondoRecurso)){
                    return 1;
               }
            }
        }

        // Revision hacia abajo 
        for (contador = 1; contador <= radio; contador++){
            if (fila + contador <= 49){
               if (Ventana.tablero[fila+contador][columna].getBackground().equals(Recursos.fondoRecurso)){
                    return 2;
               }
            }
        }

        // Revision hacia derecha 
        for (contador = 1; contador <= radio; contador++){
            if (columna + contador <= 49){
               if (Ventana.tablero[fila][columna+contador].getBackground().equals(Recursos.fondoRecurso)){
                    return 3;
               }
            }
        }

        // Revision hacia izquierda 
        for (contador = 1; contador <= radio; contador++){
            if (columna - contador >= 0){
               if (Ventana.tablero[fila][columna-contador].getBackground().equals(Recursos.fondoRecurso)){
                    return 4;
               }
            }
        }
        return 0; // Caso en el que el movimiento no se ve afectado
    }
    
    @Override
    public void comportamientoHormiga(){
        boolean llevaraRecurso = false;

        if (estadoRecurso && Recolectores.llegoABase(fila, columna)){
             estadoRecurso = false;
             filaRecurso = 0;
             columnaRecurso = 0;
        }

        int dirCasoAmenaza = hayAmenazaCerca(3);

        // Caso en el que no se detecta amenaza 
        if (dirCasoAmenaza == 0){
            if ( !(estadoRecurso) && llevaRecurso(rangoHormiga(3)) != 0){
                llevaraRecurso = true;
            }        
        }
        // Los diferentes tipos de movimiento
        if (dirCasoAmenaza != 0 && estadoRecurso == false){  // Huir de Amenazas
            moverAgente(dirCasoAmenaza);
            if(filaRecurso != 0 && columnaRecurso != 0){
                filaRecurso = 0; 
                columnaRecurso = 0; 
            }
        }
        else if (estadoRecurso){ // Volver a base
            moverAgente(volverABase());
        }
        else { 
            detectarHormigaConRecurso(3);
            if (rangoHormiga(3) != 0){ // Direccion afectada por un recurso
                moverAgente(rangoHormiga(3));
            }
            else if (filaRecurso != 0 && columnaRecurso != 0){ // Ruta en busqueda de un recurso del cual venia otra hormiga
                moverAgente(irHaciaRecurso());       
                llegoAPosicionRecurso();
            }
            else{ // Movimiento random (No afectado por ningun factor)
                moverAgente(0);
            }
        }

        if (llevaraRecurso){
            estadoRecurso = true;
            int direccionDeRecurso = llevaRecurso(rangoHormiga(3)); 
            
            switch (direccionDeRecurso) {
                case 1: // Arriba
                    filaRecurso = fila - 1;
                    columnaRecurso = columna;
                    break;
                    
                case 2: // Abajo
                    filaRecurso = fila + 1;
                    columnaRecurso = columna;
                    break;

                case 3: // Derecha
                    filaRecurso = fila;
                    columnaRecurso = columna + 1;
                    break;

                case 4: // Izquierda
                    filaRecurso = fila;
                    columnaRecurso = columna - 1;
                    break;
            }
            Simulacion.SetContador(Simulacion.GetContador()+1);
        }
    }
 
    @Override
    public int hayAmenazaCerca(int radio){
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
}