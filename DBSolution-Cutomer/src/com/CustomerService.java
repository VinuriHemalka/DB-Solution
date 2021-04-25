package com;

import model.Customer;
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






@Path("/Customers")
public class CustomerService
{
	Customer customerObj = new Customer();

@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readCustomer()
{
return customerObj.readCustomer();
}





@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertCustomer(
@FormParam("customer_name") String customer_name,
@FormParam("email") String email,
@FormParam("phone_number") String phone_number,
@FormParam("country") String country)

{
String output = customerObj.insertCustomer(customer_name, email, phone_number, country );
return output;
}




@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateCustomer(String customerData)
{

//Convert the input string to a JSON object
JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
//Read the values from the JSON object
String customer_id = customerObject.get("customer_id").getAsString();
String customer_name = customerObject.get("customer_name").getAsString();
String email = customerObject.get("email").getAsString();
String phone_number = customerObject.get("phone_number").getAsString();
String country = customerObject.get("country").getAsString();

String output = customerObj.updateCustomer(customer_id, customer_name, email, phone_number, country);
return output;
}




@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteCustomer(String customerData)
{

//Convert the input string to an XML document
Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

//Read the value from the element <Researcher_ID>
String customer_id = doc.select("customer_id").text();
String output = customerObj.deleteCustomer(customer_id);
return output;
}
}


