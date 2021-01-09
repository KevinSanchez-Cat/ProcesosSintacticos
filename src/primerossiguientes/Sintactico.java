/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primerossiguientes;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author por_s
 */
public class Sintactico extends javax.swing.JFrame {

    public static Procesos pcs;

    /**
     * Creates new form Sintactico
     */
    public Sintactico() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("ProcesosSintacticos");
        // this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        pcs = null;
        //Bontones
        this.btnGramatica.setEnabled(true);
        this.btnPrimeros.setEnabled(false);
        this.btnSiguientes.setEnabled(false);
        this.btnAmbiguedad.setEnabled(false);
        this.btnTerminar.setEnabled(false);
        this.btnTabla.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnGramatica = new javax.swing.JButton();
        btnPrimeros = new javax.swing.JButton();
        btnSiguientes = new javax.swing.JButton();
        btnAmbiguedad = new javax.swing.JButton();
        btnTerminar = new javax.swing.JButton();
        btnTabla = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnGramatica.setText("Cargar Gramatica separada");
        btnGramatica.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGramatica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGramaticaActionPerformed(evt);
            }
        });
        getContentPane().add(btnGramatica, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 190, -1));

        btnPrimeros.setText("Buscar primeros");
        btnPrimeros.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPrimeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimerosActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrimeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, 150, -1));

        btnSiguientes.setText("Buscar siguientes");
        btnSiguientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSiguientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnSiguientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 470, 130, -1));

        btnAmbiguedad.setText("Buscar ambiguedades");
        btnAmbiguedad.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAmbiguedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmbiguedadActionPerformed(evt);
            }
        });
        getContentPane().add(btnAmbiguedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, 160, -1));

        btnTerminar.setText("Terminar");
        btnTerminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnTerminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, 90, -1));

        btnTabla.setText("Llenar tabla predictiva");
        btnTabla.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablaActionPerformed(evt);
            }
        });
        getContentPane().add(btnTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 470, 150, 20));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 920, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAmbiguedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbiguedadActionPerformed
        pcs.procesoAmbieguedad();
        Mensaje.exito(this, "Proceso realizado con exito");
        this.btnGramatica.setEnabled(true);
        this.btnPrimeros.setEnabled(true);
        this.btnSiguientes.setEnabled(true);
        this.btnAmbiguedad.setEnabled(true);
        this.btnTerminar.setEnabled(true);
        this.btnTabla.setEnabled(false);
    }//GEN-LAST:event_btnAmbiguedadActionPerformed

    private void btnGramaticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGramaticaActionPerformed

        try {
            JFileChooser selecciona = new JFileChooser();  //crea objeto file chooser
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo .txt", "txt", "txt");  //solo muestra archivos .mp3 en file chocer
            selecciona.setFileFilter(filtro);  //se agrega el filtro al file chooser
            selecciona.showOpenDialog(this);   //sin esto me marcaba error :v
            File archivoExtraido = selecciona.getSelectedFile(); //obtiene el archivo en la variable archivo
            String url; //borra la ruta para evitar que se quede una anterior

            String etiquetaNombre;
            if (archivoExtraido != null) // si no se selecciono ningun archivo no hace nada
            {

                url = archivoExtraido.getCanonicalPath(); //obtiene la ruta del archivo
                etiquetaNombre = archivoExtraido.getName();//obtiene el nombre del archivo/cancion                

                String s = "";
                ArrayList<String> ar = ManipulaArchivos.cargar(url);

                if (ar != null) {
                    for (int i = 0; i < ar.size(); i++) {
                        s += ar.get(i);
                    }
                    //Escribiendo el texto en el editor

                    JTextPane txt = new JTextPane();
                    jScrollPane1.setViewportView(txt);
                    txt.setText(s);
                    Mensaje.exito(this, "Archivo extraido con exito");
                    this.btnGramatica.setEnabled(true);
                    this.btnPrimeros.setEnabled(true);
                    this.btnSiguientes.setEnabled(false);
                    this.btnAmbiguedad.setEnabled(false);
                    this.btnTerminar.setEnabled(false);
                    this.btnTabla.setEnabled(false);
                    pcs = new Procesos(url);
                } else {
                    Mensaje.advertencia(this, "No se ha extraido el archivo por cancelacion");
                }

            }

        } catch (HeadlessException | IOException e) {
            Mensaje.error(this, "Ha ocurrido un error al abrir el archivo");
        }


    }//GEN-LAST:event_btnGramaticaActionPerformed

    private void btnPrimerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimerosActionPerformed
        pcs.procesoPrimeros();
        Mensaje.exito(this, "Proceso realizado con exito");
        this.btnGramatica.setEnabled(true);
        this.btnPrimeros.setEnabled(true);
        this.btnSiguientes.setEnabled(true);
        this.btnAmbiguedad.setEnabled(false);
        this.btnTerminar.setEnabled(false);
        this.btnTabla.setEnabled(false);
    }//GEN-LAST:event_btnPrimerosActionPerformed

    private void btnSiguientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguientesActionPerformed
        String inicio = JOptionPane.showInputDialog(this, "Introduce el objeto raiz de la gramatica", "Inicio", 1);
        if (inicio != null) {
            if (inicio.isEmpty()) {
                Mensaje.advertencia(this, "No se ha introducido ningun valor ");
            } else {

                if (pcs.procesoSiguientes(inicio)) {
                    Mensaje.exito(this, "Proceso realizado con exito");
                    this.btnGramatica.setEnabled(true);
                    this.btnPrimeros.setEnabled(true);
                    this.btnSiguientes.setEnabled(true);
                    this.btnAmbiguedad.setEnabled(true);
                    this.btnTerminar.setEnabled(false);
                    this.btnTabla.setEnabled(false);
                } else {
                    Mensaje.advertencia(this, "La entrada no aparece en los registros, por favor verifique el dato ");
                }
            }
        }


    }//GEN-LAST:event_btnSiguientesActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed

        javax.swing.JTable tablaLexemas = new javax.swing.JTable();

        tablaLexemas.setFont(new java.awt.Font("Arial", 0, 14));

        tablaLexemas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        //
        //come

        tablaLexemas.getTableHeader().setReorderingAllowed(false);
        tablaLexemas.setRowHeight(20);

        tablaLexemas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        DefaultTableModel model;
        String[] columnas = {"Objeto No terminal", "Primeros", "Siguientes", "Ambiguedad"};
        model = new DefaultTableModel(null, columnas);
        String[] filas = new String[4];

        for (int i = 0; i < pcs.getObjPyS().size(); i++) {
            //textoMostrar += " " +  + "\t" +  + "\t" + lexemas.get(i).getNumToken() + "\t" + lexemas.get(i).getRenglon() + "\n";

            filas[0] = pcs.getObjPyS().get(i).getObjNT();
            filas[1] = pcs.getObjPyS().get(i).getPrimeros();
            filas[2] = pcs.getObjPyS().get(i).getSiguientes();
            filas[3] = String.valueOf(pcs.getObjPyS().get(i).isAmbiguedad());

            model.addRow(filas);
        }
        tablaLexemas.setModel(model);

        jScrollPane1.setViewportView(tablaLexemas);

        this.btnGramatica.setEnabled(true);
        this.btnPrimeros.setEnabled(true);
        this.btnSiguientes.setEnabled(true);
        this.btnAmbiguedad.setEnabled(true);
        this.btnTerminar.setEnabled(true);
        this.btnTabla.setEnabled(true);
        Mensaje.exito(this, "Proceso terminado con exito");
    }//GEN-LAST:event_btnTerminarActionPerformed

    private void btnTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablaActionPerformed
        this.btnGramatica.setEnabled(true);
        this.btnPrimeros.setEnabled(true);
        this.btnSiguientes.setEnabled(true);
        this.btnAmbiguedad.setEnabled(true);
        this.btnTerminar.setEnabled(true);
        this.btnTabla.setEnabled(true);
    }//GEN-LAST:event_btnTablaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAmbiguedad;
    private javax.swing.JButton btnGramatica;
    private javax.swing.JButton btnPrimeros;
    private javax.swing.JButton btnSiguientes;
    private javax.swing.JButton btnTabla;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}