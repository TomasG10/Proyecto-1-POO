package proyecto_1;

import java.awt.Color;
import javax.swing.ImageIcon;

public class Defensores extends AgenteBase{

    public Defensores(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        filaRecurso = 0;
        columnaRecurso = 0;
        estadoRecurso = false;
        tipoHormiga = new ImageIcon("Imagenes/defensora.jpg");
        fondoHormiga = new Color(255,0,255);
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
    public int hayAmenazaCerca(int radio){
        int contador;
        int direccion = 0;
           
        // Revision hacia Arriba
        for (contador = 1; contador <= radio; contador++){
            if (fila - contador >= 0){
               if (Ventana.tablero[fila-contador][columna].getBackground().equals(Amenazas.fondoAmenaza)){
                    direccion = 1;
               }
            }
        }

        // Revision hacia abajo 
        for (contador = 1; contador <= radio; contador++){
            if (fila + contador <= 49){
               if (Ventana.tablero[fila+contador][columna].getBackground().equals(Amenazas.fondoAmenaza)){
                    direccion = 2;
               }
            }
        }

        // Revision hacia derecha 
        for (contador = 1; contador <= radio; contador++){
            if (columna + contador <= 49){
               if (Ventana.tablero[fila][columna+contador].getBackground().equals(Amenazas.fondoAmenaza)){
                    direccion = 3;
               }
            }
        }

        // Revision hacia izquierda 
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
    
    public int dirDeAmenaza(){
        int direccion = 0;
    
        // Revision hacia arriba
        if (fila != 0){
            if (Ventana.tablero[fila-1][columna].getBackground().equals(Amenazas.fondoAmenaza)){
                direccion = 1;
            }
        }
        
        // Revision hacia abajo
        if (fila != 49){
            if (Ventana.tablero[fila+1][columna].getBackground().equals(Amenazas.fondoAmenaza)){
                direccion = 2;
            }
        }
        
        // Revision hacia derecha
        if (fila != 49){
            if (Ventana.tablero[fila][columna+1].getBackground().equals(Amenazas.fondoAmenaza)){
                direccion = 3;
            }
        }
        
        // Revision hacia izquierda
        if (fila != 0){
            if (Ventana.tablero[fila][columna-1].getBackground().equals(Amenazas.fondoAmenaza)){
                direccion = 4;
            }
        }
        return direccion;
    }
    
    public void atacandoAmenaza(int direccionDeAtaque){

        for (int i=0; i < 7; i++){ 
            Amenazas amenazaActual = Simulacion.GetlistaAmenazas()[i];

            switch(direccionDeAtaque){
                case 1:
                    if (fila - 1 == amenazaActual.fila && columna == amenazaActual.columna){
                        amenazaActual.vida -= 1;
                    }
                    break;
                case 2:
                    if (fila + 1 == amenazaActual.fila && columna == amenazaActual.columna){
                        amenazaActual.vida -= 1;
                    }
                    break;
                case 3:
                    if (fila == amenazaActual.fila && columna + 1 == amenazaActual.columna){
                        amenazaActual.vida -= 1;
                    }
                    break;
                case 4:
                    if (fila == amenazaActual.fila && columna - 1 == amenazaActual.columna){
                        amenazaActual.vida -= 1;
                    }
                    break;
            }

            // Eliminar y Regenerar una nueva amenaza
            if (amenazaActual.amenazaEliminada()){

                while(true){
                    int nuevaFila = (int) (Math.random() * (42-9) + 9);
                    int nuevaColumna= (int) (Math.random() * (40-9) + 9);

                    if (Amenazas.hayEspacioAmenazas(nuevaFila, nuevaColumna)){
                        Simulacion.GetlistaAmenazas()[i] = new Amenazas(nuevaFila, nuevaColumna);
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public void comportamientoHormiga(){
        boolean llevaraRecurso = false;

        if (estadoRecurso && Defensores.llegoABase(fila, columna)){
            estadoRecurso = false;
            filaRecurso = 0;
             columnaRecurso = 0;
        }

        int dirCasoAmenazaD = hayAmenazaCerca(3);

        if (dirCasoAmenazaD == 0){
            if ( !(estadoRecurso) && llevaRecurso(rangoHormiga(3)) !=0){
                llevaraRecurso = true;
            }        
        }

        // Tipos de movimiento
        if (dirCasoAmenazaD != 0 && estadoRecurso == false){  // Acercarse a amenaza

            if (dirDeAmenaza() != 0)
                atacandoAmenaza(dirDeAmenaza());

            moverAgente(dirCasoAmenazaD);
        }
        else if (estadoRecurso){ // Volver a base con recurso
            moverAgente(volverABase());
        }
        else{
            detectarHormigaConRecurso(3); 
            if (rangoHormiga(3) != 0){ // Movimiento afectado por un recurso cercano
                moverAgente(rangoHormiga(3));
            }
            else if (filaRecurso != 0 && columnaRecurso != 0){ // Hormiga acercandose a un recurso del cual venia otra hormiga
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
        llevaraRecurso = false;

        if (Simulacion.GetContador() == 10){
            Recursos generarRecursos = new Recursos(1);
            Simulacion.SetContador(0);
        }
    } 

    public static boolean casillaVacia(int fila, int columna){
        return Ventana.tablero[fila][columna].getBackground().equals(Ventana.colorTablero);
    }
}

