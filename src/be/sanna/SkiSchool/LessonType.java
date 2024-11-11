package be.sanna.SkiSchool;

import java.util.ArrayList;
import java.util.List;

public class LessonType {

	//Attributes
	private int lessonTypeId;
	private static int lessonTypeNum = 1;
	private Level level;
	private double price;
	private List<Accreditation> accreditations;
	private List<Lesson> lessons;
	
	//Constructor
	public LessonType(Level lvl, double price_) {
		this.lessonTypeId = lessonTypeNum++;
		this.level = lvl;
		this.price = price_;
		this.accreditations = new ArrayList<Accreditation>();
		this.lessons = new ArrayList<Lesson>();
	}
	
	public LessonType() {
		this.lessonTypeId = lessonTypeNum++;
		this.level = null;
		this.price = 0;
		this.accreditations = new ArrayList<Accreditation>();
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
	
	public List<Accreditation> getAccreditations(){
		return accreditations;
	}
	
	public List<Lesson> getLessons(){
		return lessons;
	}
	
	//Setter
	public void setLevel(Level lvl) {
		this.level = lvl;
	}
	
	public void setPrice(double price_) {
		this.price = price_;
	}
	
	public void setAccreditations(Accreditation accr) {
		//Not finished
		addAccreditation(accr);
	}
	
	public void setLessons(Lesson lesson) {
		//NOt finished
		addLesson(lesson);
	}
	
	//Methods
	public void addAccreditation(Accreditation accr) {
		//NOt finsihed
		accreditations.add(accr);
	}
	
	public void addLesson(Lesson lesson) {
		//NOt finished
		lessons.add(lesson);
	}
}
