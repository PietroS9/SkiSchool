package be.sanna.SkiSchool.POJO;

import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.DAO.LessonTypeDAO;

public class LessonType {

	//Attributes
	private int lessonTypeId;
	private Level level;
	private double price;
	private Accreditation accreditation;
	private List<Lesson> lessons;
	
	//Constructor
	public LessonType(int lessonTypeID_, Level lvl, double price_, Accreditation accr_) {
		this.lessonTypeId = lessonTypeID_;
		this.level = lvl;
		this.price = price_;
		this.accreditation = accr_;
		this.lessons = new ArrayList<Lesson>();
	}
	
	public LessonType() {
		this.lessonTypeId = 0;
		this.level = null;
		this.price = 0;
		this.accreditation = null;
		this.lessons = new ArrayList<Lesson>();
	}
	
	//Getter
	public int getLessonTypeId() {
		return lessonTypeId;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Accreditation getAccreditation(){
		return accreditation;
	}
	
	public List<Lesson> getLessons(){
		return lessons;
	}
	
	//Setter
	public void setLessonTypeID(int lessonTypeID_) {
		this.lessonTypeId = lessonTypeID_;
	}
	
	public void setLevel(Level lvl) {
		this.level = lvl;
	}
	
	public void setPrice(double price_) {
		this.price = price_;
	}
	
	public void setAccreditation(Accreditation accr) {
		try {
			addAccreditation(accr);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setLessons(Lesson lesson) {
		try {
			addLesson(lesson);
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
		this.accreditation = accr;
	}
	
	public void addLesson(Lesson lesson) {
		if(lesson == null) {
			throw new IllegalArgumentException("Lesson can't be null !");
		}
		lessons.add(lesson);
	}
	
	@Override
	public String toString() {
		return getLevel().getDescription();
	}
}
