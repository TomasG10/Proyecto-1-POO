package proyecto_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Ventana implements ActionListener{
    private JFrame ventana;
    private JPanel matriz;
    private JPanel panelBoton;
    private JButton btnSiguiente;
    public Defensores[] listaDefensores; 
    public Recolectores[] listaRecolectores; 
    public static Color colorTablero = new java.awt.Color(238,183,29);
    private static Color colorFondo = new java.awt.Color(160,160,160);
    public static ImageIcon fondo = new ImageIcon("tierra.jpg") ;
    
    public static JButton tablero[][] = new JButton[50][50];

    public Ventana(){
        ventana = new JFrame("Enjambre");
        matriz = new JPanel();
        panelBoton = new JPanel();
        btnSiguiente = new JButton("Siguiente");
        
        listaRecolectores = Simulacion.GetlistaRecolectores();
        listaDefensores = Simulacion.GetlistaDefensores();
        
        matriz.setLayout(new GridLayout(50,50));
        matriz.setBackground(colorFondo);
        matriz.setBorder(new LineBorder(Color.BLACK));
        ventana.add(matriz);
        
        btnSiguiente.addActionListener(this);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crearTablero();
    }
    
    private void crearTablero(){
        for (int fila = 0; fila < 50; fila++ ){
            for (int columna = 0; columna < 50; columna++){
                tablero[fila][columna] = new JButton(fondo);
                tablero[fila][columna].setBounds(10,15,400,400);
                tablero[fila][columna].setBorderPainted(false);
                tablero[fila][columna].setBackground(colorTablero);
                matriz.add(tablero[fila][columna]);
                matriz.setVisible(true);
            }
        }
    ventana.setBounds(250, 20, 1000, 800);
    matriz.setBounds(300, 300, 800, 720);
    matriz.setBorder(new LineBorder(Color.BLACK));
    ventana.setVisible(true);
    panelBoton.setLayout(new GridLayout(1, 1));
    panelBoton.add(btnSiguiente);
    ventana.add(panelBoton, BorderLayout.SOUTH);
    }
    
    // ERROR AQUI
    public void moverHormigas(){
        boolean llevaraRecurso = false;
        Recolectores recolectoraActual;
        Defensores defensoraActual;
        
        // Recorrer lista Recolectores 
        for (int posicion=0; posicion < 8; posicion++){
            recolectoraActual = listaRecolectores[posicion];
            
            if (recolectoraActual.estadoRecurso && Recolectores.llegoABase(recolectoraActual.fila, recolectoraActual.columna)){
                 recolectoraActual.estadoRecurso = false;
            }

            int dirCasoAmenaza = Recolectores.hayAmenazaCercaRecolectores(recolectoraActual.fila, recolectoraActual.columna, 3);
            
            // Caso en el que no se detecta amenaza (Prestarle atencion a este caso) (Cerca de recursos generaba un fallo)
            if (dirCasoAmenaza == 0){
                if ( !(recolectoraActual.estadoRecurso) && Recolectores.llevaRecurso(recolectoraActual, Recolectores.rangoRecolector(recolectoraActual, 3))){
                    llevaraRecurso = true;
                }        
            }
            
            // Los 3 tipos de movimiento
            if (dirCasoAmenaza != 0 && recolectoraActual.estadoRecurso == false){  // Huir de Amenazas
                recolectoraActual.moverAgente(recolectoraActual, dirCasoAmenaza);
            }
            else if (recolectoraActual.estadoRecurso){ // Volver a base
                recolectoraActual.moverAgente(recolectoraActual, Recolectores.volverABase(recolectoraActual));
            }
            else{ // Buscando recursos // Error talvez aca
                recolectoraActual.moverAgente(recolectoraActual, Recolectores.rangoRecolector(recolectoraActual, 3));
            }
                   
            if (llevaraRecurso){
                recolectoraActual.estadoRecurso = true;
                Simulacion.SetContador(Simulacion.GetContador()+1);
            }
            llevaraRecurso = false;
        }
        
        
        // Recorrer lista Defensores
        for (int posicion=0; posicion < 8; posicion++){
            defensoraActual = listaDefensores[posicion];
            
            if (defensoraActual.estadoRecurso && Defensores.llegoABase(defensoraActual.fila, defensoraActual.columna)){
                 defensoraActual.estadoRecurso = false;
            }
            
            int dirCasoAmenazaD = Defensores.hayAmenazaCercaDefensores(defensoraActual.fila, defensoraActual.columna, 3);
            
            if (dirCasoAmenazaD == 0){
                if ( !(defensoraActual.estadoRecurso) && Defensores.llevaRecurso(defensoraActual, Defensores.rangoDefensor(defensoraActual, 3))){
                    llevaraRecurso = true;
                }        
            }

            // Tipos de movimiento
            if (dirCasoAmenazaD != 0 && defensoraActual.estadoRecurso == false){  // Acercarse a amenaza
                int fila = defensoraActual.fila;
                int columna = defensoraActual.columna;
                
                if (Defensores.dirDeAmenaza(fila, columna) != 0)
                    Defensores.atacandoAmenaza(fila ,columna, Defensores.dirDeAmenaza(fila, columna));
                
                defensoraActual.moverAgente(defensoraActual, dirCasoAmenazaD);
            }
            else if (!defensoraActual.estadoRecurso){
                defensoraActual.moverAgente(defensoraActual, Defensores.rangoDefensor(defensoraActual, 3));
            }
            else{
                defensoraActual.moverAgente(defensoraActual, Defensores.volverABase(defensoraActual));
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
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == btnSiguiente){
            moverHormigas();
        }
    }
}
  
