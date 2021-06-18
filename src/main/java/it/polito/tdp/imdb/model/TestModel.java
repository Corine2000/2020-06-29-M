package it.polito.tdp.imdb.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		m.creaGrafo(2005);
		System.out.println("#Vertici: "+ m.NumVertici() +"\n#Archi: "+m.NumArchi());
		
		//ricorsione
		List<Director> registri = m.getRegistriGrafo();
		Director partenza = registri.get(5);
		
		List<Director> percorso = m.doRicorsione(partenza, 20);
		
		for(Director d: percorso) {
			System.out.println(d.toString());
		}
	}

}
