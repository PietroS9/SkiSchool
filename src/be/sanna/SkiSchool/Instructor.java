package be.sanna.SkiSchool;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person{

	//Attributes
	
	private List<Accreditation> accreditations; 
	
	//Constructor
	public Instructor(String fn, String ln, int age_, Accreditation accr) {
		super(fn,ln,age_);
		this.accreditations = new ArrayList<Accreditation>();
		addAccreditation(accr);
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
		addAccreditation(accr);
	}
	
	//Methods
	 public void addAccreditation(Accreditation accr) {
		 accreditations.add(accr);
	 }
	 public boolean IsAccreditate(Lesson lesson) {
		 ////Not finished yet
		 boolean flag = false;
		 int i = 0;
		 while(i<accreditations.size() && flag) {
			 if(lesson.getType().getLessonTypeId())
		 }
		 
		 return false;
	 }
}
