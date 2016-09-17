package hotel;

import java.util.List;

import easyaccept.EasyAccept;
import exceptions.SistemaInvalidoException;

import java.util.ArrayList;
import java.util.HashMap;

public class Recepcao {
	private HashMap<Hospede,List<Estadia>> cadastros;
	
	public Recepcao(){
		this.cadastros = new HashMap<>();
	}
	public void iniciaSistema(){
		
	}
	public void fechaSistema(){
		
	}
	public String cadastraHospede(String nome,String email,String dataDeNascimento) throws SistemaInvalidoException{
		Hospede hospede = new Hospede(nome, email, dataDeNascimento);
		List<Estadia> estadias = new ArrayList<Estadia>();
		this.cadastros.put(hospede,estadias);
		return email;
	}
	public void removeHospede(String id){
		cadastros.remove(buscaHospede(id));
	}
	
	public String getInfoHospede(String id,String atributo) throws Exception{
		Hospede hospede = buscaHospede(id);
		if(hospede == null){
			throw new Exception("Erro na consulta de hospede. Hospede de email "+id+" nao foi cadastrado(a).");
		}
		if(atributo.equalsIgnoreCase("nome")){
			return hospede.getNome();
		}else if(atributo.equalsIgnoreCase("Data de Nascimento")){
			return hospede.getAno();
		}return hospede.getEmail();
	}
	public void atualizaCadastro(String id,String atributo,String valor){
		Hospede hospede = buscaHospede(id);
		if(atributo.equalsIgnoreCase("nome")){
			hospede.setNome(valor);
		}else if(atributo.equalsIgnoreCase("email")){
			hospede.setEmail(valor);
		}hospede.setAno(valor);
	}
	private Hospede buscaHospede(String id){
		for(Hospede hospede: cadastros.keySet()){
			if(hospede.getEmail().equals(id)){
				return hospede;
			}
		}return null;
	}
	public static void main(String[] args) {
		args = new String[] { "hotel.Recepcao", "acceptance_test/testes_uc1.txt","acceptance_test/testes_uc1_exception.txt"};
		System.out.println();
		EasyAccept.main(args);
	}
}
