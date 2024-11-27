package be.sanna.SkiSchool.JFrames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import be.sanna.SkiSchool.DAO.*;

import javax.swing.JTabbedPane;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.awt.Color;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	//Attributes
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection conn = null;
	private AccreditationDAO accrDAO = new AccreditationDAO();
	//private BookingDAO bookingDAO = new BookingDAO();
	private InstructorDAO instructorDAO = new InstructorDAO();
	//private LessonDAO lessonDAO = new LessonDAO();
	//private LessonTypeDAO lessonTypeDAO = new LessonTypeDAO();
	//private PeriodDAO periodDAO = new PeriodDAO();
	//private PersonDAO personDAO = new PersonDAO();
	private StudentDAO studentDAO = new StudentDAO();

	//Application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Constructor
	public MainFrame() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleExit();
            }
        });
		
		setBounds(100, 100, 1115, 767);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane SkiSchoolPane = new JTabbedPane(JTabbedPane.TOP);
		SkiSchoolPane.setBounds(10, 118, 1080, 600);
		contentPane.add(SkiSchoolPane);
		
		JTabbedPane DisplayPane = new JTabbedPane(JTabbedPane.TOP);
		DisplayPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SkiSchoolPane.addTab("Afficher/Modifier", null, DisplayPane, null);
		
		JTabbedPane DBookingPane = new JTabbedPane(JTabbedPane.TOP);
		DisplayPane.addTab("Réservations", new DBookingPanel());
		
		JTabbedPane DLessonPane = new JTabbedPane(JTabbedPane.TOP);
		DisplayPane.addTab("Cours", new DLessonPanel());
		
		JTabbedPane DInstructorPane = new JTabbedPane(JTabbedPane.TOP);
		DisplayPane.addTab("Instructeur", new DInstructorPanel());
		
		JTabbedPane DSkierPane = new JTabbedPane(JTabbedPane.TOP);
		DisplayPane.addTab("Skieur", new DSkierPanel(studentDAO));
		
		JTabbedPane CreatePane = new JTabbedPane(JTabbedPane.TOP);
		CreatePane.setBackground(Color.LIGHT_GRAY);
		SkiSchoolPane.addTab("Créer", null, CreatePane, null);
		
		JTabbedPane CBookingPane = new JTabbedPane(JTabbedPane.TOP);
		CreatePane.addTab("Réservations", new CBookingPanel());
		
		JTabbedPane CLessonPane = new JTabbedPane(JTabbedPane.TOP);
		CreatePane.addTab("Cours", new CLessonPanel());
		
		JTabbedPane CInstructorPane = new JTabbedPane(JTabbedPane.TOP);
		CreatePane.addTab("Instructeur", new CInstructorPanel(instructorDAO, accrDAO));
		
		JTabbedPane CSkierPane = new JTabbedPane(JTabbedPane.TOP);
		CreatePane.addTab("Skieur", new CSkierPanel(studentDAO));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(214, 204, 179));
		panel.setBounds(0, 0, 1107, 736);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MainFrame.class.getResource("/images/LogoLarge.jpg")));
		lblLogo.setBounds(430, 11, 180, 120);
		panel.add(lblLogo);
		
		SkiSchoolPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = SkiSchoolPane.getSelectedIndex();
                String selectedTab = SkiSchoolPane.getTitleAt(selectedIndex);
                System.out.println("Onglet sélectionné dans SkiSchoolPane : " + selectedTab);
                
                if (selectedTab.equals("Afficher/Modifier") || selectedTab.equals("Créer")) {
                    refreshStudentData();  // Refresh data when switching between tabs
                }
            }
        });

        DisplayPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = DisplayPane.getSelectedIndex();
                String selectedTab = DisplayPane.getTitleAt(selectedIndex);
                System.out.println("Onglet sélectionné dans DisplayPane : " + selectedTab);
            }
        });
        
        CreatePane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = CreatePane.getSelectedIndex();
                String selectedTab = CreatePane.getTitleAt(selectedIndex);
                System.out.println("Onglet sélectionné dans CreatePane : " + selectedTab);
            }
        });
	}
	
	//Methods
	public void refreshStudentData() {
		
		CSkierPanel cSkierPanel = (CSkierPanel) ((JTabbedPane) ((JTabbedPane) contentPane.getComponent(0)).getComponentAt(1)).getComponentAt(3);
	    cSkierPanel.loadStudentData();

	    DSkierPanel dSkierPanel = (DSkierPanel) ((JTabbedPane) ((JTabbedPane) contentPane.getComponent(0)).getComponentAt(0)).getComponentAt(3);
	    dSkierPanel.loadStudentData();
    }
	
	private void handleExit() {
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Voulez-vous vraiment quitter l'application ?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
        } 
    }
}
