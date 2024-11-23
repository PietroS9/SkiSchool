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

	private static final long serialVersionUID = 1L;
	private JTable tableCLesson;

	/**
	 * Create the panel.
	 */
	public CLessonPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 31, 400, 175);
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);
		
		JLabel lbl_info_AccrediatationName = new JLabel("Catégorie :");
		lbl_info_AccrediatationName.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_AccrediatationName);
		
		JLabel lbl_info_LessonTypeLevel = new JLabel("Niveau :");
		lbl_info_LessonTypeLevel.setBounds(21, 83, 67, 14);
		panel.add(lbl_info_LessonTypeLevel);
		
		JComboBox cBox_info_Skier = new JComboBox();
		cBox_info_Skier.setBounds(133, 34, 232, 22);
		panel.add(cBox_info_Skier);
		
		JComboBox cBox_info_LessonType = new JComboBox();
		cBox_info_LessonType.setBounds(133, 79, 232, 22);
		panel.add(cBox_info_LessonType);
		
		JLabel lbl_info_instructor = new JLabel("Instructeur :");
		lbl_info_instructor.setBounds(21, 133, 96, 14);
		panel.add(lbl_info_instructor);
		
		JComboBox cBox_info_InstructorName = new JComboBox();
		cBox_info_InstructorName.setBounds(133, 129, 232, 22);
		panel.add(cBox_info_InstructorName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableCLesson = new JTable();
		scrollPane.setColumnHeaderView(tableCLesson);
		
		JButton btnCreate = new JButton("Créer");
		btnCreate.setBounds(170, 217, 104, 41);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnCreate);
	}
}
