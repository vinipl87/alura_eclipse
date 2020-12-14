package br.com.caelum.desafio;

import org.junit.Assert;
import org.junit.Test;

public class PalindromoTest {

	@Test
	public void deveValidarPalindromos() {
		Palindromo marrocos = new Palindromo();
		boolean resultado = marrocos.ehPalindromo("A base do teto desaba");
		Assert.assertEquals(true, resultado);
	}

}
