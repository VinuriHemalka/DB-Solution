package com;

import model.Funder;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Funders")

public class funderService {
	Funder funderObj = new Funder();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunders()
	 {
	  return funderObj.readFunders();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFunder(
	@FormParam("funderName") String funderName,
	@FormParam("phoneNumber") String phoneNumber,
	@FormParam("country") String country
	)

	{
	String output = funderObj.insertFunder(funderName, phoneNumber, country);
	return output;
	}




	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFunder(String researchData)
	{

	//Convert the input string to a JSON object
	JsonObject researcherObject = new JsonParser().parse(researchData).getAsJsonObject();
	//Read the values from the JSON object
	String funder_ID = researcherObject.get("funder_ID").getAsString();
	String funderName = researcherObject.get("funderName").getAsString();
	String phoneNumber = researcherObject.get("phoneNumber").getAsString();
	String country = researcherObject.get("country").getAsString();

	String output = funderObj.updateFunder(funder_ID, funderName, phoneNumber, country);
	return output;
	}




	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFunder(String researchData)
	{

	//Convert the input string to an XML document
	Document doc = Jsoup.parse(researchData, "", Parser.xmlParser());

	//Read the value from the element <funder_ID>
	String funder_ID = doc.select("funder_ID").text();
	String output = funderObj.deleteFunder(funder_ID);
	return output;
	}

}
