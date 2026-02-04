



public class ClienteDAO {
    
    public String mostrarNomeRg(ClienteDTO clienteDTO){
        return "O nome informado foi: " + clienteDTO.getNome() + "\nO RG informado foi: " + clienteDTO.getRg();
    }
    public String mostrarNomeIdade(ClienteDTO clienteDTO){
        return "O nome informado foi: " + clienteDTO.getNome() + "\nA Idade informada foi: " + clienteDTO.getIdade();
    }
}
