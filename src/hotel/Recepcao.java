package hotel;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import easyaccept.EasyAccept;
import exceptions.HospedeInvalidoException;
import exceptions.RecepcaoInvalidaException;
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

	public void removeHospede(String id) throws Exception {
		if (!id.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+")) {
			if (!id.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+\\.[ a-zA-Z]+")) {
				throw new RecepcaoInvalidaException("Erro na remocao do Hospede. Formato de email invalido.");
			}
			throw new RecepcaoInvalidaException("Erro na remocao do Hospede. Formato de email invalido.");
		}
		cadastros.remove(buscaHospede(id));
	}

	public String getInfoHospede(String id, String atributo) throws Exception {
		Hospede hospede = buscaHospede(id);
		if(hospede == null){
			throw new Exception("Erro na consulta de hospede. Hospede de email " + id + " nao foi cadastrado(a).");
		}
		if (atributo.equalsIgnoreCase("nome")) {
			return hospede.getNome();
		} else if (atributo.equalsIgnoreCase("Data de Nascimento")) {
			return hospede.getAno();
		}
		return hospede.getEmail();
	}

	public void atualizaCadastro(String id, String atributo, String valor) throws Exception {
		if (atributo.equalsIgnoreCase("nome")) {
			if(valor == null || valor.trim().isEmpty()){
				throw new RecepcaoInvalidaException("Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
			}if (!valor.trim().matches("[ a-zA-Z]*")) {
				throw new RecepcaoInvalidaException("Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede esta invalido.");
			}buscaHospede(id).setNome(valor);
		} else if (atributo.equalsIgnoreCase("email")){
			if(valor == null || valor.trim().isEmpty()){
				throw new RecepcaoInvalidaException("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
			}
			if (!valor.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+")) {
				if (!valor.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+\\.[ a-zA-Z]+")) {
					throw new RecepcaoInvalidaException("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
				}
			}
			buscaHospede(id).setEmail(valor);
		}else if(atributo.equalsIgnoreCase("data de nascimento")){
			if(valor == null || valor.trim().isEmpty()){
				throw new HospedeInvalidoException("Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio."); 
			}
			if(!valor.trim().matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/\\d{4}")){
				throw new HospedeInvalidoException("Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
			}
			if(!validaIdade(valor)){
				throw new HospedeInvalidoException("Erro na atualizacao do cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
			}
			
			buscaHospede(id).setAno(valor);
		}
	}
	
	public boolean realizaCheckin(String email, int qtdeDias, String idQuarto, String tipoQuarto) throws Exception {

		if (email == null || email.trim().isEmpty()) {
			throw new HospedeInvalidoException("Erro ao realizar checkin. Email do(a) hospede nao pode ser vazio.");
		}
		if (!email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+")) {
			if (!email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+\\.[ a-zA-Z]+")) {
				throw new HospedeInvalidoException("Erro ao realizar checkin. Email do(a) hospede esta invalido.");
			}
		}
		if (idQuarto == null || idQuarto.trim().isEmpty() || (!idQuarto.matches("[0-9]+[ A-Z]"))) {
			throw new Exception("Erro ao realizar checkin. ID do quarto invalido, use apenas numeros ou letras.");
		}
		if (qtdeDias <= 0) {
			throw new Exception("Erro ao realizar checkin. Quantidade de dias esta invalida.");
		}
		if (!(tipoQuarto.equalsIgnoreCase("Presidencial") || tipoQuarto.equals("Simples")
				|| tipoQuarto.equalsIgnoreCase("Luxo"))) {
			throw new Exception("Erro ao realizar checkin. Tipo de quarto invalido.");
		}
		Hospede buscado = buscaHospede(email);
		if (buscado == null) {
			throw new Exception("Erro ao realizar checkin. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		
		Estadia estadia = new Estadia(idQuarto, getTipoQuarto(tipoQuarto), qtdeDias);
		return cadastros.get(buscado).add(estadia);
	}
	
	private Quartos getTipoQuarto(String tipo) {
		if (tipo.equalsIgnoreCase("Presidencial")) {
			return Quartos.PRESIDENCIAL;
		} else if (tipo.equals("Simples")) {
			return Quartos.SIMPLES;
		} else {
			return Quartos.LUXO;
		}
	}
	
	private boolean validaIdade(String data){
		String[] dataNasc = data.split("/");
		int dia = Integer.parseInt(dataNasc[0]);
		int mes = Integer.parseInt(dataNasc[1]);
		int ano = Integer.parseInt(dataNasc[2]);
		LocalDate dataDeNascimento = new LocalDate(ano, mes, dia);
		LocalDate hoje = new LocalDate();
		Years anos = Years.yearsBetween(dataDeNascimento, hoje);
		int qtdeDeAnos = anos.getYears();
		
		if(qtdeDeAnos >= 18){
			return true;
		} else {
			return false;
		}
	}

	private Hospede buscaHospede(String id) throws Exception {
		for (Hospede hospede : cadastros.keySet()) {
			if (hospede.getEmail().equals(id)) {
				return hospede;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		args = new String[] { "hotel.Recepcao", "acceptance_test/testes_uc1.txt",
				"acceptance_test/testes_uc1_exception.txt", "acceptance_test/testes_uc2.txt",
				"acceptance_test/testes_uc2_exception.txt"};
		System.out.println();
		EasyAccept.main(args);
	}
}