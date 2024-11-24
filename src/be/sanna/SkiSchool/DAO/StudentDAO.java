package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
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
	
	//Constructor
	public StudentDAO() {
		this.conn = ConnectionJDBC.getInstance();
	}
	
	//Methods
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		String query = "SELECT p.personID, p.firstname, p.lastname, p.birthDate "
				+ "FROM persons p "
				+ "JOIN students s ON p.personID = s.personID";
		
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			while (rs.next()) {
				Student student = new Student();
				student.setID(rs.getInt("p.personID"));
				student.setFirstName(rs.getString("p.firstname"));
				student.setLastName(rs.getString("p.lastname"));
				student.setDob(rs.getDate("birthDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;	
	}
}
