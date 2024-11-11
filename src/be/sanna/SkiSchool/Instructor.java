package be.sanna.SkiSchool;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person{

	//Attributes
	private List<Accreditation> accreditations;
	private List<Lesson> lessons;
	private List<Booking> books;
	
	//Constructor
	public Instructor(String fn, String ln, int age_, Accreditation accr) {
		super(fn,ln,age_);
		this.accreditations = new ArrayList<Accreditation>();
		this.lessons = new ArrayList<Lesson>();
		this.books = new ArrayList<Booking>();
		try {
			addAccreditation(accr);
		} 
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Instructor() {
		super();
		this.accreditations = new ArrayList<Accreditation>();
		this.lessons = new ArrayList<Lesson>();
		this.books = new ArrayList<Booking>();
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
	
	public List<Accreditation> getAccreditations(){
		return accreditations;
	}
	
	public List<Lesson> getLessons(){
		return lessons;
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
	
	public void setAccreditations(Accreditation accr) {
		try {
			addAccreditation(accr);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setLessons(Lesson lesson_) {
		try {
			addLesson(lesson_);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
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
	 public void addAccreditation(Accreditation accr) {
		 if(accr == null) {
			 throw new IllegalArgumentException("Accreditation can't be null !");
		 }
		 accreditations.add(accr);
	 }
	 
	 public void addLesson(Lesson lesson_) {
		 if(lesson_ == null) {
			 throw new IllegalArgumentException("Lesson can't be null !");
		 }
		 lessons.add(lesson_);
	 }
	 
	 public void addBooking(Booking booking_) {
			if(booking_ == null) {
				throw new IllegalArgumentException("Booking can't be null !");
			}
			this.books.add(booking_);
	}
	 
	 public boolean IsAccreditate(Lesson lesson) {
		 boolean flag = false;
		 int i = 0;
		 while(i<accreditations.size() && flag) {
			 if(lesson.getType().getAccreditations() == getAccreditations()) {
				 flag = true;
			 }
		 }
		 return flag;
	 }
}
