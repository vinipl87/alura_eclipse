package capitulo3.ocp_dip;

public class Teste {
	public static void main(String[] args) {
		TabelaDePreco tabela = new TabelaDePrecoDiferenciada();
		ServicoDeEntrega entrega = new Frete();
		new CalculadoraDePrecos(tabela, entrega);
	}
}
