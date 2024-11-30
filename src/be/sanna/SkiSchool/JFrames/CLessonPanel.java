package be.sanna.SkiSchool.JFrames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

import be.sanna.SkiSchool.DAO.AccreditationDAO;
import be.sanna.SkiSchool.DAO.InstructorDAO;
import be.sanna.SkiSchool.DAO.LessonDAO;
import be.sanna.SkiSchool.DAO.LessonTypeDAO;
import be.sanna.SkiSchool.POJO.Accreditation;
import be.sanna.SkiSchool.POJO.Instructor;
import be.sanna.SkiSchool.POJO.Lesson;
import be.sanna.SkiSchool.POJO.LessonType;
import be.sanna.SkiSchool.POJO.Level;

import javax.swing.JRadioButton;

public class CLessonPanel extends JPanel {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTable tableCLesson;
	private JPanel panel;
	private JLabel lbl_info_AccrediatationName;
	private JLabel lbl_info_LessonTypeLevel;
	private JComboBox cBox_info_Accreditations;
	private JComboBox cBox_info_LessonType;
	private JLabel lbl_info_instructor;
	private JComboBox cBox_info_InstructorName;
	private JScrollPane scrollPane;
	private JButton btnCreate;
	private JLabel lbl_info_date;
	private JDateChooser lessonDateChooser;
	private ButtonGroup individualLesson;;
	private JLabel lbl_info_individual;
	private JRadioButton rdbtn_individual;
	private JRadioButton rdbtn_Collective;
	private ButtonGroup amORpmLesson;
	private JLabel lbl_info_AM_PM;
	private JRadioButton rdbtn_AM;
	private JRadioButton rdbtn_PM;
	private ButtonGroup durationLesson;
	private JLabel lbl_info_Duration;
	private JRadioButton rdbtn_duration_2;
	private JRadioButton rdbtn_duration_1;
	private Lesson lesson = new Lesson();
	private LessonDAO lessonDAO;
	private InstructorDAO instructorDAO;
	private AccreditationDAO accrDAO;
	private LessonTypeDAO lessonTypeDAO;
	private List<Accreditation> accrs = new ArrayList<>();
	private List<LessonType> lessonTypes = new ArrayList<>();
	private List<Instructor> instructors = new ArrayList<>();
	private LocalDate nLDate = null;
	private Lesson newLesson;
	private Level level_;
	
	//Constructor
	public CLessonPanel(LessonDAO lessonDAO_, InstructorDAO instructorDAO_, AccreditationDAO accrDAO_,
						LessonTypeDAO lessonTypeDAO_) {
		this.lessonDAO = lessonDAO_;
		this.instructorDAO = instructorDAO_;
		this.accrDAO = accrDAO_;
		this.lessonTypeDAO = lessonTypeDAO_;
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(26, 31, 400, 379);
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);
		
		lbl_info_AccrediatationName = new JLabel("Catégorie :");
		lbl_info_AccrediatationName.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_AccrediatationName);
		
		lbl_info_LessonTypeLevel = new JLabel("Niveau :");
		lbl_info_LessonTypeLevel.setBounds(21, 83, 67, 14);
		panel.add(lbl_info_LessonTypeLevel);
		
		cBox_info_Accreditations = new JComboBox();
		cBox_info_Accreditations.setBounds(133, 34, 232, 22);
		panel.add(cBox_info_Accreditations);
		accrs = accrDAO.getAllAccreditations();
		
		for(Accreditation accr : accrs) {
			cBox_info_Accreditations.addItem(accr);
		}
		cBox_info_Accreditations.setSelectedIndex(-1);
		
		cBox_info_Accreditations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
		        Accreditation selectedAccreditation = (Accreditation) cBox_info_Accreditations.getSelectedItem();
		        
		        if (selectedAccreditation != null) {
		        	
		            lessonTypes = lessonTypeDAO.getLessonTypesByAccreditation(selectedAccreditation);
		            cBox_info_LessonType.removeAllItems();
		            
		            instructors = instructorDAO.getInstructorsByAccreditation(selectedAccreditation);
		            cBox_info_InstructorName.removeAllItems();
		            
		            for (LessonType lt : lessonTypes) {
		            	
		                cBox_info_LessonType.addItem(lt);
		            }
		            
		            for(Instructor inst : instructors) {
		            	cBox_info_InstructorName.addItem(inst);
		            }
		        }
		    }
		});
		
		cBox_info_LessonType = new JComboBox();
		cBox_info_LessonType.setBounds(133, 79, 232, 22);
		panel.add(cBox_info_LessonType);
		
		lbl_info_instructor = new JLabel("Instructeur :");
		lbl_info_instructor.setBounds(21, 133, 96, 14);
		panel.add(lbl_info_instructor);
		
		cBox_info_InstructorName = new JComboBox();
		cBox_info_InstructorName.setBounds(133, 129, 232, 22);
		panel.add(cBox_info_InstructorName);
		
		lbl_info_date = new JLabel("Date du cours :");
		lbl_info_date.setBounds(21, 179, 96, 14);
		panel.add(lbl_info_date);
		
		lessonDateChooser = new JDateChooser();
		lessonDateChooser.setBounds(133, 179, 118, 20);
		panel.add(lessonDateChooser);
		
		lbl_info_individual = new JLabel("Cours individuel :");
		lbl_info_individual.setBounds(21, 230, 118, 14);
		panel.add(lbl_info_individual);
		
		rdbtn_individual = new JRadioButton("Oui");
		rdbtn_individual.setBounds(167, 226, 67, 23);
		panel.add(rdbtn_individual);
		
		rdbtn_individual.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (rdbtn_individual.isSelected()) {
		            rdbtn_AM.setEnabled(false);
		            rdbtn_PM.setEnabled(false);
		            rdbtn_duration_1.setEnabled(true);
		            rdbtn_duration_2.setEnabled(true);
		        }
		    }
		});
		
		rdbtn_Collective = new JRadioButton("Non");
		rdbtn_Collective.setBounds(255, 226, 96, 23);
		rdbtn_Collective.setSelected(true);
		panel.add(rdbtn_Collective);
		
		rdbtn_Collective.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (rdbtn_Collective.isSelected()) {
		        	rdbtn_AM.setEnabled(true);
		            rdbtn_PM.setEnabled(true);
		            rdbtn_duration_1.setEnabled(false);
		            rdbtn_duration_2.setEnabled(false);
		        }
		    }
		});
		
		individualLesson = new ButtonGroup();
		individualLesson.add(rdbtn_individual);
		individualLesson.add(rdbtn_Collective);
		
		lbl_info_AM_PM = new JLabel("Moment de la journée :");
		lbl_info_AM_PM.setBounds(21, 282, 140, 14);
		panel.add(lbl_info_AM_PM);
		
		rdbtn_AM = new JRadioButton("Matin");
		rdbtn_AM.setSelected(true);
		rdbtn_AM.setBounds(167, 278, 67, 23);
		panel.add(rdbtn_AM);
		
		rdbtn_PM = new JRadioButton("Après-midi");
		rdbtn_PM.setBounds(255, 278, 96, 23);
		panel.add(rdbtn_PM);
		
		amORpmLesson = new ButtonGroup();
		amORpmLesson.add(rdbtn_AM);
		amORpmLesson.add(rdbtn_PM);
		
		lbl_info_Duration = new JLabel("Nombres d'heures :");
		lbl_info_Duration.setBounds(21, 335, 118, 14);
		panel.add(lbl_info_Duration);
		
		rdbtn_duration_2 = new JRadioButton("2H");
		rdbtn_duration_2.setBounds(255, 335, 96, 23);
		panel.add(rdbtn_duration_2);
		rdbtn_duration_2.setEnabled(false);
		
		rdbtn_duration_1 = new JRadioButton("1H");
		rdbtn_duration_1.setSelected(true);
		rdbtn_duration_1.setBounds(167, 335, 67, 23);
		panel.add(rdbtn_duration_1);
		rdbtn_duration_1.setEnabled(false);
		
		durationLesson = new ButtonGroup();
		durationLesson.add(rdbtn_duration_1);
		durationLesson.add(rdbtn_duration_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableCLesson = new JTable();
		scrollPane.setViewportView(tableCLesson);
		
		btnCreate = new JButton("Créer");
		btnCreate.setBounds(165, 421, 104, 41);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createLesson();
			}
		});
		add(btnCreate);
		
		loadLessonData();
	}
	
	//Methods
	protected void loadLessonData() {
		List<Lesson> lessons = lesson.getAllLessons(lessonDAO, 
				instructorDAO.getAllInstructors(accrDAO.getAccreditations()), lessonTypeDAO.getAllLessontypes(accrDAO.getAllAccreditations()));
		
		updateTable(lessons);
	}
	
	private void updateTable(List<Lesson> lessons) {
		
		String[] columnNames = {"N° de cours", "Intitulé", "Instructeur/rice", "Date", "Prix", "Individuel", 
								"Moment de la journée","Durée (heures)", "Min participants", "Max participants"};
        Object[][] data = new Object[lessonDAO.getLessons().size()][10];

        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            data[i][0] = lesson.getLessonId();
            data[i][1] = lesson.getType().getLevel();
            data[i][2] = lesson.getInstructor().getFirstName().toString() + " - " +
            			 lesson.getInstructor().getLastName().toString();
            data[i][3] = lesson.getLessonDate().format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
            data[i][4] = lesson.getLessonPrice();
            if(lesson.getIndividual()==false) {
            	data[i][5] = "Collectif";
            } else {
            	data[i][5] = "Individuel";
            }
            if(lesson.getAMorPM()==false){//false = morning
            	data[i][6] = "Matin";
            }else {
            	data[i][6] = "Aprem";
            }
            data[i][7] = lesson.getDuration();
            data[i][8] = lesson.getMinStudents();
            data[i][9] = lesson.getMaxStudents();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tableCLesson.setModel(model);
	}
	
	private void createLesson() {
		
		LocalDate selectedDate = lessonDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    if (selectedDate == null) {
	        JOptionPane.showMessageDialog(this, "Veuillez sélectionner une date valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    LocalDate minDate = LocalDate.of(2024, 12, 6);
	    LocalDate maxDate = LocalDate.of(2025, 5, 3);

	    if (selectedDate.isBefore(minDate) || selectedDate.isAfter(maxDate)) {
	        JOptionPane.showMessageDialog(this, 
	            "La date doit être comprise entre le 06/12/2024 et le 03/05/2025.", 
	            "Erreur", JOptionPane.ERROR_MESSAGE);
	        return;
	    } else {
	    	int minStd = 5;
	    	int maxStd = 8;
	    	if(( ((Accreditation) cBox_info_Accreditations.getSelectedItem()).getAccrId()==3 ||
	    	   ((Accreditation) cBox_info_Accreditations.getSelectedItem()).getAccrId()==4 ) && 
	    	   ( ((LessonType)cBox_info_LessonType.getSelectedItem()).getLevel() != level_.COMPETITION &&
	    	   ((LessonType)cBox_info_LessonType.getSelectedItem()).getLevel() != level_.HORS_PISTE)) {
	    		minStd = 6;
	    		maxStd = 10;
	    	}
			if(rdbtn_Collective.isSelected()) {
				if(rdbtn_AM.isSelected()) {
					newLesson = new Lesson(lessonDAO.getNextID(),false,selectedDate,false,1,minStd,maxStd,
							(LessonType)cBox_info_LessonType.getSelectedItem(),(Instructor)cBox_info_InstructorName.getSelectedItem());
				} else {
					newLesson = new Lesson(lessonDAO.getNextID(),false,selectedDate,true,1,minStd,maxStd,
							(LessonType)cBox_info_LessonType.getSelectedItem(),(Instructor)cBox_info_InstructorName.getSelectedItem());
				}
			} else {
				if(rdbtn_duration_1.isSelected()) {
					newLesson = new Lesson(lessonDAO.getNextID(),true,selectedDate,false,1,minStd,maxStd,
							(LessonType)cBox_info_LessonType.getSelectedItem(),(Instructor)cBox_info_InstructorName.getSelectedItem());
				}else {
					newLesson = new Lesson(lessonDAO.getNextID(),true,selectedDate,false,2,minStd,maxStd,
							(LessonType)cBox_info_LessonType.getSelectedItem(),(Instructor)cBox_info_InstructorName.getSelectedItem());
				}
			}
	    }
	    newLesson.insertToDB(lessonDAO);
	    
	    loadLessonData();
	    
	    resetFields();
	    
	}
	
	private void resetFields() {
	    cBox_info_Accreditations.setSelectedIndex(-1);
	    cBox_info_LessonType.removeAllItems();
	    cBox_info_InstructorName.removeAllItems();
	    lessonDateChooser.setDate(null);
	    rdbtn_Collective.setSelected(true);
	    rdbtn_AM.setEnabled(true);
	    rdbtn_PM.setEnabled(true);
	    rdbtn_duration_1.setEnabled(false);
	    rdbtn_duration_2.setEnabled(false);
	    rdbtn_AM.setSelected(true);
	    rdbtn_duration_1.setSelected(true);
	    
	    JOptionPane.showMessageDialog(this, "Le cours à été créé avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
