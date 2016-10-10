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
		assertEquals("R$1,00", cartao.convertePontos(10));
		assertEquals(0, cartao.getPontos());
	
	}
	@Test
	public void testStrategy() throws Exception{
		assertTrue(cartao.getTipoDeCartao() instanceof Padrao);
		cartao.addPontos(4000);
		assertEquals("R$11,10", cartao.convertePontos(35));
		cartao.verificaTipo();
		assertTrue(cartao.getTipoDeCartao() instanceof Premium);
		assertEquals("R$11,10", cartao.convertePontos(35));
		cartao.addPontos(8500);
		cartao.verificaTipo();
		assertTrue(cartao.getTipoDeCartao() instanceof Vip);
		assertEquals("R$26,00", cartao.convertePontos(35));
	}
}
