package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.POJO.*;
import be.sanna.SkiSchool.Utilities.ConnectionJDBC;

public class StudentDAO {
	
	//Attributes
	private Connection conn = null;
	private List<Student> students = new ArrayList<>();
	private List<Student> wStudents = new ArrayList<>();
	
	//Constructor
	public StudentDAO() {
		this.conn = ConnectionJDBC.getInstance();
	}
	
	//Getter
	public List<Student> getStudents(){
		return wStudents;
	}
	
	public List<Student> getWStudents(){
		return wStudents;
	}
	
	//Setter
	public void setStudents(Student student_) {
		this.students.add(student_);
	}
	
	public void setWStudents(Student student_) {
		this.wStudents.add(student_);
	}
	
	//Methods
	public List<Student> getAllStudents() {
		String query = "SELECT p.personID, p.firstname, p.lastname, p.birthDate "
				+ "FROM persons p "
				+ "JOIN students s ON p.personID = s.personID "
				+ "ORDER BY p.personID";
		
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			while (rs.next()) {
				Student student = new Student();
				student.setID(rs.getInt("personID"));
				student.setFirstName(rs.getString("firstname"));
				student.setLastName(rs.getString("lastname"));
				student.setDob(rs.getDate("birthDate").toLocalDate());
				
				students.add(student);
				wStudents.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wStudents;	
	}
	
	public void addStudent(Student student_) {
		wStudents.add(student_);
	}
	
	public void SyncStudentsToDB() {
		String personsQuery = "INSERT INTO persons VALUES (?,?,?,?)";
		String studentsQuery = "INSERT INTO students VALUES (?)";
		String updateQuery = "UPDATE persons SET firstname=?, lastname=?, birthDAte=? WHERE personID=?";
		
		try (PreparedStatement pstmtPersons = conn.prepareStatement(personsQuery);
				PreparedStatement pstmtStudents = conn.prepareStatement(studentsQuery);
				PreparedStatement pstmtUpdate = conn.prepareStatement(updateQuery)){
			
			for (Student wStudent : wStudents) {
				
				 Student existingStudent = students.stream().filter(s -> s.getId() == wStudent.getId()).findFirst().orElse(null);
				 
				 //if newStudent --> insert
				 if(existingStudent == null) {
					 
					 pstmtPersons.setInt(1, wStudent.getId());
					 pstmtPersons.setString(2, wStudent.getFirstName());
					 pstmtPersons.setString(3, wStudent.getLastName());
					 pstmtPersons.setDate(4, java.sql.Date.valueOf(wStudent.getDob()));
					 pstmtPersons.executeUpdate();
					
					 pstmtStudents.setInt(1, wStudent.getId());
					 pstmtStudents.executeUpdate();
					
					 students.add(wStudent);
					//if student has changed
				 } else if (!existingStudent.equals(wStudent)) {
					 
					 pstmtUpdate.setString(1, wStudent.getFirstName());
		             pstmtUpdate.setString(2, wStudent.getLastName());
		             pstmtUpdate.setDate(3, java.sql.Date.valueOf(wStudent.getDob()));
		             pstmtUpdate.setInt(4, wStudent.getId());
		             pstmtUpdate.executeUpdate();

		             
		             int index = students.indexOf(existingStudent);
		             students.set(index, wStudent);
				 }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
