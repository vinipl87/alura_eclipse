package br.com.caelum.leilao.servico;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LanceTest {

	private Usuario joao;

	@Before
	public void setUp() {
		joao = new Usuario("Joao");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveRecusarLancesComValorDeZero() {
		new CriadorDeLeilao().para("Playstation 3 novo").lance(joao, 0.0).constroi();
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveRecusarLancesComValorNegativo() {
		new CriadorDeLeilao().para("Playstation 3 novo").lance(joao, -200.0).constroi();
	}
}
