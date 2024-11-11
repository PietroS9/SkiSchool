package be.sanna.SkiSchool;

import java.util.List;
import java.util.ArrayList;

public class Student extends Person{
	
	//Attributes
	private  List<Booking> books;
	
	//Constructor
	public Student(String fn, String ln, int age_) {
		super(fn,ln,age_);
		this.books = new ArrayList<>();
	}
	
	//Getter
	public int getId() {
		return super.getId();
	}
	
	public String getFirstName() {
		return super.getFirstName();
	}
	
	public String getLastName() {
		return super.getLastName();
	}
	
	public int getAge() {
		return super.getAge();
	}
	
	public List<Booking> getBooking(){
		return books;
	}
	
	//Setter
	public void setFirstName(String fn) {
		super.setFirstName(fn);
	}
	
	public void setLastName(String ln) {
		super.setLastName(ln);
	}
	
	public void setAge(int age_) {
		super.setAge(age_);
	}
	
	public void setBooking(List<Booking> books_) {
		if(books_ == null) {
			throw new IllegalArgumentException("List of booking can't be null");
		}
		this.books = books_;
	}
	
	//Methods
	public void addBooking(Booking booking_) {
		if(booking_ == null) {
			throw new IllegalArgumentException("Booking can't be null");
		}
		this.books.add(booking_);
	}
	
	
}
