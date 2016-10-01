package cartao;

public interface TipoDeCartaoIF {

	public int addPontos(double valorGasto);
	
	public double aplicaDesconto(double valorCobranca);
	
	public double credito(int pontos);
	
}
