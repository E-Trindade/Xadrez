package enums;

import java.awt.Color;

public enum Cor {
//	PADRAO_BRANCA(Color.decode("#ccd9ff")), 
	PADRAO_BRANCA(Color.LIGHT_GRAY), 
	PADRAO_PRETA(Color.BLACK),
	ORIGEM_MOVIMENTO(Color.ORANGE),
	SELECIONADA_MOVIMENTO(new Color(255, 236, 179)),
	SELECIONADA_ATAQUE(Color.RED);
	
	
	private Color cor;
	
	Cor(Color cor){
		this.cor = cor;
	}
	
	public Color getColor() {
		return cor;
	}
}
