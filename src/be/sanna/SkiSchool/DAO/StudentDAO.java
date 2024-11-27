package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import be.sanna.SkiSchool.POJO.*;
import be.sanna.SkiSchool.Utilities.ConnectionJDBC;

public class StudentDAO {
	
	//Attributes
	private Connection conn = null;
	private List<Student> students = new ArrayList<>();
	
	//Constructor
	public StudentDAO() {
		this.conn = ConnectionJDBC.getInstance();
	}
	
	//Getter
	public List<Student> getStudents(){
		return students;
	}
	
	//Setter
	public void setStudents(Student student_) {
		this.students.add(student_);
	}
	
	//Methods
	public List<Student> getAllStudents() {
		if(students.isEmpty()) {
			
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
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return students;	
	}
	
	public void addStudent(Student student_) {
		students.add(student_);
	}
	
	public void removeStudent(Student student_) {
		students.remove(student_);
	}
	
	public int getNextID() {
		String getNextIDQuery = "SELECT PERSONS_SEQ.NEXTVAL FROM dual";
		int stdID = -1;
		
		try (Statement stmt = conn.createStatement()){
				
			 ResultSet rsNextID = stmt.executeQuery(getNextIDQuery);
			 if(rsNextID.next()) {
				 stdID = rsNextID.getInt(1);
			 }
			 rsNextID.close();
			 
			 if(stdID == -1) throw new SQLException("Échec de la récupération du prochain ID");
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stdID;
	}
	
	public void insertToDB(Student newStudent) {
		String personsQuery = "INSERT INTO persons VALUES (?,?,?,?)";
		String studentsQuery = "INSERT INTO students VALUES (?)";
		
		try (PreparedStatement pstmtPersons = conn.prepareStatement(personsQuery);
			 PreparedStatement pstmtStudents = conn.prepareStatement(studentsQuery)){
			 
			 pstmtPersons.setInt(1, newStudent.getId());
			 pstmtPersons.setString(2, newStudent.getFirstName());
			 pstmtPersons.setString(3, newStudent.getLastName());
			 pstmtPersons.setDate(4, java.sql.Date.valueOf(newStudent.getDob()));
			 pstmtPersons.executeUpdate();
			 
			 pstmtStudents.setInt(1, newStudent.getId());
			 pstmtStudents.executeUpdate();
			 
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, 
		            "Erreur lors de l'insertion du skieur dans la base de données : " + e.getMessage(), 
		            "Erreur", 
		            JOptionPane.ERROR_MESSAGE);
		}
		
		addStudent(newStudent);
	}
	
	public void updateToDB(Student modifiedStudent) {
		String updateQuery = "UPDATE persons SET firstname=?, lastname=?, birthDAte=? WHERE personID=?";
		
		try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateQuery)){
			
			 pstmtUpdate.setString(1, modifiedStudent.getFirstName());
             pstmtUpdate.setString(2, modifiedStudent.getLastName());
             pstmtUpdate.setDate(3, java.sql.Date.valueOf(modifiedStudent.getDob()));
             pstmtUpdate.setInt(4, modifiedStudent.getId());
             pstmtUpdate.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteToDB(Student studentToDelete) {
		String deleteStudentQuery = "DELETE FROM STUDENTS WHERE PERSONID = ?";
		String deletePersonsQuery = "DELETE FROM PERSONS WHERE PERSONID = ?";
		
		try (PreparedStatement pstmtStdDelete = conn.prepareStatement(deleteStudentQuery);
			 PreparedStatement pstmtPrsDelete = conn.prepareStatement(deletePersonsQuery)){
			
			pstmtStdDelete.setInt(1, studentToDelete.getId());
			pstmtStdDelete.executeUpdate();
			
			pstmtPrsDelete.setInt(1, studentToDelete.getId());
			pstmtPrsDelete.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		removeStudent(studentToDelete);
	}
	
}
