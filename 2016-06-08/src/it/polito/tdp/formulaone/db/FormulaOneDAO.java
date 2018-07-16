package it.polito.tdp.formulaone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.formulaone.model.Driver;
import it.polito.tdp.formulaone.model.DriverIdMap;
import it.polito.tdp.formulaone.model.Race;
import it.polito.tdp.formulaone.model.RaceAndDrivers;
import it.polito.tdp.formulaone.model.Season;

public class FormulaOneDAO {

	public List<Integer> getAllYearsOfRace() {

		String sql = "SELECT year FROM races ORDER BY year";

		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			List<Integer> list = new ArrayList<>();
			while (rs.next()) {
				list.add(rs.getInt("year"));
			}

			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}

	public List<Season> getAllSeasons() {

		String sql = "SELECT year, url FROM seasons ORDER BY year";

		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			List<Season> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Season(rs.getInt("year"), rs.getString("url")));
			}

			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Driver> getAllDrivers(DriverIdMap dim) {

		String sql = "Select DISTINCT drivers.driverId, forename, surname from drivers";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			List<Driver> drivers = new ArrayList<>();
			while (rs.next()) {
				drivers.add(dim.get(new Driver(rs.getInt("driverId"), rs.getString("forename"), rs.getString("surname"))));
			}

			conn.close();
			return drivers;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}
	
	public List<Driver> getAllDriversBySeason(Season s, DriverIdMap dim) {

		String sql = "Select DISTINCT drivers.driverId, forename, surname\n" + "from drivers, races, results\n"
				+ "where races.year = ?\n" + "and results.raceId = races.raceId\n"
				+ "and results.driverId is not null\n" + "and results.driverId = drivers.driverId";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getYear());
			ResultSet rs = st.executeQuery();

			List<Driver> drivers = new ArrayList<>();
			while (rs.next()) {
				drivers.add(dim.get(new Driver(rs.getInt("driverId"), rs.getString("forename"), rs.getString("surname"))));
			}

			conn.close();
			return drivers;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}
	
	public List<Race> getRacesByYear(int year) {

		String sql = "Select raceId,year,round,circuitId,name,date,time,url from races where races.year = ?";
		
		List<Race> list = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, year);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				list.add(new Race(rs.getInt("raceId"),rs.getInt("year"),rs.getInt("round"),rs.getInt("circuitId"),rs.getString("name"),rs.getDate("date"),rs.getTime("time"), rs.getString("url")));
			}

			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}
	
	public List<RaceAndDrivers> getDriversByRace(int year, int round) {

		String sql = "Select races.raceId, races.year, races.round, races.circuitId, races.name, races.date, races.time, races.url, " + 
				" drivers.driverId, drivers.forename, drivers.surname " + 
				"from drivers, races, results " + 
				"where races.year = ? " + 
				"and races.round = ? " + 
				"and results.raceId = races.raceId " + 
				"and results.driverId is not null " + 
				"and results.driverId = drivers.driverId";
		
		List<RaceAndDrivers> list = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, year);
			st.setInt(2, round);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				list.add(new RaceAndDrivers(
						new Race(rs.getInt("races.raceId"),rs.getInt("races.year"),rs.getInt("races.round"),rs.getInt("races.circuitId"),
								rs.getString("races.name"),rs.getDate("races.date"),rs.getTime("races.time"), rs.getString("races.url"))
						,new Driver(rs.getInt("drivers.driverId"), rs.getString("drivers.forename"), rs.getString("drivers.surname") )));
			}

			conn.close();
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}
	
	
}
