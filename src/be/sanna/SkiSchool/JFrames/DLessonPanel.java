package be.sanna.SkiSchool.JFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;

public class DLessonPanel extends JPanel {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTextField text_info_Lessontype_Price;
	private JTextField text_info_Lesson_min;
	private JTextField text_info_Lesson_max;
	private JTable tableDLesson;
	private JPanel SearchPanel;
	private JLabel lblSearchID;
	private JButton btnSearch;
	private JComboBox cBox_search_Accreditation_Name;
	private JPanel panel;
	private JLabel lbl_info_AccrediatationName;
	private JLabel lbl_info_LessonTypeLevel;
	private JLabel lbl_info_Lessontype_Price;
	private JLabel lbl_info_euroSign;
	private JComboBox cBox_info_Skier;
	private JComboBox cBox_info_LessonType;
	private JLabel lbl_info_Lesson_min;
	private JLabel lbl_info_Lesson_max;
	private JLabel lbl_info_instructor;
	private JComboBox cBox_info_InstructorName;
	private JScrollPane scrollPane;
	private JButton btnClear;
	private JButton btnUpdate;
	private JButton btnDelete;
	/*private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;*/

	//Constructor
	public DLessonPanel() {
		setLayout(null);
		
		SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SearchPanel.setBounds(26, 31, 400, 68);
		add(SearchPanel);
		SearchPanel.setLayout(null);
		
		lblSearchID = new JLabel("Catégorie :");
		lblSearchID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchID.setBounds(21, 29, 94, 17);
		SearchPanel.add(lblSearchID);

		btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(267, 28, 108, 23);
		SearchPanel.add(btnSearch);
		
		cBox_search_Accreditation_Name = new JComboBox();
		cBox_search_Accreditation_Name.setBounds(105, 28, 152, 22);
		SearchPanel.add(cBox_search_Accreditation_Name);
		
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
		
		lbl_info_Lessontype_Price = new JLabel("Prix/semaine :");
		lbl_info_Lessontype_Price.setBounds(21, 178, 96, 14);
		panel.add(lbl_info_Lessontype_Price);
		
		lbl_info_euroSign = new JLabel("€");
		lbl_info_euroSign.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_info_euroSign.setBounds(239, 177, 23, 14);
		panel.add(lbl_info_euroSign);
		
		cBox_info_Skier = new JComboBox();
		cBox_info_Skier.setBounds(133, 34, 232, 22);
		panel.add(cBox_info_Skier);
		
		cBox_info_LessonType = new JComboBox();
		cBox_info_LessonType.setBounds(133, 79, 232, 22);
		panel.add(cBox_info_LessonType);
		
		text_info_Lessontype_Price = new JTextField();
		text_info_Lessontype_Price.setBounds(133, 174, 96, 22);
		panel.add(text_info_Lessontype_Price);
		text_info_Lessontype_Price.setColumns(10);
		
		text_info_Lesson_min = new JTextField();
		text_info_Lesson_min.setColumns(10);
		text_info_Lesson_min.setBounds(238, 223, 50, 22);
		panel.add(text_info_Lesson_min);
		
		lbl_info_Lesson_min = new JLabel("Nombre de participants minimum:");
		lbl_info_Lesson_min.setBounds(21, 227, 208, 14);
		panel.add(lbl_info_Lesson_min);
		
		text_info_Lesson_max = new JTextField();
		text_info_Lesson_max.setColumns(10);
		text_info_Lesson_max.setBounds(238, 266, 50, 22);
		panel.add(text_info_Lesson_max);
		
		lbl_info_Lesson_max = new JLabel("Nombre de participants maximum :");
		lbl_info_Lesson_max.setBounds(21, 270, 208, 14);
		panel.add(lbl_info_Lesson_max);
		
		lbl_info_instructor = new JLabel("Instructeur :");
		lbl_info_instructor.setBounds(21, 133, 96, 14);
		panel.add(lbl_info_instructor);
		
		cBox_info_InstructorName = new JComboBox();
		cBox_info_InstructorName.setBounds(133, 129, 232, 22);
		panel.add(cBox_info_InstructorName);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableDLesson = new JTable();
		scrollPane.setViewportView(tableDLesson);
		
		btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(165, 480, 122, 41);
		add(btnClear);
		
		btnUpdate = new JButton("Mettre à jour");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(26, 480, 122, 41);
		add(btnUpdate);
		
		btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(304, 480, 122, 41);
		add(btnDelete);
	}
	
	//Methods
}
