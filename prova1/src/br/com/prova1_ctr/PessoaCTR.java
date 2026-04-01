/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.prova1_ctr;

import java.sql.*;
import br.com.prova1_dto.PessoaDTO;
import br.com.prova1_dao.PessoaDAO;
import br.com.prova1_dao.ConexaoDAO;

public class PessoaCTR {

    PessoaDAO pessoaDAO = new PessoaDAO();

    public PessoaCTR() {
    }

    public String inserirPessoa(PessoaDTO pessoaDTO) {
        try {
            if (pessoaDAO.inserirPessoa(pessoaDTO)) {
                return "Pessoa cadastrada com sucesso!!!";
            } else {
                return "Pessoa NÃO cadastrada!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Pessoa não cadastrada!!!";
        }
    }

    public ResultSet consultarPessoa(PessoaDTO pessoaDTO, int opcao) {
        ResultSet rs = null;
        rs = pessoaDAO.consultarPessoa(pessoaDTO, opcao);
        return rs;
    }

    public String alterarPessoa(PessoaDTO pessoaDTO) {
        try {
            if (pessoaDAO.alterarPessoa(pessoaDTO)) {
                return "Pessoa alterada com sucesso!!!";
            } else {
                return "Pessoa NÃO alterada!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Pessoa NÃO alterada!!!";
        }
    }

    public String excluirPessoa(PessoaDTO pessoaDTO) {
        try {
            if (pessoaDAO.excluirPessoa(pessoaDTO)) {
                return "Pessoa excluída com sucesso!!!";
            } else {
                return "Pessoa NÃO excluída!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Pessoa NÃO excluída!!!";
        }
    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
}