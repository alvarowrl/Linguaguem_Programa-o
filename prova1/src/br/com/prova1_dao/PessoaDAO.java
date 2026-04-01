/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prova1_dao;
import java.sql.*;
import br.com.prova1_dto.PessoaDTO;
import java.sql.ResultSet;


    public class PessoaDAO {

    private ResultSet rs = null;
    private Statement stmt = null;

    public PessoaDAO() {
    }

    public boolean inserirPessoa(PessoaDTO pessoaDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "Insert into pessoa (nome_pe, logradouro_pe, numero_pe, "
                    + "bairro_pe, cidade_pe, estado_pe, cep_pe, cpf_pe, rg_pe, "
                    + "emprego_pe, residentes_pe, rendaMensal_pe, rendaAdicional_pe, "
                    + "rendaTotalCasa_pe, despesasAdicionais_pe, despesasMensais_pe) values ( "
                    + "'" + pessoaDTO.getNome_pe() + "', "
                    + "'" + pessoaDTO.getLogradouro_pe() + "', "
                    + pessoaDTO.getNumero_pe() + ", "
                    + "'" + pessoaDTO.getBairro_pe() + "', "
                    + "'" + pessoaDTO.getCidade_pe() + "', "
                    + "'" + pessoaDTO.getEstado_pe() + "', "
                    + "'" + pessoaDTO.getCep_pe() + "', "
                    + "'" + pessoaDTO.getCpf_pe() + "', "
                    + "'" + pessoaDTO.getRg_pe() + "', "
                    + "'" + pessoaDTO.getEmprego_pe() + "', "
                    + pessoaDTO.getResidentes_pe() + ", "
                    + pessoaDTO.getRendaMensal_pe() + ", "
                    + pessoaDTO.getRendaAdicional_pe() + ", "
                    + pessoaDTO.getRendaTotalCasa_pe() + ", "
                    + pessoaDTO.getDespesasAdicionais_pe() + ", "
                    + pessoaDTO.getDespesasMensais_pe() + ") ";

            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    public ResultSet consultarPessoa(PessoaDTO pessoaDTO, int opcao) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";

            switch (opcao) {
                case 1: // Pesquisa por nome
                    comando = "Select p.* "
                            + "from pessoa p "
                            + "where nome_pe like '" + pessoaDTO.getNome_pe() + "%' "
                            + "order by p.nome_pe";
                    break;

                case 2: // Pesquisa por ID
                    comando = "Select p.* "
                            + "from pessoa p "
                            + "where p.id_pe = " + pessoaDTO.getId_pe();
                    break;

                case 3: // Lista básica
                    comando = "Select p.id_pe, p.nome_pe from pessoa p ";
                    break;
            }

            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;

        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
            return rs;
        }
    }

    public boolean alterarPessoa(PessoaDTO pessoaDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Update pessoa set "
                    + "nome_pe = '" + pessoaDTO.getNome_pe() + "', "
                    + "logradouro_pe = '" + pessoaDTO.getLogradouro_pe() + "', "
                    + "numero_pe = " + pessoaDTO.getNumero_pe() + ", "
                    + "bairro_pe = '" + pessoaDTO.getBairro_pe() + "', "
                    + "estado_pe = '" + pessoaDTO.getEstado_pe() + "', "
                    + "cep_pe = '" + pessoaDTO.getCep_pe() + "', "
                    + "cidade_pe = '" + pessoaDTO.getCidade_pe() + "', "
                    + "cpf_pe = '" + pessoaDTO.getCpf_pe() + "', "
                    + "rg_pe = '" + pessoaDTO.getRg_pe() + "', "
                    + "emprego_pe = '" + pessoaDTO.getEmprego_pe() + "', "
                    + "residentes_pe = " + pessoaDTO.getResidentes_pe() + ", "
                    + "rendaMensal_pe = " + pessoaDTO.getRendaMensal_pe() + ", "
                    + "rendaAdicional_pe = " + pessoaDTO.getRendaAdicional_pe() + ", "
                    + "rendaTotalCasa_pe = " + pessoaDTO.getRendaTotalCasa_pe() + ", "
                    + "despesasAdicionais_pe = " + pessoaDTO.getDespesasAdicionais_pe() + ", "
                    + "despesasMensais_pe = " + pessoaDTO.getDespesasMensais_pe() + " "
                    + "where id_pe = " + pessoaDTO.getId_pe();

            stmt.execute(comando.toUpperCase());
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao alterar: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    public boolean excluirPessoa(PessoaDTO pessoaDTO) {
        try {
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from pessoa where id_pe = " + pessoaDTO.getId_pe();

            stmt.execute(comando);
            ConexaoDAO.con.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
}

