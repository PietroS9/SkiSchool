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
	private Student student;
	private Instructor instructor;
	private Lesson lesson;
	
	
	//Constructor
	public Booking(Period period_, double price_, int duration_, boolean insurance_, 
			boolean individual_, Date dpl_, Student student_, Instructor instructor_, Lesson lesson_) {
		this.bookId = bookNum++;
		this.period = period_;
		this.price = price_;
		this.duration = duration_;
		this.insurance = insurance_;
		this.individual = individual_;
		this.datePrivateLesson = dpl_;
		this.student = student_;
		this.instructor = instructor_;
		this.lesson = lesson_;
	}
	
	public Booking() {
		this.bookId = bookNum++;
		this.period = null;
		this.price = 0;
		this.duration = 0;
		this.insurance = false;
		this.individual = false;
		this.datePrivateLesson = null;
		this.student = null;
		this.instructor = null;
		this.lesson = null;
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
	
	public Student getStudent() {
		return student;
	}
	
	public Instructor getInstructor() {
		return instructor;
	}
	
	public Lesson getLesson() {
		return lesson;
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
	
	public void setStudent(Student student_) {
		this.student = student_;
	}
	
	public void setInstructor(Instructor instructor_) {
		this.instructor = instructor_;
	}
	
	public void setLesson(Lesson lesson_) {
		this.lesson = lesson_;
	}
	
	//Methods
	
	public double calculatePrice() {
		//Not finished yet
		return 0;
	}
	
}
