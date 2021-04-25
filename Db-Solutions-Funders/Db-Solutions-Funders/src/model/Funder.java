package model;

import java.sql.*;

public class Funder {
	//A common method to connect to the DB
	
	private Connection connect()
	 {
		Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db-solutions-funder", "root", "root");
	 }
	 catch (Exception e)
	 	{e.printStackTrace();}
	 return con;
	 }
	
	public String insertFunder(String fName, String PhNumber,  String fCountry)
	
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 // create a prepared statement
	 String query = " insert into funders(`funder_ID`,`funderName`,`phoneNumber`,`country` )" + " values (?, ?, ?, ?)";
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, fName);
	 preparedStmt.setInt(3, Integer.parseInt(PhNumber));
	 preparedStmt.setString(4, fCountry);
	 
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while inserting the funder.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String readFunders()
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
	 
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Funder Name</th>" +
	 "<th>Phone Number</th>" +
	 "<th>Country</th>" + 
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from funders";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String funder_ID = Integer.toString(rs.getInt("funder_ID"));
		 String funderName = rs.getString("funderName");
		 String phoneNumber =Integer.toString(rs.getInt("phoneNumber"));
		 String country = rs.getString("country");
	 
	 // Add into the html table
	 output += "<tr><td>" + funderName + "</td>";
	 output += "<td>" + phoneNumber + "</td>";
	 output += "<td>" + country + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='funder_ID' type='hidden' value='" + funder_ID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 
	 // Complete the html table
	 output += "</table>";
	 
	 }
	 catch (Exception e)
	 { 
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
	 
	 }
	 
	 return output;
	 }
	
	public String updateFunder(String funderID, String fName, String PhNumber,  String fCountry)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 	String query = "UPDATE funders SET funderName=?,phoneNumber=?,country=?WHERE funder_ID=?";
		 	PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, fName);
		 preparedStmt.setInt(2, Integer.parseInt(PhNumber));
		 preparedStmt.setString(3, fCountry);
		 preparedStmt.setInt(4, Integer.parseInt(funderID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the Funder.";
			 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteFunder(String funder_ID)
		 {
			String output = "";
		 try
		 {
			 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from funders where funder_ID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(funder_ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while deleting the funder.";
			 System.err.println(e.getMessage());
		 }
		 return output;
		 }

}
