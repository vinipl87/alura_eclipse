package br.com.caelum.leilao.dominio;

import static br.com.caelum.leilao.matcher.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class LeilaoTest {

	private Usuario joao;

	@Before
	public void setUp() {
		joao = new Usuario("joao");
	}

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		assertEquals(0, leilao.getLances().size());

		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000));

		assertEquals(2, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarDoisTestesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steve = new Usuario("Steve Jobs");

		leilao.propoe(new Lance(steve, 2000));
		leilao.propoe(new Lance(steve, 3000));

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steve = new Usuario("Steve Jobs");
		Usuario bill = new Usuario("Bill Gates");

		leilao.propoe(new Lance(steve, 2000));
		leilao.propoe(new Lance(bill, 3000));

		leilao.propoe(new Lance(steve, 4000));
		leilao.propoe(new Lance(bill, 5000));

		leilao.propoe(new Lance(steve, 6000));
		leilao.propoe(new Lance(bill, 7000));

		leilao.propoe(new Lance(steve, 8000));
		leilao.propoe(new Lance(bill, 9000));

		leilao.propoe(new Lance(steve, 10000));
		leilao.propoe(new Lance(bill, 11000));

		// Deve ser ignorado
		leilao.propoe(new Lance(steve, 12000));

		assertEquals(10, leilao.getLances().size());
		assertEquals(11000, leilao.ultimoLanceDado().getValor(), 0.00001);
	}

	@Test
	public void deveDobrarOLanceDadoAnteriormentePeloUsuarioDepoisDeTerDadoLance() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steve = new Usuario("Steve Jobs");
		Usuario bill = new Usuario("Bill Gates");

		leilao.propoe(new Lance(steve, 2000));
		leilao.propoe(new Lance(bill, 3000));

		leilao.dobrarLance(steve);

		assertEquals(3, leilao.getLances().size());
		assertEquals(2000 * 2, leilao.ultimoLanceDado().getValor(), 0.00001);

	}

	@Test
	public void deveVerificarSeLeilaoTemDeterminadoLance() {
		Leilao leilao = new CriadorDeLeilao().para("Iphone X").constroi();
		leilao.propoe(new Lance(joao, 1000));

		assertThat(leilao.getLances().size(), equalTo(1));
		assertThat(leilao, temUmLance(new Lance(joao, 1000)));
	}
}
