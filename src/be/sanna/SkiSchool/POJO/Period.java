package be.sanna.SkiSchool.POJO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Period {

	//Attributes
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean isVacation;
	private List<Booking> books;
	
	//Constructor
	public Period(LocalDate start, LocalDate end, boolean isVacation_) {
		this.startDate = start;
		this.endDate = end;
		this.isVacation = isVacation_;
		this.books = new ArrayList<Booking>();
	}
	
	public Period() {
		this.startDate = null;
		this.endDate = null;
		this.isVacation = false;
		this.books = new ArrayList<Booking>();
	}
	
	//Getter
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
	public void addBooking(Booking booking_) {
		if(booking_ == null) {
			throw new IllegalArgumentException("Booking can't be null !");
		}
		this.books.add(booking_);
	}
}
