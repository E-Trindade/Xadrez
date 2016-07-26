package tabuleiro;

import java.util.List;

import enums.Time;

public class TabuleiroIterator {

	Casa[][] tabuleiro;

	Casa casaAtual;
	Casa casaInicial;

	private int coef;
	private boolean retornarPecasMesmoTime = false;
	private boolean resetarAposMovimento = true;

	public TabuleiroIterator(Casa casa, Casa[][] casas) {
		this.tabuleiro = casas;
		this.casaInicial = casa;
		this.coef = casaInicial.getPeca().getTime() == Time.BRANCO ? -1 : 1;
		resetarIterator();
	}

	public Casa moverFrente() {
		irParaPosicaoRelativa(coef, 0);
		Casa novaCasa = casaAtual;
		if (resetarAposMovimento)
			resetarIterator();
		return novaCasa;
	}

	public Casa moverTras() {
		irParaPosicaoRelativa(-coef, 0);
		Casa novaCasa = casaAtual;
		if (resetarAposMovimento)
			resetarIterator();
		return novaCasa;
	}

	public Casa moverDireita() {
		irParaPosicaoRelativa(0, coef);
		Casa novaCasa = casaAtual;
		if (resetarAposMovimento)
			resetarIterator();
		return novaCasa;
	}

	public Casa moverEsquerda() {
		irParaPosicaoRelativa(0, -coef);
		Casa novaCasa = casaAtual;
		if (resetarAposMovimento)
			resetarIterator();
		return novaCasa;
	}

	public Casa moverDiagonal(int linhaIncr, int colIncr) {
		irParaPosicaoRelativa(linhaIncr, colIncr);
		Casa novaCasa = casaAtual;
		if (resetarAposMovimento)
			resetarIterator();
		return novaCasa;
	}

	public void moverFrenteEnquantoConseguir(List<Casa> casas) {
		do {
			irParaPosicaoRelativa(coef, 0);
			if (casaAtual != null && casas != null)
				casas.add(casaAtual);
		} while (casaAtual != null && casaAtual.isVazia());
		if (resetarAposMovimento)
			resetarIterator();
	}

	public void moverTrasEnquantoConseguir(List<Casa> casas) {
		do {
			irParaPosicaoRelativa(-coef, 0);
			if (casaAtual != null && casas != null)
				casas.add(casaAtual);
		} while (casaAtual != null && casaAtual.isVazia());
		if (resetarAposMovimento)
			resetarIterator();
	}

	public void moverDireitaEnquantoConseguir(List<Casa> casas) {
		do {
			irParaPosicaoRelativa(0, coef);
			if (casaAtual != null && casas != null)
				casas.add(casaAtual);
		} while (casaAtual != null && casaAtual.isVazia());
		if (resetarAposMovimento)
			resetarIterator();
	}

	public void moverEsquerdaEnquantoConseguir(List<Casa> casas) {
		do {
			irParaPosicaoRelativa(0, -coef);
			if (casaAtual != null && casas != null)
				casas.add(casaAtual);
		} while (casaAtual != null && casaAtual.isVazia());
		if (resetarAposMovimento)
			resetarIterator();
	}

	public void moverDiagonalEnquantoConseguir(int lIncr, int cIncr, List<Casa> casas) {
		do {
			irParaPosicaoRelativa(lIncr, cIncr);
			if (casaAtual != null && casas != null)
				casas.add(casaAtual);
		} while (casaAtual != null && casaAtual.isVazia());
		if (resetarAposMovimento)
			resetarIterator();
	}

	public void resetarIterator() {
		casaAtual = casaInicial;
	}

	private boolean isPosicaoValida(int linha, int coluna) {
		if (linha < 0 || linha > 7)
			return false;
		if (coluna < 0 || coluna > 7)
			return false;
		return true;
	}

	private void irParaPosicaoRelativa(int linhas, int cols) {
		int novaLinha = casaAtual.getLinha() + linhas;
		int novaColuna = casaAtual.getColuna() + cols;

		if (isPosicaoValida(novaLinha, novaColuna)) {
			casaAtual = tabuleiro[novaLinha][novaColuna];

			if (!isCasaValida(casaAtual))
				casaAtual = null;

		} else {
			casaAtual = null;
		}
	}

	private boolean isCasaValida(Casa novaCasa) {
		if (novaCasa != null && !novaCasa.isVazia())
			if (novaCasa.getPeca().getTime() == casaInicial.getPeca().getTime() && !retornarPecasMesmoTime)
				return false;
		return true;
	}

	/**
	 * Coeficiente que diz se as peças desse time andam 'para cima' ou 'para baixo'
	 */
	public int getCoef() {
		return coef;
	}

	/**
	 * Indica se o iterator deve considerar casas ocupadas por peças do mesmo time como jogadas
	 * válidas (Necessário para Roque)
	 * 
	 * @param retornarCasasMesmoTime
	 */
	public void setRetornarPecasMesmoTime(boolean retornarCasasMesmoTime) {
		this.retornarPecasMesmoTime = retornarCasasMesmoTime;
	}

	/**
	 * @param resetarAposMovimento
	 */
	public void setResetarAposMovimento(boolean resetarAposMovimento) {
		this.resetarAposMovimento = resetarAposMovimento;
	}

	public Casa getCasaAtual() {
		return casaAtual;
	}
	
	public Casa getCasaInicial() {
		return casaInicial;
	}
}
