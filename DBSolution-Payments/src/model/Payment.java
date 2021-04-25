package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	private Connection connect() 
	 { 
		Connection con = null; 
		try
	 { 
			Class.forName("com.mysql.jdbc.Driver"); 
	 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbsolution-payment", "root", "root"); 
	 } 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	 	} 
		public String insertPayment(String payType, String payAmount, String payDesc) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for inserting."; } 
				// create a prepared statement
				String query = " insert into payments(`payID`,`payType`,`payAmount`,`payDesc` )"+ " values (?, ?, ?, ?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, payType); 
				preparedStmt.setDouble(3, Double.parseDouble(payAmount)); 
				preparedStmt.setString(4, payDesc); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Inserted successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while inserting the payment."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
	public String readPayments() 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Payment ID</th><th>Payment Type</th><th>Payment Amount</th>" +
					"<th>Payment Description</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from payments"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String payID = rs.getString("payID");
				String payType = rs.getString("payType"); 
				String payAmount = Double.toString(rs.getDouble("payAmount")); 
				String payDesc = rs.getString("payDesc"); 
				// Add into the html table
				output += "<tr><td>" + payID + "</td>"; 
				output += "<td>" + payType + "</td>";
				output += "<td>" + payAmount + "</td>"; 
				output += "<td>" + payDesc + "</td>"; 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='payments.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
								+ "<input name='payID' type='hidden' value='" + payID 
								+ "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the payments."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 	} 
		public String updatePayment(String payId, String payType, String payAmount, String payDesc)
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for updating."; } 
				// create a prepared statement
				String query = "UPDATE payments SET payType=?,payAmount=?,payDesc=? WHERE payID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				
				preparedStmt.setString(1, payType); 
				preparedStmt.setDouble(2, Double.parseDouble(payAmount)); 
				preparedStmt.setString(3, payDesc); 
				preparedStmt.setInt(4, Integer.parseInt(payId)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Updated successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while updating the payment."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		 } 
			public String deletePayment(String payID) 
			{ 
				String output = ""; 
				try
				{ 
					Connection con = connect(); 
					if (con == null) 
					{return "Error while connecting to the database for deleting."; } 
					// create a prepared statement
					String query = "delete from payments where payID=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(payID)); 
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					output = "Deleted successfully"; 
				} 
				catch (Exception e) 
				{ 
					output = "Error while deleting the payment."; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
			} 
		

}

