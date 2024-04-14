package Inicio;

import javax.swing.JFrame;
//Nombre, Edad, Peso, Altura, Indice de masa corporal
//opciones de buscar, nuevo, guardar, anterior, siguiente y indicador de pagina
import inicioSesion.interfaz;

public class demo extends JFrame {
   public static void main(String[] args) {
      interfaz test = new interfaz();
      GUI panel = new GUI();
        test.run();
        while (test.isVisible()) {
            System.out.print("");
            if (test.acc) {
                System.out.println("");
                panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                panel.setSize(600, 400);
                panel.setVisible(true);
                panel.setResizable(false);
            }
        }
   }
}
