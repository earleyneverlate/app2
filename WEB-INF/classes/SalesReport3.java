import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SalesReport3")

public class SalesReport3 extends HttpServlet {

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
		HashMap<String,Product> pMap = MySqlDataStoreUtilities.getDailySales();

		utility.printHtml("Header.html");
		pw.println("<table style=\"font-size:12px; width:80%;color:#4076AB\">");
					pw.println("<tr>");
					pw.println("<h1 style=\"color: #7D7D7D;border-bottom: 2px solid #7D7D7D;\">Sales Table: all products sold</h1>");
					pw.println("</td>");
					pw.println("</table>");
					pw.println("<br/>");
					pw.println("<br/>");
					pw.println("<div >");
					pw.println("<table style=\"font-size:12px; width:80%;color:#4076AB\">");					
					pw.println("<tr>");
					pw.println("<th align=\"center\" width=\"50%\">");
					pw.println("<span style=\"font-size:12px;font-weight: bold\">Order Date</span>");
					pw.println("</th>");
					pw.println("<th align=\"center\" width=\"50%\">");
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
								pw.println("<table style=\"font-size:12px; width:80%;color:#4076AB\">");			    
								pw.println("<tr>");
								pw.println("<td  width=\"50%\">");
								pw.println("<span style=\"font-size:12px;font-weight: bold\">"+product.getOrderDate()+"</span>");				   
								pw.println("</td>");	
                                pw.println("<td align=\"center\" width=\"50%\">");	
								pw.println("<span style=\"font-size:12px;font-weight: bold\">"+product.getTotal()+"</span>");						
								pw.println("</td>");								
								pw.println("</tr>");               
								pw.println("</table>");
							}
						}
					}
					else{
						pw.println("<br/>");
						pw.println("<br/>");
						pw.println("<h3>SQL server is not running. Try again.</h3>");
                    }								
					pw.println("</div >");				
		utility.printHtml("Footer.html");
	}
}