/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_3.view;

import br.com.projeto_3.ctr.ClienteCTR;
import br.com.projeto_3.ctr.ProdutoCTR;
import br.com.projeto_3.ctr.VendaCTR;
import br.com.projeto_3.dto.ClienteDTO;
import br.com.projeto_3.dto.ProdutoDTO;
import br.com.projeto_3.dto.VendaDTO;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class VendaVIEW extends javax.swing.JInternalFrame {
    
    ClienteDTO clienteDTO = new ClienteDTO();
    ClienteCTR clienteCTR = new ClienteCTR();
    VendaDTO vendaDTO = new VendaDTO();
    VendaCTR vendaCTR = new VendaCTR();
    ProdutoDTO produtoDTO = new ProdutoDTO();
    ProdutoCTR produtoCTR = new ProdutoCTR();
   
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_cli;
    DefaultTableModel modelo_jtl_consultar_pro;
    DefaultTableModel modelo_jtl_consultar_pro_selecionado;

    public VendaVIEW() {
        initComponents();
        
        liberaCampos(false);
        liberaBotoes(true, false, false, true);
        modelo_jtl_consultar_cli = (DefaultTableModel) jtl_consultar_cli.getModel();
        modelo_jtl_consultar_pro = (DefaultTableModel) jtl_consultar_pro.getModel();
        modelo_jtl_consultar_pro_selecionado = (DefaultTableModel) jtl_consultar_pro_selecionado.getModel();
    }
     
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private void gravar(){
   
           vendaDTO.setDat_vend(new Date());
           vendaDTO.setVal_vend(Double.parseDouble(TotalVenda.getText()));
           clienteDTO.setId_cli(Integer.parseInt(String.valueOf(
           jtl_consultar_cli.getValueAt(jtl_consultar_cli.getSelectedRow(), 0))));
           
           JOptionPane.showMessageDialog(null, vendaCTR.inserirVenda(vendaDTO, clienteDTO, jtl_consultar_pro_selecionado));
    }
    
    private void preencheTabelaCliente(String nome_cli){
        try{
            modelo_jtl_consultar_cli.setNumRows(0);
            clienteDTO.setNome_cli(nome_cli);
            rs = clienteCTR.consultarCliente(clienteDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_cli.addRow(new Object[]{
                    rs.getString("id_cli"),
                    rs.getString("nome_cli"),
                });
            }
        
       }catch(Exception erTab){
           System.out.println("Erro SQL: "+erTab);
       }
        finally{
            clienteCTR.CloseDB();
        }
    }
    
    private void preencheTabelaProduto(String nome_prod){
        try{
            modelo_jtl_consultar_pro.setNumRows(0);
            produtoDTO.setNome_prod(nome_prod);
            rs = produtoCTR.consultarProduto(produtoDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_pro.addRow(new Object[]{
                    rs.getString("id_prod"),
                    rs.getString("nome_prod"),
                    rs.getDouble("p_venda_prod")
                });
            }
        
       }catch(Exception erTab){
           System.out.println("Erro SQL: "+erTab);
       }
        finally{
            produtoCTR.CloseDB();
        }
    }   
   private void calculaTotalVenda(){
        try{
            
            double total=0;
            for(int cont=0; cont<jtl_consultar_pro_selecionado.getRowCount(); cont++){
            total += (Double.parseDouble(String.valueOf(
                    jtl_consultar_pro_selecionado.getValueAt(cont, 2))) *
                      Integer.parseInt(String.valueOf(
                      jtl_consultar_pro_selecionado.getValueAt(cont, 3))));        
            }
            TotalVenda.setText(String.valueOf(total));
        }
        catch(Exception erTab){
            System.out.println("Erro Sql"+erTab);
        }
    }

    private void adicionaProdutoSelecionado(int id_prod, String nome_prod, double p_venda_prod){
        try{
            modelo_jtl_consultar_pro_selecionado.addRow(new Object[]{
                id_prod,
                nome_prod,
                p_venda_prod
            });
            
        }catch(Exception erTab){
            System.out.println("Erro Sql"+erTab);
        }
    } 
    private void removeProdutoSelecionado(int linha_selecionada){
        try{
            if(linha_selecionada >= 0){
                modelo_jtl_consultar_pro_selecionado.removeRow(linha_selecionada);
                calculaTotalVenda();
            }
            
            
        }catch(Exception erTab){
            System.out.println("Erro Sql"+erTab);
        }
    }
    private void liberaCampos(boolean a){
    pesquisa_nome_cli.setEnabled(a);
    btnPesquisarCli.setEnabled(a);
    jtl_consultar_cli.setEnabled(a);
    pesquisa_nome_pro.setEnabled(a);
    btnPesquisarPro.setEnabled(a);
    jtl_consultar_pro.setEnabled(a);
    btnProAdd.setEnabled(a);
    btnProRem.setEnabled(a);
    jtl_consultar_pro_selecionado.setEnabled(a);
    TotalVenda.setText("0.00");
}
    private void limpaCampos(){
    pesquisa_nome_cli.setText("");
    modelo_jtl_consultar_cli.setNumRows(0);
    pesquisa_nome_pro.setText("");
    modelo_jtl_consultar_pro.setNumRows(0);
    modelo_jtl_consultar_pro_selecionado.setNumRows(0);
}
     private void liberaBotoes(boolean a, boolean b, boolean c, boolean d){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnSair.setEnabled(d);
    }
    private boolean verificaPreenchimento() {
    if(jtl_consultar_cli.getSelectedRowCount() <= 0){
        JOptionPane.showMessageDialog(null, "Deve ser selecionado um Cliente");
        jtl_consultar_cli.requestFocus();
        return false;
    }
    else{
        if(jtl_consultar_pro_selecionado.getRowCount() <= 0){
            JOptionPane.showMessageDialog(null, "É necessário adicionar pelo menos um produto no pedido");
            jtl_consultar_pro_selecionado.requestFocus();
            return false;
        }
        else{
            int verifica=0;
            for(int cont=0; cont<jtl_consultar_pro_selecionado.getRowCount(); cont++){
                if(String.valueOf(jtl_consultar_pro_selecionado.getValueAt(
                    cont, 3)).equalsIgnoreCase("null")){
                    verifica++;
                }
            }
            if(verifica > 0){
                JOptionPane.showMessageDialog(null,
                "A quantidade de cada produto vendido deve ser informado");
                jtl_consultar_pro_selecionado.requestFocus();
                return false;
            }
            else{
                return true;
            }//Fecha else da quantidade de cada produto vendido
        }//Fecha else jtl_consultar_pro_selecionado
    }//Fecha else jtl_consultar_cli
}
   
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pesquisa_nome_cli = new javax.swing.JTextField();
        btnPesquisarCli = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_cli = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pesquisa_nome_pro = new javax.swing.JTextField();
        btnPesquisarPro = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtl_consultar_pro_selecionado = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtl_consultar_pro = new javax.swing.JTable();
        btnProRem = new javax.swing.JButton();
        btnProAdd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TotalVenda = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel9.setText("Dados");

        jLabel10.setText("Cliente:");

        pesquisa_nome_cli.addActionListener(this::pesquisa_nome_cliActionPerformed);

        btnPesquisarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/pesquisar.png"))); // NOI18N
        btnPesquisarCli.setText("OK");
        btnPesquisarCli.setToolTipText("");
        btnPesquisarCli.addActionListener(this::btnPesquisarCliActionPerformed);

        jtl_consultar_cli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_cli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_cliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_cli);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPesquisarCli)))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btnPesquisarCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel11.setText("Produtos");

        jLabel12.setText("Descrição:");

        btnPesquisarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/pesquisar.png"))); // NOI18N
        btnPesquisarPro.setText("OK");
        btnPesquisarPro.setToolTipText("");
        btnPesquisarPro.addActionListener(this::btnPesquisarProActionPerformed);

        jtl_consultar_pro_selecionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Valor", "QTD"
            }
        ));
        jtl_consultar_pro_selecionado.setColumnSelectionAllowed(true);
        jtl_consultar_pro_selecionado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_pro_selecionadoMouseClicked(evt);
            }
        });
        jtl_consultar_pro_selecionado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtl_consultar_pro_selecionadoKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtl_consultar_pro_selecionado);
        jtl_consultar_pro_selecionado.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jtl_consultar_pro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Valor"
            }
        ));
        jtl_consultar_pro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_proMouseClicked(evt);
            }
        });
        jtl_consultar_pro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtl_consultar_proKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jtl_consultar_pro);
        jtl_consultar_pro.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnProRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/prod_rem.png"))); // NOI18N
        btnProRem.setToolTipText("");
        btnProRem.addActionListener(this::btnProRemActionPerformed);

        btnProAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/prod_add.png"))); // NOI18N
        btnProAdd.setToolTipText("");
        btnProAdd.addActionListener(this::btnProAddActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pesquisa_nome_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPesquisarPro)))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(btnProAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProRem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pesquisa_nome_pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(btnPesquisarPro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProAdd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnProRem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(69, 69, 69)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(179, Short.MAX_VALUE)))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Total Venda:");

        TotalVenda.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        TotalVenda.setForeground(new java.awt.Color(0, 204, 102));
        TotalVenda.setText("0.00");

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br.com.projeto_3.view.imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(this::btnSairActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addComponent(TotalVenda)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TotalVenda)
                            .addComponent(jLabel1))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSair))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtl_consultar_cliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_cliMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtl_consultar_cliMouseClicked

    private void btnPesquisarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCliActionPerformed
        preencheTabelaCliente(pesquisa_nome_cli.getText());
    }//GEN-LAST:event_btnPesquisarCliActionPerformed

    private void btnPesquisarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProActionPerformed
        preencheTabelaProduto(pesquisa_nome_pro.getText());
    }//GEN-LAST:event_btnPesquisarProActionPerformed

    private void jtl_consultar_pro_selecionadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_pro_selecionadoMouseClicked
    }//GEN-LAST:event_jtl_consultar_pro_selecionadoMouseClicked

    private void jtl_consultar_proMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_proMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtl_consultar_proMouseClicked

    private void btnProRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProRemActionPerformed
        removeProdutoSelecionado(jtl_consultar_pro_selecionado.getSelectedRow());
    }//GEN-LAST:event_btnProRemActionPerformed

    private void btnProAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProAddActionPerformed
        adicionaProdutoSelecionado(
        Integer.parseInt(String.valueOf(jtl_consultar_pro.getValueAt(
            jtl_consultar_pro.getSelectedRow(), 0))),
        String.valueOf(jtl_consultar_pro.getValueAt(jtl_consultar_pro.getSelectedRow(), 1)),
        Double.parseDouble(String.valueOf(jtl_consultar_pro.getValueAt(
            jtl_consultar_pro.getSelectedRow(), 2)))
    );        // TODO add your handling code here:
    }//GEN-LAST:event_btnProAddActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true,true);
       
    }//GEN-LAST:event_btnNovoActionPerformed
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_cli.setNumRows(0);
        modelo_jtl_consultar_pro.setNumRows(0);
        liberaBotoes(true, false, false,true);
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(verificaPreenchimento()){
        gravar();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, true);}

        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void jtl_consultar_proKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtl_consultar_proKeyReleased
      
    }//GEN-LAST:event_jtl_consultar_proKeyReleased

    private void pesquisa_nome_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_cliActionPerformed

    private void jtl_consultar_pro_selecionadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtl_consultar_pro_selecionadoKeyReleased
         if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            calculaTotalVenda();
        }
    }//GEN-LAST:event_jtl_consultar_pro_selecionadoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotalVenda;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarCli;
    private javax.swing.JButton btnPesquisarPro;
    private javax.swing.JButton btnProAdd;
    private javax.swing.JButton btnProRem;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jtl_consultar_cli;
    private javax.swing.JTable jtl_consultar_pro;
    private javax.swing.JTable jtl_consultar_pro_selecionado;
    private javax.swing.JTextField pesquisa_nome_cli;
    private javax.swing.JTextField pesquisa_nome_pro;
    // End of variables declaration//GEN-END:variables
}
