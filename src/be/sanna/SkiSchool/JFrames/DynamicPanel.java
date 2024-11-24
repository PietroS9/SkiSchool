package be.sanna.SkiSchool.JFrames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import be.sanna.SkiSchool.DAO.*;
import be.sanna.SkiSchool.Utilities.DatabaseGetter;

public class DynamicPanel extends JPanel {
	
	//Attributes
	private JTable table;
	private String query;
	
	//Constructor
	public DynamicPanel(String query_) {
		this.query = query_;
		initializeUI();
		loadData();
	}

	//Methods
	private void initializeUI() {
        setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 595, 490);
        add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
    }

    private void loadData() {
        Object[][] data = DatabaseGetter.getData(query);
        String[] columnNames = DatabaseGetter.getColumnNames(query);

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }

}
