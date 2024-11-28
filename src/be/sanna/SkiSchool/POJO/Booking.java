package be.sanna.SkiSchool.POJO;

import java.time.LocalDate;

public class Booking {

	//Attributes
	private int bookId;
	private boolean insurance;
	private double price;
	private Period period;
	private LocalDate datePrivateLesson;
	private Student student;
	private Instructor instructor;
	private Lesson lesson;
	
	
	//Constructor
	public Booking(int id_, Period period_, double price_, boolean insurance_, LocalDate dpl_, 
				   Student student_,Instructor instructor_, Lesson lesson_) {
		this.bookId = id_;
		this.period = period_;
		this.price = price_;
		this.insurance = insurance_;
		this.datePrivateLesson = dpl_;
		this.student = student_;
		this.instructor = instructor_;
		this.lesson = lesson_;
	}
	
	public Booking() {
		this.bookId = 0;
		this.period = null;
		this.price = 0;
		this.insurance = false;
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
	
	public boolean getInsurance() {
		return insurance;
	}
	
	public LocalDate getDatePrivateLesson() {
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
	public void setID(int bookingID_) {
		this.bookId = bookingID_;
	}
	
	public void setPeriod(Period period_) {
		this.period = period_;
	}
	
	public void setPrice(double price_) {
		this.price = price_;
	}
	
	public void setInsurance(boolean insurance_) {
		this.insurance = insurance_;
	}
	
	public void setDatePrivateLesson(LocalDate dpl_) {
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
		if(insurance == true) {
			return lesson.getLessonPrice() + 20;
		}else {
			return lesson.getLessonPrice();
		}
	}
	
}
