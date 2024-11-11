package be.sanna.SkiSchool;

public class Lesson {
	
	//Attributes
	private int lessonId;
	private static int lessonNum = 1;
	private int minStudents;
	private int maxStudents;
	private LessonType type;
	
	//Constructor
	public Lesson(int min, int max, LessonType type_) {
		this.lessonId = lessonNum++;
		this.minStudents = min;
		this.maxStudents = max;
		this.type = type_;
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
	
	//Methods
	public double getLessonPrice() {
		return type.getPrice();
	}
	
	
}
