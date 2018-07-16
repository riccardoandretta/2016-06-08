package it.polito.tdp.formulaone.model;

import java.util.List;

public class RaceAndDrivers {

	private Race race;
	private Driver driver;

	public RaceAndDrivers(Race race, Driver driver) {
		super();
		this.race = race;
		this.driver = driver;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RaceAndDrivers other = (RaceAndDrivers) obj;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		if (race == null) {
			if (other.race != null)
				return false;
		} else if (!race.equals(other.race))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("RaceAndDrivers [race=%s, driver=%s]", race, driver);
	}

}