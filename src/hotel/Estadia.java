package hotel;

public class Estadia {
	private Quarto quarto;
	private int qtdeDias;
	private double gastos;
	
	public Estadia (String id,Quartos diaria, int qtdeDias){
		this.quarto = new Quarto(id, diaria);
		this.qtdeDias = qtdeDias;
		this.gastos = diaria.getPreco()*this.qtdeDias;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public int getQtdeDias() {
		return qtdeDias;
	}

	public void setQtdeDias(int qtdeDias) {
		this.qtdeDias = qtdeDias;
	}

	public double getGastos() {
		return gastos;
	}

	public void setGastos(double gastos) {
		this.gastos = gastos;
	}
	
	public double totalPago(){
		double gastoTotal = 0.0;
		gastoTotal = this.getQtdeDias() * this.getQuarto().getDiaria();
		gastoTotal += this.getGastos();
		return gastoTotal;
	}
}
