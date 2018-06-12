package it.polito.tdp.formulaone.model;

import java.util.PriorityQueue;

public class Simulazione {

	private Model model;
	private PriorityQueue<Evento> pq;

	public Simulazione(Model model) {
		this.model = model;
		pq = new PriorityQueue<Evento>();
	}

	public void simula() {

		// Inizializzazione
		for (FantaPilota fp : model.getFantaPiloti()) {
			pq.add(new Evento(fp, 0, fp.getLapTimes().get(0)));
		}

		// Processo gli eventi
		while (!pq.isEmpty()) {
			Evento e = pq.poll();
		
			// assegnare dei punti al pilota
			int count = 0;
			for (FantaPilota ifp : model.getFantaPiloti()) {
				if (!ifp.getEliminato()) {
					if (ifp.getLap() >= e.getLap()) {
						count++;
					}
				}
			}
			// Se la posizione del pilota è migliore di quella del giro precedente
			// Aggiorno il punteggio
			if (count < e.getFantaPilota().getRank()) {
				int points = (e.getFantaPilota().getPoints()) + 1;
				e.getFantaPilota().setPoints(points);
			}
			e.getFantaPilota().setLap(e.getLap());
			e.getFantaPilota().setRank(count);

			// capire se il polita è stato doppiato
			boolean doppiato = false;
			for (FantaPilota ifp : model.getFantaPiloti()) {
				if (!ifp.getEliminato()) {
					if (ifp.getLap() > e.getLap() + 1) {
						doppiato = true;
					}
				}
			}
			if (doppiato) {
				e.getFantaPilota().setEliminato();
				System.out.println("Pilota eliminato " + e.getFantaPilota().getYear());

			} else {
				// inserire un nuovo evento
				if (e.getFantaPilota().getLapTimes().size() > e.getLap() + 1) {
					// se ci sono ancora eventi da aggiungere
					int t = e.getFantaPilota().getLapTimes().get(e.getLap() + 1);
					pq.add(new Evento(e.getFantaPilota(), e.getLap() + 1, e.getTime() + t));
				}
			}
		}
	}
}
