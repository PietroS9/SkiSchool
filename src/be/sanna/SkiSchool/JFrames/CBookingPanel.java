package be.sanna.SkiSchool.JFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import be.sanna.SkiSchool.DAO.AccreditationDAO;
import be.sanna.SkiSchool.DAO.BookingDAO;
import be.sanna.SkiSchool.DAO.InstructorDAO;
import be.sanna.SkiSchool.DAO.LessonDAO;
import be.sanna.SkiSchool.DAO.LessonTypeDAO;
import be.sanna.SkiSchool.DAO.PeriodDAO;
import be.sanna.SkiSchool.DAO.StudentDAO;
import be.sanna.SkiSchool.POJO.Booking;
import be.sanna.SkiSchool.POJO.Lesson;
import be.sanna.SkiSchool.POJO.Period;
import be.sanna.SkiSchool.POJO.Student;

import javax.swing.JTable;

public class CBookingPanel extends JPanel {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTable tableCBooking;
	private JPanel panel;
	private JLabel lbl_info_Skier;
	private JLabel lbl_info_Lesson;
	private JLabel lbl_info_Period_startDate;
	private JLabel lbl_info_Period_endDate;
	private JLabel lbl_info_Booking_insurance;
	private JRadioButton rdbtnIsInsured;
	private JRadioButton rdbtnNotInsured;
	private ButtonGroup insuranceLesson;
	private JLabel lbl_info_Booking_price;
	private JTextField txtPrice;
	private JLabel lbl_info_euroSign;
	private JComboBox cBox_info_Skier;
	private JComboBox cBox_info_Lesson;
	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	private JScrollPane scrollPane;
	private JButton btnCreateBooking;
	private LessonDAO lessonDAO;
	private InstructorDAO instructorDAO;
	private AccreditationDAO accrDAO;
	private LessonTypeDAO lessonTypeDAO;
	private StudentDAO studentDAO;
	private BookingDAO bookingDAO;
	private PeriodDAO periodDAO;
	private Booking booking = new Booking();
	private List<Student> students = new ArrayList<>();
	private List<Lesson> lessons = new ArrayList<>();

	//Constructor
	public CBookingPanel(AccreditationDAO accrDAO_, BookingDAO bookingDAO_, InstructorDAO instDAO_, LessonDAO lessonDAO_,
						 LessonTypeDAO lessonTypeDAO_, PeriodDAO periodDAO_, StudentDAO studentDAO_) {
		this.accrDAO  = accrDAO_;
		this.bookingDAO = bookingDAO_;
		this.instructorDAO = instDAO_;
		this.lessonDAO = lessonDAO_;
		this.lessonTypeDAO = lessonTypeDAO_;
		this.periodDAO = periodDAO_;
		this.studentDAO = studentDAO_;
		setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 31, 400, 306);
		add(panel);
		panel.setLayout(null);
		
		lbl_info_Skier = new JLabel("Skier :");
		lbl_info_Skier.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_Skier);
		
		lbl_info_Lesson = new JLabel("Cours :");
		lbl_info_Lesson.setBounds(21, 86, 67, 14);
		panel.add(lbl_info_Lesson);
		
		lbl_info_Period_startDate = new JLabel("Date de début :");
		lbl_info_Period_startDate.setBounds(21, 131, 85, 14);
		panel.add(lbl_info_Period_startDate);
		
		lbl_info_Period_endDate = new JLabel("Date de fin :");
		lbl_info_Period_endDate.setBounds(21, 176, 85, 14);
		panel.add(lbl_info_Period_endDate);
		
		lbl_info_Booking_insurance = new JLabel("Assurance :");
		lbl_info_Booking_insurance.setBounds(21, 223, 104, 14);
		panel.add(lbl_info_Booking_insurance);
		
		rdbtnIsInsured = new JRadioButton("Oui");
		rdbtnIsInsured.setBounds(138, 219, 55, 23);
		panel.add(rdbtnIsInsured);
		
		rdbtnIsInsured.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        updatePrice();
		    }
		});
		
		rdbtnNotInsured = new JRadioButton("Non");
		rdbtnNotInsured.setSelected(true);
		rdbtnNotInsured.setBounds(195, 219, 55, 23);
		panel.add(rdbtnNotInsured);
		
		rdbtnNotInsured.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        updatePrice();
		    }
		});
		
		insuranceLesson = new ButtonGroup();
		insuranceLesson.add(rdbtnIsInsured);
		insuranceLesson.add(rdbtnNotInsured);
		
		lbl_info_Booking_price = new JLabel("Total :");
		lbl_info_Booking_price.setBounds(21, 270, 46, 14);
		panel.add(lbl_info_Booking_price);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(77, 267, 59, 20);
		txtPrice.setEditable(false);
		txtPrice.setText("0");
		panel.add(txtPrice);
		
		lbl_info_euroSign = new JLabel("€");
		lbl_info_euroSign.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_info_euroSign.setBounds(146, 269, 23, 14);
		panel.add(lbl_info_euroSign);
		
		cBox_info_Skier = new JComboBox();
		cBox_info_Skier.setBounds(133, 34, 232, 22);
		panel.add(cBox_info_Skier);
		
		students = studentDAO.getAllStudents();
		cBox_info_Skier.removeAllItems();
		for(Student student : students) {
			cBox_info_Skier.addItem(student);
		}
		cBox_info_Skier.setSelectedIndex(-1);
		
		cBox_info_Lesson = new JComboBox();
		cBox_info_Lesson.setBounds(133, 82, 232, 22);
		panel.add(cBox_info_Lesson);
		
		lessons = lessonDAO.getAllLessons(instructorDAO.getAllInstructors(accrDAO.getAllAccreditations()), 
										  lessonTypeDAO.getAllLessontypes(accrDAO.getAllAccreditations()));
		cBox_info_Lesson.removeAllItems();
		for(Lesson lesson : lessons) {
			cBox_info_Lesson.addItem(lesson);
		}
		cBox_info_Lesson.setSelectedIndex(-1);
		
		cBox_info_Lesson.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            updatePrice();
		        }
		    }
		});
		
		startDateChooser = new JDateChooser();
		startDateChooser.setBounds(133, 131, 232, 20);
		panel.add(startDateChooser);
		
		startDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		        if (evt.getNewValue() != null) {
		            updatePrice();
		        }
		    }
		});
		
		endDateChooser = new JDateChooser();
		endDateChooser.setBounds(133, 176, 232, 20);
		panel.add(endDateChooser);
		
		endDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		        if (evt.getNewValue() != null) {
		            updatePrice();
		        }
		    }
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableCBooking = new JTable();
		scrollPane.setViewportView(tableCBooking);
		
		btnCreateBooking = new JButton("Créer");
		btnCreateBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createBooking();
			}
		});
		btnCreateBooking.setBounds(176, 348, 89, 34);
		add(btnCreateBooking);
		
		loadBookingData();
	}
	
	//Methods
	protected void loadBookingData() {
		List<Booking> bookings = booking.getAllBookings(bookingDAO, lessonDAO.getAllLessons(instructorDAO.getAllInstructors(accrDAO.getAllAccreditations()),
														lessonTypeDAO.getAllLessontypes(accrDAO.getAllAccreditations())), 
														studentDAO.getAllStudents(), periodDAO.getAllPeriods());
		
		updateTable(bookings);
	}
	
	private void updateTable(List<Booking> bookings) {
		
		String[] columnNames = {"N° réservation", "Date début", "Date fin", "Assuré", "Prix", "Cours", 
								"Skieur/euse","Instructeur/rice"};
        Object[][] data = new Object[bookingDAO.getBookings().size()][8];

        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            data[i][0] = booking.getBookId();
            data[i][1] = booking.getPeriod().getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
            data[i][2] = booking.getPeriod().getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
            if(booking.getInsurance()==false) {
            	data[i][3] = "Pas assuré";
            } else {
            	data[i][3] = "Assuré";
            }
            data[i][4] = booking.getPrice();
            data[i][5] = booking.getLesson().toString();
            data[i][6] = booking.getStudent().toString();
            data[i][7] = booking.getLesson().getInstructor().toString();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tableCBooking.setModel(model);
	}
	
	private void createBooking() {
		
		Student selectedStudent = (Student) cBox_info_Skier.getSelectedItem();
	    Lesson selectedLesson = (Lesson) cBox_info_Lesson.getSelectedItem();
	    LocalDate startDate = startDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate endDate = endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate minDate = LocalDate.of(2024, 12, 6);
	    LocalDate maxDate = LocalDate.of(2025, 5, 3);
	    if(startDate == null || endDate == null || startDate.isBefore(minDate) || 
	    		endDate.isAfter(maxDate) || endDate.isBefore(startDate)==true) {
	    	JOptionPane.showMessageDialog(this, "Veuillez sélectionner des dates valides ou cohérentes.", "Erreur", JOptionPane.ERROR_MESSAGE);
	    }    
	    boolean isInsured = rdbtnIsInsured.isSelected();
	    
	    if (selectedStudent == null || selectedLesson == null || startDate == null || endDate == null) {
	        JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    Period newPeriod = new Period();
	    newPeriod.setID(periodDAO.getNextID());
	    newPeriod.setStartDate(startDate);
	    newPeriod.setEndDate(endDate);
	    newPeriod.setIsVacation(isInsured);
	    
	    Booking newBooking = new Booking();
	    newBooking.setID(bookingDAO.getNextID());
	    newBooking.setPeriod(newPeriod);
	    newBooking.setStudent(selectedStudent);
	    newBooking.setLesson(selectedLesson);
	    newBooking.setInsurance(isInsured);
	    newBooking.setPrice(newBooking.calculatePrice());
		newBooking.insertToDB(bookingDAO, periodDAO);
		
		loadBookingData();
		resetFields();
		JOptionPane.showMessageDialog(this, "La réservation a été créée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void resetFields() {
	    cBox_info_Skier.setSelectedIndex(0);
	    cBox_info_Lesson.setSelectedIndex(0);
	    startDateChooser.setDate(null);
	    endDateChooser.setDate(null);
	    insuranceLesson.clearSelection();
	    rdbtnNotInsured.setSelected(true);
	    txtPrice.setText("0");
	}
	
	private void updatePrice() {
	    Lesson selectedLesson = (Lesson) cBox_info_Lesson.getSelectedItem();
	    boolean isInsured = rdbtnIsInsured.isSelected();
	    
	    if(startDateChooser.getDate()==null || endDateChooser.getDate()== null) {
	    	if (selectedLesson != null) {
		        double basePrice = selectedLesson.getLessonPrice();
		        double finalPrice = isInsured ? basePrice + 20 : basePrice;
		        txtPrice.setText(String.valueOf(finalPrice));
	    	}
	    } else {
		    int nbWeek = (int)ChronoUnit.WEEKS.between(startDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
		    										   , endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		    if(nbWeek<1) nbWeek=1;
		    if (selectedLesson != null) {
		        double basePrice = selectedLesson.getLessonPrice() * nbWeek;
		        double finalPrice = isInsured ? basePrice + 20 : basePrice;
		        txtPrice.setText(String.valueOf(finalPrice));
		    }
	    }
	}
}
