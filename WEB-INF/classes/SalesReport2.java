import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SalesReport2")

public class SalesReport2 extends HttpServlet {

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
		pw.print("<div id='content'><div class='post'><h2 class='title meta' style='text-align:center; padding-top:15px;'>");
		pw.print("<a style='font-size: 32px;'>Bar Chart of Products and Total Sales</a>");
		pw.print("</h2><div class='entry'>");

		try
		{
			Map<String,Product> pMap = MySqlDataStoreUtilities.getSales();

			pw.print("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
            pw.print("<script type='text/javascript'>");
    		pw.print("google.charts.load('current', {packages: ['corechart', 'bar']});");
    		pw.print("google.charts.setOnLoadCallback(drawBasic);");
    		pw.print("function drawBasic() {");
    		pw.print("var data = google.visualization.arrayToDataTable([");
    		pw.print("['Product Name', 'Total Sales'],");
			
			Product p = new Product();
            for(String prodID: pMap.keySet())
            {
               p = (Product)pMap.get(prodID);
               String name = p.getName();
               Double total_sales = p.getTotal();
               pw.print("[' " +name+ " ', "+total_sales+ "],");
            }

            pw.print("]);");
            pw.print("var options = {");
    		pw.print("title: 'Product Names and Total Sales',");
    		pw.print("chartArea: {width: '55%', height: 800},");
    		pw.print("fontSize: 12,");
    		pw.print("hAxis: {");
    		pw.print("title: 'Total Sales',");
    		pw.print("minValue: 0");
    		pw.print("},");
    		pw.print("vAxis: {");
    		pw.print("title: 'Product Name'");
    		pw.print("}");
    		pw.print("};");
    		pw.print("var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
    		pw.print("chart.draw(data, options);");
    		pw.print("}");
            pw.print("</script>");
            pw.print("<div id='chart_div' style='width:730px; height:900px; padding-top:20px;'></div>");

		}
		catch(Exception e)
		{
            e.printStackTrace();
		}
		pw.print("</div></div></div>");	
		utility.printHtml("Footer.html");
	}
}