package it.polito.tdp.formulaone.model;

import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	private FormulaOneDAO fonedao;
	private SimpleDirectedWeightedGraph<Driver, DefaultWeightedEdge> grafo;
	private DriverIdMap driverIdMap;
	private List<Driver> drivers;	
	
	public Model() {
		fonedao = new FormulaOneDAO();
		driverIdMap = new DriverIdMap();
		drivers = fonedao.getAllDrivers(driverIdMap);
	}

	public List<Season> getAllSeasons() {
		return fonedao.getAllSeasons();
	}
	
	public List<Driver> getAllDrivers() {
		return this.drivers;
	}
	
	public List<Race> getRacesByYear(int year) {
		return fonedao.getRacesByYear(year);
	}
	
	public List<RaceAndDrivers> getDriversByRace(int year, int raceRound) {
		return fonedao.getDriversByRace(year, raceRound);
	}
		
	public void creaGrafo(int year, Race race) {
	}
}
