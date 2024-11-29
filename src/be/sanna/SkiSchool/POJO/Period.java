package be.sanna.SkiSchool.POJO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.DAO.InstructorDAO;
import be.sanna.SkiSchool.DAO.PeriodDAO;

public class Period {

	//Attributes
	private int periodID;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean isVacation;
	private List<Booking> books;
	
	//Constructor
	public Period(int periodID_, LocalDate start, LocalDate end) {
		this.periodID = periodID_;
		this.startDate = start;
		this.endDate = end;
		this.books = new ArrayList<Booking>();
	}
	
	public Period() {
		this.startDate = null;
		this.endDate = null;
		this.isVacation = false;
		this.books = new ArrayList<Booking>();
	}
	
	//Getter
	public int getID() {
		return periodID;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public boolean getIsVacation() {
		return isVacation;
	}
	
	public List<Booking> getBooking(){
		return books;
	}
	
	//Setter
	public void setID(int id) {
		this.periodID = id;
	}
	
	public void setStartDate(LocalDate start) {
		this.startDate = start;
	}
	
	public void setEndDate(LocalDate end) {
		this.endDate = end;
	}
	
	public void setIsVacation(boolean isVac) {
		this.isVacation = isVac;
	}
	
	public void setBooking(Booking booking_) {
		try {
			addBooking(booking_);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Methods
	@Override
	public String toString() {
		return getID() + "SD : " + getStartDate() + "ED :" + getEndDate();
	}
	public void addBooking(Booking booking_) {
		if(booking_ == null) {
			throw new IllegalArgumentException("Booking can't be null !");
		}
		this.books.add(booking_);
	}
	
	public List<Period> getAllPeriods(PeriodDAO dao){
		return dao.getAllPeriods();
	}
	
	public void addPeriod(PeriodDAO dao) {
		dao.addPeriod(this);
	}
	
	public void removePeriod(PeriodDAO dao) {
		dao.removePeriod(this);
	}
	
	public void insertToDB(PeriodDAO dao) {
		dao.insertToDB(this);
	}
	
	public void updateToDB(PeriodDAO dao) {
		dao.updateToDB(this);
	}
	
	public void deleteToDB(PeriodDAO dao) {
		dao.deleteToDB(this);
	}
	
}
