/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package br.com.projeto_3.view;

import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class PrincipalVIEW extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalVIEW
     */
    public PrincipalVIEW() {
        initComponents();
    }

  private void sair(){
        Object[] options = {"Sair", "Cancelar"};
        if(JOptionPane.showOptionDialog(null, "Deseja Sair do Sistema", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0){
           System.exit(0);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        itemMenuCliente = new javax.swing.JMenuItem();
        itemMenuFornecedor = new javax.swing.JMenuItem();
        itemMenuProduto = new javax.swing.JMenuItem();
        menuVenda = new javax.swing.JMenu();
        itemMenuVenda = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuCadastro.setMnemonic('f');
        menuCadastro.setText("Cadastro");

        itemMenuCliente.setMnemonic('o');
        itemMenuCliente.setText("Cliente");
        itemMenuCliente.addActionListener(this::itemMenuClienteActionPerformed);
        menuCadastro.add(itemMenuCliente);

        itemMenuFornecedor.setMnemonic('s');
        itemMenuFornecedor.setText("Fornecedor");
        menuCadastro.add(itemMenuFornecedor);

        itemMenuProduto.setMnemonic('a');
        itemMenuProduto.setText("Produto");
        itemMenuProduto.addActionListener(this::itemMenuProdutoActionPerformed);
        menuCadastro.add(itemMenuProduto);

        menuBar.add(menuCadastro);

        menuVenda.setMnemonic('e');
        menuVenda.setText("Venda");

        itemMenuVenda.setMnemonic('t');
        itemMenuVenda.setText("Realizar Venda");
        menuVenda.add(itemMenuVenda);

        menuBar.add(menuVenda);

        menuSair.setMnemonic('h');
        menuSair.setText("Sair");
        menuSair.addActionListener(this::menuSairActionPerformed);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemMenuClienteActionPerformed

    private void itemMenuProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemMenuProdutoActionPerformed

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
    sair();        // TODO add your handling code here:
    }//GEN-LAST:event_menuSairActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itemMenuCliente;
    private javax.swing.JMenuItem itemMenuFornecedor;
    private javax.swing.JMenuItem itemMenuProduto;
    private javax.swing.JMenuItem itemMenuVenda;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuVenda;
    // End of variables declaration//GEN-END:variables

}
