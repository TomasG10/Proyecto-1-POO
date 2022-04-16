package proyecto_1;

public class Simulacion {
    private Ventana Prueba;
    private Base generarBase;
    private Objetos generarObstaculos;
    private Objetos generarRecursos;
    private AgenteBase recolectoras;
    private AgenteBase defensoras;
    private Amenazas amenazas;
    private static Recolectores[] listaRecolectores;
    private static Defensores[] listaDefensores;
    
    
    public Simulacion(){
        listaRecolectores = new Recolectores[8];
        listaDefensores = new Defensores[8];
        Prueba = new Ventana();
        crearRecolectores();
        crearDefensores();
        generarBase = new Base();
        generarRecursos = new Recursos();
        generarObstaculos = new Obstaculos();
        amenazas = new Amenazas();
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
    
    public static Recolectores[] GetlistaRecolectores(){
        return listaRecolectores;
    }
    
    public static Defensores[] GetlistaDefensores(){
        return listaDefensores;
    }
}
