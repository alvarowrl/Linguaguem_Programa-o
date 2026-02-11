


public class MotoristaDAO {
    private String mensagem;
    
    public String Calcular(MotoristaDTO motoristaDTO){
        if(motoristaDTO.getIdade()>=18){
            this.mensagem = "O motorista pode ter CNH.";
        }else{
            this.mensagem = "O motorista NÃO pode ter CNH.";
        }
        return this.mensagem;
    }
}
