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
    private static Recolectores[] listaRecolectores;
    private static Defensores[] listaDefensores;
    private static Amenazas[] listaAmenazas;
    
    
    public Simulacion(){
        listaRecolectores = new Recolectores[8];
        listaDefensores = new Defensores[8];
        listaAmenazas = new Amenazas[7];
        Prueba = new Ventana();
        generarAmenazas();
        crearRecolectores();
        crearDefensores();
        generarBase = new Base();
        generarObstaculos = new Obstaculos(12);
        generarRecursos = new Recursos(6);
        System.out.println("Fila " + listaDefensores[0].fila + "Columna " + listaDefensores[0].columna); // BORRAR
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
                listaRecolectores[contador] = new Recolectores(fila,columna);
                contador ++;
            }
        }
    }
    
    private void crearDefensores (){
        int contador = 0;

        while(contador < 8){
            int fila = (int) (Math.random()*(49-1) + 1);
            int columna = (int) (Math.random()*(49-1) + 1);

            if (verificarEspacio(fila,columna)){
                listaDefensores[contador] = new Defensores(fila,columna);
                contador ++;
            }
        }
    }
    
    private void generarAmenazas() {
        int contador = 0;
        int fila = 0;
        int columna = 0;
        
        while (contador < 7){
            fila = (int) (Math.random() * (42-4) + 4);
            columna= (int) (Math.random() * (47-4) + 4);

            if (verificarEspacio(fila,columna)){
                listaAmenazas[contador] = new Amenazas(fila, columna);
                contador ++;
            }
        }
    }

    public static Recolectores[] GetlistaRecolectores(){
        return listaRecolectores;
    }
    
    public static Defensores[] GetlistaDefensores(){
        return listaDefensores;
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
