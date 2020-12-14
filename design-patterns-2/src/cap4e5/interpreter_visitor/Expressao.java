package cap4e5.interpreter_visitor;

public interface Expressao {
	public int avalia();
	void aceita(Visitor impressora);
}
