package pecas;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import enums.Time;
import tabuleiro.Casa;
import tabuleiro.FrameTabuleiro;
import tabuleiro.TabuleiroIterator;

public class Cavalo extends Peca {

	public Cavalo(Time time) {
		super(time);
	}

	@Override
	public List<Casa> calcularMovimentosDisponiveis(TabuleiroIterator iterator) {
		ArrayList<Casa> retorno = new ArrayList<Casa>();
		int[][] movimentosCavalo = { { 1, 2 }, { 2, 1 }, { -1, -2 }, { -2, -1 }, { -1, 2 }, { -2, 1 }, { 1, -2 }, { 2, -1 } };
		for (int[] coord : movimentosCavalo) {
			Casa casa = iterator.moverDiagonal(coord[0], coord[1]);
			if (casa != null)
				retorno.add(casa);
		}
		return retorno;
	}

	@Override
	public Image getImage() {
		if (getTime() == Time.BRANCO)
			return FrameTabuleiro.cavaloBranco;
		return FrameTabuleiro.cavaloPreto;
	}

}
