package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.POJO.Accreditation;
import be.sanna.SkiSchool.POJO.Instructor;
import be.sanna.SkiSchool.Utilities.ConnectionJDBC;

public class InstructorDAO {

	//Attributes
	private Connection conn = null;
	private List<Instructor> instructors = new ArrayList<>();
	private List<Instructor> wInstructors = new ArrayList<>();
	
	//Constructor
	public InstructorDAO() {
		this.conn = ConnectionJDBC.getInstance();
	}
	
	//Getter
	public List<Instructor> getInstructors(){
		return instructors;
	}
	
	public List<Instructor> getWInstructors(){
		return wInstructors;
	}
	
	//Setter
	public void setInstructors(Instructor instructor_) {
		this.instructors.add(instructor_);
	}
	
	public void setWInstructors(Instructor instructor_) {
		this.wInstructors.add(instructor_);
	}
	
	//Methods
	public List<Instructor> getAllInstructors(List<Accreditation> accrs) {
		String query = "SELECT p.personID, p.firstname, p.lastname, p.birthDate, a.name_ "
				+ "FROM persons p "
				+ "JOIN instructors i ON p.personID = i.personID "
				+ "JOIN instructors_accreditations ia ON i.personID = ia.personID "
				+ "JOIN accreditations a ON ia.accrID = a.accrID "
				+ "ORDER BY p.personID";
		
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while (rs.next()) {
				
				//If instructor has more than one accreditation
				for(Instructor instructor : instructors) {
					if(instructor.getId() == rs.getInt("personID")) {
						for(Accreditation accr : accrs) {
							if(accr.getName().equals(rs.getString("name_"))) {
								instructor.addAccreditation(accr);
								break;
							}
						}
						break;
					}
				}
				//Else
				Instructor instructor = new Instructor();
				instructor.setID(rs.getInt("personID"));
				instructor.setFirstName(rs.getString("firstname"));
				instructor.setLastName(rs.getString("lastname"));
				instructor.setDob(rs.getDate("birthDate").toLocalDate());
				for(Accreditation accr : accrs) {
					if(accr.getName().equals(rs.getString("name_"))) {
						instructor.addAccreditation(accr);
					}
				}
				
				instructors.add(instructor);
				wInstructors.add(instructor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wInstructors;	
	}
	
	public void addInstructor(Instructor instructor_) {
		wInstructors.add(instructor_);
	}
	
	public void SyncInstructorsToDB() {
		String personsQuery = "INSERT INTO persons VALUES (?,?,?,?)";
		String instructorsQuery = "INSERT INTO instructors VALUES (?)";
		String updateInstructorQuery = "UPDATE persons SET firstname=?, lastname=?, birthDAte=? WHERE personID=?";
		String insertAccreditationsQuery = "INSERT INTO instructors_accreditations VALUES (?,?)";
		String cleanInst_AccrQuery = "DELETE FROM instructors_accreditations WHERE personID = ?";
		
		try (PreparedStatement pstmtPersons = conn.prepareStatement(personsQuery);
				PreparedStatement pstmtInstructors = conn.prepareStatement(instructorsQuery);
				PreparedStatement pstmtUpdate = conn.prepareStatement(updateInstructorQuery);
				PreparedStatement pstmtInsertAccr = conn.prepareStatement(insertAccreditationsQuery);
				PreparedStatement pstmtClean = conn.prepareStatement(cleanInst_AccrQuery)){
			
			for (Instructor wInstructor : wInstructors) {
				
				Instructor existingInstructor = instructors.stream().filter(s -> s.getId() == wInstructor.getId()).findFirst().orElse(null);
				 
				 //if newInstructor --> insert
				 if(existingInstructor == null) {
					 
					 //Insert into persons
					pstmtPersons.setInt(1, wInstructor.getId());
	                pstmtPersons.setString(2, wInstructor.getFirstName());
	                pstmtPersons.setString(3, wInstructor.getLastName());
	                pstmtPersons.setDate(4, java.sql.Date.valueOf(wInstructor.getDob()));
	                pstmtPersons.executeUpdate();
	
	                //insert into instructors
	                pstmtInstructors.setInt(1, wInstructor.getId());
	                pstmtInstructors.executeUpdate();
					 
					 /*for(Accreditation accr : wInstructor.getAccreditations()) {
						 System.out.println("Vérification pour instructeur ID: " + wInstructor.getId()
						 + "Nom : " + wInstructor.getFirstName()
						 + "Prenom : " + wInstructor.getLastName()
						 + "DOB : " + wInstructor.getDob()
 						 + ", accréditation ID: " + accr.getAccrId()
 						 + "Name :" + accr.getName());
					 }*/
					 
					 for(Accreditation accr : wInstructor.getAccreditations()) {
						 
						 pstmtInsertAccr.setInt(1, wInstructor.getId());
						 pstmtInsertAccr.setInt(2, accr.getAccrId());
						 pstmtInsertAccr.executeUpdate();
					 }
					
					 instructors.add(wInstructor);
				 //if instructor has changed
				 } else {
					 System.out.println("Je rentre dans le else");
					 //Clean all linked accreditations
					 pstmtClean.setInt(1, wInstructor.getId());
					 pstmtClean.executeUpdate();
					 
					 //Update info
					 pstmtUpdate.setString(1, wInstructor.getFirstName());
		             pstmtUpdate.setString(2, wInstructor.getLastName());
		             pstmtUpdate.setDate(3, java.sql.Date.valueOf(wInstructor.getDob()));
		             pstmtUpdate.setInt(4, wInstructor.getId());
		             pstmtUpdate.executeUpdate();
		             
		             //Insert all accreditations
		             for(Accreditation accr : wInstructor.getAccreditations()) {
						 
						 pstmtInsertAccr.setInt(1, wInstructor.getId());
						 pstmtInsertAccr.setInt(2, accr.getAccrId());
						 pstmtInsertAccr.executeUpdate();
					 }

		             int index = instructors.indexOf(existingInstructor);
		             instructors.set(index, wInstructor);
				 }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
