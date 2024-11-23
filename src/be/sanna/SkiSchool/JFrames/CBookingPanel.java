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
import javax.swing.JTable;

public class CBookingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textPrice;
	private JTable tableCBooking;

	/**
	 * Create the panel.
	 */
	public CBookingPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 31, 400, 343);
		add(panel);
		panel.setLayout(null);
		
		JLabel lbl_info_Skier = new JLabel("Skier :");
		lbl_info_Skier.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_Skier);
		
		JLabel lbl_info_LessonTypeLevel = new JLabel("Cours :");
		lbl_info_LessonTypeLevel.setBounds(21, 128, 67, 14);
		panel.add(lbl_info_LessonTypeLevel);
		
		JLabel lbl_info_Period_startDate = new JLabel("Date de début :");
		lbl_info_Period_startDate.setBounds(21, 173, 85, 14);
		panel.add(lbl_info_Period_startDate);
		
		JLabel lbl_info_Period_endDate = new JLabel("Date de fin :");
		lbl_info_Period_endDate.setBounds(21, 218, 85, 14);
		panel.add(lbl_info_Period_endDate);
		
		JLabel lbl_info_Booking_individual = new JLabel("Cours individuel :");
		lbl_info_Booking_individual.setBounds(21, 85, 104, 14);
		panel.add(lbl_info_Booking_individual);
		
		JRadioButton rdbtnIsIndividual = new JRadioButton("Oui");
		rdbtnIsIndividual.setBounds(138, 81, 55, 23);
		panel.add(rdbtnIsIndividual);
		
		JRadioButton rdbtnNotIndividual = new JRadioButton("Non");
		rdbtnNotIndividual.setSelected(true);
		rdbtnNotIndividual.setBounds(195, 81, 55, 23);
		panel.add(rdbtnNotIndividual);
		
		ButtonGroup individualLesson = new ButtonGroup();
		individualLesson.add(rdbtnIsIndividual);
		individualLesson.add(rdbtnNotIndividual);
		
		JLabel lbl_info_Booking_insurance = new JLabel("Assurance :");
		lbl_info_Booking_insurance.setBounds(21, 265, 104, 14);
		panel.add(lbl_info_Booking_insurance);
		
		JRadioButton rdbtnIsInsured = new JRadioButton("Oui");
		rdbtnIsInsured.setBounds(138, 261, 55, 23);
		panel.add(rdbtnIsInsured);
		
		JRadioButton rdbtnNotInsured = new JRadioButton("Non");
		rdbtnNotInsured.setSelected(true);
		rdbtnNotInsured.setBounds(195, 261, 55, 23);
		panel.add(rdbtnNotInsured);
		
		ButtonGroup insuranceLesson = new ButtonGroup();
		insuranceLesson.add(rdbtnIsInsured);
		insuranceLesson.add(rdbtnNotInsured);
		
		JLabel lbl_info_Booking_price = new JLabel("Total :");
		lbl_info_Booking_price.setBounds(21, 308, 46, 14);
		panel.add(lbl_info_Booking_price);
		
		JLabel lbl_info_euroSign = new JLabel("€");
		lbl_info_euroSign.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_info_euroSign.setBounds(173, 308, 23, 14);
		panel.add(lbl_info_euroSign);
		
		JComboBox cBox_info_Skier = new JComboBox();
		cBox_info_Skier.setBounds(133, 34, 232, 22);
		panel.add(cBox_info_Skier);
		
		JComboBox cBox_info_LessonType = new JComboBox();
		cBox_info_LessonType.setBounds(133, 124, 232, 22);
		panel.add(cBox_info_LessonType);
		
		JDateChooser startDateChooser = new JDateChooser();
		startDateChooser.setBounds(133, 173, 232, 20);
		panel.add(startDateChooser);
		
		JDateChooser endDateChooser = new JDateChooser();
		endDateChooser.setBounds(133, 218, 232, 20);
		panel.add(endDateChooser);
		
		textPrice = new JTextField();
		textPrice.setBounds(77, 305, 86, 20);
		panel.add(textPrice);
		textPrice.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableCBooking = new JTable();
		scrollPane.setColumnHeaderView(tableCBooking);
		
		JButton btnCreateBooking = new JButton("Créer");
		btnCreateBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateBooking.setBounds(176, 385, 89, 34);
		add(btnCreateBooking);
	}
}
