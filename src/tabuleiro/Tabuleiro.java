package tabuleiro;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import enums.EstadoCasa;
import enums.EstadoJogador;
import enums.Time;
import pecas.Bispo;
import pecas.Cavalo;
import pecas.Peao;
import pecas.Peca;
import pecas.Rainha;
import pecas.Rei;
import pecas.Torre;

public class Tabuleiro {

	private static final String MSG_MOVIMENTO_INVALIDO = "Posição inválida para movimento!";
	private static final String MSG_VEZ_OUTRO_TIME = "Não é a sua vez!";
	private static final Object XEQUE = "Xeque!";
	private static final Object XEQUE_MATE = "Xeque Mate!";

	private Casa[][] casas = new Casa[8][8];

	private Time timeAtual = Time.BRANCO;
	private EstadoJogador estadoJogador = EstadoJogador.NENHUMA_PECA_SELECIONADA;

	/**
	 * Popula o tabuleiro com objetos <code>Casa</code>
	 */
	public void criaEPintaAsCasas() {
		// InicializarTabuleiro
		casas[0][0] = new Casa(0, 0, new Torre(Time.PRETO), this);
		casas[0][1] = new Casa(0, 1, new Cavalo(Time.PRETO), this);
		casas[0][2] = new Casa(0, 2, new Bispo(Time.PRETO), this);
		casas[0][3] = new Casa(0, 3, new Rei(Time.PRETO), this);
		casas[0][4] = new Casa(0, 4, new Rainha(Time.PRETO), this);
		casas[0][5] = new Casa(0, 5, new Bispo(Time.PRETO), this);
		casas[0][6] = new Casa(0, 6, new Cavalo(Time.PRETO), this);
		casas[0][7] = new Casa(0, 7, new Torre(Time.PRETO), this);

		for (int i = 0; i < 8; i++) {
			casas[1][i] = new Casa(1, i, new Peao(Time.PRETO), this);
		}

		casas[7][0] = new Casa(7, 0, new Torre(Time.BRANCO), this);
		casas[7][1] = new Casa(7, 1, new Cavalo(Time.BRANCO), this);
		casas[7][2] = new Casa(7, 2, new Bispo(Time.BRANCO), this);
		casas[7][3] = new Casa(7, 3, new Rei(Time.BRANCO), this);
		casas[7][4] = new Casa(7, 4, new Rainha(Time.BRANCO), this);
		casas[7][5] = new Casa(7, 5, new Bispo(Time.BRANCO), this);
		casas[7][6] = new Casa(7, 6, new Cavalo(Time.BRANCO), this);
		casas[7][7] = new Casa(7, 7, new Torre(Time.BRANCO), this);

		for (int i = 0; i < 8; i++) {
			casas[6][i] = new Casa(6, i, new Peao(Time.BRANCO), this);
		}

		for (int linha = 2; linha < 6; linha++) {
			for (int coluna = 0; coluna < 8; coluna++)
				if (casas[linha][coluna] == null)
					casas[linha][coluna] = new Casa(linha, coluna, this);
		}
	}

	/**
	 * @param linha
	 * @param coluna
	 * @return o objeto <code>Casa</code> associado a essa posição no tabuleiro
	 */
	public Casa obtemCasa(int linha, int coluna) {
		return casas[linha][coluna];
	}

	/**
	 * @param linha
	 * @param coluna
	 * @return <code>JButton</code> associado a essa posição no tabuleiro
	 */
	public JButton obtemBotaoCasa(int linha, int coluna) {
		return casas[linha][coluna].getBotaoUI();
	}

	/**
	 * @param linha
	 * @param coluna
	 */
	public void trataCliqueSobreUmaCasa(int linha, int coluna) {
		Casa casa = casas[linha][coluna];

		switch (casa.getEstado()) {
			case NORMAL:
				switch (estadoJogador) {

					case SELECIONOU_PECA_PARA_MOVIMENTO:
						if (casa.isVazia())
							JOptionPane.showMessageDialog(null, MSG_MOVIMENTO_INVALIDO);
					case NENHUMA_PECA_SELECIONADA:
						// Selecionar nova casa para movimento

						if (casa.isVazia())
							return;

						if (casa.getPeca().getTime() != timeAtual) {
							JOptionPane.showMessageDialog(null, MSG_VEZ_OUTRO_TIME);
							return;
						}

						limparSelecaoCasas();
						List<Casa> disponiveis = casa.mostrarMovimentosDisponiveis();
						casa.setEstado(EstadoCasa.ORIGEM_MOVIMENTO);
						casa.atualizarCorFundo();
						for (Casa c : disponiveis) {
							if (c == null)
								continue;
							// Checa se é um simples movimento ou um ataque a uma casa populada pelo
							// rival
							if (c.isVazia())
								c.setEstado(EstadoCasa.SELECIONADA_PARA_MOVIMENTO);
							else
								c.setEstado(EstadoCasa.SELECIONADA_PARA_ATAQUE);
							c.atualizarCorFundo();
						}

						estadoJogador = EstadoJogador.SELECIONOU_PECA_PARA_MOVIMENTO;
						break;

				}
				break;

			case ORIGEM_MOVIMENTO://
				return;

			case SELECIONADA_PARA_MOVIMENTO:
			case SELECIONADA_PARA_ATAQUE:
			case XEQUE:
				Casa casaOrigem = getCasaOrigem();
				Peca pecaMorta = casaOrigem.moverPecaPara(casa);

				if (pecaMorta instanceof Rei)
					JOptionPane.showMessageDialog(null, XEQUE_MATE);

				// Promocao de peao
				if ((linha == 0 || linha == 7) && casa.getPeca() instanceof Peao) {
					Peca peca = mostrarPopupPromocao(casa.getPeca().getTime());
					casa.promoverPeca(peca);
				}
				for (int i = 0; i < 64; i++) {
					Casa c = casas[i / 8][i % 8];
					if (c.getPeca() instanceof Rei) {
						Rei rei = (Rei) c.getPeca();
						if (rei.detectaXeque(c.getIterator()))
							JOptionPane.showMessageDialog(null, XEQUE);
					}
				}

				timeAtual = (timeAtual == Time.BRANCO) ? Time.PRETO : Time.BRANCO;
				limparSelecaoCasas();
		}

	}

	private Peca mostrarPopupPromocao(Time time) {
		JPanel painel = new JPanel();
		painel.add(new JLabel("Selecione o tipo de peça para promoção:"));
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Rainha");
		model.addElement("Bispo");
		model.addElement("Cavalo");
		model.addElement("Torre");

		JComboBox<String> comboBox = new JComboBox<String>(model);
		comboBox.setSelectedIndex(0);

		painel.add(comboBox);

		int result = JOptionPane.showConfirmDialog(null, painel, "Promoção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		switch (result) {
			case JOptionPane.OK_OPTION:
				String r = (String) comboBox.getSelectedItem();

				if (r.equalsIgnoreCase("Bispo"))
					return new Bispo(time);

				if (r.equalsIgnoreCase("Cavalo"))
					return new Cavalo(time);

				if (r.equals("Torre"))
					return new Torre(time);

		}
		return new Rainha(time);
	}

	/**
	 * @return Casa do tabuleiro cujo estado seja igual a <code>EstadoCasa.ORIGEM_MOVIMENTO</code>
	 */
	private Casa getCasaOrigem() {
		for (int i = 0; i < 64; i++) {
			Casa c = casas[i / 8][i % 8];
			if (c.getEstado() == EstadoCasa.ORIGEM_MOVIMENTO)
				return c;
		}
		return null;
	}

	/**
	 * Altera o estado de todas as casas do tabuleiro para <code>EstadoCasa.NORMAL</code> e reseta a
	 * sua cor de fundo
	 */
	private void limparSelecaoCasas() {
		for (int i = 0; i < 64; i++) {
			Casa c = casas[i / 8][i % 8];
			switch (c.getEstado()) {
				case ORIGEM_MOVIMENTO:
				case SELECIONADA_PARA_MOVIMENTO:
				case SELECIONADA_PARA_ATAQUE:
				case XEQUE:
					c.setEstado(EstadoCasa.NORMAL);
					c.atualizarCorFundo();
				default:
					break;
			}
		}
	}

	public Casa[][] getCasas() {
		return casas;
	}

	public Time getTimeAtual() {
		return timeAtual;
	}

}
