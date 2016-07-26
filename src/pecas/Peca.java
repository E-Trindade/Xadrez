package pecas;

import java.awt.Image;
import java.util.List;

import enums.Time;
import tabuleiro.Casa;
import tabuleiro.TabuleiroIterator;

public abstract class Peca {

	private Time time;

	protected boolean moveu = false;

	public Peca(Time time) {
		this.time = time;
	}

	public Time getTime() {
		return time;
	}

	public boolean getMoveu() {
		return moveu;
	}

	public void setMoveu(boolean moveu) {
		this.moveu = moveu;
	}

	public abstract Image getImage();

	public abstract List<Casa> calcularMovimentosDisponiveis(TabuleiroIterator iterator);

	@Override
	public String toString() {
		return "Peca [time=" + time + ", moveu=" + moveu + ", getImage()=" + getImage() + "]";
	}

}
