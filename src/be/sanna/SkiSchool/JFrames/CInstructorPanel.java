package be.sanna.SkiSchool.JFrames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import be.sanna.SkiSchool.DAO.AccreditationDAO;
import be.sanna.SkiSchool.DAO.InstructorDAO;
import be.sanna.SkiSchool.POJO.Accreditation;
import be.sanna.SkiSchool.POJO.Instructor;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class CInstructorPanel extends JPanel {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTextField textFN;
	private JTextField textLN;
	private JPanel panel;
	private JLabel lbl_info_FN;
	private JLabel lbl_info_birthDate;
	private JLabel lbl_info_accreditations;
	private JLabel lbl_info_LN;
	private JDateChooser dobDateChooser;
	private JCheckBox chckbxAccr1;
	private JCheckBox chckbxAccr2;
	private JCheckBox chckbxAccr3;
	private JCheckBox chckbxAccr4;
	private JCheckBox chckbxAccr5;
	private JCheckBox chckbxAccr6;
	private JScrollPane scrollPane;
	private JButton btnCreateBooking;
	private JTable tableCInstructor;
	private Instructor instructor = new Instructor();
	private InstructorDAO instructorDAO;
	private AccreditationDAO accrDAO;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private List<Accreditation> accreditations = new ArrayList<>();

	//Constructor
	public CInstructorPanel(InstructorDAO instructorDAO_, AccreditationDAO accrDAO_) {
		this.instructorDAO = instructorDAO_;
		this.accrDAO = accrDAO_;
		setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 31, 400, 264);
		add(panel);
		panel.setLayout(null);
		
		lbl_info_FN = new JLabel("Prénom :");
		lbl_info_FN.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_FN);
		
		lbl_info_birthDate = new JLabel("Date de naissance :");
		lbl_info_birthDate.setBounds(21, 128, 124, 14);
		panel.add(lbl_info_birthDate);
		
		lbl_info_accreditations = new JLabel("Accreditation(s) :");
		lbl_info_accreditations.setBounds(21, 173, 85, 14);
		panel.add(lbl_info_accreditations);
		
		lbl_info_LN = new JLabel("Nom :");
		lbl_info_LN.setBounds(21, 85, 53, 14);
		panel.add(lbl_info_LN);
		
		dobDateChooser = new JDateChooser();
		dobDateChooser.setBounds(147, 128, 232, 20);
		panel.add(dobDateChooser);
		
		textFN = new JTextField();
		textFN.setBounds(83, 35, 180, 20);
		panel.add(textFN);
		textFN.setColumns(10);
		
		textLN = new JTextField();
		textLN.setColumns(10);
		textLN.setBounds(64, 82, 180, 20);
		panel.add(textLN);
		
		chckbxAccr1 = new JCheckBox("Ski enfant");
		chckbxAccr1.setBounds(133, 173, 97, 23);
		panel.add(chckbxAccr1);
		
		chckbxAccr2 = new JCheckBox("Snowboard enfant");
		chckbxAccr2.setBounds(232, 173, 133, 23);
		panel.add(chckbxAccr2);
		
		chckbxAccr3 = new JCheckBox("Ski adulte");
		chckbxAccr3.setBounds(133, 199, 97, 23);
		panel.add(chckbxAccr3);
		
		chckbxAccr4 = new JCheckBox("Snowboard adulte");
		chckbxAccr4.setBounds(232, 199, 133, 23);
		panel.add(chckbxAccr4);
		
		chckbxAccr5 = new JCheckBox("Télémark");
		chckbxAccr5.setBounds(133, 225, 97, 23);
		panel.add(chckbxAccr5);
		
		chckbxAccr6 = new JCheckBox("Ski de fond");
		chckbxAccr6.setBounds(232, 225, 133, 23);
		panel.add(chckbxAccr6);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableCInstructor = new JTable();
		scrollPane.setViewportView(tableCInstructor);
		
		btnCreateBooking = new JButton("Créer");
		btnCreateBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createInstructor();
			}
		});
		btnCreateBooking.setBounds(174, 306, 89, 34);
		add(btnCreateBooking);
		
		loadInstructorData();
	}
	
	//Methods
	private void loadInstructorData() {
		
		List<Instructor> instructors = instructor.getAllInstructors(instructorDAO, accrDAO.getAllAccreditations());
		
		updateTable();
	}
	
	private void updateTable() {
		String[] columnNames = {"N° instructeur", "Prénom", "Nom", "Date de naissance", "Accréditation"};
        Object[][] data = new Object[instructorDAO.getWInstructors().size()][5];

        for (int i = 0; i < instructorDAO.getWInstructors().size(); i++) {
            Instructor instructor = instructorDAO.getWInstructors().get(i);
            data[i][0] = instructor.getId();
            data[i][1] = instructor.getFirstName();
            data[i][2] = instructor.getLastName();
            data[i][3] = instructor.getDob().format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
            data[i][4] = instructor.getAccreditations().getFirst().getName();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tableCInstructor.setModel(model);
	}
	
	private void createInstructor() {
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
        
		
		
		if (chckbxAccr1.isSelected()) {
	        accreditations.add(new Accreditation(1,"Ski enfant"));
	    }
	    if (chckbxAccr2.isSelected()) {
	        accreditations.add(new Accreditation(2,"Snowboard enfant"));
	    }
	    if (chckbxAccr3.isSelected()) {
	        accreditations.add(new Accreditation(3,"Ski adulte"));
	    }
	    if (chckbxAccr4.isSelected()) {
	        accreditations.add(new Accreditation(4,"Snowboard adulte"));
	    }
	    if (chckbxAccr5.isSelected()) {
	        accreditations.add(new Accreditation(5,"Télémark"));
	    }
	    if (chckbxAccr6.isSelected()) {
	        accreditations.add(new Accreditation(6,"Ski de fond"));
	    }

	    if (accreditations.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Veuillez sélectionner au moins une accréditation.", "Erreur", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
		
	    Instructor newInstructor = new Instructor();
	    newInstructor.setID(instructorDAO.getWInstructors().size() + 11);
	    newInstructor.setFirstName(firstName);
	    newInstructor.setLastName(lastName);
	    newInstructor.setDob(birthDate);
	    
	    newInstructor.setAccreditationsList(accreditations);
	    
	    newInstructor.addInstructor(instructorDAO);
	    
	    updateTable();

	    textFN.setText("");
	    textLN.setText("");
	    dobDateChooser.setDate(null);
	    chckbxAccr1.setSelected(false);
	    chckbxAccr2.setSelected(false);
	    chckbxAccr3.setSelected(false);
	    chckbxAccr4.setSelected(false);
	    chckbxAccr5.setSelected(false);
	    chckbxAccr6.setSelected(false);

	    JOptionPane.showMessageDialog(this, "L'instructeur/instructrice a été ajouté(e) avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

	}
}
