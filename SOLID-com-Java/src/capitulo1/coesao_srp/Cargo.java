package capitulo1.coesao_srp;

public enum Cargo {
	DESENVOLVEDOR(new DezOuVintePorcento()),
	DBA(new QuinzeOuVintePorcento()),
	TESTER(new QuinzeOuVintePorcento());
	
	private RegraDeCalculo regra;

	Cargo(RegraDeCalculo regra){
		this.regra = regra;
	}

	public RegraDeCalculo getRegra() {
		return regra;
	}
}
