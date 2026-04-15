/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_3.ctr;

import java.sql.*;
import br.com.projeto_3.dto.ClienteDTO;
import br.com.projeto_3.dao.ClienteDAO;
import br.com.projeto_3.dao.ConexaoDAO;

public class ClienteCTR {
    ClienteDAO clienteDAO = new ClienteDAO();
    public ClienteCTR(){
    }
    public String inserirCliente(ClienteDTO clienteDTO){
        try{
            if(clienteDAO.inserirCliente(clienteDTO)){
                return "Cliente cadastrado com sucesso!!!";
            } else { 
                return "Cliente NÃO cadastrado!!!";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Cliente não cadastrado!!!";
        }
        
    }
    public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao){
        ResultSet rs = null;
        rs = clienteDAO.consultarCliente(clienteDTO, opcao);

        return rs;       
    }
    public String alterarCliente(ClienteDTO clienteDTO){
        try{
            if(clienteDAO.alterarCliente(clienteDTO)){
                return "Cliente Alterado com Sucesso!!!";
            } else{
                return "Cliente NÃO Alterado!!!";
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Cliente NÃO Alterado!!!";
        }
    }
    public String excluirCliente(ClienteDTO clienteDTO){
        try{
            if(clienteDAO.excluirCliente(clienteDTO)){
                return "Cliente Excluido com Sucesso!!!";
            } else{
                return "Cliente NÃO Excluido!!!";
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Cliente NÃO Excluido!!!";
        }
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
}
