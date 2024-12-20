package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.sanna.SkiSchool.POJO.Accreditation;
import be.sanna.SkiSchool.POJO.Instructor;
import be.sanna.SkiSchool.POJO.Student;
import be.sanna.SkiSchool.Utilities.ConnectionJDBC;

public class InstructorDAO {

	//Attributes
	private Connection conn = null;
	private List<Instructor> instructors = new ArrayList<>();
	
	//Constructor
	public InstructorDAO() {
		this.conn = ConnectionJDBC.getInstance();
	}
	
	//Getter
	public List<Instructor> getInstructors(){
		return instructors;
	}
	
	//Setter
	public void setInstructors(Instructor instructor_) {
		this.instructors.add(instructor_);
	}
	
	//Methods
	public List<Instructor> getAllInstructors(List<Accreditation> accrs) {
		if(instructors.isEmpty()) {
			
			String query = "SELECT p.personID, p.firstname, p.lastname, p.birthDate, a.name_ "
					+ "FROM persons p "
					+ "JOIN instructors i ON p.personID = i.personID "
					+ "JOIN instructors_accreditations ia ON i.personID = ia.personID "
					+ "JOIN accreditations a ON ia.accrID = a.accrID "
					+ "ORDER BY p.personID";
			
			try(Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)){
				
				Map<Integer, Instructor> instructorMap = new HashMap<>();
				
				while (rs.next()) {
					
					int instructorID = rs.getInt("personID");
					String accrName = rs.getString("name_");
					Instructor instructor = instructorMap.get(instructorID);
					
					
					if (instructor == null) {
	                    instructor = new Instructor();
	                    instructor.setID(instructorID);
	                    instructor.setFirstName(rs.getString("firstname"));
	                    instructor.setLastName(rs.getString("lastname"));
	                    instructor.setDob(rs.getDate("birthDate").toLocalDate());

	                    instructorMap.put(instructorID, instructor);
	                }
					
					for (Accreditation accr : accrs) {
	                    if (accr.getName().equals(accrName)) {
	                        instructor.addAccreditation(accr);
	                        break;
	                    }
	                }
				}
				
				instructors.addAll(instructorMap.values());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instructors;	
	}
	
	public List<Instructor> getInstructorsByAccreditation(Accreditation accr){
		List<Instructor> instructors_ = new ArrayList<>();
		
		for(Instructor inst : instructors) {
			for(Accreditation accr_ : inst.getAccreditations()) {
				if(accr_.getAccrId() == accr.getAccrId()) {
					instructors_.add(inst);
				}
			}
		}
		return instructors_;
	}
	
	public void addInstructor(Instructor instructor_) {
		instructors.add(instructor_);
	}
	
	public void removeInstructor(Instructor instructor_) {
		instructors.remove(instructor_);
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
	
	public void insertToDB(Instructor instructor) {
		String personsQuery = "INSERT INTO persons VALUES (?,?,?,?)";
		String instructorsQuery = "INSERT INTO instructors VALUES (?)";
		String insertAccreditationsQuery = "INSERT INTO instructors_accreditations VALUES (?,?)";
		
		try (PreparedStatement pstmtPersons = conn.prepareStatement(personsQuery);
			 PreparedStatement pstmtInstructors = conn.prepareStatement(instructorsQuery);
			 PreparedStatement pstmtInsertAccr = conn.prepareStatement(insertAccreditationsQuery)){

					 //Insert into persons
					pstmtPersons.setInt(1, instructor.getId());
	                pstmtPersons.setString(2, instructor.getFirstName());
	                pstmtPersons.setString(3, instructor.getLastName());
	                pstmtPersons.setDate(4, java.sql.Date.valueOf(instructor.getDob()));
	                pstmtPersons.executeUpdate();
	
	                //insert into instructors
	                pstmtInstructors.setInt(1, instructor.getId());
	                pstmtInstructors.executeUpdate();
					 
					 
	                for(Accreditation accr : instructor.getAccreditations()) {
					 
						 pstmtInsertAccr.setInt(1, instructor.getId());
						 pstmtInsertAccr.setInt(2, accr.getAccrId());
						 pstmtInsertAccr.executeUpdate();
	                }
				
					addInstructor(instructor);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateToDB(Instructor instructor) {
		String updateQuery = "UPDATE persons SET firstname=?, lastname=?, birthDAte=? WHERE personID=?";
		String deleteQuery = "DELETE FROM INSTRUCTORS_ACCREDITATIONS WHERE personID = ?";
		String insertAccrQuery = "INSERT INTO instructors_accreditations VALUES (?,?)";
		
		try(PreparedStatement pstmtPersons = conn.prepareStatement(updateQuery);
			PreparedStatement pstmtDeleteAccr = conn.prepareStatement(deleteQuery);
			PreparedStatement pstmtInsertAccr = conn.prepareStatement(insertAccrQuery)){
			
			//Update person
			pstmtPersons.setString(1, instructor.getFirstName());
			pstmtPersons.setString(2, instructor.getLastName());
			pstmtPersons.setDate(3, java.sql.Date.valueOf(instructor.getDob()));
			pstmtPersons.setInt(4, instructor.getId());
			pstmtPersons.executeUpdate();
			
			//Clean all accreditations linked to instructor
			pstmtDeleteAccr.setInt(1, instructor.getId());
			pstmtDeleteAccr.executeUpdate();
			
			//Insert all accreditations of instructor
			for(Accreditation accr : instructor.getAccreditations()) {
				pstmtInsertAccr.setInt(1, instructor.getId());
				pstmtInsertAccr.setInt(2, accr.getAccrId());
				pstmtInsertAccr.executeUpdate();
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteToDB(Instructor instructorToDelete) {
		String deleteInst_AccrQuery = "DELETE FROM INSTRUCTORS_ACCREDITATIONS WHERE PERSONID = ?";
		String deleteInstructorQuery = "DELETE FROM INSTRUCTORS WHERE PERSONID = ?";
		String deletePersonsQuery = "DELETE FROM PERSONS WHERE PERSONID = ?";
		
		try (PreparedStatement pstmtINst_AccrDelete = conn.prepareStatement(deleteInst_AccrQuery);
			 PreparedStatement pstmtInstDelete = conn.prepareStatement(deleteInstructorQuery);
			 PreparedStatement pstmtPrsDelete = conn.prepareStatement(deletePersonsQuery)){
			
			pstmtINst_AccrDelete.setInt(1, instructorToDelete.getId());
			pstmtINst_AccrDelete.executeUpdate();
			
			pstmtInstDelete.setInt(1, instructorToDelete.getId());
			pstmtInstDelete.executeUpdate();
			
			pstmtPrsDelete.setInt(1, instructorToDelete.getId());
			pstmtPrsDelete.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		removeInstructor(instructorToDelete);
	}
}
