package cap8.facade_singleton;

public class Programa {
	public static void main(String[] args) {
		EmpresaFacade facade = new EmpresaFacadeSingleton().getInstancia();
		
		facade.buscaCliente(cpf);
		facade.criaFatura(cliente, valor);
		facade.fazContato(cliente, cobranca);
		facade.geraCobranca(fatura);
		
	}
}
