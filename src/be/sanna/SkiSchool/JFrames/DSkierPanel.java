package be.sanna.SkiSchool.JFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import be.sanna.SkiSchool.DAO.StudentDAO;
import be.sanna.SkiSchool.POJO.Student;

import javax.swing.JTable;

public class DSkierPanel extends JPanel {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTextField text_search_StudentID;
	private JPanel SearchPanel;
	private JLabel lblSearchID;
	private JButton btnSearch;
	private JPanel panel;
	private JLabel lbl_info_Person_FN;
	private JLabel lbl_info_Person_LN;
	private JLabel lbl_info_Person_birthDate;
	private JDateChooser dateChooser_info_birthDate;
	private JScrollPane scrollPane;
	private JButton btnClear;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTextField text_info_FN;
	private JTextField text_info_LN;
	private JTable tableDSkier;
	private StudentDAO studentDAO;
	private Student student = new Student();

	//Constructor
	public DSkierPanel(StudentDAO studentDAO_) {
		this.studentDAO =studentDAO_;
		setLayout(null);
		
		SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SearchPanel.setBounds(26, 31, 400, 68);
		add(SearchPanel);
		SearchPanel.setLayout(null);
		
		lblSearchID = new JLabel("N° skieur :");
		lblSearchID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchID.setBounds(21, 29, 113, 17);
		SearchPanel.add(lblSearchID);

		btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(267, 28, 108, 23);
		SearchPanel.add(btnSearch);
		
		text_search_StudentID = new JTextField();
		text_search_StudentID.setBounds(111, 29, 146, 20);
		SearchPanel.add(text_search_StudentID);
		text_search_StudentID.setColumns(10);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 120, 400, 178);
		add(panel);
		panel.setLayout(null);
		
		lbl_info_Person_FN = new JLabel("Prénom :");
		lbl_info_Person_FN.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_Person_FN);
		
		lbl_info_Person_LN = new JLabel("Nom :");
		lbl_info_Person_LN.setBounds(21, 83, 67, 14);
		panel.add(lbl_info_Person_LN);
		
		lbl_info_Person_birthDate = new JLabel("Date de naissance:");
		lbl_info_Person_birthDate.setBounds(21, 129, 129, 14);
		panel.add(lbl_info_Person_birthDate);
		
		dateChooser_info_birthDate = new JDateChooser();
		dateChooser_info_birthDate.setBounds(160, 129, 184, 20);
		panel.add(dateChooser_info_birthDate);
		
		text_info_FN = new JTextField();
		text_info_FN.setBounds(98, 35, 184, 20);
		panel.add(text_info_FN);
		text_info_FN.setColumns(10);
		
		text_info_LN = new JTextField();
		text_info_LN.setColumns(10);
		text_info_LN.setBounds(98, 80, 184, 20);
		panel.add(text_info_LN);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableDSkier = new JTable();
		scrollPane.setViewportView(tableDSkier);
		
		btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setBounds(199, 309, 100, 41);
		add(btnClear);
		
		btnUpdate = new JButton("Mettre à jour le profil");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStudent();
				//loadStudentData();
			}
		});
		btnUpdate.setBounds(26, 309, 165, 41);
		add(btnUpdate);
		
		btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteStudent();
			}
		});
		btnDelete.setBounds(309, 309, 117, 41);
		add(btnDelete);
		
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
        tableDSkier.setModel(model);
    }
	
	public void search() {
		String searchID = text_search_StudentID.getText().trim();
		
		if (searchID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un ID de skieur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int studentId = Integer.parseInt(searchID);
            Student foundStudent = null;
            
            for (Student student : student.getAllStudents(studentDAO)) {
                if (student.getId() == studentId) {
                    foundStudent = student;
                    break;
                }
            }

            if (foundStudent != null) {
                text_info_FN.setText(foundStudent.getFirstName());
                text_info_LN.setText(foundStudent.getLastName());
                dateChooser_info_birthDate.setDate(java.sql.Date.valueOf(foundStudent.getDob()));
            } else {
                JOptionPane.showMessageDialog(null, "Aucun skieur trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void clear() {
        text_info_FN.setText("");
        text_info_LN.setText("");
        dateChooser_info_birthDate.setDate(null);
	}
	
	public void updateStudent() {
		String idText = text_search_StudentID.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un ID de skieur pour mettre à jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int studentId = Integer.parseInt(idText);
            String updatedFirstName = text_info_FN.getText().trim();
            String updatedLastName = text_info_LN.getText().trim();
            LocalDate updatedDob = null;

            if (dateChooser_info_birthDate.getDate() != null) {
                updatedDob = dateChooser_info_birthDate.getDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            }

            if (updatedFirstName.isEmpty() || updatedLastName.isEmpty() || updatedDob == null) {
                JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean found = false;
            for (Student student : studentDAO.getStudents()) {
                if (student.getId() == studentId) {
                	
                    student.setFirstName(updatedFirstName);
                    student.setLastName(updatedLastName);
                    student.setDob(updatedDob);
                    
                    student.updateToDB(studentDAO);

                    loadStudentData();
                    
                    JOptionPane.showMessageDialog(null, "Le profil a été mis à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Aucun skieur trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void deleteStudent() {
		String idText = text_search_StudentID.getText().trim();

	    if (idText.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Veuillez entrer un ID de skieur à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    try {
	        int studentId = Integer.parseInt(idText);

	        boolean found = false;
	        for (Student student : studentDAO.getStudents()) {
	            if (student.getId() == studentId) {
	            	
	                student.deleteToDB(studentDAO);
	                studentDAO.getStudents().remove(student);
	                
	                loadStudentData();
	                
	                JOptionPane.showMessageDialog(null, "Le skieur a été supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	                clear();
	                text_search_StudentID.setText("");
	                found = true;
	                break;
	            }
	        }

	        if (!found) {
	            JOptionPane.showMessageDialog(null, "Aucun skieur trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
	        }

	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
