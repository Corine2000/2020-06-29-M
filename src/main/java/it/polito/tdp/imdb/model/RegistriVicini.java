package it.polito.tdp.imdb.model;

public class RegistriVicini implements Comparable<RegistriVicini>{

	private Director registro;
	private double AttoriCondivisi;
	
	public RegistriVicini(Director registro, double attoriCondivisi) {
		super();
		this.registro = registro;
		AttoriCondivisi = attoriCondivisi;
	}
	
	public Director getRegistro() {
		return registro;
	}
	public void setRegistro(Director registro) {
		this.registro = registro;
	}
	public double getAttoriCondivisi() {
		return AttoriCondivisi;
	}
	public void setAttoriCondivisi(double attoriCondivisi) {
		AttoriCondivisi = attoriCondivisi;
	}

	@Override
	public int compareTo(RegistriVicini o) {
		
		return -Double.compare(this.AttoriCondivisi, o.AttoriCondivisi);
	}

	/*@Override
	public String toString() {
		return this.registro.toString()+"#attori condivisi: "+this.AttoriCondivisi+"\n";
	}*/
	
}
