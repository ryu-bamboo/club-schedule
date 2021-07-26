package com.horaire.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reflection")
public class ReflectionData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "datecol")
	private String date;
	
	@Column(name = "reflection")
	private String reflection;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReflection() {
		return reflection;
	}
	public void setReflection(String reflection) {
		this.reflection = reflection;
	}
}
