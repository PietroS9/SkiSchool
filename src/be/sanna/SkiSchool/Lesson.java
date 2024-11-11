package be.sanna.SkiSchool;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
	
	//Attributes
	private int lessonId;
	private static int lessonNum = 1;
	private int minStudents;
	private int maxStudents;
	private LessonType type;
	private Instructor instructor;
	private List<Booking> books;
	
	//Constructor
	public Lesson(int min, int max, LessonType type_, Instructor instructor_) {
		this.lessonId = lessonNum++;
		this.minStudents = min;
		this.maxStudents = max;
		this.type = type_;
		this.instructor = instructor_;
		this.books = new ArrayList<Booking>();
	}
	
	public Lesson() {
		this.lessonId = lessonNum++;
		this.minStudents = 0;
		this.maxStudents = 1;
		this.type = null;
		this.instructor = null;
		this.books = new ArrayList<Booking>();
	}
	
	//Getter
	public int getLessonId() {
		return lessonId;
	}
	
	public int getMinStudenyts() {
		return minStudents;
	}
	
	public int getMaxStudents() {
		return maxStudents;
	}
	
	public LessonType getType() {
		return type;
	}
	
	public Instructor getInstructor() {
		return instructor;
	}
	
	public List<Booking> getBooking(){
		return books;
	}
	
	//Setter
	public void setMinStudents(int min) {
		this.minStudents = min;
	}
	
	public void setMaxStudents(int max) {
		this.maxStudents = max;
	}
	
	public void setType(LessonType type_) {
		this.type = type_;
	}
	
	public void setInstructor(Instructor instructor_) {
		this.instructor = instructor_;
	}
	
	public void setBooking(Booking booking) {
		try {
			addBooking(booking);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Methods
	public double getLessonPrice() {
		return type.getPrice();
	}
	
	public void addBooking(Booking booking_) {
		if(booking_ == null) {
			throw new IllegalArgumentException("Booking can't be null !");
		}
		this.books.add(booking_);
	}
	
	
}
