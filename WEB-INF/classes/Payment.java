import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		//String msg = "good";
		//String Customername= "";
		//HttpSession session = request.getSession(true);

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	

		String userAddress=request.getParameter("userAddress");
		String creditCardNo=request.getParameter("creditCardNo");
		String orderDate=request.getParameter("orderDate");
		System.out.println("User address == " +userAddress);
		System.out.println("creditCardNo == " + creditCardNo); 	
		System.out.println("orderDate=="+orderDate);
		orderDate="11/11/2019";
		if(!userAddress.isEmpty() && !creditCardNo.isEmpty()&& !orderDate.isEmpty() )
		{
			int orderId=utility.getOrderPaymentSize()+1;

			for (OrderItem oi : utility.getCustomerOrders())
			{
				utility.storePayment(orderId,oi.getName(),oi.getPrice(),userAddress,creditCardNo,orderDate);
			}
			
			OrdersHashMap.orders.remove(utility.username());	
			utility.printHtml("Header.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h2>Your order is stored.");
			pw.print("<br>Your order number is: " + (orderId) + ".");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}
		else
		{
			utility.printHtml("Header.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:red'>Enter valid address and credit card number.</h4>");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
	}
}
