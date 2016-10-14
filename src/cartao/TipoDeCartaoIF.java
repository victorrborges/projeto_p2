package cartao;

import java.io.Serializable;

/**
 * Interface para definição dos tipos de "Cartão";
 * 
 * @author wesleyga,victorboar,Lucasarc,DayvsonWes.
 *
 */
public interface TipoDeCartaoIF extends Serializable {

	/**
	 * 
	 * @param valorGasto
	 *            Valor total gasto na compra
	 * @return retorna a quantidade de pontos adicionados em relacao ao valor
	 *         gasto.
	 */
	public int addPontos(double valorGasto);

	/**
	 * 
	 * @param valorCobranca
	 *            Aplica desconto no valor cobrado da compra com base no cartao
	 *            que o hospede possui.
	 * @return Retorna o valor descontado.
	 */
	public double aplicaDesconto(double valorCobranca);

	/**
	 * Converte os pontos do parametro em dinheiro.
	 * 
	 * @param pontos
	 *            Quantidade de pontos do cartao.
	 * @return Retorna a quantidade em dinheiro da conversao dos pontos.
	 */
	public double credito(int pontos);

}
