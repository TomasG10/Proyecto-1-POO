package proyecto_1;
import java.awt.Color;

import javax.swing.ImageIcon;

public class Recolectores extends AgenteBase{

    public Recolectores(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.alejarHormiga = 10;
        estadoRecurso = false;
        tipoHormiga = new ImageIcon("recolectora.png");
        fondoHormiga = new Color(0,255,255);
        super.pintarAgente(fila,columna, tipoHormiga, fondoHormiga);
    } 

    // VER SI SE PUEDE HACER LA HERENCIA
    @Override
    public int rangoHormiga(int radio){
 
        int contador;
        
        if (estadoRecurso == false){
            
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
    
    @Override
    public void recorrerListaHormigas(){
        boolean llevaraRecurso = false;
        Recolectores recolectoraActual;
        Recolectores[] listaRecolectores = Simulacion.GetlistaRecolectores();

        
        // Recorrer lista Recolectores 
        for (int posicion=0; posicion < 8; posicion++){
            recolectoraActual = listaRecolectores[posicion];
            
            if (recolectoraActual.estadoRecurso && Recolectores.llegoABase(recolectoraActual.fila, recolectoraActual.columna)){
                 recolectoraActual.estadoRecurso = false;
                 //contador aca
            }

            int dirCasoAmenaza = recolectoraActual.hayAmenazaCerca(3);
            
            // Caso en el que no se detecta amenaza (Prestarle atencion a este caso) (Cerca de recursos generaba un fallo)
            if (dirCasoAmenaza == 0){
                if ( !(recolectoraActual.estadoRecurso) && recolectoraActual.llevaRecurso(recolectoraActual.rangoHormiga(3))){
                    llevaraRecurso = true;
                }        
            }
            // Los 3 tipos de movimiento
            if (dirCasoAmenaza != 0 && recolectoraActual.estadoRecurso == false){  // Huir de Amenazas
                recolectoraActual.moverAgente(dirCasoAmenaza);
            }
            else if (recolectoraActual.estadoRecurso){ // Volver a base
                recolectoraActual.moverAgente(recolectoraActual.volverABase());
            }
            else{ // Buscando recursos // Error talvez aca
                recolectoraActual.moverAgente(recolectoraActual.rangoHormiga(3));
            }
                   
            if (llevaraRecurso){
                recolectoraActual.estadoRecurso = true;
                Simulacion.SetContador(Simulacion.GetContador()+1);
            }
            llevaraRecurso = false;
    }}
    

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