/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_2.ctr;

import java.sql.ResultSet;
import br.com.projeto_2.dto.FornecedorDTO;
import br.com.projeto_2.dto.ProdutoDTO;
import br.com.projeto_2.dao.ProdutoDAO;
import br.com.projeto_2.dao.ConexaoDAO;

public class ProdutoCTR {
    
    ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public ProdutoCTR() {
    }
    
    public String inserirProduto(ProdutoDTO produtoDTO, FornecedorDTO fornecedorDTO){
        try{
            if(produtoDAO.inserirProduto(produtoDTO, fornecedorDTO)){
                return "Produto cadastrado com sucesso!!!";
            } else { 
                return "Produto NÃO cadastrado!!!";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Produto não cadastrado!!!";
        }   
    }
    
    public String alterarProduto(ProdutoDTO produtoDTO, FornecedorDTO fornecedorDTO){
        try{
            if(produtoDAO.alterarProduto(produtoDTO, fornecedorDTO)){
                return "Produto Alterado com Sucesso!!!";
            } else{
                return "Produto NÃO Alterado!!!";
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Produto NÃO Alterado!!!";
        }
    }
    
    public String excluirProduto(ProdutoDTO produtoDTO){
        try{
            if(produtoDAO.excluirProduto(produtoDTO)){
                return "Produto Excluido com Sucesso!!!";
            } else{
                return "Produto NÃO Excluido!!!";
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Produto NÃO Excluido!!!";
        }
    }
    
     public ResultSet consultarProduto(ProdutoDTO produtoDTO, int opcao){
        ResultSet rs = null;
        rs = produtoDAO.consultarProduto(produtoDTO, opcao);

        return rs;       
    }
     
     public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}
