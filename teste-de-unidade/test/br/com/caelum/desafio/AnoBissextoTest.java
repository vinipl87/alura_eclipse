package br.com.caelum.desafio;

import org.junit.Assert;
import org.junit.Test;

public class AnoBissextoTest {

	@Test
	public void deveEntenderUmAnoBissexto() {
		AnoBissexto ano = new AnoBissexto();
		Assert.assertEquals(true, ano.ehBissexto(2016));
	}

	@Test
	public void deveRetornarFalsoAUmAnoNaoBissexto() {
		AnoBissexto ano = new AnoBissexto();
		Assert.assertEquals(false, ano.ehBissexto(2010));
	}

}
