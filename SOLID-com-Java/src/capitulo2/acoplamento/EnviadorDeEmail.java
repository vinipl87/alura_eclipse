package capitulo2.acoplamento;

public class EnviadorDeEmail implements AcaoAposGerarNota{
	
	@Override
    public void executa(NotaFiscal nf) {
        System.out.println("envia email da nf " + nf.getId());
    }
}