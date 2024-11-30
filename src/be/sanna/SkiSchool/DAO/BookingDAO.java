package be.sanna.SkiSchool.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.sanna.SkiSchool.POJO.Booking;
import be.sanna.SkiSchool.POJO.Lesson;
import be.sanna.SkiSchool.POJO.Period;
import be.sanna.SkiSchool.POJO.Student;
import be.sanna.SkiSchool.Utilities.ConnectionJDBC;

public class BookingDAO {
	
	//Attributes
	private Connection conn = null;
	private List<Booking> bookings = new ArrayList<>();
	
	//Construction
	public BookingDAO() {
		this.conn = ConnectionJDBC.getInstance();
	}
	
	//Getter
	public List<Booking> getBookings(){
		return bookings;
	}
	
	//Setter
	public void setBookings(Booking booking_) {
		this.bookings.add(booking_);
	}
	
	//Methods
	public List<Booking> getAllBookings(List<Lesson> lessons, List<Student> students, List<Period> periods){
		if(bookings.isEmpty()) {
			String query = "SELECT BOOKINGID, PRICE, INSURANCE, PERIODID, PERSONID, LESSONID "
					+ "FROM BOOKINGS";
			try(Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)){
				
				while(rs.next()) {
					Booking booking = new Booking();
					
					booking.setID(rs.getInt("BOOKINGID"));
					booking.setPrice(rs.getDouble("PRICE"));
					if(rs.getInt("INSURANCE") == 0) {
						booking.setInsurance(false);
					} else {
						booking.setInsurance(true);
					}
					
					for(Period period : periods) {
						if(rs.getInt("PERIODID") == period.getID()) {
							booking.setPeriod(period);
							break;
						}
					}
					
					for(Student student : students) {
						if(rs.getInt("PERSONID") == student.getId()) {
							booking.setStudent(student);
							break;
						}
					}
					
					for(Lesson lesson : lessons) {
						if(rs.getInt("LESSONID") == lesson.getLessonId()) {
							booking.setLesson(lesson);
							booking.setInstructor(lesson.getInstructor());
							break;
						}
					}
					addBooking(booking);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bookings;
	}
	
	public void addBooking(Booking booking_) {
		bookings.add(booking_);
	}
	
	public void removeBooking(Booking booking_) {
		bookings.remove(booking_);
	}
	
	public int getNextID() {
		String getNextIDQuery = "SELECT BOOKINGS_SEQ.NEXTVAL FROM dual";
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
	
	public void insertToDB(Booking booking_, PeriodDAO periodDAO) {
		String bookingQuery = "INSERT INTO BOOKINGS VALUES (?,?,?,?,?,?)";
		
		try(PreparedStatement pstmtBooking = conn.prepareStatement(bookingQuery)){
			
			booking_.getPeriod().insertToDB(periodDAO);
			
			pstmtBooking.setInt(1, booking_.getBookId());
			pstmtBooking.setDouble(2, booking_.getPrice());
			if(booking_.getInsurance()==false) {
				pstmtBooking.setInt(3, 0);
			} else {
				pstmtBooking.setInt(3, 1);
			}
			pstmtBooking.setInt(4, booking_.getPeriod().getID());
			pstmtBooking.setInt(5, booking_.getStudent().getId());
			pstmtBooking.setInt(6, booking_.getLesson().getLessonId());
			
			pstmtBooking.executeUpdate();
			addBooking(booking_);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateToDB(Booking booking_, PeriodDAO periodDAO) {
		String updateQuery = "UPDATE BOOKINGS SET PRICE = ?, INSURANCE = ?, PERIODID = ?, " +
							 "PERSONID = ?, LESSONID = ? WHERE BOOKINGID = ?";
		
		try(PreparedStatement pstmtBooking = conn.prepareStatement(updateQuery)){
			
			booking_.getPeriod().updateToDB(periodDAO);
			pstmtBooking.setDouble(1, booking_.getPrice());
			if(booking_.getInsurance()==false) {
				pstmtBooking.setInt(2, 0);
			} else {
				pstmtBooking.setInt(2, 1);
			}
			pstmtBooking.setInt(3, booking_.getPeriod().getID());
			pstmtBooking.setInt(4, booking_.getStudent().getId());
			pstmtBooking.setInt(5, booking_.getLesson().getLessonId());
			pstmtBooking.setInt(6, booking_.getBookId());
			pstmtBooking.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteToDB(Booking booking_) {
		String deleteQuery = "DELETE FROM BOOKINGS WHERE BOOKINGID = ?";
		
		try(PreparedStatement pstmtDelete = conn.prepareStatement(deleteQuery)){
			
			pstmtDelete.setInt(1, booking_.getBookId());
			pstmtDelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		removeBooking(booking_);
	}
}
