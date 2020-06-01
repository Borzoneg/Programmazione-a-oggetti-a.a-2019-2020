package libreria;

public class EditoreInesistente extends Exception {
    
    String nome;
    
    EditoreInesistente(String nome){
	this.nome = nome;
    }
    public String getMsg() {
	return String.format("%s non è presente", nome);
    }
}
