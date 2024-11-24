package be.sanna.SkiSchool.Utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseGetter {

	//Attributes
	
	//Constructor
	
	//Methods
	public static Object[][] getData(String query){
		ArrayList<Object[]> dataList = new ArrayList<>();
		try (Statement statement = ConnectionJDBC.getInstance().createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {

	            ResultSetMetaData metaData = resultSet.getMetaData();
	            int columnCount = metaData.getColumnCount();

	            while (resultSet.next()) {
	                Object[] row = new Object[columnCount];
	                for (int i = 1; i <= columnCount; i++) {
	                    row[i - 1] = resultSet.getObject(i);
	                }
	                dataList.add(row);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

        return dataList.toArray(new Object[0][]);
	}
	
	public static String[] getColumnNames(String query) {
        ArrayList<String> columnNames = new ArrayList<>();
        try (Statement statement = ConnectionJDBC.getInstance().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnNames.toArray(new String[0]);
    }
}
