package it.polito.tdp.formulaone.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Race {

	private int raceId;
	private int year;
	private int round;
	private int circuitId;
	private String name;
	private Date date;
	private Time time;
	private String url;

	public Race(int raceId, int year, int round, int circuitId, String name, Date date, Time time,
			String url) {
		super();
		this.raceId = raceId;
		this.year = year;
		this.round = round;
		this.circuitId = circuitId;
		this.name = name;
		this.date = date;
		this.time = time;
		this.url = url;
	}

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(int circuitId) {
		this.circuitId = circuitId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + raceId;
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
		Race other = (Race) obj;
		if (raceId != other.raceId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Race [raceId=" + raceId + ", year=" + year + ", round=" + round + ", circuitId=" + circuitId + ", name="
				+ name + ", date=" + date + ", time=" + time + ", url=" + url + "]";
	}

}
