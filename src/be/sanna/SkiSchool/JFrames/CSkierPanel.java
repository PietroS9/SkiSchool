package be.sanna.SkiSchool.JFrames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
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
		
		tableCSkier = new JTable();
		scrollPane.setColumnHeaderView(tableCSkier);
		
		JButton btnCreateBooking = new JButton("Créer");
		btnCreateBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateBooking.setBounds(173, 213, 89, 34);
		add(btnCreateBooking);
	}

}
