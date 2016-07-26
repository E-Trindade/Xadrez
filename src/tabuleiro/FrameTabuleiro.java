package tabuleiro;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class FrameTabuleiro extends JFrame implements ActionListener {

	static final long serialVersionUID = 1L;

	// imagens das pecas pretas
	public static final Image peaoPreto = new ImageIcon("imagens/pecas/pretas/peao.png", "").getImage().getScaledInstance(30, 45, java.awt.Image.SCALE_SMOOTH);
	public static final Image torrePreta = new ImageIcon("imagens/pecas/pretas/torre.png", "").getImage().getScaledInstance(45, 50, java.awt.Image.SCALE_SMOOTH);
	public static final Image cavaloPreto = new ImageIcon("imagens/pecas/pretas/cavalo.png", "").getImage().getScaledInstance(40, 55, java.awt.Image.SCALE_SMOOTH);
	public static final Image bispoPreto = new ImageIcon("imagens/pecas/pretas/bispo.png", "").getImage().getScaledInstance(40, 58, java.awt.Image.SCALE_SMOOTH);
	public static final Image rainhaPreta = new ImageIcon("imagens/pecas/pretas/rainha.png", "").getImage().getScaledInstance(35, 50, java.awt.Image.SCALE_SMOOTH);
	public static final Image reiPreto = new ImageIcon("imagens/pecas/pretas/rei.png", "").getImage().getScaledInstance(40, 60, java.awt.Image.SCALE_SMOOTH);

	// imagens das pecas brancas
	public static final Image peaoBranco = new ImageIcon("imagens/pecas/brancas/peao.png", "").getImage().getScaledInstance(30, 45, java.awt.Image.SCALE_SMOOTH);
	public static final Image torreBranca = new ImageIcon("imagens/pecas/brancas/torre.png", "").getImage().getScaledInstance(45, 50, java.awt.Image.SCALE_SMOOTH);
	public static final Image cavaloBranco = new ImageIcon("imagens/pecas/brancas/cavalo.png", "").getImage().getScaledInstance(40, 55, java.awt.Image.SCALE_SMOOTH);
	public static final Image bispoBranco = new ImageIcon("imagens/pecas/brancas/bispo.png", "").getImage().getScaledInstance(40, 58, java.awt.Image.SCALE_SMOOTH);
	public static final Image rainhaBranca = new ImageIcon("imagens/pecas/brancas/rainha.png", "").getImage().getScaledInstance(35, 50, java.awt.Image.SCALE_SMOOTH);
	public static final Image reiBranco = new ImageIcon("imagens/pecas/brancas/rei.png", "").getImage().getScaledInstance(40, 60, java.awt.Image.SCALE_SMOOTH);

	GridLayout gerenciadorDeLayout = new GridLayout(8, 8);

	static final String TEXTO_BARRA_SUPERIOR = "O Fabuloso Jogo de Xadrez";
	Tabuleiro tabuleiro = new Tabuleiro();

	FrameTabuleiro() {
		super.setTitle(TEXTO_BARRA_SUPERIOR);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.montaTabuleiro();
		super.pack();
		super.setVisible(true);
		super.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] coordenada = e.getActionCommand().split(",");
		int linha = Integer.parseInt(coordenada[0]);
		int coluna = Integer.parseInt(coordenada[1]);
		tabuleiro.trataCliqueSobreUmaCasa(linha, coluna);
	}

	private void montaTabuleiro() {
		tabuleiro.criaEPintaAsCasas();

		JPanel painelTabuleiro = new JPanel();
		painelTabuleiro.setLayout(gerenciadorDeLayout);

		// adiciona as casas
		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				painelTabuleiro.add(tabuleiro.obtemBotaoCasa(linha, coluna));
				tabuleiro.obtemBotaoCasa(linha, coluna).addActionListener(this);
				tabuleiro.obtemBotaoCasa(linha, coluna).setActionCommand(linha + "," + coluna);
			}
		}
		super.getContentPane().add(painelTabuleiro);
	}

	public static void main(String[] args) throws Exception{
//		 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		new FrameTabuleiro();
	}
}
