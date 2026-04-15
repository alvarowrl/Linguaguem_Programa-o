/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_3.ctr;

import java.sql.ResultSet;
import br.com.projeto_3.dto.FornecedorDTO;
import br.com.projeto_3.dao.FornecedorDAO;
import br.com.projeto_3.dao.ConexaoDAO;
        
public class FornecedorCTR {
    
     FornecedorDAO fornecedorDAO = new FornecedorDAO();
    public FornecedorCTR(){
    }
    
    public String inserirFornecedor(FornecedorDTO fornecedorDTO){
        try{
            if(fornecedorDAO.inserirFornecedor(fornecedorDTO)){
                return "Fornecedor cadastrado com sucesso!!!";
            } else { 
                return "Fornecedor NÃO cadastrado!!!";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Fornecedor não cadastrado!!!";
        }   
    }
    
     public ResultSet consultarFornecedor(FornecedorDTO fornecedorDTO, int opcao){
        ResultSet rs = null;
        rs = fornecedorDAO.consultarFornecedor(fornecedorDTO, opcao);

        return rs;       
    }
     
     public String alterarCliente(FornecedorDTO fornecedorDTO){
        try{
            if(fornecedorDAO.alterarFornecedor(fornecedorDTO)){
                return "Fornecedor Alterado com Sucesso!!!";
            } else{
                return "Fornecedor NÃO Alterado!!!";
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Fornecedor NÃO Alterado!!!";
        }
    }
     public String excluirFornecedor(FornecedorDTO fornecedorDTO){
        try{
            if(fornecedorDAO.excluirFornecedor(fornecedorDTO)){
                return "Fornecedor Excluido com Sucesso!!!";
            } else{
                return "Fornecedor NÃO Excluido!!!";
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Fornecedor NÃO Excluido!!!";
        }
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}