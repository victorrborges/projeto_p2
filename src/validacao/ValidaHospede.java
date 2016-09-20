package validacao;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class ValidaHospede {

	public ValidaHospede() {
	}

	public boolean validaNome(String nome) {
		return nome.trim().matches("[ a-zA-Z]+");
	}

	public boolean validaIdade(String data) {
		String[] dataNasc = data.split("/");
		int dia = Integer.parseInt(dataNasc[0]);
		int mes = Integer.parseInt(dataNasc[1]);
		int ano = Integer.parseInt(dataNasc[2]);
		LocalDate dataDeNascimento = new LocalDate(ano, mes, dia);
		LocalDate hoje = new LocalDate();
		Years anos = Years.yearsBetween(dataDeNascimento, hoje);
		int qtdeDeAnos = anos.getYears();

		if (qtdeDeAnos >= 18) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validaEmail(String email) {
		if (email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+")
				|| email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+\\.[ a-zA-Z]+")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validaData(String dataDeNascimento) {
		return dataDeNascimento
				.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/\\d{4}");
	}

	public boolean validaIdQuarto(String id) {
		if (id == null || id.trim().isEmpty() || (!id.matches("[0-9]+[ A-Z]"))) {
			return false;
		} else {
			return true;
		}
	}
}