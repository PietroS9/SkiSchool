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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

import be.sanna.SkiSchool.DAO.AccreditationDAO;
import be.sanna.SkiSchool.DAO.InstructorDAO;
import be.sanna.SkiSchool.POJO.Accreditation;
import be.sanna.SkiSchool.POJO.Instructor;

import javax.swing.JTable;
import javax.swing.JCheckBox;

public class DInstructorPanel extends JPanel {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTextField text_search_InstructorID;
	private JTextField text_info_FN;
	private JTextField text_info_LN;
	private JTable tableDInstructor;
	private JPanel SearchPanel;
	private JLabel lblSearchID;
	private JButton btnSearch;
	private JPanel panel;
	private JLabel lbl_info_Person_FN;
	private JLabel lbl_info_Person_LN;
	private JLabel lbl_info_Person_birthDate;
	private JLabel lbl_info_instAccr_accrid;
	private JDateChooser dateChooser_info_birthDate;
	private JCheckBox chckbxAccr1;
	private JCheckBox chckbxAccr2;
	private JCheckBox chckbxAccr3;
	private JCheckBox chckbxAccr4;
	private JCheckBox chckbxAccr5;
	private JCheckBox chckbxAccr6;
	private JScrollPane scrollPane;
	private JButton btnUpdate;
	private JButton btnClear;
	private JButton btnDelete;
	private Instructor instructor = new Instructor();
	private InstructorDAO instructorDAO;
	private AccreditationDAO accrDAO;
	private List<Accreditation> accreditations = new ArrayList<>();
	
	//Constructor
	public DInstructorPanel(InstructorDAO instructorDAO_, AccreditationDAO accrDAO_) {
		this.instructorDAO = instructorDAO_;
		this.accrDAO = accrDAO_;
		setLayout(null);
		
		SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SearchPanel.setBounds(26, 31, 400, 68);
		add(SearchPanel);
		SearchPanel.setLayout(null);
		
		lblSearchID = new JLabel("N° instructeur :");
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
		
		text_search_InstructorID = new JTextField();
		text_search_InstructorID.setBounds(144, 29, 113, 20);
		SearchPanel.add(text_search_InstructorID);
		text_search_InstructorID.setColumns(10);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 120, 400, 352);
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
		
		lbl_info_instAccr_accrid = new JLabel("Accrédiatations :");
		lbl_info_instAccr_accrid.setBounds(21, 178, 106, 14);
		panel.add(lbl_info_instAccr_accrid);
		
		text_info_FN = new JTextField();
		text_info_FN.setBounds(98, 35, 246, 20);
		panel.add(text_info_FN);
		text_info_FN.setColumns(10);
		
		text_info_LN = new JTextField();
		text_info_LN.setColumns(10);
		text_info_LN.setBounds(98, 80, 246, 20);
		panel.add(text_info_LN);
		
		dateChooser_info_birthDate = new JDateChooser();
		dateChooser_info_birthDate.setBounds(160, 129, 184, 20);
		panel.add(dateChooser_info_birthDate);
		
		chckbxAccr1 = new JCheckBox("Ski enfant");
		chckbxAccr1.setBounds(133, 178, 97, 23);
		panel.add(chckbxAccr1);
		
		chckbxAccr2 = new JCheckBox("Snowboard enfant");
		chckbxAccr2.setBounds(232, 178, 133, 23);
		panel.add(chckbxAccr2);
		
		chckbxAccr3 = new JCheckBox("Ski adulte");
		chckbxAccr3.setBounds(133, 204, 97, 23);
		panel.add(chckbxAccr3);
		
		chckbxAccr4 = new JCheckBox("Snowboard adulte");
		chckbxAccr4.setBounds(232, 204, 133, 23);
		panel.add(chckbxAccr4);
		
		chckbxAccr5 = new JCheckBox("Télémark");
		chckbxAccr5.setBounds(133, 230, 97, 23);
		panel.add(chckbxAccr5);
		
		chckbxAccr6 = new JCheckBox("Ski de fond");
		chckbxAccr6.setBounds(232, 230, 133, 23);
		panel.add(chckbxAccr6);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableDInstructor = new JTable();
		scrollPane.setViewportView(tableDInstructor);
		tableDInstructor.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseMoved(MouseEvent e) {
		        int row = tableDInstructor.rowAtPoint(e.getPoint());
		        int col = tableDInstructor.columnAtPoint(e.getPoint());
		        if (col == 4) {
		        	tableDInstructor.setToolTipText((String) tableDInstructor.getValueAt(row, col));
		        } else {
		        	tableDInstructor.setToolTipText(null);
		        }
		    }
		});
		
		btnUpdate = new JButton("Mettre à jour le profil");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateInstructor();
			}
		});
		btnUpdate.setBounds(26, 480, 165, 41);
		add(btnUpdate);
		
		btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setBounds(210, 480, 95, 41);
		add(btnClear);
		
		btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteInstructor();
			}
		});
		btnDelete.setBounds(318, 480, 108, 41);
		add(btnDelete);
		
		loadInstructorData();
	}
	
	//Methods
	protected void loadInstructorData() {
		
		List<Instructor> instructors = instructor.getAllInstructors(instructorDAO, accrDAO.getAllAccreditations());
		
		updateTable(instructors);
	}
	
	private void updateTable(List<Instructor> instructors) {
		String[] columnNames = {"N° instructeur", "Prénom", "Nom", "Date de naissance", "Accréditation(s)"};
        Object[][] data = new Object[instructorDAO.getInstructors().size()][5];

        for (int i = 0; i < instructors.size(); i++) {
            Instructor instructor = instructors.get(i);
            data[i][0] = instructor.getId();
            data[i][1] = instructor.getFirstName();
            data[i][2] = instructor.getLastName();
            data[i][3] = instructor.getDob().format(DateTimeFormatter.ofPattern("dd-MM-yyy"));
            
            StringBuilder accreditations = new StringBuilder();
            for (Accreditation accr : instructor.getAccreditations()) {
                if (accreditations.length() > 0) {
                    accreditations.append(",\n");
                }
                accreditations.append(accr.getName());
            }
            data[i][4] = accreditations.toString();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tableDInstructor.setModel(model);
	}
	
	private void search() {
		String searchID = text_search_InstructorID.getText().trim();
		
		if(searchID.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Veuillez entrer un ID d'instructeur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
		}
		
		try {
			int instructorID = Integer.parseInt(searchID);
			Instructor foundInstructor = null;
			
			for(Instructor instructor : instructor.getAllInstructors(instructorDAO,accrDAO.getAllAccreditations())) {
				if(instructor.getId() == instructorID) {
					foundInstructor = instructor;
					break;
				}
			}
			
			if(foundInstructor != null) {
				text_info_FN.setText(foundInstructor.getFirstName());
				text_info_LN.setText(foundInstructor.getLastName());
                dateChooser_info_birthDate.setDate(java.sql.Date.valueOf(foundInstructor.getDob()));
                
                chckbxAccr1.setSelected(false);
                chckbxAccr2.setSelected(false);
                chckbxAccr3.setSelected(false);
                chckbxAccr4.setSelected(false);
                chckbxAccr5.setSelected(false);
                chckbxAccr6.setSelected(false);
                
                for (Accreditation accr : foundInstructor.getAccreditations()) {
                    switch (accr.getAccrId()) {
                        case 1:
                            chckbxAccr1.setSelected(true);
                            break;
                        case 2:
                            chckbxAccr2.setSelected(true);
                            break;
                        case 3:
                            chckbxAccr3.setSelected(true);
                            break;
                        case 4:
                            chckbxAccr4.setSelected(true);
                            break;
                        case 5:
                            chckbxAccr5.setSelected(true);
                            break;
                        case 6:
                            chckbxAccr6.setSelected(true);
                            break;
                    }
                }
                
			} else {
				JOptionPane.showMessageDialog(null, "Aucun(e) instructeur/rice trouvé(e) avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void clear() {
		text_info_FN.setText("");
        text_info_LN.setText("");
        dateChooser_info_birthDate.setDate(null);
        chckbxAccr1.setSelected(false);
        chckbxAccr2.setSelected(false);
        chckbxAccr3.setSelected(false);
        chckbxAccr4.setSelected(false);
        chckbxAccr5.setSelected(false);
        chckbxAccr6.setSelected(false);
	}
	
	public void updateInstructor() {
		String idText = text_search_InstructorID.getText().trim();
		
		if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un ID d'instructeur/rice pour mettre à jour.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
		
		try {
			int instructorId = Integer.parseInt(idText);
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
            
            if (updatedFirstName.length() > 20 || updatedLastName.length() > 20) {
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
    	    
            boolean found = false;
            for (Instructor instructor : instructorDAO.getInstructors()) {
            	if(instructor.getId() == instructorId) {
            		instructor.setFirstName(updatedFirstName);
            		instructor.setLastName(updatedLastName);
            		instructor.setDob(updatedDob);
            		instructor.setAccreditationsList(accreditations);
            		
            		instructor.updateToDB(instructorDAO);
            		
            		loadInstructorData();
            		
            		JOptionPane.showMessageDialog(null, "Le profil a été mis à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
            	}
            }
            
            if (!found) {
                JOptionPane.showMessageDialog(null, "Aucun(e) instructeur/rice trouvé(e) avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            
		} catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void deleteInstructor() {
		String idText = text_search_InstructorID.getText().trim();
				
		if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un ID d'instructeur/rice à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
		
		try {
			int instructorId = Integer.parseInt(idText);
			
			boolean found = false;
			for (Instructor instructor : instructorDAO.getInstructors()) {
            	if(instructor.getId() == instructorId) {
            		
            		instructor.deleteToDB(instructorDAO);
            		instructor.removeInstructor(instructorDAO);
            		
            		loadInstructorData();
            		
            		JOptionPane.showMessageDialog(null, "L'instructeur/rice a été supprimé(e) avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	                clear();
	                text_search_InstructorID.setText("");
	                found = true;
	                break;
            	}
			}
			
			if (!found) {
                JOptionPane.showMessageDialog(null, "Aucun(e) instructeur/rice trouvé(e) avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
		} catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "L'ID doit être un entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}
}
