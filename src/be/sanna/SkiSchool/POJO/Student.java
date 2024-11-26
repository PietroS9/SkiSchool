package be.sanna.SkiSchool.POJO;

import java.util.List;

import be.sanna.SkiSchool.DAO.StudentDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person{
	
	//Attributes
	private  List<Booking> books;
	
	//Constructor
	public Student(int id_, String fn, String ln, LocalDate dob_) {
		super(id_,fn,ln,dob_);
		this.books = new ArrayList<>();
	}
	
	public Student() {
		super();
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
	
	public LocalDate getDob() {
		return super.getDob();
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
	
	public void setBooking(Booking booking) {
		try {
			addBooking(booking);
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
	
	public int CalculateAge() {
		return super.CalculateAge();
	}
	
	public List<Student> getAllStudents(StudentDAO dao){
		return dao.getAllStudents();
	}
	
	public void addStudent(StudentDAO dao) {
		dao.addStudent(this);
	}
	
	public void SyncStudentsToDB(StudentDAO dao) {
		dao.SyncStudentsToDB();
	}
	
}
