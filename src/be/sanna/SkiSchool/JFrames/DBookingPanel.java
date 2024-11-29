package be.sanna.SkiSchool.JFrames;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
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
import be.sanna.SkiSchool.POJO.Student;

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
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class DBookingPanel extends JPanel {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTextField textSearchID;
	private JTable tableDBooking;
	private JPanel SearchPanel;
	private JLabel lblSearchID;
	private JButton btnSearch;
	private JPanel panel;
	private JLabel lbl_info_Skier;
	private JLabel lbl_info_LessonTypeLevel;
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
	private JButton btnClear;
	private JButton btnUpdate;
	private JButton btnDelete;
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
	public DBookingPanel(AccreditationDAO accrDAO_, BookingDAO bookingDAO_, InstructorDAO instDAO_, LessonDAO lessonDAO_,
					 	 LessonTypeDAO lessonTypeDAO_, PeriodDAO periodDAO_, StudentDAO studentDAO_) {
		this.accrDAO  = accrDAO_;
		this.bookingDAO = bookingDAO_;
		this.instructorDAO = instDAO_;
		this.lessonDAO = lessonDAO_;
		this.lessonTypeDAO = lessonTypeDAO_;
		this.periodDAO = periodDAO_;
		this.studentDAO = studentDAO_;
		setLayout(null);
		
		SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SearchPanel.setBounds(26, 31, 400, 68);
		add(SearchPanel);
		SearchPanel.setLayout(null);
		
		lblSearchID = new JLabel("N° réservation :");
		lblSearchID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchID.setBounds(21, 29, 114, 17);
		SearchPanel.add(lblSearchID);
		
		textSearchID = new JTextField();
		textSearchID.setBounds(145, 29, 108, 20);
		SearchPanel.add(textSearchID);
		textSearchID.setColumns(10);
		
		btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(267, 28, 108, 23);
		SearchPanel.add(btnSearch);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 120, 400, 292);
		add(panel);
		panel.setLayout(null);
		
		lbl_info_Skier = new JLabel("Skier :");
		lbl_info_Skier.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_Skier);
		
		lbl_info_LessonTypeLevel = new JLabel("Cours :");
		lbl_info_LessonTypeLevel.setBounds(21, 83, 67, 14);
		panel.add(lbl_info_LessonTypeLevel);
		
		lbl_info_Period_startDate = new JLabel("Date de début :");
		lbl_info_Period_startDate.setBounds(21, 128, 85, 14);
		panel.add(lbl_info_Period_startDate);
		
		lbl_info_Period_endDate = new JLabel("Date de fin :");
		lbl_info_Period_endDate.setBounds(21, 173, 85, 14);
		panel.add(lbl_info_Period_endDate);
		
		lbl_info_Booking_insurance = new JLabel("Assurance :");
		lbl_info_Booking_insurance.setBounds(21, 217, 104, 14);
		panel.add(lbl_info_Booking_insurance);
		
		rdbtnIsInsured = new JRadioButton("Oui");
		rdbtnIsInsured.setBounds(138, 213, 55, 23);
		panel.add(rdbtnIsInsured);
		
		rdbtnIsInsured.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        updatePrice();
		    }
		});
		
		rdbtnNotInsured = new JRadioButton("Non");
		rdbtnNotInsured.setSelected(true);
		rdbtnNotInsured.setBounds(195, 213, 55, 23);
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
		lbl_info_Booking_price.setBounds(18, 257, 46, 14);
		panel.add(lbl_info_Booking_price);
		
		lbl_info_euroSign = new JLabel("€");
		lbl_info_euroSign.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_info_euroSign.setBounds(170, 257, 23, 14);
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
		cBox_info_Lesson.setBounds(133, 79, 232, 22);
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
		startDateChooser.setBounds(133, 128, 232, 20);
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
		endDateChooser.setBounds(133, 173, 232, 20);
		panel.add(endDateChooser);
		
		endDateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		        if (evt.getNewValue() != null) {
		            updatePrice();
		        }
		    }
		});
		
		txtPrice = new JTextField();
		txtPrice.setText("0");
		txtPrice.setEditable(false);
		txtPrice.setBounds(80, 254, 59, 20);
		panel.add(txtPrice);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableDBooking = new JTable();
		scrollPane.setViewportView(tableDBooking);
		
		btnUpdate = new JButton("Mettre à jour");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateToDB();
			}
		});
		btnUpdate.setBounds(26, 423, 122, 41);
		add(btnUpdate);
		
		btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setBounds(165, 423, 122, 41);
		add(btnClear);
		
		btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteToDB();
			}
		});
		btnDelete.setBounds(304, 423, 122, 41);
		add(btnDelete);

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
            data[i][7] = booking.getInstructor().toString();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tableDBooking.setModel(model);
	}
	
	private void search() {
		String searchID = textSearchID.getText().trim();
		
		if(searchID.isEmpty()){
			JOptionPane.showMessageDialog(this, "Veuillez entrer un ID de réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
		}
		
		try {
			int bookingID = Integer.parseInt(searchID);
			Booking foundBooking = null;
			List<Booking> bookings = booking.getAllBookings(bookingDAO, lessonDAO.getAllLessons(instructorDAO.getAllInstructors(accrDAO.getAllAccreditations()),
					lessonTypeDAO.getAllLessontypes(accrDAO.getAllAccreditations())), 
					studentDAO.getAllStudents(), periodDAO.getAllPeriods());
			
			for(Booking b : bookings) {
				if(b.getBookId()==bookingID) {
					foundBooking = b;
					break;
				}
			}
			
			if(foundBooking != null) {
				cBox_info_Skier.setSelectedItem(foundBooking.getStudent());
				cBox_info_Lesson.setSelectedItem(foundBooking.getLesson());
				startDateChooser.setDate(java.sql.Date.valueOf(foundBooking.getPeriod().getStartDate()));
				endDateChooser.setDate(java.sql.Date.valueOf(foundBooking.getPeriod().getEndDate()));
				if(foundBooking.getInsurance()==true) {
					rdbtnIsInsured.setSelected(true);
				} else {
					rdbtnNotInsured.setSelected(true);
				}
				updatePrice();
			} else {
				JOptionPane.showMessageDialog(this, "Aucune réservation trouvée avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void updateToDB() {
		String searchID = textSearchID.getText().trim();
		
		if(searchID.isEmpty()){
			JOptionPane.showMessageDialog(this, "Veuillez entrer un ID de réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
		}
		
		try {
			int bookingID = Integer.parseInt(searchID);
			Boolean found = null;
			List<Booking> bookings = booking.getAllBookings(bookingDAO, lessonDAO.getAllLessons(instructorDAO.getAllInstructors(accrDAO.getAllAccreditations()),
					lessonTypeDAO.getAllLessontypes(accrDAO.getAllAccreditations())), 
					studentDAO.getAllStudents(), periodDAO.getAllPeriods());
			Student selectedStudent = (Student) cBox_info_Skier.getSelectedItem();
		    Lesson selectedLesson = (Lesson) cBox_info_Lesson.getSelectedItem();
			LocalDate startDate = startDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    LocalDate endDate = endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    if(startDate == null || endDate == null || endDate.isBefore(startDate)==true) {
		    	JOptionPane.showMessageDialog(this, "Veuillez sélectionner des dates valides ou cohérentes.", "Erreur", JOptionPane.ERROR_MESSAGE);
		    }    
		    boolean isInsured = rdbtnIsInsured.isSelected();
		    
		    if (selectedStudent == null || selectedLesson == null || startDate == null || endDate == null) {
		        JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
			for(Booking b : bookings) {
				if(b.getBookId()==bookingID) {
					b.setStudent(selectedStudent);
					b.setLesson(selectedLesson);
					b.getPeriod().setStartDate(startDate);
					b.getPeriod().setEndDate(endDate);
					b.setInsurance(isInsured);
					b.setPrice(b.calculatePrice());
					b.updateToDB(bookingDAO, periodDAO);
					
					loadBookingData();
					JOptionPane.showMessageDialog(null, "La réservation a été mise à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
					break;
				}
			}
			if (!found) {
                JOptionPane.showMessageDialog(null, "Aucune réservation trouvée avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
		} catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void clear() {
	    cBox_info_Skier.setSelectedIndex(-1);
	    cBox_info_Lesson.setSelectedIndex(-1);
	    startDateChooser.setDate(null);
	    endDateChooser.setDate(null);
	    rdbtnNotInsured.setSelected(true);
	    txtPrice.setText("0");
	}
	
	private void deleteToDB() {
		String searchID = textSearchID.getText().trim();
		
		if(searchID.isEmpty()){
			JOptionPane.showMessageDialog(this, "Veuillez entrer un ID de réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
		}
		
		try {
			int bookingID = Integer.parseInt(searchID);
			Boolean found = null;
			List<Booking> bookings = booking.getAllBookings(bookingDAO, lessonDAO.getAllLessons(instructorDAO.getAllInstructors(accrDAO.getAllAccreditations()),
					lessonTypeDAO.getAllLessontypes(accrDAO.getAllAccreditations())), 
					studentDAO.getAllStudents(), periodDAO.getAllPeriods());
			for(Booking b : bookings) {
				if(b.getBookId()==bookingID) {
					b.deleteToDB(bookingDAO);
					b.removeBooking(bookingDAO);
					
					loadBookingData();
					JOptionPane.showMessageDialog(null, "La réservation a été supprimée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    clear();
                    textSearchID.setText("");
					found = true;
					break;
				}
			}
			if (!found) {
                JOptionPane.showMessageDialog(null, "Aucune réservation trouvée avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
		} catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
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
