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

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTextField textPrice;
	private JTable tableCBooking;
	private JPanel panel;
	private JLabel lbl_info_Skier;
	private JLabel lbl_info_LessonTypeLevel;
	private JLabel lbl_info_Period_startDate;
	private JLabel lbl_info_Period_endDate;
	private JLabel lbl_info_Booking_individual;
	private JRadioButton rdbtnIsIndividual;
	private JRadioButton rdbtnNotIndividual;
	private ButtonGroup individualLesson;
	private JLabel lbl_info_Booking_insurance;
	private JRadioButton rdbtnIsInsured;
	private JRadioButton rdbtnNotInsured;
	private ButtonGroup insuranceLesson;
	private JLabel lbl_info_Booking_price;
	private JLabel lbl_info_euroSign;
	private JComboBox cBox_info_Skier;
	private JComboBox cBox_info_LessonType;
	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	private JScrollPane scrollPane;
	private JButton btnCreateBooking;
	/*private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;
	private ;*/

	//Constructor
	public CBookingPanel() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 31, 400, 343);
		add(panel);
		panel.setLayout(null);
		
		lbl_info_Skier = new JLabel("Skier :");
		lbl_info_Skier.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_Skier);
		
		lbl_info_LessonTypeLevel = new JLabel("Cours :");
		lbl_info_LessonTypeLevel.setBounds(21, 128, 67, 14);
		panel.add(lbl_info_LessonTypeLevel);
		
		lbl_info_Period_startDate = new JLabel("Date de début :");
		lbl_info_Period_startDate.setBounds(21, 173, 85, 14);
		panel.add(lbl_info_Period_startDate);
		
		lbl_info_Period_endDate = new JLabel("Date de fin :");
		lbl_info_Period_endDate.setBounds(21, 218, 85, 14);
		panel.add(lbl_info_Period_endDate);
		
		lbl_info_Booking_individual = new JLabel("Cours individuel :");
		lbl_info_Booking_individual.setBounds(21, 85, 104, 14);
		panel.add(lbl_info_Booking_individual);
		
		rdbtnIsIndividual = new JRadioButton("Oui");
		rdbtnIsIndividual.setBounds(138, 81, 55, 23);
		panel.add(rdbtnIsIndividual);
		
		rdbtnNotIndividual = new JRadioButton("Non");
		rdbtnNotIndividual.setSelected(true);
		rdbtnNotIndividual.setBounds(195, 81, 55, 23);
		panel.add(rdbtnNotIndividual);
		
		individualLesson = new ButtonGroup();
		individualLesson.add(rdbtnIsIndividual);
		individualLesson.add(rdbtnNotIndividual);
		
		lbl_info_Booking_insurance = new JLabel("Assurance :");
		lbl_info_Booking_insurance.setBounds(21, 265, 104, 14);
		panel.add(lbl_info_Booking_insurance);
		
		rdbtnIsInsured = new JRadioButton("Oui");
		rdbtnIsInsured.setBounds(138, 261, 55, 23);
		panel.add(rdbtnIsInsured);
		
		rdbtnNotInsured = new JRadioButton("Non");
		rdbtnNotInsured.setSelected(true);
		rdbtnNotInsured.setBounds(195, 261, 55, 23);
		panel.add(rdbtnNotInsured);
		
		insuranceLesson = new ButtonGroup();
		insuranceLesson.add(rdbtnIsInsured);
		insuranceLesson.add(rdbtnNotInsured);
		
		lbl_info_Booking_price = new JLabel("Total :");
		lbl_info_Booking_price.setBounds(21, 308, 46, 14);
		panel.add(lbl_info_Booking_price);
		
		lbl_info_euroSign = new JLabel("€");
		lbl_info_euroSign.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_info_euroSign.setBounds(173, 308, 23, 14);
		panel.add(lbl_info_euroSign);
		
		cBox_info_Skier = new JComboBox();
		cBox_info_Skier.setBounds(133, 34, 232, 22);
		panel.add(cBox_info_Skier);
		
		cBox_info_LessonType = new JComboBox();
		cBox_info_LessonType.setBounds(133, 124, 232, 22);
		panel.add(cBox_info_LessonType);
		
		startDateChooser = new JDateChooser();
		startDateChooser.setBounds(133, 173, 232, 20);
		panel.add(startDateChooser);
		
		endDateChooser = new JDateChooser();
		endDateChooser.setBounds(133, 218, 232, 20);
		panel.add(endDateChooser);
		
		textPrice = new JTextField();
		textPrice.setBounds(77, 305, 86, 20);
		panel.add(textPrice);
		textPrice.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableCBooking = new JTable();
		scrollPane.setViewportView(tableCBooking);
		
		btnCreateBooking = new JButton("Créer");
		btnCreateBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateBooking.setBounds(176, 385, 89, 34);
		add(btnCreateBooking);
	}
	
	//Methods
}
