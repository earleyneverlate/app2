import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ManagerHome")

public class ManagerHome extends HttpServlet {
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
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<form method='get' action='ProductModify'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<input type='submit' class='btnbuy' name='button' value='Addproduct'style = 'font-size:50px'></input>"
				+ "</td></tr><tr><td></td></tr><tr><td>"
				+ "<input type='submit' class='btnbuy' name='button' value='Updateproduct' style = 'font-size:50px'></input>"
				+ "</td></tr><tr><td></td></tr><tr><td>"
				+ "<input type='submit' class='btnbuy' name='button' value='Deleteproduct' style = 'font-size:50px'></input>"
				+ "</td></tr><tr><td></td></tr><tr><td>"
				+ "<input type='submit' class='btnbuy' name='button' value='Trending' style = 'font-size:50px'></input>"
				+ "</td></tr></table>"
				+ "</form>" + "</div></div></div>");
		utility.printHtml("Footer.html");
	}
}