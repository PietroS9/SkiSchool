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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;

public class DInstructorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField text_search_InstructorID;
	private JTextField text_info_Firstname;
	private JTextField text_info_LastName;
	private JTable tableDInstructor;

	/**
	 * Create the panel.
	 */
	public DInstructorPanel() {
		setLayout(null);
		
		JPanel SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SearchPanel.setBounds(26, 31, 400, 68);
		add(SearchPanel);
		SearchPanel.setLayout(null);
		
		JLabel lblSearchID = new JLabel("N° instructeur :");
		lblSearchID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchID.setBounds(21, 29, 113, 17);
		SearchPanel.add(lblSearchID);

		JButton btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(267, 28, 108, 23);
		SearchPanel.add(btnSearch);
		
		text_search_InstructorID = new JTextField();
		text_search_InstructorID.setBounds(144, 29, 113, 20);
		SearchPanel.add(text_search_InstructorID);
		text_search_InstructorID.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 120, 400, 352);
		add(panel);
		panel.setLayout(null);
		
		JLabel lbl_info_Person_FN = new JLabel("Prénom :");
		lbl_info_Person_FN.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_Person_FN);
		
		JLabel lbl_info_Person_LN = new JLabel("Nom :");
		lbl_info_Person_LN.setBounds(21, 83, 67, 14);
		panel.add(lbl_info_Person_LN);
		
		JLabel lbl_info_Person_birthDate = new JLabel("Date de naissance:");
		lbl_info_Person_birthDate.setBounds(21, 129, 129, 14);
		panel.add(lbl_info_Person_birthDate);
		
		JLabel lbl_info_instAccr_accrid = new JLabel("Accrédiatations :");
		lbl_info_instAccr_accrid.setBounds(21, 178, 106, 14);
		panel.add(lbl_info_instAccr_accrid);
		
		JTextArea txtrAttentionLajout = new JTextArea();
		txtrAttentionLajout.setEditable(false);
		txtrAttentionLajout.setFont(new Font("Tahoma", Font.ITALIC, 10));
		txtrAttentionLajout.setText("Attention ! L'ajout ou le retrait d'une accréditation met à jour automatiquement \r\nle profil de l'instructeur/l'instructrice.");
		txtrAttentionLajout.setBounds(21, 306, 356, 35);
		panel.add(txtrAttentionLajout);
		
		JButton btnAddAccreditation = new JButton("Ajouter une accreditation");
		btnAddAccreditation.setBackground(new Color(0, 128, 0));
		btnAddAccreditation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddAccreditation.setBounds(21, 221, 189, 23);
		panel.add(btnAddAccreditation);
		
		JButton btnRemoveAccreditation = new JButton("Retirer une accreditation");
		btnRemoveAccreditation.setBackground(new Color(241, 147, 141));
		btnRemoveAccreditation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveAccreditation.setBounds(21, 262, 189, 23);
		panel.add(btnRemoveAccreditation);
		
		text_info_Firstname = new JTextField();
		text_info_Firstname.setBounds(98, 35, 246, 20);
		panel.add(text_info_Firstname);
		text_info_Firstname.setColumns(10);
		
		text_info_LastName = new JTextField();
		text_info_LastName.setColumns(10);
		text_info_LastName.setBounds(98, 80, 246, 20);
		panel.add(text_info_LastName);
		
		JDateChooser dateChooser_info_birthDate = new JDateChooser();
		dateChooser_info_birthDate.setBounds(160, 129, 184, 20);
		panel.add(dateChooser_info_birthDate);
		
		JComboBox cBox_info_instAccr_accrid = new JComboBox();
		cBox_info_instAccr_accrid.setBounds(160, 174, 184, 22);
		panel.add(cBox_info_instAccr_accrid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableDInstructor = new JTable();
		scrollPane.setColumnHeaderView(tableDInstructor);
		
		JButton btnUpdate = new JButton("Mettre à jour le profil");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(26, 480, 165, 41);
		add(btnUpdate);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(210, 480, 95, 41);
		add(btnClear);
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(318, 480, 108, 41);
		add(btnDelete);
	}
}
