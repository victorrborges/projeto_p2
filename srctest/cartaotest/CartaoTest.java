package cartaotest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cartao.*;

public class CartaoTest {
	private CartaoFidelidade cartao;
	
	
	@Before
	public void test(){
		this.cartao = new CartaoFidelidade();
	}
	@Test
	public void testPontos() throws Exception{
		assertEquals(0, cartao.getPontos());
		cartao.addPontos(100);
		assertEquals(10, cartao.getPontos());
		cartao.convertePontos(10);
		assertEquals(0, cartao.getPontos());
	
	}
	@Test
	public void testStrategy() throws Exception{
		assertTrue(cartao.getTipoDeCartao() instanceof Padrao);
		cartao.addPontos(3500);
		cartao.verificaTipo();
		assertTrue(cartao.getTipoDeCartao() instanceof Premium);
		cartao.addPontos(8500);
		cartao.verificaTipo();
		assertTrue(cartao.getTipoDeCartao() instanceof Vip);
	}
}
