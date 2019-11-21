import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			
			String msg = "good";
			String producttype= "",productId="",productName="",productImage="",productManufacturer="",productCondition="",prod = "";
			double productPrice=0.0,productDiscount = 0.0;
			HashMap<String,Tv> alltvs = new HashMap<String,Tv>();
			HashMap<String,SoundSystem> allsounds = new HashMap<String,SoundSystem>();
			HashMap<String,Phone> allphones = new HashMap<String,Phone>();
			HashMap<String,Laptop> alllaptops = new HashMap<String, Laptop>();
			HashMap<String, VoiceAssistant> allvoices = new HashMap<String, VoiceAssistant>();
			HashMap<String, FitnessWatch> allfitnesses = new HashMap<String, FitnessWatch>();
			HashMap<String, SmartWatch> allsmarts = new HashMap<String, SmartWatch>();
			HashMap<String, Headphones> allheadphones = new HashMap<String, Headphones>();
			HashMap<String, WirelessPlan> allwireless = new HashMap<String, WirelessPlan>();
			HashMap<String,Accessory> allaccessories = new HashMap<String,Accessory>();

			if (action.equals("add") || action.equals("update"))
			{	
				 producttype = request.getParameter("producttype");
				 productId   = request.getParameter("productId");
				 productName = request.getParameter("productName"); 
				 productPrice = Double.parseDouble(request.getParameter("productPrice"));
				 productImage = request.getParameter("productImage");
				 productManufacturer = request.getParameter("productManufacturer");
				 productCondition = request.getParameter("productCondition");
				 productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
				 
			}
			else{
				productId   = request.getParameter("productId");
			}	
			utility.printHtml("Header.html");
			utility.printHtml("Sidebar.html");

			if(action.equals("add"))
			{
			  	  if(producttype.equals("tvs")){
				  //check this to see if getTvs works 
				  alltvs = MySqlDataStoreUtilities.getTvs();
				  if(alltvs.containsKey(productId)){
					  msg = "Product already available.";		  
				  }	  
			  }
			  else if(producttype.equals("sounds"))
			  {
				  //check this to see if getSounds works
				  allsounds = MySqlDataStoreUtilities.getSounds();
				  if(allsounds.containsKey(productId)){
					  msg = "Product already available.";
				  }
			  }
			  else if (producttype.equals("phones"))
			  {
				  //check this to see if getPhones works
				  allphones = MySqlDataStoreUtilities.getPhones();
				  if(allphones.containsKey(productId)){
					  msg = "Product already available.";
				  }
			  }
			  else if (producttype.equals("laptops"))
			  {
				  //check this to see if getLaptops works
				  alllaptops = MySqlDataStoreUtilities.getLaptops();
				  if(alllaptops.containsKey(productId)){
					  msg = "Product already available.";
				  }
			  }
			  else if (producttype.equals("voices"))
			  {
				  //check this to see if getVoices works
				  allvoices = MySqlDataStoreUtilities.getVoices();
				  if(allvoices.containsKey(productId)){
					  msg = "Product already available.";
				  }
			  }
			  else if (producttype.equals("fitnesses"))
			  {
				  //check this to see if getFitnesses works
				  allfitnesses = MySqlDataStoreUtilities.getFitnesses();
				  if(allfitnesses.containsKey(productId)){
					  msg = "Product already available.";
				  }
			  }
			  else if (producttype.equals("smarts"))
			  {
				  //check this to see if getSmarts works
				  allsmarts = MySqlDataStoreUtilities.getSmarts();
				  if(allsmarts.containsKey(productId)){
					  msg = "Product already available.";
				  }
			  }
			  else if (producttype.equals("headphones"))
			  {
				  //check this to see if getHeadphones works
				  allheadphones = MySqlDataStoreUtilities.getHeadphones();
				  if(allheadphones.containsKey(productId)){
					  msg = "Product already available.";
				  }
			  }
			  else if (producttype.equals("wireless"))
			  {
				  //check this to see if getWireless works
				  allwireless = MySqlDataStoreUtilities.getWireless();
				  if(allwireless.containsKey(productId)){
					  msg = "Product already available.";
				  }
			  }
			  else if (producttype.equals("accessories"))
			  {  
					if(!request.getParameter("product").isEmpty())
						{
							prod = request.getParameter("product");
							alltvs = MySqlDataStoreUtilities.getTvs();
							if(alltvs.containsKey(prod))
							{
								allaccessories = MySqlDataStoreUtilities.getAccessories();
								if(allaccessories.containsKey(productId)){
									msg = "Product already available.";
								}
							}else{
								msg = "The product related to accessories is not available.";
							}
						
						
						}else{
							msg = "Please add the prodcut name.";
						}
				  
			  }	
			  if (msg.equals("good"))
			  {  
				  try
				  {
					  msg = MySqlDataStoreUtilities.addproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,prod);
				  }
				  catch(Exception e)
				  { 
					msg = "Product cannot be inserted.";
				  }
				  msg = "Product has been successfully added.";
			  }					
			}else if(action.equals("update"))
			{
				
			  if(producttype.equals("tvs")){
				  alltvs = MySqlDataStoreUtilities.getTvs();
				  if(!alltvs.containsKey(productId)){
					  msg = "P roduct not available.";
				  }
					  
			  }
			  else if(producttype.equals("sounds"))
			  {
				  allsounds = MySqlDataStoreUtilities.getSounds();
				  if(!allsounds.containsKey(productId)){
					  msg = "Product not available.";
				  }
			  }
			  else if (producttype.equals("phones"))
			  {
				  allphones = MySqlDataStoreUtilities.getPhones();
				  if(!allphones.containsKey(productId)){
					  msg = "Product not available.";
				  }
			  }
			  else if (producttype.equals("laptops"))
			  {
				  alllaptops = MySqlDataStoreUtilities.getLaptops();
				  if(!alllaptops.containsKey(productId)){
					  msg = "Product not available.";
				  }
			  }
			  else if (producttype.equals("voices"))
			  {
				  allvoices = MySqlDataStoreUtilities.getVoices();
				  if(!allvoices.containsKey(productId)){
					  msg = "Product not available.";
				  }
			  }
			  else if (producttype.equals("fitnesses"))
			  {
				  allfitnesses = MySqlDataStoreUtilities.getFitnesses();
				  if(!allfitnesses.containsKey(productId)){
					  msg = "Product not available.";
				  }
			  }
			  else if (producttype.equals("smarts"))
			  {
				  allsmarts= MySqlDataStoreUtilities.getSmarts();
				  if(!allsmarts.containsKey(productId)){
					  msg = "Product not available.";
				  }
			  }
			  else if (producttype.equals("headphones"))
			  {
				  allheadphones = MySqlDataStoreUtilities.getHeadphones();
				  if(!allheadphones.containsKey(productId)){
					  msg = "Product not available.";
				  }
			  }
			   else if (producttype.equals("wirelesses"))
			  {
				  HashMap<String, WirelessPlan> allwireless = MySqlDataStoreUtilities.getWireless();
				  if(!allwireless.containsKey(productId)){
					  msg = "Product not available.";
				  }
			  }
			  else if (producttype.equals("accessories"))
			  {
				  allaccessories = MySqlDataStoreUtilities.getAccessories();
				  if(!allaccessories.containsKey(productId)){
					  msg = "Product not available.";
				}
			  }	
			  if (msg.equals("good"))
			  {		
				
				  try
				  {
					msg = MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount);
				  }
				  catch(Exception e)
				  { 
					msg = "Product cannot be updated.";
				  }
				  msg = "Product has been successfully updated.";
			  } 
			}else if(action.equals("delete"))
			{
				  msg = "bad";
				  alltvs = MySqlDataStoreUtilities.getTvs();
				  if(alltvs.containsKey(productId)){
					  msg = "good";	 
				  }
					  
				  allsounds = MySqlDataStoreUtilities.getSounds();
				  if(allsounds.containsKey(productId)){
					  msg = "good";
				  }
			  
				  allphones = MySqlDataStoreUtilities.getPhones();
				  if(allphones.containsKey(productId)){
					  msg = "good";
				  }

				  alllaptops= MySqlDataStoreUtilities.getLaptops();
				  if(alllaptops.containsKey(productId)){
					  msg = "good";
				  }

				  allvoices = MySqlDataStoreUtilities.getVoices();
				  if(allvoices.containsKey(productId)){
					  msg = "good";
				  }

				  allfitnesses = MySqlDataStoreUtilities.getFitnesses();
				  if(allfitnesses.containsKey(productId)){
					  msg = "good";
				  }

				  allsmarts = MySqlDataStoreUtilities.getSmarts();
				  if(allsmarts.containsKey(productId)){
					  msg = "good";
				  }
			  
				  allheadphones = MySqlDataStoreUtilities.getHeadphones();
				  if(allheadphones.containsKey(productId)){
					  msg = "good";
				  }

				  allwireless = MySqlDataStoreUtilities.getWireless();
				  if(allwireless.containsKey(productId)){
					  msg = "good";
				  }

				  allaccessories = MySqlDataStoreUtilities.getAccessories();
				  if(allaccessories.containsKey(productId)){
					  msg = "good";
				}
		       		
				  if (msg.equals("good"))
				  {		
					
					  try
					  {  
						 msg = MySqlDataStoreUtilities.deleteproducts(productId);
					  }
					  catch(Exception e)
					  { 
						msg = "Product cannot be deleted.";
					  }
					   msg = "Product has been successfully deleted.";
				  }else{
					  msg = "Product not available.";
				  }
			}	
				
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
			
	}
}