package br.com.caelum.desafio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatematicaMalucaTest {

	@Test
	public void deveMultiplicarNumerosMaioresQue30() {
		MatematicaMaluca maluca = new MatematicaMaluca();
		assertEquals(31 * 4, maluca.contaMaluca(31));
	}

	@Test
	public void deveMultiplicarNumerosMaioresQue10EMenoresQue30() {
		MatematicaMaluca maluca = new MatematicaMaluca();
		assertEquals(29 * 3, maluca.contaMaluca(29));
	}

	@Test
	public void deveMultiplicarNumerosMenoresQue10() {
		MatematicaMaluca maluca = new MatematicaMaluca();
		assertEquals(5 * 2, maluca.contaMaluca(5));
	}
}
