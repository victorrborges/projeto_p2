package hotel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import exceptions.RecepcaoInvalidaException;
import exceptions.SistemaInvalidoException;

public class Registrador  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6257126875476158041L;
	private List<Hospede> historicoHospedes;
	private List<String> historicoDeGastos;
	private List<String> historicoDeCompra;
	private double total;
	private String saida = "";

	public Registrador() {
		this.historicoHospedes = new ArrayList<Hospede>();
		this.historicoDeGastos = new ArrayList<String>();
		this.historicoDeCompra = new ArrayList<String>();
		this.total = 0;
	}

	/**
	 * Consulta as transacoes realizacoes de acordo com o indice
	 * 
	 * @param atributo
	 *            Nome, total
	 * @param indice
	 *            Indice do checkout
	 * @return Retorna o nome do hospede ou o valor total de acordo com o indice
	 *         de checkout realizado
	 * @throws SistemaInvalidoException
	 */
	public String consultaTransacoes(String atributo, int indice) throws SistemaInvalidoException {
		if (indice < 0 || indice >= historicoHospedes.size()) {
			throw new RecepcaoInvalidaException("Erro na consulta de transacoes. Indice invalido.");
		}

		if (atributo.equalsIgnoreCase("Nome")) {
			return historicoHospedes.get(indice).getNome();
		} else if (atributo.equalsIgnoreCase("Detalhes")) {
			return historicoDeCompra.get(indice);
		} else {
			return historicoDeGastos.get(indice);
		}
	}

	/**
	 * Consulta as transacoes realizadas
	 * 
	 * @param atributo
	 *            Quantidade, total ou nome
	 * @return Retorna a quantidade, o total em relacao aos checkouts realizados
	 *         ou o nome dos hospedes que fizeram checkout
	 */
	public String consultaTransacoes(String atributo) {
		if (atributo.equalsIgnoreCase("Quantidade")) {
			return String.format("%d", historicoHospedes.size());
		} else if (atributo.equals("Total")) {
			total = (int) (total * 100);
			total /= 100;
			return String.format("R$%.2f", total);
		} else {
			return saida.substring(0, saida.length() - 1);
		}
	}

	public void registraGasto(Hospede hospede, double precoTotal, String nomeProduto) {
		historicoHospedes.add(hospede);
		historicoDeGastos.add(String.format("R$%.2f", precoTotal));
		historicoDeCompra.add(nomeProduto);
		saida += hospede.getNome() + ";";
		this.total += precoTotal;
	}

	public void gravaArquivoRegistros() throws IOException {
		String FIM_DE_LINHA = System.lineSeparator();
		BufferedWriter out = new BufferedWriter(new FileWriter("arquivos_sistema/relatorios/cad_transacoes.txt"));
		int cont = 1;
		int indice = 0;
		String saida = "Historico de Transacoes:" + FIM_DE_LINHA;
		for (Hospede hospede : historicoHospedes){
			saida += "==> Nome: " + hospede.getNome() + " Gasto: " + historicoDeGastos.get(indice) + " Detalhes: " + historicoDeCompra.get(indice) + FIM_DE_LINHA;
			cont = cont + 1;
			indice = indice + 1;
		}
		double lucro = this.total/cont;
		String lucroF = String.format("%.2f", lucro);
		
		saida += "===== Resumo de transacoes =====" + FIM_DE_LINHA + "Lucro total:R$" 
		+ this.total + FIM_DE_LINHA + "Total de transacoes:" + cont + FIM_DE_LINHA 
				+ "Lucro medio por transacao: R$" + lucroF + FIM_DE_LINHA; 
		out.write(saida);
		out.close();
	}
}
