import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SalesReport1")

public class SalesReport1 extends HttpServlet {

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

		utility.printHtml("Header.html");
		HashMap<String,Product> pMap = MySqlDataStoreUtilities.getSales();

		pw.println("<table style=\"font-size:12px; width:100%;color:#4076AB\">");					
					pw.println("<tr>");
					pw.println("<th align=\"center\" width=\"15%\">");
					pw.println("<span style=\"font-size:12px;font-weight: bold\">Product</span>");
					pw.println("</th>");
					pw.println("<th align=\"center\" width=\"5%\">");
					pw.println("<span style=\"font-size:12px;font-weight: bold\">Price</span>");
					pw.println("</th>");
					pw.println("<th align=\"center\" width=\"10%\">");
					pw.println("<span style=\"font-size:12px;font-weight: bold\">Item Sold</span>");
					pw.println("</th>");
					pw.println("<th align=\"center\" width=\"15%\">");
					pw.println("<span style=\"font-size:12px;font-weight: bold\">Total Sales</span>");
					pw.println("</th>");
					pw.println("</tr>");
					pw.println("</table>");
					if(pMap != null){
						if(pMap.isEmpty()){
							pw.println("<br/>");
							pw.println("<br/>");
							pw.println("<h3 style=\"font-size:12px;font-weight: bold\">Not enough information. Try again.</h3>");
						}
						else{
							for (Map.Entry<String,Product> entry : pMap.entrySet()) {
								Product product = entry.getValue();
								pw.println("<table style=\"font-size:12px; width:100%;color:#4076AB\">");			    
								pw.println("<tr>");
								pw.println("<td  width=\"15%\">");
								pw.println("<span style=\"font-size:12px;font-weight: bold\">"+product.getName()+"</span>");				   
								pw.println("</td>");
								 pw.println("<td align=\"center\" width=\"5%\">");	
								pw.println("<span style=\"font-size:12px;font-weight: bold\">"+product.getPrice()+"</span>");						
								pw.println("</td>");
								pw.println("<td align=\"center\" width=\"10%\">");	
								pw.println("<span style=\"font-size:12px;font-weight: bold\">"+product.getProductCount()+"</span>");						
								pw.println("</td>");							
								pw.println("<td align=\"center\" width=\"15%\">");	
								pw.println("<span style=\"font-size:12px;font-weight: bold\">"+product.getTotal()+"</span>");						
								pw.println("</td>");							
								pw.println("</tr>");               
								pw.println("</table>");
							}
						}
					}
					else{
                        //e.printStackTrace();
						pw.println("<br/>");
						pw.println("<br/>");
						pw.println("<h3>SQL server is not running. Try again.</h3>");
					}									
					pw.println("</div >");				

		utility.printHtml("Footer.html");
        }
}