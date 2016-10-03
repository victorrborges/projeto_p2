package hotelTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import exceptions.SistemaInvalidoException;
import hotel.RecepcaoController;

public class RecepcaoTest {

	private RecepcaoController recepcao;
	
	@Before
	public void RecepcaoTest(){
		this.recepcao = new RecepcaoController();
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
	}
}
