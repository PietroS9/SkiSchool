package be.sanna.SkiSchool.JFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;

public class CLessonPanel extends JPanel {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTable tableCLesson;
	private JPanel panel;
	private JLabel lbl_info_AccrediatationName;
	private JLabel lbl_info_LessonTypeLevel;
	private JComboBox cBox_info_Skier;
	private JComboBox cBox_info_LessonType;
	private JLabel lbl_info_instructor;
	private JComboBox cBox_info_InstructorName;
	private JScrollPane scrollPane;
	private JButton btnCreate;
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
	private ;*/
	
	//Constructor
	public CLessonPanel() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(26, 31, 400, 175);
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);
		
		lbl_info_AccrediatationName = new JLabel("Catégorie :");
		lbl_info_AccrediatationName.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_AccrediatationName);
		
		lbl_info_LessonTypeLevel = new JLabel("Niveau :");
		lbl_info_LessonTypeLevel.setBounds(21, 83, 67, 14);
		panel.add(lbl_info_LessonTypeLevel);
		
		cBox_info_Skier = new JComboBox();
		cBox_info_Skier.setBounds(133, 34, 232, 22);
		panel.add(cBox_info_Skier);
		
		cBox_info_LessonType = new JComboBox();
		cBox_info_LessonType.setBounds(133, 79, 232, 22);
		panel.add(cBox_info_LessonType);
		
		lbl_info_instructor = new JLabel("Instructeur :");
		lbl_info_instructor.setBounds(21, 133, 96, 14);
		panel.add(lbl_info_instructor);
		
		cBox_info_InstructorName = new JComboBox();
		cBox_info_InstructorName.setBounds(133, 129, 232, 22);
		panel.add(cBox_info_InstructorName);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableCLesson = new JTable();
		scrollPane.setViewportView(tableCLesson);
		
		btnCreate = new JButton("Créer");
		btnCreate.setBounds(170, 217, 104, 41);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnCreate);
	}
	
	//Methods
}
