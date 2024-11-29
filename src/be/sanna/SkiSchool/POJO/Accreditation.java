package be.sanna.SkiSchool.POJO;

import java.util.ArrayList;
import java.util.List;

public class Accreditation {

	//Attributes
	private int accrId;
	private String name;
	private List<LessonType> lessonTypes;
	private List<Instructor> instructors;

	
	//Constructor
	public Accreditation(int accrId_, String name_, LessonType lessonType_) {
		this.accrId = accrId_;
		this.name = name_;
		this.lessonTypes = new ArrayList<LessonType>();
		this.instructors = new ArrayList<Instructor>();
		addLessonType(lessonType_);
	}
	
	public Accreditation(int accrId_, String name_) {
		this.accrId = accrId_;
		this.name = name_;
		this.lessonTypes = new ArrayList<LessonType>();
		this.instructors = new ArrayList<Instructor>();
	}
	public Accreditation() {
		this.accrId = 0;
		this.name = "";
		this.lessonTypes = new ArrayList<LessonType>();
		this.instructors = new ArrayList<Instructor>();
	}
	
	//Getter
	public int getAccrId() {
		return accrId;
	}
	
	public String getName() {
		return name;
	}
	
	public List<LessonType> getLessonTypes(){
		return lessonTypes;
	}
	
	public List<Instructor> getInstructors(){
		return instructors;
	}
	
	//Setter
	public void setID(int accrId_) {
		this.accrId = accrId_;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void setLessonTypes(LessonType lessonTypes_) {
		try {
			addLessonType(lessonTypes_);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setInstructors(Instructor instructor_) {
		try {
			addInstructor(instructor_);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Methods
	public void addLessonType(LessonType lessonType_) {
		if(lessonType_ == null) {
			throw new IllegalArgumentException("LessonType can't be null !");
		}
		this.lessonTypes.add(lessonType_);
	}
	
	public void addInstructor(Instructor instructor_) {
		if(instructor_ == null) {
			throw new IllegalArgumentException("Instructor can't be null !");
		}
		this.instructors.add(instructor_);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
