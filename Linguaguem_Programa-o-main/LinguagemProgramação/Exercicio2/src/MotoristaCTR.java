



public class MotoristaCTR {
    MotoristaDAO motoristaDAO = new MotoristaDAO();
    
    public String imprimir(MotoristaDTO motoristaDTO){
        return motoristaDAO.Calcular(motoristaDTO);
    }
}
