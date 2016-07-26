package enums;

public enum EstadoCasa {
	NORMAL(null), 
	ORIGEM_MOVIMENTO(Cor.ORIGEM_MOVIMENTO),
	SELECIONADA_PARA_MOVIMENTO(Cor.SELECIONADA_MOVIMENTO),
	SELECIONADA_PARA_ATAQUE(Cor.SELECIONADA_ATAQUE),
	XEQUE(Cor.SELECIONADA_ATAQUE);

	private Cor cor;
	
	EstadoCasa(Cor cor){
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}
	
}
