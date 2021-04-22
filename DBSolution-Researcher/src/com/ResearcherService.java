package com;
import model.Research;
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




@Path("/Researchers")
public class ResearcherService
{
	Research researcherObj = new Research();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearchers()
	{
		return researcherObj.readResearchers();
	}



	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertResearcher(
			@FormParam("Reseracher_name") String Reseracher_name, 
			@FormParam("Category") String Category, 
			@FormParam("PhoneNumber") String PhoneNumber, 
			@FormParam("Emai") String Emai, 
			@FormParam("Address") String Address, 
			@FormParam("Country") String Country) 
	
	{ 
		String output = researcherObj.insertResearcher(Reseracher_name, Category, PhoneNumber, Emai, Address, Country ); 
		return output; 
	}


	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateResearcher(String researchData) 
	{ 
		
		//Convert the input string to a JSON object 
		JsonObject researcherObject = new JsonParser().parse(researchData).getAsJsonObject(); 
		//Read the values from the JSON object
		String Researcher_ID = researcherObject.get("Researcher_ID").getAsString(); 
		String Reseracher_name = researcherObject.get("Reseracher_name").getAsString(); 
		String Category = researcherObject.get("Category").getAsString(); 
		String PhoneNumber = researcherObject.get("PhoneNumber").getAsString(); 
		String Emai = researcherObject.get("Emai").getAsString(); 
		String Address = researcherObject.get("Address").getAsString(); 
		String Country = researcherObject.get("Country").getAsString(); 
		
		String output = researcherObj.updateResearcher(Researcher_ID, Reseracher_name, Category, PhoneNumber, Emai, Address, Country); 
		return output; 
	}


	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteResearcher(String researchData) 
	{ 
		
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(researchData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <Researcher_ID>
		String Researcher_ID = doc.select("Researcher_ID").text(); 
		String output = researcherObj.deleteResearcher(Researcher_ID); 
	 	return output; 
	}
}