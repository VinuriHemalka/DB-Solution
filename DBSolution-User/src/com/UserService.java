
	package com;
	import model.User;
	import javax.ws.rs.Consumes;
	import javax.ws.rs.DELETE;
	import javax.ws.rs.FormParam;
	import javax.ws.rs.GET;
	import javax.ws.rs.POST;
	import javax.ws.rs.PUT;
	import javax.ws.rs.Path;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;
	import org.jsoup.parser.Parser;
	import com.google.gson.JsonObject;
	import com.google.gson.JsonParser;
	//For REST Service
	import javax.ws.rs.*;
	import javax.ws.rs.core.MediaType;
	//For JSON
	import com.google.gson.*;
	//For XML
	import org.jsoup.*;
	import org.jsoup.parser.*;
	import org.jsoup.nodes.Document;




	@Path("/Users")
	public class UserService
	{
		User userObj = new User();
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readUsers()
		{
			return userObj.readUsers();
		}



		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertUser(
				@FormParam("user_Name") String user_Name, 
				@FormParam("password") String password, 
				@FormParam("userType") String userType) 
		
		{ 
			String output = userObj.insertUser(user_Name, password, userType ); 
			return output; 
		}


		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateUser(String userData) 
		{ 
			
			//Convert the input string to a JSON object 
			JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
			//Read the values from the JSON object
			String user_ID = userObject.get("user_ID").getAsString(); 
			String user_Name = userObject.get("user_Name").getAsString(); 
			String password = userObject.get("password").getAsString(); 
			String userType = userObject.get("userType").getAsString(); 
			
			String output = userObj.updateUser(user_ID, user_Name, password, userType); 
			return output; 
		}


		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteUser(String userData) 
		{ 
			
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
		 
			//Read the value from the element <user_ID>
			String user_ID = doc.select("user_ID").text(); 
			String output = userObj.deleteUser(user_ID); 
		 	return output; 
		}
	}