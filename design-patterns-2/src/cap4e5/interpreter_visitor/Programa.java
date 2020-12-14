package cap4e5.interpreter_visitor;

public class Programa {
	public static void main(String[] args) {	
		Expressao esquerda = new Subtracao(new Numero(10),new Numero(5));
		Expressao direita = new Soma(new Numero(2),new Numero(10));
		Expressao soma = new Soma(esquerda,direita);
		
		int resultado = soma.avalia();
		
		System.out.println(resultado);
		
		Expressao raiz = new RaizQuadrada(new Numero(9));
		Visitor impressora = new ImpressoraVisitor();
		soma.aceita(impressora);
	}
}
	