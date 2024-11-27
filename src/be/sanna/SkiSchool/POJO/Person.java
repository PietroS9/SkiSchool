package be.sanna.SkiSchool.POJO;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {

	//Attributes
	private int id;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	
	//Constructor
	public Person(int id_, String fn, String ln, LocalDate dob_) {
		this.id = id_;
		this.firstName = fn;
		this.lastName = ln;
		this.dob = dob_;
	}
	
	public Person() {
		this.id = 0;
		this.firstName = "";
		this.lastName = "";
		this.dob = null;
	}
	
	//Getter
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public LocalDate getDob() {
		return dob;
	}
	
	//Setter
	public void setID(int id_) {
		this.id = id_;
	}
	
	public void setFirstName(String fn) {
		this.firstName = fn;
	}
	
	public void setLastName(String ln) {
		this.lastName = ln;
	}
	
	public void setDob(LocalDate dob_) {
		if(dob_ == null || dob_.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("Invalid Date of birth !");
		}
		this.dob = dob_;
	}
	
	//Methods
	public int CalculateAge() {
		return Period.between(dob, LocalDate.now()).getYears();
	}
}
