package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	private double media;
	private List<Lance> maiores;

	public void avaliar(Leilao leilao) {
		// lancando a excecao
		if (leilao.getLances().size() == 0)
			throw new RuntimeException("Não é possível avaliar um leilão sem lances");

		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > this.maiorLance)
				this.maiorLance = lance.getValor();
			if (lance.getValor() < this.menorLance)
				this.menorLance = lance.getValor();
		}
		calculaMediaDosLances(leilao);
		pegaOsMaioresNo(leilao);
	}

	private void pegaOsMaioresNo(Leilao leilao) {
		maiores = new ArrayList<Lance>(leilao.getLances());
		maiores.sort(Comparator.comparing(Lance::getValor).reversed());
		maiores = maiores.subList(0, maiores.size() >= 3 ? 3 : maiores.size());
	}

	private void calculaMediaDosLances(Leilao leilao) {
		double total = leilao.getLances().stream().mapToDouble(Lance::getValor).sum();
		if (total == 0)
			this.media = 0;
		else
			this.media = total / leilao.getLances().size();
	}

	public double getMaiorLance() {
		return maiorLance;
	}

	public double getMenorLance() {
		return menorLance;
	}

	public double getMedia() {
		return media;
	}

	public List<Lance> getTresMaiores() {
		return maiores;
	}

}
