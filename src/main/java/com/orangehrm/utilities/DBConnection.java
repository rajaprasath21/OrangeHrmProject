package com.orangehrm.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.orangehrm.base.BaseClass;

public class DBConnection {
	
	public static final Logger logger=LoggerManager.getLogger(BaseClass.class);
	private static final String DB_URL="jdbc:mysql://localhost:3306/orangehrm";
	private static final String DB_USERNAME="root";
	private static final String DB_PASSWORD="";

	public static Connection getDBConnection() {
		try {
			logger.info("Starting DB Connection....");
			Connection connection=DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			logger.info("DB connected successfully..");
			return connection;
		} catch (SQLException e) {
			logger.error("Error while establishing the DB Connection");
			e.printStackTrace();
			return null ;
		}
	}
	
	//Get Employee details from the Database & store in a map
	public static Map<String, String> getEmployeeDetails(String employee_id){
		String query="select emp_firstname,emp_middle_name,emp_lastname from hs_hr_employee where employee_id="+employee_id;

		Map<String, String> employeeDetails=new HashMap<>();

		try(Connection connection=getDBConnection();
				Statement statement=connection.createStatement();
				ResultSet rs=statement.executeQuery(query);
				) {
			logger.info("Executing query: "+query);
			if(rs.next()) {
				String firstName=rs.getString("emp_firstname");
				String middleName=rs.getString("emp_middle_name");
				String lastName=rs.getString("emp_lastname");

				//Store in a Map
				employeeDetails.put("firstName", firstName);
				employeeDetails.put("middleName", middleName!=null ? middleName:"");
				employeeDetails.put("lastName", lastName);

				logger.info("Query executed Successfully");
				logger.info("Employee Data Fetched: "+employeeDetails);
			}else {
				logger.error("Employee not found");
			}
		} 
		catch (Exception e) {
			logger.error("Error while executing the query");
			e.printStackTrace();
		}

		return employeeDetails;
	}

}
