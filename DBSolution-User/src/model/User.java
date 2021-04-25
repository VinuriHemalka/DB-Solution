
	package model;
	import java.sql.*;


	//A common method to connect to the DB
		public class User
		{ 
			private Connection connect()
			{
				Connection con = null;
				try
				{
					Class.forName("com.mysql.jdbc.Driver");

					//Provide the correct details: DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/dbsolution-user", "root", "root");
				}
	            catch (Exception e)
					{e.printStackTrace();}
					return con;
				}
			
			
				public String insertUser(String uName, String Password, String uType)
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
						String query = " insert into user(`user_ID`,`user_Name`,`password`,`userType` )" + " values (?, ?, ?, ?)";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						// binding values
						preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, uName);
						preparedStmt.setString(3, Password);
						preparedStmt.setString(4, uType);
						
	 
						// execute the statement
						preparedStmt.execute();
						con.close();
						output = "Inserted successfully";
					}
					catch (Exception e)
					{
						output = "Error while inserting the user.";
						System.err.println(e.getMessage());
					}
					return output;
				}
				
				//Read Researchers	
				public String readUsers()
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
						output = "<table border='1'><tr><th>User Name</th>" +
								"<th>Password</th>" +
								"<th>User Type</th>" + 
								"<th>Update</th><th>Remove</th></tr>";

						String query = "select * from user";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
	 
	 
						// iterate through the rows in the result set
						while (rs.next())
						{
							String user_ID = Integer.toString(rs.getInt("user_ID"));
							String user_Name = rs.getString("user_Name");
							String password = rs.getString("password");
							String userType = rs.getString("userType");
					
	 
							// Add into the html table
							output += "<tr><td>" + user_Name + "</td>";
							output += "<td>" + password + "</td>";
							output += "<td>" + userType + "</td>";
						
	 
	 
							// buttons
							output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
									+ "<input name='user_ID' type='hidden' value='" + user_ID
									+ "'>" + "</form></td></tr>";
						}
						con.close();
	 
						// Complete the html table
						output += "</table>";
					}
					catch (Exception e)
					{
						output = "Error while reading the users.";
						System.err.println(e.getMessage());
					}
					return output;
				}
				
				//updateResearcher			
				public String updateUser(String uID, String uName, String Password, String uType)
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
						String query = "UPDATE user SET user_Name=?,password=?,userType=?WHERE user_ID=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
		 
						// binding values
						preparedStmt.setString(1, uName);
						preparedStmt.setString(2, Password);
						preparedStmt.setString(3, uType);
						preparedStmt.setInt(4,Integer.parseInt (uID));
		 
		 
						// execute the statement
						preparedStmt.execute();
						con.close();
						output = "Updated successfully";
					}
					catch (Exception e)
					{
						output = "Error while updating the User.";
						System.err.println(e.getMessage());
					}
					return output;
				    }
				
				//deleteResearcher		
				public String deleteUser(String user_ID)
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
						String query = "delete from user where user_ID=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
		 
						// binding values
						preparedStmt.setInt(1,Integer.parseInt (user_ID));
		 
						// execute the statement
						preparedStmt.execute();
						con.close();
						output = "Deleted successfully";
				}
				catch (Exception e)
				{
					output = "Error while deleting the user.";
					System.err.println(e.getMessage());
				}
				return output;
				}
		} 