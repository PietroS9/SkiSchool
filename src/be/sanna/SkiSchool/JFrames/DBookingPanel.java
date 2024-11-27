package be.sanna.SkiSchool.JFrames;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class DBookingPanel extends JPanel {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JTextField textSearchID;
	private JTextField textTotalPrice;
	private JTable tableDBooking;
	private JPanel SearchPanel;
	private JLabel lblSearchID;
	private JButton btnSearch;
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
	private JButton btnClear;
	private JButton btnUpdate;
	private JButton btnDelete;
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
	public DBookingPanel() {
		setLayout(null);
		
		SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SearchPanel.setBounds(26, 31, 400, 68);
		add(SearchPanel);
		SearchPanel.setLayout(null);
		
		lblSearchID = new JLabel("N° réservation :");
		lblSearchID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchID.setBounds(21, 29, 114, 17);
		SearchPanel.add(lblSearchID);
		
		textSearchID = new JTextField();
		textSearchID.setBounds(145, 29, 108, 20);
		SearchPanel.add(textSearchID);
		textSearchID.setColumns(10);
		
		btnSearch = new JButton("Rechercher");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(267, 28, 108, 23);
		SearchPanel.add(btnSearch);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 120, 400, 349);
		add(panel);
		panel.setLayout(null);
		
		lbl_info_Skier = new JLabel("Skier :");
		lbl_info_Skier.setBounds(21, 38, 67, 14);
		panel.add(lbl_info_Skier);
		
		lbl_info_LessonTypeLevel = new JLabel("Cours :");
		lbl_info_LessonTypeLevel.setBounds(21, 131, 67, 14);
		panel.add(lbl_info_LessonTypeLevel);
		
		lbl_info_Period_startDate = new JLabel("Date de début :");
		lbl_info_Period_startDate.setBounds(21, 176, 85, 14);
		panel.add(lbl_info_Period_startDate);
		
		lbl_info_Period_endDate = new JLabel("Date de fin :");
		lbl_info_Period_endDate.setBounds(21, 221, 85, 14);
		panel.add(lbl_info_Period_endDate);
		
		lbl_info_Booking_individual = new JLabel("Cours individuel :");
		lbl_info_Booking_individual.setBounds(21, 87, 104, 14);
		panel.add(lbl_info_Booking_individual);
		
		rdbtnIsIndividual = new JRadioButton("Oui");
		rdbtnIsIndividual.setBounds(138, 83, 55, 23);
		panel.add(rdbtnIsIndividual);
		
		rdbtnNotIndividual = new JRadioButton("Non");
		rdbtnNotIndividual.setSelected(true);
		rdbtnNotIndividual.setBounds(195, 83, 55, 23);
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
		
		textTotalPrice = new JTextField();
		textTotalPrice.setBounds(77, 305, 86, 20);
		panel.add(textTotalPrice);
		textTotalPrice.setColumns(10);
		
		lbl_info_euroSign = new JLabel("€");
		lbl_info_euroSign.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_info_euroSign.setBounds(173, 308, 23, 14);
		panel.add(lbl_info_euroSign);
		
		cBox_info_Skier = new JComboBox();
		cBox_info_Skier.setBounds(133, 34, 232, 22);
		panel.add(cBox_info_Skier);
		
		cBox_info_LessonType = new JComboBox();
		cBox_info_LessonType.setBounds(133, 127, 232, 22);
		panel.add(cBox_info_LessonType);
		
		startDateChooser = new JDateChooser();
		startDateChooser.setBounds(133, 176, 232, 20);
		panel.add(startDateChooser);
		
		endDateChooser = new JDateChooser();
		endDateChooser.setBounds(133, 221, 232, 20);
		panel.add(endDateChooser);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 31, 595, 490);
		add(scrollPane);
		
		tableDBooking = new JTable();
		scrollPane.setViewportView(tableDBooking);
		
		btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(165, 480, 122, 41);
		add(btnClear);
		
		btnUpdate = new JButton("Mettre à jour");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(26, 480, 122, 41);
		add(btnUpdate);
		
		btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(304, 480, 122, 41);
		add(btnDelete);

	}
	
	//Methods
}
