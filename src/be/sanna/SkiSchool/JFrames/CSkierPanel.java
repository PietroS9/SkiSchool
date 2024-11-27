package be.sanna.SkiSchool.JFrames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JButton;
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

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTextField textFN;
	private JTextField textLN;
	private JTable tableCSkier;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private JPanel panel;
	private JLabel lbl_info_FN;
	private JLabel lbl_info_birthDate;
	private JLabel lbl_info_LN;
	private JDateChooser dobDateChooser;
	private JScrollPane scrollPane;
	private JButton btnCreateStudent;
	private StudentDAO studentDAO;
	private Student student = new Student();

	//Constructor
	public CSkierPanel(StudentDAO studentDAO_) {
		this.studentDAO = studentDAO_;
		setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 31, 400, 171);
		add(panel);
		panel.setLayout(null);
		
		lbl_info_FN = new JLabel("Prénom :");
		lbl_info_FN.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_FN);
		
		lbl_info_birthDate = new JLabel("Date de naissance :");
		lbl_info_birthDate.setBounds(21, 128, 124, 14);
		panel.add(lbl_info_birthDate);
		
		lbl_info_LN = new JLabel("Nom :");
		lbl_info_LN.setBounds(21, 85, 53, 14);
		panel.add(lbl_info_LN);
		
		dobDateChooser = new JDateChooser();
		dobDateChooser.setBounds(147, 128, 232, 20);
		panel.add(dobDateChooser);
		
		textFN = new JTextField();
		textFN.setBounds(98, 35, 170, 20);
		panel.add(textFN);
		textFN.setColumns(10);
		
		textLN = new JTextField();
		textLN.setColumns(10);
		textLN.setBounds(78, 82, 170, 20);
		panel.add(textLN);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableCSkier = new JTable();
		scrollPane.setViewportView(tableCSkier);
		
		btnCreateStudent = new JButton("Créer");
		btnCreateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createStudent();
		   }
		});
		btnCreateStudent.setBounds(173, 213, 89, 34);
		add(btnCreateStudent);
		
		loadStudentData();
	}
	
	//Methods
	protected void loadStudentData() {

        List<Student> students = student.getAllStudents(studentDAO);

        updateTable(students);
	}
	
	private void updateTable(List<Student> students) {
        String[] columnNames = {"N° skieur", "Prénom", "Nom", "Date de naissance"};
        Object[][] data = new Object[studentDAO.getStudents().size()][4];

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getId();
            data[i][1] = student.getFirstName();
            data[i][2] = student.getLastName();
            data[i][3] = student.getDob().format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tableCSkier.setModel(model);
    }
	
	private void createStudent() {
		
		firstName = textFN.getText().trim();
        lastName = textLN.getText().trim();

        if (dobDateChooser.getDate() != null) {
        	birthDate = dobDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        
        if (firstName.isEmpty() || lastName.isEmpty() || birthDate == null) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (firstName.length() > 20 || lastName.length() > 20) {
            JOptionPane.showMessageDialog(null, 
                "Le prénom et le nom ne doivent pas dépasser 20 caractères.", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Student newStudent = new Student();
        newStudent.setID(studentDAO.getNextID());
        newStudent.setFirstName(firstName);
        newStudent.setLastName(lastName);
        newStudent.setDob(birthDate);
        
        newStudent.insertToDB(studentDAO);

        loadStudentData();

        textFN.setText("");
        textLN.setText("");
        dobDateChooser.setDate(null);
        
        JOptionPane.showMessageDialog(this, "Le Skieur/Skieuse a été ajouté(e) avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	}
}
