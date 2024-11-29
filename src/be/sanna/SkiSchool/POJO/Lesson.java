package be.sanna.SkiSchool.POJO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.DAO.LessonDAO;

public class Lesson {
	
	//Attributes
	private int lessonId;
	private boolean individual;
	private LocalDate lessonDate;
	private boolean amORpm;
	private int duration;
	private int minStudents;
	private int maxStudents;
	private LessonType type;
	private Instructor instructor;
	private List<Booking> books;
	
	//Constructor
	public Lesson(int id_, boolean individual_, LocalDate lessonDate_, boolean amORpm_,
				  int duration_, int min, int max, LessonType type_, Instructor instructor_) {
		this.lessonId = id_;
		this.individual = individual_;
		this.lessonDate = lessonDate_;
		this.amORpm = amORpm_;
		this.duration = duration_;
		this.minStudents = min;
		this.maxStudents = max;
		this.type = type_;
		this.instructor = instructor_;
		this.books = new ArrayList<Booking>();
	}
	
	public Lesson() {
		this.lessonId = 0;
		this.individual = false;
		this.lessonDate = null;
		this.amORpm = false;
		this.duration = 0;
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
	
	public boolean getIndividual() {
		return individual;
	}
	
	public LocalDate getLessonDate() {
		return lessonDate;
	}
	
	public boolean getAMorPM() {
		return amORpm;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getMinStudents() {
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
	public void setID(int lessonID_) {
		this.lessonId = lessonID_;
	}
	
	public void setIndividual(boolean individual_) {
		this.individual = individual_;
	}
	
	public void setLessonDate(LocalDate lessonDate_) {
		this.lessonDate = lessonDate_;
	}
	
	public void setAMorPM(boolean amORpm_) {
		this.amORpm = amORpm_;
	}
	
	public void setDuration(int duration_) {
		this.duration = duration_;
	}
	
	public void setMinStudents(int min) {
		this.minStudents = min;
	}
	
	public void setMaxStudents(int max) {
		this.maxStudents = max;
	}
	
	public void setType(LessonType type_) {
		if(type_ == null) {
			throw new IllegalArgumentException("Type can't be null !");
		}
		this.type = type_;
	}
	
	public void setInstructor(Instructor instructor_) {
		if(instructor_ == null) {
			throw new IllegalArgumentException("Instructor can't be null !");
		}
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
	@Override
	public String toString() {
		return type.getLevel().getDescription();
	}
	public double getLessonPrice() {
		if(individual == false) {
			return type.getPrice();
		} else {
			if(duration == 1) {
				return (double)60;
			} else {
				return (double)90;
			}
		}
	}
	
	public void addBooking(Booking booking_) {
		if(booking_ == null) {
			throw new IllegalArgumentException("Booking can't be null !");
		}
		this.books.add(booking_);
	}
	
	public List<Lesson> getAllLessons(LessonDAO dao, List<Instructor> instructors, List<LessonType> lessonTypes){
		return dao.getAllLessons(instructors, lessonTypes);
	}
	
	public void addLesson(LessonDAO dao) {
		dao.addLesson(this);
	}
	
	public void removeLesson(LessonDAO dao) {
		dao.removeLesson(this);
	}
	
	public void insertToDB(LessonDAO dao) {
		dao.insertToDB(this);
	}
	
	public void updateToDB(LessonDAO dao) {
		dao.updateToDB(this);
	}
	
	public void deleteToDB(LessonDAO dao) {
		dao.deleteToDB(this);
	}
	
	
}
