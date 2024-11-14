package be.sanna.SkiSchool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1321, 1147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane SkiSchoolPane = new JTabbedPane(JTabbedPane.TOP);
		SkiSchoolPane.setBounds(10, 118, 1285, 979);
		contentPane.add(SkiSchoolPane);
		
		JTabbedPane DisplayPane = new JTabbedPane(JTabbedPane.TOP);
		DisplayPane.setBackground(Color.LIGHT_GRAY);
		DisplayPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SkiSchoolPane.addTab("Display", null, DisplayPane, null);
		
		JTabbedPane DBookingPane = new JTabbedPane(JTabbedPane.TOP);
		DisplayPane.addTab("Booking", null, DBookingPane, null);
		
		JTabbedPane DInstructorPane = new JTabbedPane(JTabbedPane.TOP);
		DisplayPane.addTab("Instructor", null, DInstructorPane, null);
		
		JTabbedPane DLessonPane = new JTabbedPane(JTabbedPane.TOP);
		DisplayPane.addTab("Lesson", null, DLessonPane, null);
		
		JTabbedPane CreatePane = new JTabbedPane(JTabbedPane.TOP);
		CreatePane.setBackground(Color.LIGHT_GRAY);
		SkiSchoolPane.addTab("Create", null, CreatePane, null);
		
		JTabbedPane CBookingPane = new JTabbedPane(JTabbedPane.TOP);
		CreatePane.addTab("Booking", null, CBookingPane, null);
		
		JTabbedPane CInstructorPane = new JTabbedPane(JTabbedPane.TOP);
		CreatePane.addTab("Instructor", null, CInstructorPane, null);
		
		JTabbedPane CSkierPane = new JTabbedPane(JTabbedPane.TOP);
		CreatePane.addTab("Skier", null, CSkierPane, null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(214, 204, 179));
		panel.setBounds(0, 0, 1305, 1108);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MainFrame.class.getResource("/images/LogoLarge.jpg")));
		lblLogo.setBounds(529, 11, 180, 120);
		panel.add(lblLogo);
	}
}