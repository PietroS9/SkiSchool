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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;

public class DSkierPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField text_search_StudentID;
	private JTextField text_info_FN;
	private JTextField text_info_LN;
	private JTable tableDSkier;

	/**
	 * Create the panel.
	 */
	public DSkierPanel() {
		setLayout(null);
		
		JPanel SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SearchPanel.setBounds(26, 31, 400, 68);
		add(SearchPanel);
		SearchPanel.setLayout(null);
		
		JLabel lblSearchID = new JLabel("N° skieur :");
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
		
		text_search_StudentID = new JTextField();
		text_search_StudentID.setBounds(111, 29, 146, 20);
		SearchPanel.add(text_search_StudentID);
		text_search_StudentID.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 120, 400, 178);
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
		
		JDateChooser dateChooser_info_birthDate = new JDateChooser();
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableDSkier = new JTable();
		scrollPane.setColumnHeaderView(tableDSkier);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(199, 309, 100, 41);
		add(btnClear);
		
		JButton btnUpdate = new JButton("Mettre à jour le profil");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(26, 309, 165, 41);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(309, 309, 117, 41);
		add(btnDelete);
	}

}
