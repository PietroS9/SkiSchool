package be.sanna.SkiSchool;

import java.util.ArrayList;
import java.util.List;

public class Accreditation {

	//Attributes
	private int accrId;
	private static int accrNum = 1;
	private String name;
	private List<LessonType> lessonTypes;

	
	//Constructor
	public Accreditation(String name_, LessonType lessonType_) {
		this.accrId = accrNum++;
		this.name = name_;
		this.lessonTypes = new ArrayList<>();
		addLesson(lessonType_);
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
	
	//Setter	
	public void setName(String n) {
		this.name = n;
	}
	
	public void setLessonTypes(List<LessonType> lessonTypes_) {
		if(lessonTypes_ == null) {
			throw new IllegalArgumentException("Lesson can't be null");
		}
		this.lessonTypes = lessonTypes_;
	}
	
	//Methods
	public void addLesson(LessonType lesson_) {
		if(lesson_ == null) {
			throw new IllegalArgumentException("Lesson can't be null");
		}
		this.lessonTypes.add(lesson_);
	}
	
}
