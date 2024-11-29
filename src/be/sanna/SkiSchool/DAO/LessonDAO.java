package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.POJO.Instructor;
import be.sanna.SkiSchool.POJO.Lesson;
import be.sanna.SkiSchool.POJO.LessonType;
import be.sanna.SkiSchool.Utilities.ConnectionJDBC;

public class LessonDAO {
	
	//Attributes
	private Connection conn = null;
	private List<Lesson> lessons = new ArrayList<>();
	
	//Constructor
	public LessonDAO() {
		this.conn = ConnectionJDBC.getInstance();
	}
	
	//Getter
	public List<Lesson> getLessons(){
		return lessons;
	}
	
	//Setter
	public void setLessons(Lesson lesson_) {
		this.lessons.add(lesson_);
	}
	
	//Methods
	public List<Lesson> getAllLessons(List<Instructor> instructors, List<LessonType> lessonTypes){
		if(lessons.isEmpty()) {
			String query = "SELECT l.lessonID, l.lessonDate,l.individual, l.amORpm, "
					+ "l.duration, l.minStudent, l.maxStudent, l.personid, l.lessontypeID "
					+ "from lessons l ";
			
			try(Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)){
				
				while(rs.next()) {
					Lesson lesson = new Lesson();
					
					lesson.setID(rs.getInt("lessonid"));
					lesson.setLessonDate(rs.getDate("lessonDate").toLocalDate());
					
					int individual_ = rs.getInt("individual");
					if(individual_==0) {
						lesson.setIndividual(false);
					}else {
						lesson.setIndividual(true);
					}
					
					int amORpm_ = rs.getInt("amORpm");
					if(amORpm_ == 0) {
						lesson.setAMorPM(false);
					} else {
						lesson.setAMorPM(true);
					}
					lesson.setDuration(rs.getInt("duration"));
					lesson.setMinStudents(rs.getInt("minStudent"));
					lesson.setMaxStudents(rs.getInt("maxStudent"));
					
					int instructor_ = rs.getInt("personID");
					for(Instructor instructor : instructors) {
						if(instructor.getId() == instructor_) {
							lesson.setInstructor(instructor);
							break;
						}
					}
					
					int lessonType_ = rs.getInt("lessonTypeID");
					for(LessonType lessonType : lessonTypes) {
						if(lessonType.getLessonTypeId() == lessonType_) {
							lesson.setType(lessonType);
							break;
						}
					}
					addLesson(lesson);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lessons;
	}
	
	public void addLesson(Lesson lesson_) {
		lessons.add(lesson_);
	}
	
	public void removeLesson(Lesson lesson_) {
		lessons.remove(lesson_);
	}
	
	public int getNextID() {
		String getNextIDQuery = "SELECT LESSONS_SEQ.NEXTVAL FROM dual";
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
	
	public void insertToDB(Lesson lesson) {
		String lessonQuery = "INSERT INTO lessons VALUES (?,?,?,?,?,?,?,?,?)";
		
		try(PreparedStatement pstmtLessons = conn.prepareStatement(lessonQuery)){
			
			pstmtLessons.setInt(1, lesson.getLessonId());
			pstmtLessons.setDate(2, java.sql.Date.valueOf(lesson.getLessonDate()));
			if(lesson.getAMorPM()==false) { //false = morning
				pstmtLessons.setInt(3, 0);
			} else {
				pstmtLessons.setInt(3,1);
			}
			pstmtLessons.setInt(4, lesson.getMinStudents());
			pstmtLessons.setInt(5, lesson.getMaxStudents());
			pstmtLessons.setInt(6, lesson.getInstructor().getId());
			pstmtLessons.setInt(7, lesson.getType().getLessonTypeId());
			if(lesson.getIndividual()==false) {
				pstmtLessons.setInt(8, 0);
			} else {
				pstmtLessons.setInt(8, 1);
			}
			pstmtLessons.setInt(9, lesson.getDuration());
			pstmtLessons.executeUpdate();
			
			addLesson(lesson);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateToDB(Lesson lesson) {
		String updateQuery = "UPDATE LESSONS SET lessondate = ?, amorpm = ?, minStudent = ?, " +
							 "maxStudent = ?, individual = ?, duration = ?, lessontypeid = ? " +
							 "WHERE lessonid = ?";
		
		try(PreparedStatement pstmtLesson = conn.prepareStatement(updateQuery)){
			
			pstmtLesson.setDate(1, java.sql.Date.valueOf(lesson.getLessonDate()));
			if(lesson.getAMorPM()==false) {
				pstmtLesson.setInt(2, 0);
			} else {
				pstmtLesson.setInt(2,1);
			}
			pstmtLesson.setInt(3, lesson.getMinStudents());
			pstmtLesson.setInt(4, lesson.getMaxStudents());
			if(lesson.getIndividual()==false) {
				pstmtLesson.setInt(5, 0);
			} else {
				pstmtLesson.setInt(5, 1);
			}
			pstmtLesson.setInt(6, lesson.getDuration());
			System.out.println("Dans l'updat lessons : ID =" + lesson.getType().getLessonTypeId());
			pstmtLesson.setInt(7, lesson.getType().getLessonTypeId());
			pstmtLesson.setInt(8, lesson.getLessonId());
			pstmtLesson.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteToDB(Lesson lessonToDelete) {
		String deleteQuery = "DELETE FROM LESSONS WHERE LESSONID = ?";
		
		try(PreparedStatement pstmtDelete = conn.prepareStatement(deleteQuery)){
			
			pstmtDelete.setInt(1, lessonToDelete.getLessonId());
			pstmtDelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		removeLesson(lessonToDelete);
	}
}
