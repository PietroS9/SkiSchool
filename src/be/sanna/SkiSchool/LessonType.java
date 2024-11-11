package be.sanna.SkiSchool;

public class LessonType {

	//Attributes
	private int lessonTypeId;
	private static int lessonTypeNum = 1;
	private Level level;
	private double price;
	
	//Constructor
	public LessonType(Level lvl, double price_) {
		this.lessonTypeId = lessonTypeNum++;
		this.level = lvl;
		this.price = price_;
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
	
	//Setter
	public void setLevel(Level lvl) {
		this.level = lvl;
	}
	
	public void setPrice(double price_) {
		this.price = price_;
	}
	
	
	//Methods
}
