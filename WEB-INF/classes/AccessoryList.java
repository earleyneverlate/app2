import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AccessoryList")

public class AccessoryList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		//String name = null;
		//String CategoryName = request.getParameter("maker");
        
		String CategoryName = request.getParameter("maker");
		HashMap<String,Accessory> allaccessories = new HashMap<String,Accessory> ();
        HashMap<String,Tv> alltvs = new HashMap<String,Tv> ();

		try{
		     alltvs = MySqlDataStoreUtilities.getTvs();
		}
		catch(Exception e)
		{
			
		}
		try{
		     allaccessories = MySqlDataStoreUtilities.getAccessories();
		}
		catch(Exception e)
		{

		}	

		HashMap<String, Tv> hm = new HashMap<String, Tv>();	
		   if(CategoryName.equalsIgnoreCase("samsung"))
		   {
			 for(Map.Entry<String,Tv> entry : alltvs.entrySet())
			 {
				 if(entry.getValue().getRetailer().equalsIgnoreCase("Samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
		   }
		   else if(CategoryName.equalsIgnoreCase("lcl"))
		   {
			   for(Map.Entry<String,Tv> entry : alltvs.entrySet())
			   {
				   if(entry.getValue().getRetailer().equalsIgnoreCase("LCL"))
				   {
					   hm.put(entry.getValue().getId(),entry.getValue());
				   }
			   }
			}
			else if(CategoryName.equalsIgnoreCase("lg"))
		    {
				for(Map.Entry<String,Tv> entry : alltvs.entrySet())
				{
					if(entry.getValue().getRetailer().equalsIgnoreCase("LG"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
			}
			else if(CategoryName.equalsIgnoreCase("sony"))
		    {
				for(Map.Entry<String,Tv> entry : alltvs.entrySet())
				{
					if(entry.getValue().getRetailer().equalsIgnoreCase("Sony"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
			}
		
		/* Header, Left Navigation Bar are Printed.

		All the Tv and TV information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("Sidebar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+ CategoryName +": Accessories</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; 
		int size= 2;
		for(Map.Entry<String, Tv> entry : hm.entrySet())
		{
			Tv tv = entry.getValue();
			for(Map.Entry<String, String> acc:tv.getAccessories().entrySet())
			{
	        
				Accessory accessory = allaccessories.get(acc.getValue());
				if(i%2==1) pw.print("<tr>");
				System.out.print(size);
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+accessory.getName()+"</h3>");
				pw.print("<strong>"+accessory.getPrice()+"$</strong><ul>");
				pw.print("<li id='item'><img src='images/accessories_images/"+accessory.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+acc.getValue()+"'>"+
					"<input type='hidden' name='type' value='accessories'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value='"+tv.getName()+"'>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+acc+"'>"+
					"<input type='hidden' name='type' value='accessories'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value='"+tv.getName()+"'>"+
					"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+acc+"'>"+
					"<input type='hidden' name='type' value='accessories'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value='"+tv.getName()+"'>"+
					"<input type='submit' value='ViewReview' class='btnreview'></form></li>");
	
				pw.print("</ul></div></td>");
				if(i%2==0 || i == size) pw.print("</tr>");
				i++;
		
			}	
		}	
		pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
	}
}