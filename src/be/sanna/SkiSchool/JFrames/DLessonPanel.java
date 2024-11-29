package be.sanna.SkiSchool.JFrames;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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

import javax.swing.JTable;

public class DLessonPanel extends JPanel {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTable tableDLesson;
	private JPanel SearchPanel;
	private JLabel lblSearchID;
	private JButton btnSearch;
	private JPanel panel;
	private JLabel lbl_info_AccrediatationName;
	private JLabel lbl_info_LessonTypeLevel;
	private JLabel lbl_info_individual;
	private JComboBox cBox_info_Accreditations;
	private JComboBox cBox_info_LessonType;
	private JLabel lbl_info_instructor;
	private JComboBox cBox_info_InstructorName;
	private JLabel lbl_info_date;
	private JDateChooser lessonDateChooser;
	private ButtonGroup individualLesson;;
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
	private JScrollPane scrollPane;
	private JButton btnClear;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTextField text_SearchID;
	private LessonDAO lessonDAO;
	private InstructorDAO instructorDAO;
	private AccreditationDAO accrDAO;
	private LessonTypeDAO lessonTypeDAO;
	private Lesson lesson = new Lesson();
	private List<Accreditation> accrs = new ArrayList<>();
	private List<LessonType> lessonTypes = new ArrayList<>();
	private List<Instructor> instructors = new ArrayList<>();
	private LocalDate nLDate = null;
	private Lesson newLesson;
	private Level level_;

	//Constructor
	public DLessonPanel(LessonDAO lessonDAO_, InstructorDAO instructorDAO_, AccreditationDAO accrDAO_,
			LessonTypeDAO lessonTypeDAO_) {
		this.lessonDAO = lessonDAO_;
		this.instructorDAO = instructorDAO_;
		this.accrDAO = accrDAO_;
		this.lessonTypeDAO = lessonTypeDAO_;
		setLayout(null);
		
		SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SearchPanel.setBounds(26, 31, 400, 68);
		add(SearchPanel);
		SearchPanel.setLayout(null);
		
		lblSearchID = new JLabel("N° Cours :");
		lblSearchID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchID.setBounds(21, 29, 94, 17);
		SearchPanel.add(lblSearchID);

		btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(267, 28, 108, 23);
		SearchPanel.add(btnSearch);
		
		text_SearchID = new JTextField();
		text_SearchID.setBounds(125, 29, 108, 20);
		SearchPanel.add(text_SearchID);
		text_SearchID.setColumns(10);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 120, 400, 349);
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
		
		lbl_info_individual = new JLabel("Cours individuel :");
		lbl_info_individual.setBounds(21, 222, 96, 14);
		panel.add(lbl_info_individual);
		
		rdbtn_Collective = new JRadioButton("Non");
		rdbtn_Collective.setSelected(true);
		rdbtn_Collective.setBounds(255, 218, 96, 23);
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
		
		rdbtn_individual = new JRadioButton("Oui");
		rdbtn_individual.setBounds(167, 218, 67, 23);
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
		
		individualLesson = new ButtonGroup();
		individualLesson.add(rdbtn_individual);
		individualLesson.add(rdbtn_Collective);
		
		lbl_info_AM_PM = new JLabel("Moment de la journée :");
		lbl_info_AM_PM.setBounds(21, 263, 140, 14);
		panel.add(lbl_info_AM_PM);
		
		rdbtn_AM = new JRadioButton("Matin");
		rdbtn_AM.setSelected(true);
		rdbtn_AM.setBounds(167, 259, 67, 23);
		panel.add(rdbtn_AM);
		
		rdbtn_PM = new JRadioButton("Après-midi");
		rdbtn_PM.setBounds(255, 259, 96, 23);
		panel.add(rdbtn_PM);
		
		amORpmLesson = new ButtonGroup();
		amORpmLesson.add(rdbtn_AM);
		amORpmLesson.add(rdbtn_PM);
		
		lbl_info_Duration = new JLabel("Nombres d'heures :");
		lbl_info_Duration.setBounds(21, 307, 118, 14);
		panel.add(lbl_info_Duration);
		
		rdbtn_duration_2 = new JRadioButton("2H");
		rdbtn_duration_2.setEnabled(false);
		rdbtn_duration_2.setBounds(255, 307, 96, 23);
		panel.add(rdbtn_duration_2);
		
		rdbtn_duration_1 = new JRadioButton("1H");
		rdbtn_duration_1.setSelected(true);
		rdbtn_duration_1.setEnabled(false);
		rdbtn_duration_1.setBounds(167, 307, 67, 23);
		panel.add(rdbtn_duration_1);
		
		lessonDateChooser = new JDateChooser();
		lessonDateChooser.setBounds(133, 172, 118, 20);
		panel.add(lessonDateChooser);
		
		lbl_info_date = new JLabel("Date du cours :");
		lbl_info_date.setBounds(21, 172, 96, 14);
		panel.add(lbl_info_date);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableDLesson = new JTable();
		scrollPane.setViewportView(tableDLesson);
		
		btnUpdate = new JButton("Mettre à jour");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLesson();
			}
		});
		btnUpdate.setBounds(26, 480, 122, 41);
		add(btnUpdate);
		
		btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setBounds(165, 480, 122, 41);
		add(btnClear);
		
		btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteLesson();
			}
		});
		btnDelete.setBounds(304, 480, 122, 41);
		add(btnDelete);
		
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
        tableDLesson.setModel(model);
	}
	
	private void search() {
		String searchIdText = text_SearchID.getText().trim();
		        
        if (searchIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID de cours.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
        	int lessonID = Integer.parseInt(searchIdText);
        	Lesson foundLesson = null;
        	List<Lesson> lessons = lesson.getAllLessons(lessonDAO, instructorDAO.getAllInstructors(accrDAO.getAccreditations()),
        			lessonTypeDAO.getAllLessontypes(accrDAO.getAllAccreditations()));
        	for(Lesson lesson_ : lessons){
        		if(lesson_.getLessonId() == lessonID) {
        			foundLesson = lesson_;
        			break;
        		}
        	}
        	
        	if(foundLesson!=null) {
        		cBox_info_Accreditations.setSelectedItem(foundLesson.getType().getAccreditation());
        		cBox_info_LessonType.setSelectedItem(foundLesson.getType());
        		cBox_info_InstructorName.setSelectedItem(foundLesson.getInstructor());
        		lessonDateChooser.setDate(java.sql.Date.valueOf(foundLesson.getLessonDate()));
        		if(foundLesson.getIndividual()==false) {
        			rdbtn_Collective.setSelected(true);
        			if(foundLesson.getAMorPM()==false) {
        				rdbtn_AM.setSelected(true);
        			}else {
        				rdbtn_PM.setSelected(true);
        			}
        		} else {
        			rdbtn_individual.setSelected(true);
        			if(foundLesson.getDuration()==1) {
        				rdbtn_duration_1.setSelected(true);
        			} else {
        				rdbtn_duration_2.setSelected(true);
        			}
        		}
        	}else {
                JOptionPane.showMessageDialog(this, "Aucun cours trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
    		
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void updateLesson() {
		String searchID = text_SearchID.getText().trim();
	    if (searchID.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Veuillez rechercher une leçon à mettre à jour avant de continuer.", "Erreur", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    try {
	        int lessonID = Integer.parseInt(searchID);
	        boolean found = false;
	        List<Lesson> lessons = lesson.getAllLessons(lessonDAO, instructorDAO.getAllInstructors(accrDAO.getAccreditations()),
        			lessonTypeDAO.getAllLessontypes(accrDAO.getAllAccreditations()));
	        for(Lesson lesson_ : lessons) {
        		if(lesson_.getLessonId() == lessonID) {
        			lesson_.setInstructor((Instructor)cBox_info_InstructorName.getSelectedItem());
        			lesson_.setType((LessonType)cBox_info_LessonType.getSelectedItem());
        			lesson_.setLessonDate(lessonDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        			if(rdbtn_Collective.isSelected()) {
        				lesson_.setIndividual(false);
        				if(rdbtn_AM.isSelected()) {
        					lesson_.setAMorPM(false);
        				} else {
        					lesson_.setAMorPM(true);
        				}
        			} else {
        				lesson_.setIndividual(true);
        				if(rdbtn_duration_1.isSelected()) {
        					lesson_.setDuration(1);
        				}else {
        					lesson_.setDuration(2);
        				}
        			}
        			lesson_.updateToDB(lessonDAO);
        			
        			loadLessonData();
        			
        			JOptionPane.showMessageDialog(null, "Le cours a été mis à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
        			break;
        		}
        	}
	        
	        if (!found) {
                JOptionPane.showMessageDialog(null, "Aucun cours trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
	    } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void clear() {
	    cBox_info_Accreditations.setSelectedIndex(-1);
	    cBox_info_LessonType.setSelectedIndex(-1);
	    cBox_info_InstructorName.setSelectedIndex(-1);
	    lessonDateChooser.setCalendar(null);
	    rdbtn_Collective.setSelected(true);
	    rdbtn_AM.setEnabled(true);
	    rdbtn_PM.setEnabled(true);
	    rdbtn_AM.setSelected(true);
	    rdbtn_duration_1.setEnabled(false);
	    rdbtn_duration_2.setEnabled(false);
	    rdbtn_duration_1.setSelected(true);
	    tableDLesson.clearSelection();

	    JOptionPane.showMessageDialog(this, "Tous les champs ont été réinitialisés.", "Information", JOptionPane.INFORMATION_MESSAGE);

	}
	
	private void deleteLesson() {
		String searchIdText = text_SearchID.getText().trim();
        
        if (searchIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID de cours.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
        	int lessonID = Integer.parseInt(searchIdText);
        	boolean found = false;
        	List<Lesson> lessons = lesson.getAllLessons(lessonDAO, instructorDAO.getAllInstructors(accrDAO.getAccreditations()),
        			lessonTypeDAO.getAllLessontypes(accrDAO.getAllAccreditations()));
        	for(Lesson lesson_ : lessons) {
        		if(lesson_.getLessonId() == lessonID) {
        			lesson_.deleteToDB(lessonDAO);
        			lesson_.removeLesson(lessonDAO);
        			
        			loadLessonData();
        			
        			JOptionPane.showMessageDialog(null, "Le cours a été supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	                clear();
	                text_SearchID.setText("");
	                found = true;
	                break;
        		}
        	}
        	
        	if (!found) {
                JOptionPane.showMessageDialog(null, "Aucun cours trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
}
