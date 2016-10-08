package cartao;

/**
 * Interface para definição dos tipos de "Cartão";
 * 
 * @author wesleyga,victorboar,Lucasarc,DayvsonWes.
 *
 */
public interface TipoDeCartaoIF {

	public int addPontos(double valorGasto);

	public double aplicaDesconto(double valorCobranca);

	public double credito(int pontos);

}