package restaurantetest;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.RestauranteInvalidoException;
import restaurante.Cardapio;

public class CardapioTest {
	private Cardapio cardapio;
	@Before
	public void test() throws RestauranteInvalidoException{
		this.cardapio = new Cardapio();
		cardapio.cadastraPrato("Carbonara",50 , "Prato tradicional italiano");
		cardapio.cadastraPrato("Peixe refogado", 40, "Peixe ao molho presto");
		cardapio.cadastraPrato("Frutos do mar", 100, "Frutos selecionados");
		cardapio.cadastraPrato("Alga", 54, "Alga");
	}
	@Test
	public void testCadastro() throws RestauranteInvalidoException{
		cardapio.cadastraRefeicao("Banquete ao mar", "Banquete de pesticos marinhos e frutos do mar", "Peixe refogado;Frutos do mar;Alga");
		assertTrue(cardapio.contemPrato("Carbonara"));
		assertTrue(cardapio.contemPrato("Peixe refogado"));
		assertTrue(cardapio.contemRefeicao("Banquete ao mar"));
		assertEquals("R$174,60", cardapio.consultaCardapioRefeicao("Banquete ao mar", "Preco"));
		assertEquals("Banquete de pesticos marinhos e frutos do mar Serao servidos: (1) Peixe refogado, (2) Frutos do mar, (3) Alga.",cardapio.consultaCardapioRefeicao("Banquete ao mar", "Descricao"));
	}
	@Test
	public void testOrdenacao(){
		cardapio.ordenaMenu("Nome");
		assertEquals("Alga", cardapio.getRefeicoes().get(0).getNome());
		assertEquals(50, cardapio.getRefeicoes().get(1).getPreco(),0.01);
		cardapio.ordenaMenu("Preco");
		assertEquals("Peixe refogado", cardapio.getRefeicoes().get(0).getNome());
	}
	@Test
	public void testExceptions(){
		try{
			cardapio.cadastraRefeicao("refeição test", "Descrição para test", "Alga");
			fail();
		}catch (RestauranteInvalidoException e) {
			assertEquals("Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.", e.getMessage());
		}try{
			cardapio.cadastraRefeicao("Resfeição tes", "Test normal", "pratoNaoexistente;outro;maisoutro");
			fail();
		}catch (RestauranteInvalidoException e) {
			assertEquals("Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.", e.getMessage());
		}
	}
}
