package com.horaire.db;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="starting_time", catalog="clubschedule", schema="SchemaName")
public class StartingTimeData {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String date;
    private Time starting_time;
    
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Time getStarting_time() {
		return starting_time;
	}
	public void setStarting_time(Time starting_time) {
		this.starting_time = starting_time;
	}
    
    
}