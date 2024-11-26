package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.POJO.Accreditation;
import be.sanna.SkiSchool.Utilities.ConnectionJDBC;

public class AccreditationDAO {
	
	//Attributes
	private Connection conn = null;
	private List<Accreditation> accreditations = new ArrayList<>();
	
	//Constructor
	public AccreditationDAO() {
		this.conn = ConnectionJDBC.getInstance();
	}
	
	//Getter
	public List<Accreditation> getAccreditations(){
		return accreditations;
	}
	
	//Setter
	public void setAccreditations(Accreditation accr_) {
		this.accreditations.add(accr_);
	}
	
	//Methods
	public List<Accreditation> getAllAccreditations(){
		
		String query = "SELECT accrID, name_ FROM ACCREDITATIONS";
		
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while (rs.next()) {
				Accreditation accr = new Accreditation();
				accr.setID(rs.getInt("accrID"));
				accr.setName(rs.getString("name_"));
				
				accreditations.add(accr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accreditations;
	}

}
