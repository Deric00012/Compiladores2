/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ioch
 */
public class frmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form frmPrincipal
     */
    public frmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAnalizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Linea","Caracter", "Token", "Valor"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(326, 326, 326)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
    DefaultTableModel modelo =  (DefaultTableModel) jTable2.getModel();
    JFileChooser chooser = new JFileChooser();
    chooser.showOpenDialog(null);
        
        try {
            Reader leer = new BufferedReader(new FileReader(chooser.getSelectedFile()));
            Lexer lexer = new Lexer(leer);

            
            int operadoresAritmetico = 0;
            int operadoresAsignacion = 0;
            int identificadores = 0;
            int operacionesAgrupacion = 0;
            int palabrasReservadas = 0;
            int simbolosDePuntuacion = 0;
            int operadoresDeComparacion = 0;
            int cadena = 0;
            int numeros = 0;
            int decimales = 0;
            int logicos = 0;
            int booleanos = 0;
            int incrementales = 0;
            int errores = 0;
            int indefinido = 0;
            
            
            int ErrorLinea = 1;
            
            while (true) {                
            
                Tokens tokens = lexer.yylex();
                //si es la ultima linea del token, realiza la escritura final
                if (tokens == null){
                    modelo.addRow(new Object[]{"FIN","FIN","FIN"});
                    return;
                }
                
                switch (tokens) {
                    //se valida segun el tipo de token, para retornar una respuesta (aqui esta de forma muy general)
                    case ERROR: case errorIdentificador: case errorEnLinea:
                        errores++;
                        modelo.addRow(new Object[]{ErrorLinea,"ERROR en linea "+ ErrorLinea,"Errores","(N , N-"+errores+")"});
                        break;
                    case INICIO:
                        break;
                    case opSuma: case opResta: case opMultiplicacion: case opDivision:
                        operadoresAritmetico++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Operador Aritmetico","(A , A-"+operadoresAritmetico+")"});
                        break;
                    case opIgual:
                        operadoresAsignacion++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Asignador","(B , B-"+operadoresAsignacion+")"});
                        break;
                    case Identificador:
                        identificadores++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Identificador","(C , C-"+identificadores+")"});
                        break;
                    case aParentesis: case cParentesis: case aLlave: case cLlave: case aCorchete: case cCorchete:
                        operacionesAgrupacion++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Operador de agrupacion","(D , D-"+operacionesAgrupacion+")"});
                        break;
                    case Reservadas:
                        palabrasReservadas++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Reservada","(E , E-"+palabrasReservadas+")"});
                        break;
                    case finLine:
                        simbolosDePuntuacion++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Fin Linea","(F , F-"+simbolosDePuntuacion+")"});
                        ErrorLinea++;
                        break;
                    case sigMayor: case sigMenor: case mayorIgual: case menorIgual: case comparacion: case diferente:
                        operadoresDeComparacion++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Comparador","(G , G-"+operadoresDeComparacion+")"});
                        break;
                    case cadena:
                        cadena++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Cadena","(H , H-"+cadena+")"});
                        break;
                    case Numero:
                        numeros++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Numero","(I , I-"+numeros+")"});
                        break;
                    case Decimal:
                        decimales++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Decimal","(J , J-"+decimales+")"});
                        break;
                    case opOr: case opAnd:
                        logicos++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Operador logico","(K , K-"+logicos+")"});
                        break;
                    case booleano:
                        booleanos++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Booleano","(L , L-"+booleanos+")"});
                        break;
                    case incremento: case decremento: case yoMas: case yoMenos:
                        incrementales++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Incremental","(M , M-"+incrementales+")"});
                        break;
                    default:
                        indefinido++;
                        modelo.addRow(new Object[]{ErrorLinea,lexer.lexeme,"Indefinido","(O , O-"+indefinido+")"});
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(frmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnAnalizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
