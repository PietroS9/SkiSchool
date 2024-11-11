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
	
	//Constructor
	public LessonType(Level lvl, double price_) {
		this.lessonTypeId = lessonTypeNum++;
		this.level = lvl;
		this.price = price_;
		this.accreditations = new ArrayList<>();
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
	
	//Setter
	public void setLevel(Level lvl) {
		this.level = lvl;
	}
	
	public void setPrice(double price_) {
		this.price = price_;
	}
	
	public void setAccreditations(List<Accreditation> accrs_) {
		this.accreditations = accrs_;
	}
	
	//Methods
}
