package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	
	ImdbDAO dao; 
	Map<Integer, Director> idMap;
	
	Graph<Director, DefaultWeightedEdge>grafo;
	List<Director> risultato;
	int TotAttori;
	
	public Model() {
		dao = new ImdbDAO();
		
		
	}

	public void creaGrafo(Integer anno) {
		idMap = new HashMap<>();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		dao.getVertici(anno, idMap);
		//aggiungo i vertici
		Graphs.addAllVertices(grafo, idMap.values());
		
		//aggiungo gli archi
		List<Adiacenza> archi = dao.getArchi(idMap, anno);
		
		for(Adiacenza a: archi) {
			if(grafo.containsVertex(a.getD1()) && grafo.containsVertex(a.getD2())) {
				if(!grafo.containsEdge(a.getD1(), a.getD2())) {
				Graphs.addEdgeWithVertices(this.grafo, a.getD1(), a.getD2(), a.getPeso());
				}
			}
		}
		
		
	}
	
	public int NumVertici() {
		return grafo.vertexSet().size();
	}

	public int NumArchi() {
		return grafo.edgeSet().size();
	}
	
	public List<Director> getRegistriGrafo(){
		List<Director> list = new ArrayList<>(grafo.vertexSet());
		Collections.sort(list);
		
		return list;
	}
	
	public boolean esisteGrafo() {
		if(this.grafo== null)
			return false;
		return true;
	}
	
	public List<RegistriVicini> getVicini(Director d){
		List<RegistriVicini> list = new ArrayList<>();
		
		for(DefaultWeightedEdge e: grafo.edgesOf(d)) {
			list.add(new RegistriVicini(grafo.getEdgeTarget(e), grafo.getEdgeWeight(e)));
		}
		
		Collections.sort(list);
		return list;
	}
	
	public List<Director> doRicorsione(Director partenza, int c) {
		 risultato = new ArrayList<>();
		 TotAttori =0;
		 
		 List<Director> parziale = new ArrayList<>();
		 parziale.add(partenza);
		 
		 cercaPercorso(parziale, c);
		 
		 return risultato;
	}

	private void cercaPercorso(List<Director> parziale, int c) {
		
		 //caso terminale : abbiamo raggiunto c, e il num di director è il maggiore possibile
		//if(TotAttori <= c) {
			if(parziale.size()>= risultato.size()) {
				risultato = new ArrayList<>(parziale);
				//return;
			//}
		}
		
		Director ultimo = parziale.get(parziale.size()-1);
		
		for(DefaultWeightedEdge edge: grafo.edgesOf(ultimo)) {
			TotAttori += (int)grafo.getEdgeWeight(edge);
			
			if(TotAttori <= c) {
				Director successivo = Graphs.getOppositeVertex(grafo, edge, ultimo);
				if(!parziale.contains(successivo)) {
					parziale.add(successivo);
					
					cercaPercorso(parziale, c);
					parziale.remove(successivo);
				}
			}
		}
		
	}
	
	public int getTotAttoriCondivisi() {
		return this.TotAttori;
	}
	
}
