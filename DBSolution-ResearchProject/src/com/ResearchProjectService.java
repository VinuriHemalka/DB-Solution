package com;
import model.ResearchProject;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Projects")

public class ResearchProjectService
{
	ResearchProject projectObj = new ResearchProject();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)

	public String readProjects()
	{

		return projectObj.readProjects();
	}


 @POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)

 	public String insertProject(@FormParam("project_Name") String project_Name,
		 @FormParam("cost") String cost,
		 @FormParam("project_Category") String project_Category,
		 @FormParam("description") String description)

 	{
	 	String output = projectObj.insertProject(project_Name, cost, project_Category, description);

	 	return output;
 	}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)

	public String updateProjects(String researchData)
	{

		//Convert the input string to a JSON object
		JsonObject projectObject = new JsonParser().parse(researchData).getAsJsonObject();

		//Read the values from the JSON object
		String project_ID = projectObject.get("project_ID").getAsString();
		String project_Name = projectObject.get("project_Name").getAsString();
		String cost = projectObject.get("cost").getAsString();
		String project_Category = projectObject.get("project_Category").getAsString();
		String description = projectObject.get("description").getAsString();

		String output = projectObj.updateProjects(project_ID, project_Name, cost, project_Category, description);
	
		return output;
	}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
	public String deleteProjects(String projectData)
	{

		//Convert the input string to an XML document
		Document doc = Jsoup.parse(projectData, "", Parser.xmlParser());

		//Read the value from the element <project_ID>
		String project_ID = doc.select("project_ID").text();
		String output = projectObj.deleteProjects(project_ID);

		return output;
	}

}
