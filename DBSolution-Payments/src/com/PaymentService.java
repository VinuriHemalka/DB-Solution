package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import model.Payment;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




		
	@Path("/Payments")
		public class PaymentService
		{
		Payment PaymentObj = new Payment();

		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readPayments()
		{
		return PaymentObj.readPayments();
		}


		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(
		@FormParam("payType") String payType,
		@FormParam("payAmount") String payAmount,
		@FormParam("payDesc") String payDesc)

		{
		String output = PaymentObj.insertPayment(payType, payAmount, payDesc );
		return output;
		}


		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String PaymentData)
		{

		//Convert the input string to a JSON object
		JsonObject PaymentObject = new JsonParser().parse(PaymentData).getAsJsonObject();
		//Read the values from the JSON object
		String payID = PaymentObject.get("payID").getAsString();
		String payType = PaymentObject.get("payType").getAsString();
		String payAmount = PaymentObject.get("payAmount").getAsString();
		String payDesc = PaymentObject.get("payDesc").getAsString();

		String output = PaymentObj.updatePayment(payID, payType, payAmount, payDesc);
		return output;
		}


		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String PaymentData)
		{

		//Convert the input string to an XML document
		Document doc = Jsoup.parse(PaymentData, "", Parser.xmlParser());

		//Read the value from the element <Payment_ID>
		String payID = doc.select("payID").text();
		String output = PaymentObj.deletePayment(payID);
		return output;
		}

	 }




