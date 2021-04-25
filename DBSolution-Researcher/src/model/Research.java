package model;
import java.sql.*;


//A common method to connect to the DB
	public class Research
	{ 
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/dbsolution-reseracher", "root", "root");
			}
            catch (Exception e)
				{e.printStackTrace();}
				return con;
			}
		
		
			public String insertResearcher(String Rename, String category, String phoneNumber,  String email, String address, String country)
			{
				String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for inserting."; 
					}
 
 
					// create a prepared statement
					String query = " insert into researcher(`Researcher_ID`,`Reseracher_name`,`Category`,`PhoneNumber`,`Emai`,`Address`,`Country` )" + " values (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, Rename);
					preparedStmt.setString(3, category);
					preparedStmt.setInt(4,Integer.parseInt( phoneNumber));
					preparedStmt.setString(5, email);
					preparedStmt.setString(6, address);
					preparedStmt.setString(7, country);
 
 
 
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
				}
				catch (Exception e)
				{
					output = "Error while inserting the researcher.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
			//Read Researchers	
			public String readResearchers()
			{
				String output = "";
				
				try
				{
					Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for reading."; 
					}
 
 
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Reasearcher Name</th><th>Category</th>" +
							"<th>Phone Number</th>" +
							"<th>Email</th>" + "<th>Address</th>" + "<th>Country</th>" +
							"<th>Update</th><th>Remove</th></tr>";

					String query = "select * from researcher";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
 
 
					// iterate through the rows in the result sets
					
					while (rs.next())
					{
						String Researcher_ID = Integer.toString(rs.getInt("Researcher_ID"));
						String Reseracher_name = rs.getString("Reseracher_name");
						String Category = rs.getString("Category");
						String PhoneNumber =Integer.toString(rs.getInt("PhoneNumber"));
						String Emai = rs.getString("Emai");
						String Address = rs.getString("Address");
						String Country = rs.getString("Country");
 
 
						// Add into the html table
						output += "<tr><td>" + Reseracher_name + "</td>";
						output += "<td>" + Category + "</td>";
						output += "<td>" + PhoneNumber + "</td>";
						output += "<td>" + Emai + "</td>";
						output += "<td>" + Address + "</td>";
						output += "<td>" + Country + "</td>";
 
 
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
								+ "<input name='Researcher_ID' type='hidden' value='" + Researcher_ID
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
			
			//updateResearcher			
			public String updateResearcher(String ResearachID, String Rename, String category, String phoneNumber,  String email, String address, String country)
			{
				String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for updating."; 
						}
	 
					// create a prepared statement
					String query = "UPDATE researcher SET Reseracher_name=?,Category=?,PhoneNumber=?,Emai=?,Address=?,Country=?WHERE Researcher_ID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
	 
					// binding values
					preparedStmt.setString(1, Rename);
					preparedStmt.setString(2, category);
					preparedStmt.setInt(3, Integer.parseInt(phoneNumber));
					preparedStmt.setString(4, email);
					preparedStmt.setString(5, address);
					preparedStmt.setString(6, country);
					preparedStmt.setInt(7,Integer.parseInt (ResearachID));
	 
	 
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Updated successfully";
				}
				catch (Exception e)
				{
					output = "Error while updating the Researcher.";
					System.err.println(e.getMessage());
				}
				return output;
			    }
			
			//deleteResearcher		
			public String deleteResearcher(String researcher_ID)
			{
				String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for deleting."; 
				}
	 
	 
					// create a prepared statement
					String query = "delete from researcher where Researcher_ID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
	 
					// binding values
					preparedStmt.setInt(1,Integer.parseInt (researcher_ID));
	 
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the researcher.";
				System.err.println(e.getMessage());
			}
			return output;
			}
	} 