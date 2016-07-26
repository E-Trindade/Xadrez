package pecas;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import enums.Time;
import tabuleiro.Casa;
import tabuleiro.FrameTabuleiro;
import tabuleiro.TabuleiroIterator;

public class Rei extends Peca {

	public Rei(Time time) {
		super(time);
	}

	@Override
	public Image getImage() {
		if (getTime() == Time.BRANCO)
			return FrameTabuleiro.reiBranco;
		return FrameTabuleiro.reiPreto;
	}

	@Override
	public List<Casa> calcularMovimentosDisponiveis(TabuleiroIterator iterator) {
		ArrayList<Casa> movimentosPossiveis = new ArrayList<Casa>();
		Casa proxCasa;

		// Movimentos ortogonais
		proxCasa = iterator.moverFrente();
		if (proxCasa != null)
			movimentosPossiveis.add(proxCasa);

		proxCasa = iterator.moverTras();
		if (proxCasa != null)
			movimentosPossiveis.add(proxCasa);

		proxCasa = iterator.moverDireita();
		if (proxCasa != null)
			movimentosPossiveis.add(proxCasa);

		proxCasa = iterator.moverEsquerda();
		if (proxCasa != null)
			movimentosPossiveis.add(proxCasa);

		// diagonais
		proxCasa = iterator.moverDiagonal(1, 1);
		if (proxCasa != null)
			movimentosPossiveis.add(proxCasa);

		proxCasa = iterator.moverDiagonal(1, -1);
		if (proxCasa != null)
			movimentosPossiveis.add(proxCasa);

		proxCasa = iterator.moverDiagonal(-1, 1);
		if (proxCasa != null)
			movimentosPossiveis.add(proxCasa);

		proxCasa = iterator.moverDiagonal(-1, -1);
		if (proxCasa != null)
			movimentosPossiveis.add(proxCasa);

		return movimentosPossiveis;
	}

	/**
	 * @param iterator
	 * @return verdadeiro se alguma das peças das redondezas pode atacar esse rei
	 */
	public boolean detectaXeque(TabuleiroIterator iterator) {
		iterator.setResetarAposMovimento(false);
		iterator.moverFrenteEnquantoConseguir(null);
		if (iterator.getCasaAtual() != null && iterator.getCasaAtual().mostrarMovimentosDisponiveis().contains(iterator.getCasaInicial()))
			return true;
		iterator.resetarIterator();
		iterator.moverTrasEnquantoConseguir(null);
		if (iterator.getCasaAtual() != null && iterator.getCasaAtual().mostrarMovimentosDisponiveis().contains(iterator.getCasaInicial()))
			return true;
		iterator.resetarIterator();
		iterator.moverDireitaEnquantoConseguir(null);
		if (iterator.getCasaAtual() != null && iterator.getCasaAtual().mostrarMovimentosDisponiveis().contains(iterator.getCasaInicial()))
			return true;
		iterator.resetarIterator();
		iterator.moverEsquerdaEnquantoConseguir(null);
		if (iterator.getCasaAtual() != null && iterator.getCasaAtual().mostrarMovimentosDisponiveis().contains(iterator.getCasaInicial()))
			return true;
		iterator.resetarIterator();
		iterator.moverDiagonalEnquantoConseguir(1, 1, null);
		if (iterator.getCasaAtual() != null && iterator.getCasaAtual().mostrarMovimentosDisponiveis().contains(iterator.getCasaInicial()))
			return true;
		iterator.resetarIterator();
		iterator.moverDiagonalEnquantoConseguir(1, -1, null);
		if (iterator.getCasaAtual() != null && iterator.getCasaAtual().mostrarMovimentosDisponiveis().contains(iterator.getCasaInicial()))
			return true;
		iterator.resetarIterator();
		iterator.moverDiagonalEnquantoConseguir(-1, 1, null);
		if (iterator.getCasaAtual() != null && iterator.getCasaAtual().mostrarMovimentosDisponiveis().contains(iterator.getCasaInicial()))
			return true;
		iterator.resetarIterator();
		iterator.moverDiagonalEnquantoConseguir(-1, -1, null);
		if (iterator.getCasaAtual() != null && iterator.getCasaAtual().mostrarMovimentosDisponiveis().contains(iterator.getCasaInicial()))
			return true;
		iterator.resetarIterator();

		int[][] movimentosCavalo = { { 1, 2 }, { 2, 1 }, { -1, -2 }, { -2, -1 }, { -1, 2 }, { -2, 1 }, { 1, -2 }, { 2, -1 } };
		for (int[] coord : movimentosCavalo) {
			Casa casa = iterator.moverDiagonal(coord[0], coord[1]);
			if (casa != null && !casa.isVazia() && casa.mostrarMovimentosDisponiveis().contains(iterator.getCasaInicial()))
				return true;
			iterator.resetarIterator();
		}
		return false;
	}

}
