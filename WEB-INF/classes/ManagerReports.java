import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ManageReports")

public class ManageReports extends HttpServlet {

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
		pw.print("<h2 class='title meta' style='text-align:center; padding-top:20px;'><a style='font-size:20px; color:white;'>Inventory Reports</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; height:500px; margin:25px; margin-left: auto;margin-right: auto;'>");
		pw.print("<p style='font-size:14px; color:white; text-align:center;'><a href='Inventory1' style='color:white;'>Inventory Table of all products</a></p>");
		pw.print("<p style='font-size:14px; color:white; text-align:center;'><a href='Inventory2' style='color:white;'>Inventory Bar Chart</a></p>");
		pw.print("<p style='font-size:14px; color:white; text-align:center;'><a href='Inventory3' style='color:white;'>Products Table of all on sale</a></p>");
		pw.print("<p style='font-size:14px; color:white; text-align:center;'><a href='Inventory4' style='color:white;'>Products Table of all manufacturer rebates</a></p>");
		pw.print("<h2 class='title meta' style='text-align:center; padding-top:20px;'><a style='font-size:20px; color:white;'>Sales Reports</a></h2>");
		pw.print("<p style='font-size:14px; color:white; text-align:center;'><a href='SalesReport1' style='color:white;'>Sales Table of all products sold</a></p>");
		pw.print("<p style='font-size:14px; color:white; text-align:center;'><a href='SalesReport2' style='color:white;'>Sales Bar Chart</a></p>");
		pw.print("<p style='font-size:14px; color:white; text-align:center;'><a href='SalesReport3' style='color:white;'>Sales Table of daily transactions/a></p>");		
		pw.print("</div></div>");
		utility.printHtml("Footer.html");
	}
}