package pecas;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import enums.Time;
import tabuleiro.Casa;
import tabuleiro.FrameTabuleiro;
import tabuleiro.TabuleiroIterator;

public class Bispo extends Peca {

	public Bispo(Time time) {
		super(time);
	}

	/**
	 * O bispo movimenta apenas através das diagonais
	 * 
	 * @param iterator
	 * @return
	 */
	@Override
	public List<Casa> calcularMovimentosDisponiveis(TabuleiroIterator iterator) {
		ArrayList<Casa> movimentosPossiveis = new ArrayList<Casa>();
		iterator.moverDiagonalEnquantoConseguir(1, 1, movimentosPossiveis);
		iterator.moverDiagonalEnquantoConseguir(1, -1, movimentosPossiveis);
		iterator.moverDiagonalEnquantoConseguir(-1, 1, movimentosPossiveis);
		iterator.moverDiagonalEnquantoConseguir(-1, -1, movimentosPossiveis);

		return movimentosPossiveis;
	}

	@Override
	public Image getImage() {
		if (getTime() == Time.BRANCO)
			return FrameTabuleiro.bispoBranco;
		return FrameTabuleiro.bispoPreto;
	}

}
