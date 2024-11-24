package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.POJO.Period;
import be.sanna.SkiSchool.Utilities.ConnectionJDBC;

public class PeriodDAO {
	
	//Attributes
	private final Connection conn;
	
	//Constructor
	public PeriodDAO() {
		this.conn = ConnectionJDBC.getInstance();
	}

	//Methods
	private boolean ToBoolean(int number) {
		if(number==0) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Period> getAllPeriods(){
		List<Period> periods = new ArrayList<>();
		String query = "SELECT periodID, startDate, endDate, isVacaction FROM period";
		
		try (PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()){
			
			while (rs.next()) {
				periods.add(new Period(rs.getInt("periodID"), rs.getDate("startDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), rs.getDate("endDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), ToBoolean(rs.getInt("isVacaction"))));
			}
		} catch (SQLException e) {
			System.err.println("Erreur lors de la récupération des périodes de réservation");
		}
		return periods;
	}
}
