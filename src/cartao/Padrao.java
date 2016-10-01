package cartao;

public class Padrao implements TipoDeCartaoIF {

	private static final double RECOMPENSA = 0.1;
	private static final double CREDITO = 0.1;

	@Override
	public int addPontos(double valorGasto) {
		return (int)(valorGasto * RECOMPENSA);
		
	}
	@Override
	public double aplicaDesconto(double valorCobranca) {
		return valorCobranca;
		
	}
	@Override
	public double credito(int pontos) {
		return pontos * CREDITO;
		
	}
	
}
