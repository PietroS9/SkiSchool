package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.POJO.Period;
import be.sanna.SkiSchool.Utilities.ConnectionJDBC;

public class PeriodDAO {
	
	//Attributes
	private final Connection conn;
	private List<Period> periods = new ArrayList<>();
	
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
		if(periods.isEmpty()) {
			String query = "SELECT PERIODID, STARTDATE, ENDDATE, ISVACATION FROM PERIODS";
			
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)){
				
				while (rs.next()) {
					Period period = new Period();
					period.setID(rs.getInt("PERIODID"));
					period.setStartDate(rs.getDate("STARTDATE").toLocalDate());
					period.setEndDate(rs.getDate("ENDDATE").toLocalDate());
					period.setIsVacation(ToBoolean(rs.getInt("ISVACATION")));
					
					addPeriod(period);
				}
			} catch (SQLException e) {
				System.err.println("Erreur lors de la récupération des périodes de réservation");
			}
		}
		return periods;
	}
	
	public void addPeriod(Period period_) {
		periods.add(period_);
	}
	
	public void removePeriod(Period period_) {
		periods.remove(period_);
	}
	
	public int getNextID() {
		String getNextIDQuery = "SELECT PERIODS_SEQ.NEXTVAL FROM dual";
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
	
	public void insertToDB(Period period_) {
		String insertQuery = "INSERT INTO PERIODS VALUES (?,?,?,?)";
		
		try(PreparedStatement pstmtPeriod = conn.prepareStatement(insertQuery)){
			
			pstmtPeriod.setInt(1, period_.getID());
			pstmtPeriod.setDate(2, java.sql.Date.valueOf(period_.getStartDate()));
			pstmtPeriod.setDate(3, java.sql.Date.valueOf(period_.getEndDate()));
			if(period_.getIsVacation()==false) {
				pstmtPeriod.setInt(4, 0);
			} else {
				pstmtPeriod.setInt(4, 1);
			}
			pstmtPeriod.executeUpdate();
			
			addPeriod(period_);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateToDB(Period period) {
		String updateQuery = "UPDATE PERIODS SET  STARTDATE= ?, ENDDATE = ?, ISVACATION = ? " +
							 "WHERE PERIODID = ?";
		
		try(PreparedStatement pstmtPeriod = conn.prepareStatement(updateQuery)){
			
			pstmtPeriod.setDate(1, java.sql.Date.valueOf(period.getStartDate()));
			pstmtPeriod.setDate(2, java.sql.Date.valueOf(period.getEndDate()));
			if(period.getIsVacation()==false) {
				pstmtPeriod.setInt(3, 0);
			} else {
				pstmtPeriod.setInt(3, 1);
			}
			pstmtPeriod.setInt(4, period.getID());
			pstmtPeriod.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteToDB(Period periodToDelete) {
		String deleteQuery = "DELETE FROM PERIODS WHERE PERIODID = ?";
		
		try(PreparedStatement pstmtDelete = conn.prepareStatement(deleteQuery)){
			
			pstmtDelete.setInt(1, periodToDelete.getID());
			pstmtDelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		removePeriod(periodToDelete);
	}
}
