package tabuleiro;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import enums.Cor;
import enums.EstadoCasa;
import pecas.Peca;

public class Casa {

	private final int linha;
	private final int coluna;
	private Peca peca;
	private Tabuleiro tabuleiro;

	private JButton botaoUI;

	private EstadoCasa estado = EstadoCasa.NORMAL;

	public Casa(int linha, int coluna, Tabuleiro tabuleiro) {
		this.linha = linha;
		this.coluna = coluna;
		this.tabuleiro = tabuleiro;
		montarBotao();
	}

	public Casa(int linha, int coluna, Peca peca, Tabuleiro tabuleiro) {
		this.linha = linha;
		this.coluna = coluna;
		this.peca = peca;
		this.tabuleiro = tabuleiro;
		montarBotao();
	}

	private void montarBotao() {
		botaoUI = new JButton();
		atualizarCorFundo();
		atualizarIcone();
	}

	public void atualizarCorFundo() {
		Cor cor;
		if (estado == EstadoCasa.NORMAL) {
			// Cores padrões do tabuleiro
			if (linha % 2 == 0)
				cor = (coluna % 2 == 0) ? Cor.PADRAO_BRANCA : Cor.PADRAO_PRETA;
			else
				cor = (coluna % 2 == 0) ? Cor.PADRAO_PRETA : Cor.PADRAO_BRANCA;
		} else {
			// Pega a cor definida no Enum EstadoCasa
			cor = estado.getCor();
		}

		botaoUI.setBackground(cor.getColor());
	}

	public void atualizarIcone() {
		if (peca == null)
			botaoUI.setIcon(null);
		else
			botaoUI.setIcon(new ImageIcon(peca.getImage()));
	}

	public TabuleiroIterator getIterator() {
		return new TabuleiroIterator(this, tabuleiro.getCasas());
	}

	public List<Casa> mostrarMovimentosDisponiveis() {
		TabuleiroIterator iterator = getIterator();
		return getPeca().calcularMovimentosDisponiveis(iterator);
	}

	public Peca moverPecaPara(Casa novaCasa) {
		Peca pecaAtacada = novaCasa.getPeca();

		novaCasa.setPeca(getPeca());
		setPeca(null);

		novaCasa.getPeca().setMoveu(true);

		atualizarIcone();
		novaCasa.atualizarIcone();

		return pecaAtacada;
	}

	public void promoverPeca(Peca peca) {
		this.setPeca(peca);
		atualizarIcone();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;

	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public EstadoCasa getEstado() {
		return estado;
	}

	public void setEstado(EstadoCasa estado) {
		this.estado = estado;
	}

	public boolean isVazia() {
		return getPeca() == null;
	}

	public JButton getBotaoUI() {
		return botaoUI;
	}

}