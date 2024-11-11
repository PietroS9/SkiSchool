package be.sanna.SkiSchool;

public abstract class Person {

	//Attributes
	private int id;
	private static int personNum = 1;
	private String firstName;
	private String lastName;
	private int age;
	
	//Constructor
	public Person(String fn, String ln, int age_) {
		this.id = personNum++;
		this.firstName = fn;
		this.lastName = ln;
		this.age = age_;
	}
	
	//Getter
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getAge() {
		return age;
	}
	
	//Setter
	public void setFirstName(String fn) {
		this.firstName = fn;
	}
	
	public void setLastName(String ln) {
		this.lastName = ln;
	}
	
	public void setAge(int age_) {
		this.age = age_;
	}
	
	//Methods
	
	
	
}
