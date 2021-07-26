package com.horaire.db;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menutable")
public class MenuTableData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String dateCol;
    private String menu;
    private String detail;
    private Time st;
    private Time ft;
    
    // getter and setter
	public String getDateCol() {
		return dateCol;
	}
	public void setDateCol(String date) {
		this.dateCol = date;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Time getSt() {
		return st;
	}
	public void setSt(Time st) {
		this.st = st;
	}
	public Time getFt() {
		return ft;
	}
	public void setFt(Time ft) {
		this.ft = ft;
	}
}
