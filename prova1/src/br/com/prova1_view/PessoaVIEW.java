/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.prova1_view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.prova1_dto.PessoaDTO;
import br.com.prova1_ctr.PessoaCTR;

public class PessoaVIEW extends javax.swing.JInternalFrame {

       PessoaDTO pessoaDTO = new PessoaDTO();
        PessoaCTR pessoaCTR = new PessoaCTR();
    
    int gravar_alterar;
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_pessoa;
    
    public PessoaVIEW() {
        
     
        initComponents();
       
        liberaCampos(false);      
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_pessoa = (DefaultTableModel) jtl_consultar_pessoa.getModel();
        
    }
      private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }
    
    private void limpaCampos(){
    nome_pe.setText("");
    logradouro_pe.setText("");
    numero_pe.setText("");
    bairro_pe.setText("");
    cidade_pe.setText("");
    estado_pe.setSelectedIndex(0); 
    cep_pe.setText("");
    cpf_pe.setText("");
    rg_pe.setText("");
    emprego_pe.setText("");
    residentes_pe.setText("");
    rendaMensal_pe.setText("");
    rendaAdicional_pe.setText("");
    rendaTotalCasa_pe.setText("");
    despesasAdicionais_pe.setText("");
    despesasMensais_pe.setText("");
}
    
    private void liberaCampos(boolean a){
    nome_pe.setEnabled(a);
    logradouro_pe.setEnabled(a);
    numero_pe.setEnabled(a);
    bairro_pe.setEnabled(a);
    cidade_pe.setEnabled(a);
    estado_pe.setEnabled(a);
    cep_pe.setEnabled(a);
    cpf_pe.setEnabled(a);
    rg_pe.setEnabled(a);
    emprego_pe.setEnabled(a);
    residentes_pe.setEnabled(a);
    rendaMensal_pe.setEnabled(a);
    rendaAdicional_pe.setEnabled(a);
    rendaTotalCasa_pe.setEnabled(a);
    despesasAdicionais_pe.setEnabled(a);
    despesasMensais_pe.setEnabled(a);
}
    private void gravar() {
    try {
        pessoaDTO.setNome_pe(nome_pe.getText());
        pessoaDTO.setLogradouro_pe(logradouro_pe.getText());
        pessoaDTO.setNumero_pe(Integer.parseInt(numero_pe.getText()));
        pessoaDTO.setBairro_pe(bairro_pe.getText());
        pessoaDTO.setCidade_pe(cidade_pe.getText());
        pessoaDTO.setEstado_pe(estado_pe.getSelectedItem().toString());
        pessoaDTO.setCep_pe(cep_pe.getText());
        pessoaDTO.setCpf_pe(cpf_pe.getText());
        pessoaDTO.setRg_pe(rg_pe.getText());
        pessoaDTO.setEmprego_pe(emprego_pe.getText());
        pessoaDTO.setResidentes_pe(Integer.parseInt(residentes_pe.getText()));
        pessoaDTO.setRendaMensal_pe(Float.parseFloat(rendaMensal_pe.getText()));
        pessoaDTO.setRendaAdicional_pe(Float.parseFloat(rendaAdicional_pe.getText()));
        pessoaDTO.setRendaTotalCasa_pe(Float.parseFloat(rendaTotalCasa_pe.getText()));
        pessoaDTO.setDespesasAdicionais_pe(Float.parseFloat(despesasAdicionais_pe.getText()));
        pessoaDTO.setDespesasMensais_pe(Float.parseFloat(despesasMensais_pe.getText()));

        JOptionPane.showMessageDialog(null, pessoaCTR.inserirPessoa(pessoaDTO));
        
    } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(null, "Erro: Verifique se os campos numéricos (número, residentes, rendas) foram preenchidos corretamente.");
    } catch (Exception e) {
        System.out.println("Erro ao gravar: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado ao gravar os dados.");
    }
}
    private void preencheTabela(String nome_pe){
        try{
            modelo_jtl_consultar_pessoa.setNumRows(0);
            pessoaDTO.setNome_pe(nome_pe);
            rs = pessoaCTR.consultarPessoa(pessoaDTO, 1);
            while(rs.next()){
                modelo_jtl_consultar_pessoa.addRow(new Object[]{
                    rs.getString("id_pe"),
                    rs.getString("nome_pe"),
                });
            }
        
       }catch(Exception erTab){
           System.out.println("Erro SQL: "+erTab);
       }
        finally{
            pessoaCTR.CloseDB();
        }
    }
    
    private void preencheCampos(int id_pe) {
    try {
        pessoaDTO.setId_pe(id_pe);

        rs = pessoaCTR.consultarPessoa(pessoaDTO, 2); 
        
        if (rs.next()) {
            limpaCampos();
            nome_pe.setText(rs.getString("nome_pe"));
            logradouro_pe.setText(rs.getString("logradouro_pe"));
            numero_pe.setText(rs.getString("numero_pe"));
            bairro_pe.setText(rs.getString("bairro_pe"));
            cidade_pe.setText(rs.getString("cidade_pe"));
            estado_pe.setSelectedItem(rs.getString("estado_pe"));
            cep_pe.setText(rs.getString("cep_pe"));
            cpf_pe.setText(rs.getString("cpf_pe"));
            rg_pe.setText(rs.getString("rg_pe"));
            emprego_pe.setText(rs.getString("emprego_pe"));
            residentes_pe.setText(rs.getString("residentes_pe"));
            rendaMensal_pe.setText(String.valueOf(rs.getFloat("rendaMensal_pe")));
            rendaAdicional_pe.setText(String.valueOf(rs.getFloat("rendaAdicional_pe")));
            rendaTotalCasa_pe.setText(String.valueOf(rs.getFloat("rendaTotalCasa_pe")));
            despesasAdicionais_pe.setText(String.valueOf(rs.getFloat("despesasAdicionais_pe")));
            despesasMensais_pe.setText(String.valueOf(rs.getFloat("despesasMensais_pe")));

            gravar_alterar = 2;
            liberaCampos(true);
        }
    } catch (Exception erTab) {
        System.out.println("Erro SQL ao preencher campos: " + erTab.getMessage());
    } finally {
        pessoaCTR.CloseDB();
    }
}
    private void alterar() {
    try {
        pessoaDTO.setNome_pe(nome_pe.getText());
        pessoaDTO.setLogradouro_pe(logradouro_pe.getText());
        pessoaDTO.setNumero_pe(Integer.parseInt(numero_pe.getText()));
        pessoaDTO.setBairro_pe(bairro_pe.getText());
        pessoaDTO.setCidade_pe(cidade_pe.getText());
        pessoaDTO.setEstado_pe(estado_pe.getSelectedItem().toString());
        pessoaDTO.setCep_pe(cep_pe.getText());
        pessoaDTO.setCpf_pe(cpf_pe.getText());
        pessoaDTO.setRg_pe(rg_pe.getText());
        pessoaDTO.setEmprego_pe(emprego_pe.getText());
        pessoaDTO.setResidentes_pe(Integer.parseInt(residentes_pe.getText()));
        pessoaDTO.setRendaMensal_pe(Float.parseFloat(rendaMensal_pe.getText()));
        pessoaDTO.setRendaAdicional_pe(Float.parseFloat(rendaAdicional_pe.getText()));
        pessoaDTO.setRendaTotalCasa_pe(Float.parseFloat(rendaTotalCasa_pe.getText()));
        pessoaDTO.setDespesasAdicionais_pe(Float.parseFloat(despesasAdicionais_pe.getText()));
        pessoaDTO.setDespesasMensais_pe(Float.parseFloat(despesasMensais_pe.getText()));

        JOptionPane.showMessageDialog(null, pessoaCTR.alterarPessoa(pessoaDTO));
        
    } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(null, "Erro: Verifique se os campos numéricos e financeiros foram preenchidos corretamente.");
    } catch (Exception e) {
        System.out.println("Erro ao Alterar: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Erro ao processar a alteração dos dados.");
    }
}
       private void excluir(){
        if(JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Cliente?", "Aviso",
            JOptionPane.YES_NO_OPTION) ==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, pessoaCTR.excluirPessoa(pessoaDTO));
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

        nome_pe = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        logradouro_pe = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rendaMensal_pe = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rendaAdicional_pe = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rendaTotalCasa_pe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        despesasAdicionais_pe = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        despesasMensais_pe = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        residentes_pe = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        emprego_pe = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        estado_pe = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        numero_pe = new javax.swing.JTextField();
        bairro_pe = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cidade_pe = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cep_pe = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cpf_pe = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        rg_pe = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_pessoa = new javax.swing.JTable();
        pesquisa_nome_pe = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nome_pe.setNextFocusableComponent(rendaMensal_pe);

        jLabel1.setText("Nome:");

        logradouro_pe.setNextFocusableComponent(bairro_pe);

        jLabel2.setText("Logradouro:");

        rendaMensal_pe.setNextFocusableComponent(rendaAdicional_pe);

        jLabel3.setText("Renda Adicional:");

        jLabel4.setText("Renda Mensal:");

        rendaTotalCasa_pe.setNextFocusableComponent(despesasMensais_pe);

        jLabel5.setText("Renda Total da Casa:");

        despesasAdicionais_pe.setNextFocusableComponent(residentes_pe);
        despesasAdicionais_pe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                despesasAdicionais_peActionPerformed(evt);
            }
        });

        jLabel6.setText("Despesas Adicionais:");

        despesasMensais_pe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                despesasMensais_peActionPerformed(evt);
            }
        });

        jLabel7.setText("Despesas Mensais:");

        residentes_pe.setNextFocusableComponent(emprego_pe);

        jLabel8.setText("Residentes:");

        emprego_pe.setNextFocusableComponent(logradouro_pe);

        jLabel9.setText("Fonte de Renda:");

        estado_pe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SP", "MG", "RJ", "GO", "RN", "MT", "MS", "SC", "RS", "MA", "CE", " " }));
        estado_pe.setNextFocusableComponent(cep_pe);
        estado_pe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_peActionPerformed(evt);
            }
        });

        jLabel10.setText("Número:");

        jLabel11.setText("Bairro:");

        numero_pe.setNextFocusableComponent(cidade_pe);
        numero_pe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_peActionPerformed(evt);
            }
        });

        bairro_pe.setNextFocusableComponent(numero_pe);

        jLabel12.setText("Cidade:");

        cidade_pe.setNextFocusableComponent(estado_pe);

        jLabel13.setText("Estado:");

        jLabel14.setText("CEP:");

        cep_pe.setNextFocusableComponent(cpf_pe);

        jLabel15.setText("CPF:");

        cpf_pe.setNextFocusableComponent(rg_pe);
        cpf_pe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpf_peActionPerformed(evt);
            }
        });

        jLabel16.setText("RG:");

        rg_pe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rg_peActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prova1_view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prova1_view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prova1_view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prova1_view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/prova1_view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jtl_consultar_pessoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_pessoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_pessoaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_pessoa);
        if (jtl_consultar_pessoa.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_pessoa.getColumnModel().getColumn(1).setResizable(false);
        }

        pesquisa_nome_pe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_peActionPerformed(evt);
            }
        });

        btnPesquisar.setText("OK");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel17.setText("Nome:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setText("PESSOA");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("CONSULTA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(cep_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(17, 17, 17)
                                            .addComponent(jLabel15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cpf_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cidade_pe))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(30, 30, 30)
                                            .addComponent(jLabel13)
                                            .addGap(18, 18, 18)
                                            .addComponent(estado_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(22, 22, 22)
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rg_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
                            .addComponent(numero_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(15, 15, 15))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(rendaTotalCasa_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rendaAdicional_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                                                .addComponent(jLabel6))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rendaMensal_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel7)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(despesasMensais_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(despesasAdicionais_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(residentes_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(nome_pe)
                                    .addComponent(emprego_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(logradouro_pe, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bairro_pe)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(85, 85, 85)))
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(pesquisa_nome_pe, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(nome_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rendaMensal_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(despesasMensais_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(despesasAdicionais_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rendaAdicional_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(residentes_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(rendaTotalCasa_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emprego_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(logradouro_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bairro_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numero_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cidade_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(estado_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pesquisa_nome_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisar)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cep_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(cpf_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(rg_pe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnExcluir)
                    .addComponent(btnSair))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void despesasAdicionais_peActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_despesasAdicionais_peActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_despesasAdicionais_peActionPerformed

    private void despesasMensais_peActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_despesasMensais_peActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_despesasMensais_peActionPerformed

    private void estado_peActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_peActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado_peActionPerformed

    private void numero_peActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_peActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numero_peActionPerformed

    private void cpf_peActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpf_peActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpf_peActionPerformed

    private void rg_peActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rg_peActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rg_peActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

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
//
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
       modelo_jtl_consultar_pessoa.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar=0;        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_pessoa.setNumRows(0);// TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void pesquisa_nome_peActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_peActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_peActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        preencheTabela(pesquisa_nome_pe.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jtl_consultar_pessoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_pessoaMouseClicked
        preencheCampos(Integer.parseInt(String.valueOf(jtl_consultar_pessoa.getValueAt(jtl_consultar_pessoa.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_pessoaMouseClicked

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
            java.util.logging.Logger.getLogger(PessoaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PessoaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PessoaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PessoaVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PessoaVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bairro_pe;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cep_pe;
    private javax.swing.JTextField cidade_pe;
    private javax.swing.JTextField cpf_pe;
    private javax.swing.JTextField despesasAdicionais_pe;
    private javax.swing.JTextField despesasMensais_pe;
    private javax.swing.JTextField emprego_pe;
    private javax.swing.JComboBox<String> estado_pe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_pessoa;
    private javax.swing.JTextField logradouro_pe;
    private javax.swing.JTextField nome_pe;
    private javax.swing.JTextField numero_pe;
    private javax.swing.JTextField pesquisa_nome_pe;
    private javax.swing.JTextField rendaAdicional_pe;
    private javax.swing.JTextField rendaMensal_pe;
    private javax.swing.JTextField rendaTotalCasa_pe;
    private javax.swing.JTextField residentes_pe;
    private javax.swing.JTextField rg_pe;
    // End of variables declaration//GEN-END:variables
}
