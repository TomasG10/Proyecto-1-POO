package proyecto_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static Color colorFondo = new java.awt.Color(160,160,160);
    public static Color colorTablero = new java.awt.Color(238,183,29);
    public static ImageIcon fondo = new ImageIcon("Imagenes/tierra.jpg") ;
    
    public static JButton tablero[][] = new JButton[50][50];

    public Ventana(){
        ventana = new JFrame("Enjambre");
        matriz = new JPanel();
        panelBoton = new JPanel();
        btnSiguiente = new JButton("Siguiente");
        
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == btnSiguiente){
            Simulacion.recorrerListaHormigas();
        }
    }
}
  
