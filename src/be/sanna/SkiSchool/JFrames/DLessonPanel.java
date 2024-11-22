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

public class DLessonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField text_info_Lessontype_Price;
	private JTextField text_info_Lesson_min;
	private JTextField text_info_Lesson_max;

	/**
	 * Create the panel.
	 */
	public DLessonPanel() {
		setLayout(null);
		
		JPanel SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SearchPanel.setBounds(26, 31, 400, 68);
		add(SearchPanel);
		SearchPanel.setLayout(null);
		
		JLabel lblSearchID = new JLabel("Catégorie :");
		lblSearchID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchID.setBounds(21, 29, 94, 17);
		SearchPanel.add(lblSearchID);

		JButton btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(267, 28, 108, 23);
		SearchPanel.add(btnSearch);
		
		JComboBox cBox_search_Accreditation_Name = new JComboBox();
		cBox_search_Accreditation_Name.setBounds(105, 28, 152, 22);
		SearchPanel.add(cBox_search_Accreditation_Name);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 120, 400, 352);
		add(panel);
		panel.setLayout(null);
		
		JLabel lbl_info_AccrediatationName = new JLabel("Catégorie :");
		lbl_info_AccrediatationName.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_AccrediatationName);
		
		JLabel lbl_info_LessonTypeLevel = new JLabel("Niveau :");
		lbl_info_LessonTypeLevel.setBounds(21, 83, 67, 14);
		panel.add(lbl_info_LessonTypeLevel);
		
		JLabel lbl_info_Lessontype_Price = new JLabel("Prix/semaine :");
		lbl_info_Lessontype_Price.setBounds(21, 129, 96, 14);
		panel.add(lbl_info_Lessontype_Price);
		
		JLabel lbl_info_euroSign = new JLabel("€");
		lbl_info_euroSign.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_info_euroSign.setBounds(239, 128, 23, 14);
		panel.add(lbl_info_euroSign);
		
		JComboBox cBox_info_Skier = new JComboBox();
		cBox_info_Skier.setBounds(133, 34, 232, 22);
		panel.add(cBox_info_Skier);
		
		JComboBox cBox_info_LessonType = new JComboBox();
		cBox_info_LessonType.setBounds(133, 79, 232, 22);
		panel.add(cBox_info_LessonType);
		
		text_info_Lessontype_Price = new JTextField();
		text_info_Lessontype_Price.setBounds(133, 125, 96, 22);
		panel.add(text_info_Lessontype_Price);
		text_info_Lessontype_Price.setColumns(10);
		
		text_info_Lesson_min = new JTextField();
		text_info_Lesson_min.setColumns(10);
		text_info_Lesson_min.setBounds(212, 174, 50, 22);
		panel.add(text_info_Lesson_min);
		
		JLabel lbl_info_Lesson_min = new JLabel("Nombre de participants minimum:");
		lbl_info_Lesson_min.setBounds(21, 178, 175, 14);
		panel.add(lbl_info_Lesson_min);
		
		text_info_Lesson_max = new JTextField();
		text_info_Lesson_max.setColumns(10);
		text_info_Lesson_max.setBounds(212, 217, 50, 22);
		panel.add(text_info_Lesson_max);
		
		JLabel lbl_info_Lesson_max = new JLabel("Nombre de participants maximum :");
		lbl_info_Lesson_max.setBounds(21, 221, 175, 14);
		panel.add(lbl_info_Lesson_max);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		JButton btnUpdate = new JButton("Mettre à jour");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(156, 480, 122, 41);
		add(btnUpdate);
	}
}
