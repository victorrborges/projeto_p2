package hotel;

import java.util.List;

import easyaccept.EasyAccept;
import exceptions.HospedeInvalidoException;
import exceptions.SistemaInvalidoException;

import java.util.ArrayList;
import java.util.HashMap;

public class Recepcao {
	private HashMap<Hospede, List<Estadia>> cadastros;

	public Recepcao() {
		this.cadastros = new HashMap<>();
	}

	public void iniciaSistema() {

	}

	public void fechaSistema() {

	}

	public String cadastraHospede(String nome, String email, String dataDeNascimento) throws SistemaInvalidoException {
		Hospede hospede = new Hospede(nome, email, dataDeNascimento);
		List<Estadia> estadias = new ArrayList<Estadia>();
		this.cadastros.put(hospede, estadias);
		return email;
	}

	public void removeHospede(String id) {
		cadastros.remove(buscaHospede(id));
	}

	public String getInfoHospede(String id, String atributo) throws Exception {
		Hospede hospede = buscaHospede(id);
		if (hospede == null) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + id + " nao foi cadastrado(a).");
		}
		if (atributo.equalsIgnoreCase("nome")) {
			return hospede.getNome();
		} else if (atributo.equalsIgnoreCase("Data de Nascimento")) {
			return hospede.getAno();
		}
		return hospede.getEmail();
	}

	public void atualizaCadastro(String id,String atributo,String valor) throws Exception {
		Hospede hospede = buscaHospede(id);
		if(atributo.equalsIgnoreCase("nome")){
			if(valor == null || valor.trim().isEmpty()){
				throw new HospedeInvalidoException("Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio."); 
			}
			if(!valor.trim().matches("[ a-zA-Z]*")){
				throw new HospedeInvalidoException("Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede esta invalido.");
			}
			hospede.setNome(valor);
		}else if(atributo.equalsIgnoreCase("email")){
			if(valor == null || valor.trim().isEmpty()){
				throw new HospedeInvalidoException("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido."); 
			}
			if(!valor.matches("[ a-zA-Z]*@[ a-zA-Z]*\\.[ a-zA-Z]*")){
				if(!valor.matches("[ a-zA-Z]*@[ a-zA-Z]*\\.[ a-zA-Z]*\\.[ a-zA-Z]*")){
					throw new HospedeInvalidoException("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
				}
			}
			hospede.setEmail(valor);
		}else if(atributo.equalsIgnoreCase("data de nascimento")){
			if(valor == null || valor.trim().isEmpty()){
				throw new HospedeInvalidoException("Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio."); 
			}
			if(!valor.trim().matches("\\d{2}/\\d{2}/\\d{4}")){
				throw new HospedeInvalidoException("Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
			}
			
			hospede.setAno(valor);
		}
	}

	private Hospede buscaHospede(String id) {
		for (Hospede hospede : cadastros.keySet()) {
			if (hospede.getEmail().equals(id)) {
				return hospede;
			}
		}
		return null;
	}

	public boolean contemHospede(Hospede hospede) {
		return cadastros.containsKey(hospede);
	}

	public boolean fazCheckIn(Hospede hospede, Estadia estadia) throws Exception {
		if (hospede == null) {
			throw new Exception("Hospede nao pode ser nulo");
		}
		if (estadia == null) {
			throw new Exception("Estadia nao pode ser nulo");
		}
		if (!contemHospede(hospede)) {
			throw new Exception("Hospede nao cadastrado no sistema");
		}
		return cadastros.get(hospede).add(estadia);
	}

	public static void main(String[] args) {
		args = new String[] { "hotel.Recepcao", "acceptance_test/testes_uc1.txt",
				"acceptance_test/testes_uc1_exception.txt" };
		System.out.println();
		EasyAccept.main(args);
	}
}
