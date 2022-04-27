package proyecto_1;

public class Simulacion {
    private Ventana Prueba;
    private Base generarBase;
    private Objetos generarObstaculos;
    private Objetos generarRecursos;
    private AgenteBase recolectoras;
    private AgenteBase defensoras;
    private Amenazas amenazas;
    private static int contadorRecursos = 0;
    private static AgenteBase[] listaHormigas;
    private static Amenazas[] listaAmenazas;
    
    
    public Simulacion(){
        listaHormigas = new AgenteBase[16];
        listaAmenazas = new Amenazas[7];
        Prueba = new Ventana();
        generarBase = new Base();
        generarObstaculos = new Obstaculos(12);
        generarRecursos = new Recursos(6);
        generarAmenazas();
        crearRecolectores();
        crearDefensores();
    }
    
    public boolean verificarEspacio(int fila, int columna){
        return Ventana.tablero[fila][columna].getBackground().equals(Ventana.colorTablero);
    }
    
    private void crearRecolectores (){
        int contador = 0;

        while(contador < 8){
            int fila = (int) (Math.random()*(49-1) + 1);
            int columna = (int) (Math.random()*(49-1) + 1);

            if (verificarEspacio(fila,columna)){
                listaHormigas[contador] = new Recolectores(fila,columna);
                contador ++;
            }
        }
    }
    
    private void crearDefensores (){
        int contador = 8;

        while(contador < 16){
            int fila = (int) (Math.random()*(49-1) + 1);
            int columna = (int) (Math.random()*(49-1) + 1);

            if (verificarEspacio(fila,columna)){
                listaHormigas[contador] = new Defensores(fila,columna);
                contador ++;
            }
        }
    }
    
    private void generarAmenazas() {
        int contador = 0;
        int fila = 0;
        int columna = 0;
        
        while (contador < 7){
            fila = (int) (Math.random() * (42-9) + 9);
            columna= (int) (Math.random() * (40-9) + 9);

            if (Amenazas.hayEspacioAmenazas(fila,columna)){
                listaAmenazas[contador] = new Amenazas(fila, columna);
                contador ++;
            }
        }
    }
    
    public static void recorrerListaHormigas(){
        for (int posicion = 0; posicion < 16; posicion++){
            listaHormigas[posicion].comportamientoHormiga();
        }
    }

    public static AgenteBase[] GetlistaHormigas(){
        return listaHormigas;
    }
    
    public static Amenazas[] GetlistaAmenazas(){
        return listaAmenazas;
    }
    
    public static int GetContador(){
        return contadorRecursos;
    }
    
    public static void SetContador(int numero){
        contadorRecursos = numero;
    }
}
