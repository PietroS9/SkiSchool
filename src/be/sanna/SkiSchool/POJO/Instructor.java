package be.sanna.SkiSchool.POJO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.DAO.InstructorDAO;
import be.sanna.SkiSchool.DAO.StudentDAO;

public class Instructor extends Person{

	//Attributes
	private List<Accreditation> accreditations;
	private List<Lesson> lessons;
	private List<Booking> books;
	
	//Constructor
	public Instructor(int id_, String fn, String ln, LocalDate dob_, Accreditation accr) {
		super(id_,fn,ln,dob_);
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
	
	public LocalDate getDob() {
		return super.getDob();
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
	public void setID(int id_) {
		super.setID(id_);
	}
	
	public void setFirstName(String fn) {
		super.setFirstName(fn);
	}
	
	public void setLastName(String ln) {
		super.setLastName(ln);
	}
	
	public void setDob(LocalDate dob_) {
		super.setDob(dob_);
	}
	
	public void setAccreditations(Accreditation accr) {
		try {
			addAccreditation(accr);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setAccreditationsList(List<Accreditation> accrs) {
		try {
			this.accreditations = accrs;
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
	public int CalculateAge() {
		return super.CalculateAge();
	}
	
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
			if(lesson.getType().getAccreditation() == getAccreditations()) {
				flag = true;
			}
		}
		return flag;
	}
	
	public List<Instructor> getAllInstructors(InstructorDAO dao, List<Accreditation> accrs){
		return dao.getAllInstructors(accrs);
	}
	
	public void addInstructor(InstructorDAO dao) {
		dao.addInstructor(this);
	}
	
	public void SyncInstructorsToDB(InstructorDAO dao) {
		dao.SyncInstructorsToDB();
	}
}
