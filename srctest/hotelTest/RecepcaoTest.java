package hotelTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import exceptions.SistemaInvalidoException;
import hotel.HotelController;

public class RecepcaoTest {

	private HotelController recepcao;
	
	@Before
	public void RecepcaoTest(){
		this.recepcao = new HotelController();
	}
	@Test
	public void TestCadastroeRemocao() throws SistemaInvalidoException{
		Assert.assertEquals("jurubeba@hotmail.com", recepcao.cadastraHospede("Jurubeba", "jurubeba@hotmail.com", "28/01/1997"));
		Assert.assertEquals(1, recepcao.totalAtivas());
		recepcao.removeHospede("jurubeba@hotmail.com");
		Assert.assertEquals(0, recepcao.totalAtivas());
	}
	@Test
	public void TestCheckinECheckout() throws SistemaInvalidoException{
		recepcao.cadastraHospede("Jurubeba", "jurubeba@hotmail.com", "28/01/1997");
		recepcao.realizaCheckin("jurubeba@hotmail.com", 10, "15A", "Simples");
		Assert.assertEquals("Jurubeba", recepcao.getInfoHospede("jurubeba@hotmail.com", "Nome"));
		Assert.assertEquals("1", recepcao.getInfoHospedagem("jurubeba@hotmail.com", "Hospedagens Ativas"));
		recepcao.realizaCheckout("jurubeba@hotmail.com", "15A");
		Assert.assertEquals("1", recepcao.consultaTransacoes("Quantidade"));
		Assert.assertEquals("R$1000,00", recepcao.consultaTransacoes("Total"));
	}
	@Test
	public void TestExceptions() throws SistemaInvalidoException{
		try{
			recepcao.cadastraHospede("asdf23423", "algo@hotmail.com", "28/12/1963");
		}catch (SistemaInvalidoException e) {
			Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.", e.getMessage() );
		}try{
			recepcao.cadastraHospede("alguma coisa", "algo.com", "28/12/1963");
		}catch (SistemaInvalidoException e) {
			Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede esta invalido.", e.getMessage());
		}try{
			recepcao.cadastraHospede("alguma coisa", "algo@hotmail.com", "28/12asdf963");
		}catch(SistemaInvalidoException e){
			Assert.assertEquals("Erro no cadastro de Hospede. Formato de data invalido.", e.getMessage());
		}try{
			recepcao.cadastraHospede("alguma coisa", "algo@hotmail.com", "28/12/2010");
		}catch (SistemaInvalidoException e) {
			Assert.assertEquals("Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.", e.getMessage());
		}
	}
}
