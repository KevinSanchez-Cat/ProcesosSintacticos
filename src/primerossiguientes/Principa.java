/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerossiguientes;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author por_s
 */
public class Principa extends JFrame {

    public static void main(String[] args) {

        try {
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            }
            Sintactico s = new Sintactico();
            s.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error fatal", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }
}
