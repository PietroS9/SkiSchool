package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.POJO.Accreditation;
import be.sanna.SkiSchool.POJO.LessonType;
import be.sanna.SkiSchool.POJO.Level;
import be.sanna.SkiSchool.Utilities.ConnectionJDBC;

public class LessonTypeDAO {
	
	//Attributes
	private Connection conn = null;
	private List<LessonType> lessonTypes = new ArrayList<>();
	
	//Constructor
	public LessonTypeDAO() {
		this.conn = ConnectionJDBC.getInstance();
	}
	
	//Getter
	public List<LessonType> getLessonTypes(){
		return lessonTypes;
	}
	
	//Setter
	public void setLessonTypes(LessonType lessonType_) {
		this.lessonTypes.add(lessonType_);
	}
	
	//Methods
	public List<LessonType> getAllLessontypes(List<Accreditation> accrs){
		if(lessonTypes.isEmpty()) {
			
			String query = "SELECT lessonTypeID, level_, price, accrid "
					+ "FROM LESSONTYPES";
			
			try(Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)){
				
				while(rs.next()) {
					LessonType lessonType = new LessonType();
					lessonType.setLessonTypeID(rs.getInt("lessonTypeID"));
					
					String levelDescription = rs.getString("level_");
					try {
						Level level = Level.fromDescription(levelDescription);
						lessonType.setLevel(level);
					}catch (IllegalArgumentException e) {
	                    System.err.println("Erreur : Niveau inconnu '" + levelDescription + "' dans la base de données.");
	                    continue;
	                }
					
					lessonType.setPrice(rs.getDouble("price"));
					int accrID = rs.getInt("accrID");
					for (Accreditation accr : accrs) {
	                    if (accr.getAccrId() == accrID) {
	                        lessonType.setAccreditation(accr);
	                        break;
	                    }
	                }
					
					lessonTypes.add(lessonType);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lessonTypes;
	}
	
	public List<LessonType> getLessonTypesByAccreditation(Accreditation accr){
		List<LessonType> lessonTypes_ = new ArrayList<>();
		
		for(LessonType lessonType : lessonTypes) {
			if(lessonType.getAccreditation().getAccrId() == accr.getAccrId()) {
				lessonTypes_.add(lessonType);
			}
		}
		return lessonTypes_;
	}
}
