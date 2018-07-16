package it.polito.tdp.formulaone.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		System.out.println(m.getAllDrivers());
		
		List<Race> gare = m.getRacesByYear(2015);
		System.out.println(gare);
		
		
		System.out.println(m.getDriversByRace(2016, gare.get(0).getRound()));
		System.out.println(m.getDriversByRace(2016, gare.get(0).getRound()).size());
		
	}
}
