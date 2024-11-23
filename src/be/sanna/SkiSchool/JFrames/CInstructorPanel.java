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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class CInstructorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTable tableCInstructor;

	/**
	 * Create the panel.
	 */
	public CInstructorPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 31, 400, 264);
		add(panel);
		panel.setLayout(null);
		
		JLabel lbl_info_FN = new JLabel("Prénom :");
		lbl_info_FN.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_FN);
		
		JLabel lbl_info_birthDate = new JLabel("Date de naissance :");
		lbl_info_birthDate.setBounds(21, 128, 124, 14);
		panel.add(lbl_info_birthDate);
		
		JLabel lbl_info_accreditations = new JLabel("Accreditation(s) :");
		lbl_info_accreditations.setBounds(21, 173, 85, 14);
		panel.add(lbl_info_accreditations);
		
		JLabel lbl_info_LN = new JLabel("Nom :");
		lbl_info_LN.setBounds(21, 85, 53, 14);
		panel.add(lbl_info_LN);
		
		JDateChooser startDateChooser = new JDateChooser();
		startDateChooser.setBounds(147, 128, 232, 20);
		panel.add(startDateChooser);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(83, 35, 180, 20);
		panel.add(textFirstName);
		textFirstName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setColumns(10);
		textLastName.setBounds(64, 82, 180, 20);
		panel.add(textLastName);
		
		JCheckBox chckbxAccr1 = new JCheckBox("Ski enfant");
		chckbxAccr1.setBounds(133, 173, 97, 23);
		panel.add(chckbxAccr1);
		
		JCheckBox chckbxAccr3 = new JCheckBox("Ski adulte");
		chckbxAccr3.setBounds(133, 199, 97, 23);
		panel.add(chckbxAccr3);
		
		JCheckBox chckbxAccr6 = new JCheckBox("Ski de fond");
		chckbxAccr6.setBounds(232, 225, 133, 23);
		panel.add(chckbxAccr6);
		
		JCheckBox chckbxAccr5 = new JCheckBox("Télémark");
		chckbxAccr5.setBounds(133, 225, 97, 23);
		panel.add(chckbxAccr5);
		
		JCheckBox chckbxAccr4 = new JCheckBox("Snowboard adulte");
		chckbxAccr4.setBounds(232, 199, 133, 23);
		panel.add(chckbxAccr4);
		
		JCheckBox chckbxAccr2 = new JCheckBox("Snowboard enfant");
		chckbxAccr2.setBounds(232, 173, 133, 23);
		panel.add(chckbxAccr2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableCInstructor = new JTable();
		scrollPane.setColumnHeaderView(tableCInstructor);
		
		JButton btnCreateBooking = new JButton("Créer");
		btnCreateBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateBooking.setBounds(174, 306, 89, 34);
		add(btnCreateBooking);
	}
}
