package be.sanna.SkiSchool.POJO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConnectionJDBC {
	
	//Attributes
	private static Connection instance = null;
	private static final String db_url = "jdbc:oracle:thin:@//193.190.64.10:1522/xepdb1";
	private static final String user_ = "STUDENT03_21";
	private static final String password = "mqeebd";
	
	//Constructor
	private ConnectionJDBC() {
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			instance = DriverManager.getConnection(db_url, user_,password);
			
			if (instance != null) {
				System.out.println("Connexion réussie à la base de données Oracle !");
			}else {
				JOptionPane.showMessageDialog(null, "La connexion a échoué !");
                System.exit(0);
			}
			
		} catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Classe de driver introuvable : " + ex.getMessage());
            System.exit(0);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur JDBC : " + ex.getMessage());
            System.exit(0);
        }
		if (instance == null) {
			JOptionPane.showMessageDialog(null, "La base de données est inaccessible, fermeture du programme !");
			System.exit(0);
		}
	}
	
	//Method
	public static Connection getInstance() {
		if(instance == null) {
			new ConnectionJDBC();
		}
		return instance;
	}
}
