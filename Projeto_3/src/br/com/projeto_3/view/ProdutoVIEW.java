/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_3.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_3.dto.FornecedorDTO;
import br.com.projeto_3.ctr.FornecedorCTR;
import br.com.projeto_3.dto.ProdutoDTO;
import br.com.projeto_3.ctr.ProdutoCTR;

public class ProdutoVIEW extends javax.swing.JInternalFrame {
    
    FornecedorDTO fornecedorDTO = new FornecedorDTO();
    FornecedorCTR fornecedorCTR = new FornecedorCTR();
    ProdutoDTO produtoDTO = new ProdutoDTO();
    ProdutoCTR produtoCTR = new ProdutoCTR();
    
     int gravar_alterar;
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_fornecedor;
    DefaultTableModel modelo_jtl_consultar_produto;
    
    public void setPosicao(){
    Dimension d = this.getDesktopPane().getSize();
    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    public ProdutoVIEW() {
        initComponents();
        liberaCampos(false);
        
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_produto = (DefaultTableModel) jtl_consultar_produto.getModel();
        modelo_jtl_consultar_fornecedor = (DefaultTableModel) jtl_consultar_fornecedor.getModel();
    }
    
    private void gravar(){
       try{
           produtoDTO.setNome_prod(nome_prod.getText());
           produtoDTO.setDesc_prod(desc_prod.getText());
           produtoDTO.setCod_bar_prod(cod_bar_prod.getText());
           produtoDTO.setP_custo_prod(Double.parseDouble(p_custo_prod.getText()));
           produtoDTO.setP_venda_prod(Double.parseDouble(p_venda_prod.getText()));
           fornecedorDTO.setId_for(Integer.parseInt(String.valueOf(
                   jtl_consultar_fornecedor.getValueAt(
                   jtl_consultar_fornecedor.getSelectedRow(), 0))));
           
           JOptionPane.showMessageDialog(null, produtoCTR.inserirProduto(produtoDTO, fornecedorDTO));
       } catch(Exception e){
           System.out.println("Erro ao gravar" + e.getMessage());
       }
    
    }
    
    private void alterar(){
        try{
           produtoDTO.setNome_prod(nome_prod.getText());
           produtoDTO.setDesc_prod(desc_prod.getText());
           produtoDTO.setCod_bar_prod(cod_bar_prod.getText());
           produtoDTO.setP_custo_prod(Double.parseDouble(p_custo_prod.getText()));
           produtoDTO.setP_venda_prod(Double.parseDouble(p_venda_prod.getText()));
           fornecedorDTO.setId_for(Integer.parseInt(String.valueOf(
                   jtl_consultar_fornecedor.getValueAt(
                   jtl_consultar_fornecedor.getSelectedRow(), 0))));
           
           JOptionPane.showMessageDialog(null, produtoCTR.alterarProduto(produtoDTO, fornecedorDTO));
        }catch(Exception e){
            System.out.println("Erro ao Alterar" + e.getMessage());
        }
    }
    private void excluir(){
        if(JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Produto?", "Aviso",
            JOptionPane.YES_NO_OPTION) ==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, produtoCTR.excluirProduto(produtoDTO));
        }
    }
    private void liberaCampos(boolean a){
      nome_prod.setEnabled(a);
      desc_prod.setEnabled(a);
      cod_bar_prod.setEnabled(a);
      p_custo_prod.setEnabled(a);
      p_venda_prod.setEnabled(a);
      pesquisa_nome_fornecedor.setEnabled(a);
      btnPesquisarFornecedor.setEnabled(a);
      jtl_consultar_fornecedor.setEnabled(a);
    }
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }
    private void limpaCampos(){
        nome_prod.setText("");
        desc_prod.setText("");
        cod_bar_prod.setText("");
        p_venda_prod.setText("");
        pesquisa_nome_fornecedor.setText("");
        modelo_jtl_consultar_fornecedor.setNumRows(0);
            }
    private void preencheTabelaProduto(String nome_prod){
        try{
            modelo_jtl_consultar_produto.setNumRows(0);
            produtoDTO.setNome_prod(nome_prod);
            rs = produtoCTR.consultarProduto(produtoDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_produto.addRow(new Object[]{
                    rs.getString("id_prod"),
                    rs.getString("nome_prod"),
                });
            }
        
       }catch(Exception erTab){
           System.out.println("Erro SQL: "+erTab);
       }
        finally{
            produtoCTR.CloseDB();
        }
    }
    private void preencheCamposProduto(int id_prod){
        try{
            produtoDTO.setId_prod(id_prod);
            rs = produtoCTR.consultarProduto(produtoDTO, 2);
            if(rs.next()){
                limpaCampos();
                
                nome_prod.setText(rs.getString("nome_prod"));
                desc_prod.setText(rs.getString("desc_prod"));
                cod_bar_prod.setText(rs.getString("cod_bar_prod"));
                p_custo_prod.setText(rs.getString("p_custo_prod"));
                p_venda_prod.setText(rs.getString("p_venda_prod"));
                
                modelo_jtl_consultar_fornecedor.setNumRows(0);
                modelo_jtl_consultar_fornecedor.addRow(new Object[]{rs.getInt("id_for"), rs.getString("nome_for"),});
                jtl_consultar_fornecedor.setRowSelectionInterval(0, 0);
                gravar_alterar = 2;
                liberaCampos(true);
                
            }
        }
        catch(Exception e){
            System.out.println("Erro preencheCamposProduto: " + e.getMessage());
        }
        finally{
            fornecedorCTR.CloseDB();
        }
    }
    private void preencheTabelaFornecedor(String nome_for){
        try{
            modelo_jtl_consultar_fornecedor.setNumRows(0);
            fornecedorDTO.setNome_for(nome_for);
            rs = fornecedorCTR.consultarFornecedor(fornecedorDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_fornecedor.addRow(new Object[]{
                    rs.getString("id_for"),
                    rs.getString("nome_for"),
                });
            }
        
       }
       catch(Exception erTab){
           System.out.println("Erro SQL: "+erTab);
       }
        finally{
            fornecedorCTR.CloseDB();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_produto = new javax.swing.JTable();
        pesquisa_nome_produto = new javax.swing.JTextField();
        btnPesquisarProduto = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nome_prod = new javax.swing.JTextField();
        desc_prod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cod_bar_prod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        p_custo_prod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        p_venda_prod = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_fornecedor = new javax.swing.JTable();
        pesquisa_nome_fornecedor = new javax.swing.JTextField();
        btnPesquisarFornecedor = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(this::btnSairActionPerformed);

        jtl_consultar_produto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_produto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_produtoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_produto);

        btnPesquisarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/pesquisar.png"))); // NOI18N
        btnPesquisarProduto.setText("OK");
        btnPesquisarProduto.setToolTipText("");
        btnPesquisarProduto.addActionListener(this::btnPesquisarProdutoActionPerformed);

        jLabel5.setText("Nome:");

        jLabel1.setText("Nome:");

        nome_prod.addActionListener(this::nome_prodActionPerformed);

        desc_prod.addActionListener(this::desc_prodActionPerformed);

        jLabel2.setText("Descrição:");

        cod_bar_prod.addActionListener(this::cod_bar_prodActionPerformed);

        jLabel3.setText("P. Custo:");

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);

        p_custo_prod.addActionListener(this::p_custo_prodActionPerformed);

        jLabel4.setText("Código:");

        p_venda_prod.addActionListener(this::p_venda_prodActionPerformed);

        jLabel6.setText("P. Venda:");

        jtl_consultar_fornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_fornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_fornecedorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_fornecedor);

        btnPesquisarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/pesquisar.png"))); // NOI18N
        btnPesquisarFornecedor.setText("OK");
        btnPesquisarFornecedor.setToolTipText("");
        btnPesquisarFornecedor.addActionListener(this::btnPesquisarFornecedorActionPerformed);

        jLabel7.setText("Forncedor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pesquisa_nome_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPesquisarFornecedor))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(p_custo_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(p_venda_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cod_bar_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(desc_prod)
                                    .addComponent(nome_prod))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pesquisa_nome_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPesquisarProduto))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(pesquisa_nome_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarProduto)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(desc_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cod_bar_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(p_custo_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(p_venda_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pesquisa_nome_fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(btnPesquisarFornecedor)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
       liberaCampos(false);
        modelo_jtl_consultar_produto.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar=0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
      limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
       modelo_jtl_consultar_produto.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(gravar_alterar==1){
            gravar();
            gravar_alterar=0;
        }else{
            if(gravar_alterar == 2){
                alterar();
                gravar_alterar = 0;
            } else{
                JOptionPane.showMessageDialog(null, "Erro no Sistema!!!");
            }
        }

        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void jtl_consultar_produtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_produtoMouseClicked
        preencheCamposProduto(Integer.parseInt(String.valueOf(
           jtl_consultar_produto.getValueAt(jtl_consultar_produto.getSelectedRow(), 0))));
       liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_produtoMouseClicked

    private void btnPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProdutoActionPerformed
         preencheTabelaProduto(pesquisa_nome_produto.getText());
    }//GEN-LAST:event_btnPesquisarProdutoActionPerformed

    private void nome_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_prodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_prodActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void desc_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desc_prodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_desc_prodActionPerformed

    private void cod_bar_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cod_bar_prodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cod_bar_prodActionPerformed

    private void p_custo_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_custo_prodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_custo_prodActionPerformed

    private void p_venda_prodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_venda_prodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_venda_prodActionPerformed

    private void jtl_consultar_fornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_fornecedorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtl_consultar_fornecedorMouseClicked

    private void btnPesquisarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarFornecedorActionPerformed
        preencheTabelaFornecedor(pesquisa_nome_fornecedor.getText());
    }//GEN-LAST:event_btnPesquisarFornecedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarFornecedor;
    private javax.swing.JButton btnPesquisarProduto;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cod_bar_prod;
    private javax.swing.JTextField desc_prod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtl_consultar_fornecedor;
    private javax.swing.JTable jtl_consultar_produto;
    private javax.swing.JTextField nome_prod;
    private javax.swing.JTextField p_custo_prod;
    private javax.swing.JTextField p_venda_prod;
    private javax.swing.JTextField pesquisa_nome_fornecedor;
    private javax.swing.JTextField pesquisa_nome_produto;
    // End of variables declaration//GEN-END:variables
}
