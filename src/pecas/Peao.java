package pecas;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import enums.Time;
import tabuleiro.Casa;
import tabuleiro.FrameTabuleiro;
import tabuleiro.TabuleiroIterator;

public class Peao extends Peca {

	public Peao(Time time) {
		super(time);
	}

	@Override
	public Image getImage() {
		if (getTime() == Time.BRANCO)
			return FrameTabuleiro.peaoBranco;
		return FrameTabuleiro.peaoPreto;
	}

	@Override
	public List<Casa> calcularMovimentosDisponiveis(TabuleiroIterator iterator) {

		iterator.setResetarAposMovimento(false);

		ArrayList<Casa> movimentosPossiveis = new ArrayList<Casa>();

		// Primeira casa pra frente
		Casa proxCasa = iterator.moverFrente();
		if (proxCasa != null && proxCasa.isVazia()) {
			movimentosPossiveis.add(proxCasa);
			if (!getMoveu()) {
				// Segunda casa
				proxCasa = iterator.moverFrente();
				if (proxCasa.isVazia())
					movimentosPossiveis.add(proxCasa);
			}
		}

		iterator.resetarIterator();
		
		proxCasa = iterator.moverDiagonal(iterator.getCoef(), 1);
		if (proxCasa != null && !proxCasa.isVazia())
			movimentosPossiveis.add(proxCasa);

		iterator.resetarIterator();
		
		proxCasa = iterator.moverDiagonal(iterator.getCoef(), -1);
		if (proxCasa != null && !proxCasa.isVazia())
			movimentosPossiveis.add(proxCasa);

		return movimentosPossiveis;
	}

}
