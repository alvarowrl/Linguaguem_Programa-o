/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.dao;

import java.sql.*;
import br.com.projeto_1.dto.ClienteDTO;

public class ClienteDAO {
     
    public ClienteDAO(){
}
    
    private ResultSet rs = null;
    private Statement stmt = null;
       public boolean inserirCliente(ClienteDTO clienteDTO){
           try{
               ConexaoDAO.ConectDB();
               stmt = ConexaoDAO.con.createStatement();
               
               String comando = "Insert into cliente (nome_cli, logradouro_cli, numero_cli, "
                         + "bairro_cli, cidade_cli, estado_cli, cep_cli, cpf_cli, rg_cli) values ( "
                         + "'" + clienteDTO.getNome_cli() + "', "
                         + "'" + clienteDTO.getLogradouro_cli() + "', "
                         + clienteDTO.getNumero_cli()+ ", "
                         + "'" + clienteDTO.getBairro_cli() + "', "
                         + "'" + clienteDTO.getCidade_cli() + "', "
                         + "'" + clienteDTO.getEstado_cli() + "', "
                         + "'" + clienteDTO.getCep_cli() + "', "
                         + "'" + clienteDTO.getCpf_cli() + "', "
                         + "'" + clienteDTO.getRg_cli() + "') ";
               
               stmt.execute(comando.toUpperCase());
               
               ConexaoDAO.con.commit();
               
               stmt.close();
               return true;
           }
           catch (Exception e){
               System.out.println(e.getMessage());
               return false;
           }
           finally{
            ConexaoDAO.CloseDB();
           }
       }
}

