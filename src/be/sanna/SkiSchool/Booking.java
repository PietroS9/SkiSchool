package be.sanna.SkiSchool;

import java.util.Date;

public class Booking {

	//Attributes
	private int bookId;
	private static int bookNum = 1;
	private Period period;
	private double price;
	private int duration; 
	private boolean insurance;
	private boolean individual;
	private Date datePrivateLesson;
	
	//Constructor
	public Booking(Period period_, double price_, int duration_, boolean insurance_, boolean individual_, Date dpl_) {
		this.bookId = bookNum++;
		this.period = period_;
		this.price = price_;
		this.duration = duration_;
		this.insurance = insurance_;
		this.individual = individual_;
		this.datePrivateLesson = dpl_;
	}
	
	//Getter
	public int getBookId() {
		return bookId;
	}
	
	public Period getPeriod() {
		return period;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public boolean getInsurance() {
		return insurance;
	}
	
	public boolean getIndividual() {
		return individual;
	}
	
	public Date getDatePrivateLesson() {
		return datePrivateLesson;
	}
	
	//Setter	
	public void setPeriod(Period period_) {
		this.period = period_;
	}
	
	public void setPrice(double price_) {
		this.price = price_;
	}
	
	public void setDuration(int duration_) {
		this.duration = duration_;
	}
	
	public void setInsurance(boolean insurance_) {
		this.insurance = insurance_;
	}
	
	public void setIndividual(boolean individual_) {
		this.individual = individual_;
	}
	
	public void setDatePrivateLesson(Date dpl_) {
		this.datePrivateLesson = dpl_;
	}
	//Methods
	
	public double calculatePrice() {
		//Not finished yet
		return 0;
	}
	
}
