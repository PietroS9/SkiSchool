package be.sanna.SkiSchool.JFrames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import be.sanna.SkiSchool.DAO.StudentDAO;
import be.sanna.SkiSchool.POJO.Student;

import javax.swing.JTable;

public class CSkierPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFN;
	private JTextField textLN;
	private JTable tableCSkier;

	/**
	 * Create the panel.
	 */
	public CSkierPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 31, 400, 171);
		add(panel);
		panel.setLayout(null);
		
		JLabel lbl_info_FN = new JLabel("Prénom :");
		lbl_info_FN.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_FN);
		
		JLabel lbl_info_birthDate = new JLabel("Date de naissance :");
		lbl_info_birthDate.setBounds(21, 128, 124, 14);
		panel.add(lbl_info_birthDate);
		
		JLabel lbl_info_LN = new JLabel("Nom :");
		lbl_info_LN.setBounds(21, 85, 53, 14);
		panel.add(lbl_info_LN);
		
		JDateChooser startDateChooser = new JDateChooser();
		startDateChooser.setBounds(147, 128, 232, 20);
		panel.add(startDateChooser);
		
		textFN = new JTextField();
		textFN.setBounds(98, 35, 170, 20);
		panel.add(textFN);
		textFN.setColumns(10);
		
		textLN = new JTextField();
		textLN.setColumns(10);
		textLN.setBounds(78, 82, 170, 20);
		panel.add(textLN);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		JButton btnCreateBooking = new JButton("Créer");
		btnCreateBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateBooking.setBounds(173, 213, 89, 34);
		add(btnCreateBooking);
		
		loadStudentData();
	}
	
	private void loadStudentData() {
	    // Instancier le DAO
	    StudentDAO studentDAO = new StudentDAO();

	    // Récupérer les skieurs depuis la base de données
	    List<Student> students = studentDAO.getAllStudents();

	    // Convertir les données pour le tableau
	    String[] columnNames = {"N° skieur", "Prénom", "Nom", "Date de naissance"};
	    Object[][] data = new Object[students.size()][4];

	    for (int i = 0; i < students.size(); i++) {
	        Student student = students.get(i);
	        data[i][0] = student.getId();
	        data[i][1] = student.getFirstName();
	        data[i][2] = student.getLastName();
	        data[i][3] = student.getDob();
	    }

	    // Mettre à jour le modèle de la JTable
	    DefaultTableModel model = new DefaultTableModel(data, columnNames);
	    tableCSkier.setModel(model);
	}
}
