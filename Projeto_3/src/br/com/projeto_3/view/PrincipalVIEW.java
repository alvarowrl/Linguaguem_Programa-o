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
        this.setLocationRelativeTo(null);
    }

  private void sair(){
        Object[] options = {"Sair", "Cancelar"};
        if(JOptionPane.showOptionDialog(null, "Deseja Sair do Sistema", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0){
           System.exit(0);
        }
    }
  
  private void abreClienteVIEW(){
        ClienteVIEW clienteVIEW = new ClienteVIEW();
        this.desktopPane.add(clienteVIEW);
        clienteVIEW.setVisible(true);
        clienteVIEW.setPosicao();
    }
  
  private void abreFornecedorVIEW(){
        FornecedorVIEW fornecedorVIEW = new FornecedorVIEW();
        this.desktopPane.add(fornecedorVIEW);
        fornecedorVIEW.setVisible(true);
        fornecedorVIEW.setPosicao();
    }
  
  private void abreProdutoVIEW(){
        ProdutoVIEW produtoVIEW = new ProdutoVIEW();
        this.desktopPane.add(produtoVIEW);
        produtoVIEW.setVisible(true);
        produtoVIEW.setPosicao();
    }
  private void abreVendaVIEW(){
        VendaVIEW vendaVIEW = new VendaVIEW();
        this.desktopPane.add(vendaVIEW);
        vendaVIEW.setVisible(true);
        vendaVIEW.setPosicao();
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
        itemMenuFuncionario = new javax.swing.JMenuItem();
        menuVenda = new javax.swing.JMenu();
        itemMenuVenda = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuCadastro.setMnemonic('f');
        menuCadastro.setText("Cadastro");

        itemMenuCliente.setMnemonic('o');
        itemMenuCliente.setText("Cliente");
        itemMenuCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMenuClienteMouseClicked(evt);
            }
        });
        itemMenuCliente.addActionListener(this::itemMenuClienteActionPerformed);
        menuCadastro.add(itemMenuCliente);

        itemMenuFornecedor.setMnemonic('s');
        itemMenuFornecedor.setText("Fornecedor");
        itemMenuFornecedor.addActionListener(this::itemMenuFornecedorActionPerformed);
        menuCadastro.add(itemMenuFornecedor);

        itemMenuProduto.setMnemonic('a');
        itemMenuProduto.setText("Produto");
        itemMenuProduto.addActionListener(this::itemMenuProdutoActionPerformed);
        menuCadastro.add(itemMenuProduto);

        itemMenuFuncionario.setText("Funcionario");
        itemMenuFuncionario.addActionListener(this::itemMenuFuncionarioActionPerformed);
        menuCadastro.add(itemMenuFuncionario);

        menuBar.add(menuCadastro);

        menuVenda.setMnemonic('e');
        menuVenda.setText("Venda");

        itemMenuVenda.setMnemonic('t');
        itemMenuVenda.setText("Realizar Venda");
        itemMenuVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemMenuVendaMouseClicked(evt);
            }
        });
        itemMenuVenda.addActionListener(this::itemMenuVendaActionPerformed);
        menuVenda.add(itemMenuVenda);

        menuBar.add(menuVenda);

        menuSair.setMnemonic('h');
        menuSair.setText("Sair");
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        menuSair.addActionListener(this::menuSairActionPerformed);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuClienteActionPerformed
        abreClienteVIEW();
    }//GEN-LAST:event_itemMenuClienteActionPerformed

    private void itemMenuProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuProdutoActionPerformed
    abreProdutoVIEW();       // TODO add your handling code here:
    }//GEN-LAST:event_itemMenuProdutoActionPerformed

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
    sair();        // TODO add your handling code here:
    }//GEN-LAST:event_menuSairActionPerformed

    private void itemMenuFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuFornecedorActionPerformed
        abreFornecedorVIEW();
    }//GEN-LAST:event_itemMenuFornecedorActionPerformed

    private void itemMenuVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuVendaActionPerformed
        abreVendaVIEW();
    }//GEN-LAST:event_itemMenuVendaActionPerformed

    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        sair();
    }//GEN-LAST:event_menuSairMouseClicked

    private void itemMenuVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMenuVendaMouseClicked
    abreVendaVIEW();        // TODO add your handling code here:
    }//GEN-LAST:event_itemMenuVendaMouseClicked

    private void itemMenuClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMenuClienteMouseClicked
    abreClienteVIEW();
    }//GEN-LAST:event_itemMenuClienteMouseClicked

    private void itemMenuFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemMenuFuncionarioActionPerformed

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
    private javax.swing.JMenuItem itemMenuFuncionario;
    private javax.swing.JMenuItem itemMenuProduto;
    private javax.swing.JMenuItem itemMenuVenda;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuVenda;
    // End of variables declaration//GEN-END:variables

}
