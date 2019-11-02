import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Inventory1")

public class Inventory1 extends HttpServlet {

	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		displayLogin(request, response, pw, true);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}

	protected void displayLogin(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		HashMap<String,Product> pMap = MySqlDataStoreUtilities.getProductsInventory();

		utility.printHtml("Header.html");
		pw.print("<table style=\"font-size:12px; width:80%;color:#4076AB\">");
					pw.print("<tr>");
					pw.print("<h1 style=\"color: #7D7D7D;border-bottom: 2px solid #7D7D7D;\">Inventory: product details</h1>");
					pw.print("</td>");
					pw.print("</table>");
					pw.print("<br/>");
					pw.print("<br/>");
					pw.print("<div >");
					pw.print("<table style=\"font-size:12px; width:80%;color:#4076AB\">");					
					pw.print("<tr>");
					pw.print("<th align=\"center\" width=\"50%\">");
					pw.print("<span style=\"font-size:12px;font-weight: bold\">Product</span>");
					pw.print("</th>");
					pw.print("<th align=\"center\" width=\"50%\">");
					pw.print("<span style=\"font-size:12px;font-weight: bold\">Price</span>");
					pw.print("</th>");
					pw.print("<th align=\"center\" width=\"50%\">");
					pw.print("<span style=\"font-size:12px;font-weight: bold\">Item Count</span>");
					pw.print("</th>");
					pw.print("</tr>");
					pw.print("</table>");
						
					System.out.println(pMap);
					if(pMap != null){
						if(pMap.isEmpty()){
							pw.print("<br/>");
							pw.print("<br/>");
							pw.print("<h3 style=\"font-size:12px;font-weight:italics\">Not enough information. Try again.</h3>");
						}
						else{
							for (HashMap.Entry<String,Product> entry : pMap.entrySet()) {
								Product product = entry.getValue();
								pw.print("<table style=\"font-size:12px; width:80%;color:#4076AB\">");			    
								pw.print("<tr>");
								pw.print("<td  width=\"50%\">");
								pw.print("<span style=\"font-size:12px;font-weight:bold\">"+product.getName()+"</span>");				   
								pw.print("</td>");
								 pw.print("<td align=\"center\" width=\"50%\">");	
								pw.print("<span style=\"font-size:12px;font-weight:bold\">"+product.getPrice()+"</span>");						
								pw.print("</td>");
								pw.print("<td align=\"center\" width=\"50%\">");	
								pw.print("<span style=\"font-size:12px;font-weight:bold\">"+product.getProductCount()+"</span>");						
								pw.print("</td>");								
								pw.print("</tr>");               
								pw.print("</table>");
							}
						}
					}
					else{
						e.printStackTrace();
						pw.print("<br/>");
						pw.print("<br/>");
						pw.print("<h3>SQL server is not running. Try again.</h3>");
					}									
					pw.print("</div >");				
		utility.printHtml("Footer.html");
	}
}
