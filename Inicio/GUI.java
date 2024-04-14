package Inicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class GUI extends JFrame {
   private final JPanel Navegacion;
   private final JPanel Texto;
   private final JPanel Informacion;
   private final JPanel Paginacion;
   public final JButton[] buttons;
   public final JLabel[] Labels;
   public final JTextField[] Text;
   public String nombre, edad, altura, peso, IMC;
   private int posicion = 0, numero = 0;
   private String usuarios[];
   private String datos[];

   public GUI() {
      super("Panel de captura de datos");
      setLayout(null);
      Navegacion = new JPanel();
      Texto = new JPanel();
      Informacion = new JPanel();
      Paginacion = new JPanel();
      buttons = new JButton[6];
      Labels = new JLabel[6];
      Text = new JTextField[6];
      usuarios = new String[200];
      datos = new String[5];

      Texto.setBounds(0, 0, 100, 200);
      Texto.setLayout(null);
      Texto.setBackground(Color.BLUE);

      Informacion.setBounds(100, 0, 300, 200);
      Informacion.setLayout(null);
      Informacion.setBackground(Color.YELLOW);

      Navegacion.setBounds(0, 200, 700, 200);
      Navegacion.setLayout(null);
      Navegacion.setBackground(Color.RED);

      Paginacion.setBounds(400, 0, 300, 200);
      Paginacion.setLayout(null);
      Paginacion.setBackground(Color.PINK);

      try (FileReader fr = new FileReader("Lista.csv")) {
         BufferedReader br = new BufferedReader(fr);
         String linea;
         int users = 0;
         while ((linea = br.readLine()) != null) {
            users++;
            numero++;
            usuarios[users] = linea;
         }
      } catch (Exception o) {
         o.printStackTrace();
      }

      // Labels
      Labels[0] = new JLabel("Nombre: ");
      Labels[1] = new JLabel("Edad: ");
      Labels[2] = new JLabel("Peso: ");
      Labels[3] = new JLabel("Altura: ");
      Labels[4] = new JLabel("IMC: ");
      Labels[5] = new JLabel(posicion + "/" + numero);

      // buttons
      buttons[0] = new JButton("Buscar");
      buttons[1] = new JButton("Nuevo");
      buttons[2] = new JButton("Guardar");
      buttons[3] = new JButton("<");
      buttons[4] = new JButton(">");
      buttons[5] = new JButton("Calcular IMC");

      // Fields
      Text[1] = new JTextField();
      Text[2] = new JTextField();
      Text[3] = new JTextField();
      Text[4] = new JTextField();
      Text[5] = new JTextField();

      int x = 30;
      int y = 30;
      // Estilos de Labels
      for (int i = 0; i < 5; i++) {
         Labels[i].setFont(new Font("Serif", Font.PLAIN, 20));
         Labels[i].setBounds(x, y, 100, 20);
         y += 30;
         Texto.add(Labels[i]);
      }
      buttons[5].setBounds(50, 180, 120, 20);
      Informacion.add(buttons[5]);

      x = 50;
      y = 35;
      // creacion y Estilos de Fields
      for (int i = 0; i < 5; i++) {
         Text[i] = new JTextField();
         Text[i].setBounds(x, y, 200, 20);
         y += 30;
         Informacion.add(Text[i]);
      }

      x = 320;
      y = 50;
      for (int i = 1; i < 3; i++) {
         buttons[i].setBounds(x, 50, 100, 30);
         x += 100;
         Navegacion.add(buttons[i]);
      }

      Labels[5].setBounds(0, 20, 40, 20);
      buttons[0].setBounds(50, 50, 100, 30);
      Text[5].setBounds(150, 50, 170, 30);
      buttons[3].setBounds(50, 100, 45, 40);
      buttons[4].setBounds(50, 60, 45, 40);
      Paginacion.add(Labels[5]);
      Navegacion.add(Text[5]);
      Navegacion.add(buttons[0]);
      Paginacion.add(buttons[3]);
      Paginacion.add(buttons[4]);

      // Accion de calcular IMC
      buttons[5].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (!Text[2].getText().isEmpty() || !Text[3].getText().isEmpty()) {
               Text[4].setText("" + ((Integer.parseInt(Text[2].getText()))
                     / (((Integer.parseInt(Text[3].getText())) / 100) ^ 2)));
            }
         }
      });

      // Accion de Nuevo
      buttons[1].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            posicion = 0;
            Labels[5].setText(posicion + "/" + numero);
            for (int i = 0; i < 5; i++) {
               Text[i].setText("");
            }
         }
      });

      // Accion de Guardar
      buttons[2].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            nombre = Text[0].getText();
            if (!nombre.isEmpty()) {
               edad = Text[1].getText();
               peso = Text[2].getText();
               altura = Text[3].getText();
               IMC = Text[4].getText();
               try {
                  String ruta = "Lista.csv";
                  String contenido = nombre + ", " + edad + ", " +
                        peso + ", " + altura + ", " + IMC + "\n";
                  File file = new File(ruta);
                  if (!file.exists()) {
                     file.createNewFile();
                  }
                  FileWriter fw = new FileWriter(file, true);
                  BufferedWriter bw = new BufferedWriter(fw);
                  bw.write(contenido);
                  bw.close();
                  numero++;
                  Labels[5].setText(posicion + "/" + numero);
               } catch (Exception p) {
                  p.printStackTrace();
               }
               try (FileReader fr = new FileReader("Lista.csv")) {
                  BufferedReader br = new BufferedReader(fr);
                  String linea;
                  int users = 0;
                  while ((linea = br.readLine()) != null) {
                     users++;
                     usuarios[users] = linea;
                  }
               } catch (Exception o) {
                  o.printStackTrace();
               }
            }
         }
      });

      // Accion de Buscar
      buttons[0].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            for (int j = 1; j <= numero; j++) {
               datos = usuarios[j].split(",");
               if (Text[5].getText().contains(datos[0])) {
                  for (int i = 0; i < 5; i++) {
                     Text[i].setText(datos[i]);
                  }
                  break;
               }
            }
         }
      });

      // Accion indice hacia adelante
      buttons[4].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (posicion + 1 <= numero) {
               posicion++;
               Labels[5].setText(posicion + "/" + numero);
               datos = usuarios[posicion].split(",");
               for (int i = 0; i < 5; i++) {
                  Text[i].setText(datos[i]);
               }
            }
         }
      });
      // Accion indice hacia atras
      buttons[3].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (posicion - 1 > 0) {
               posicion--;
               Labels[5].setText(posicion + "/" + numero);
               datos = usuarios[posicion].split(",");
               for (int i = 0; i < 5; i++) {
                  Text[i].setText(datos[i]);
               }
            }
         }
      });

      add(Texto);
      add(Navegacion);
      add(Informacion);
      add(Paginacion);
   }
}
