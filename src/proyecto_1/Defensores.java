package proyecto_1;

import java.awt.Color;
import javax.swing.ImageIcon;

public class Defensores extends AgenteBase{

    public Defensores(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.alejarHormiga = 10;
        estadoRecurso = false;
        tipoHormiga = new ImageIcon("defensora.jpg");
        fondoHormiga = new Color(255,0,255);
        super.pintarAgente(fila,columna, tipoHormiga, fondoHormiga);
    }
    
    
    // VER SI SE PUEDE HACER LA HERENCIA
    @Override
    public  int rangoHormiga(int radio){
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
            if (Amenazas.amenazaEliminada(amenazaActual)){
                
                while(true){
                    int nuevaFila = (int) (Math.random() * (42-4) + 4);
                    int nuevaColumna= (int) (Math.random() * (47-4) + 4);

                    if (casillaVacia(nuevaFila,nuevaColumna)){
                        Simulacion.GetlistaAmenazas()[i] = new Amenazas(nuevaFila, nuevaColumna);
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public void recorrerListaHormigas(){
        boolean llevaraRecurso = false;
        Defensores defensoraActual;
        Defensores[] listaDefensores = Simulacion.GetlistaDefensores();
           
        // Recorrer lista Defensores
        for (int posicion=0; posicion < 8; posicion++){
            defensoraActual = listaDefensores[posicion];
            
            if (defensoraActual.estadoRecurso && Defensores.llegoABase(defensoraActual.fila, defensoraActual.columna)){
                 defensoraActual.estadoRecurso = false;
                 //contadoraca
            }
            
            int dirCasoAmenazaD = defensoraActual.hayAmenazaCerca(3);
            
            if (dirCasoAmenazaD == 0){
                if ( !(defensoraActual.estadoRecurso) && defensoraActual.llevaRecurso(defensoraActual.rangoHormiga(3))){
                    llevaraRecurso = true;
                }        
            }

            // Tipos de movimiento
            if (dirCasoAmenazaD != 0 && defensoraActual.estadoRecurso == false){  // Acercarse a amenaza
                
                if (defensoraActual.dirDeAmenaza() != 0)
                    defensoraActual.atacandoAmenaza(defensoraActual.dirDeAmenaza());
                
                defensoraActual.moverAgente(dirCasoAmenazaD);
            }
            else if (defensoraActual.estadoRecurso){
                defensoraActual.moverAgente(defensoraActual.volverABase());
                
            }
            else{
                defensoraActual.moverAgente(defensoraActual.rangoHormiga(3)); // ARREGLAR EL DE MOVER AGENTE
            }
            
            if (llevaraRecurso){
                defensoraActual.estadoRecurso = true;
                Simulacion.SetContador(Simulacion.GetContador()+1);
            }
            llevaraRecurso = false;
        }
        
        if (Simulacion.GetContador() == 10){
            Recursos generarRecursos = new Recursos(1);
            Simulacion.SetContador(0);
        }
    } 
            
            
    public static boolean casillaVacia(int fila, int columna){
        return Ventana.tablero[fila][columna].getBackground().equals(Ventana.colorTablero);
    }
}

