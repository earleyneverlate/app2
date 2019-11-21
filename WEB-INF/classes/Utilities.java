import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet {
    HttpServletRequest req;
    PrintWriter pw;
    String url;
    HttpSession session;

    public Utilities(HttpServletRequest req, PrintWriter pw) {
        this.req = req;
        this.pw = pw;
        this.url = this.getFullURL();
        this.session = req.getSession(true);
    }



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

    public void printHtml(String file) {
        String result = HtmlToString(file);
        //to print the right navigation in header of username cart and logout etc
        if (file == "Header.html") {
            result = result + "<nav id='menu' style='float: right;'><ul>";
            if (session.getAttribute("username") != null) {
                String username = session.getAttribute("username").toString();
                username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
                result = result + "<li><a href='ViewOrder'>ViewOrder</a></li>"
                        + "<li><a>Hello, " + username + "</a></li>"
                        + "<li><a href='Account'>Account</a></li>"
                        + "<li><a href='Logout'>Logout</a></li>";
            } else
                result = result + "<li><a href='ViewOrder'>View Order</a></li>" + "<li><a href='Login'>Login</a></li>";
            result = result + "<li><a href='Cart'>Cart(" + CartCount() + ")</a></li></ul></div></div><div id='page'>";
            pw.print(result);
        } else
            pw.print(result);
    }


    /*  getFullURL Function - Reconstructs the URL user request  */

    public String getFullURL() {
        String scheme = req.getScheme();
        String serverName = req.getServerName();
        int serverPort = req.getServerPort();
        String contextPath = req.getContextPath();
        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        url.append("/");
        return url.toString();
    }

    /*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
    public String HtmlToString(String file) {
        String result = null;
        try {
            String webPage = url + file;
            URL url = new URL(webPage);
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuffer sb = new StringBuffer();
            while ((numCharsRead = isr.read(charArray)) > 0) {
                sb.append(charArray, 0, numCharsRead);
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*  logout Function removes the username , usertype attributes from the session variable*/

    public void logout() {
        session.removeAttribute("username");
        session.removeAttribute("usertype");
    }

    /*  logout Function checks whether the user is loggedIn or Not*/

    public boolean isLoggedin() {
        if (session.getAttribute("username") == null)
            return false;
        return true;
    }

    /*  username Function returns the username from the session variable.*/

    public String username() {
        if (session.getAttribute("username") != null)
            return session.getAttribute("username").toString();
        return null;
    }

    /*  usertype Function returns the usertype from the session variable.*/
    public String usertype() {
        if (session.getAttribute("usertype") != null)
            return session.getAttribute("usertype").toString();
        return null;
    }

    /*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
    public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		try
		{		
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
		}	
		User user = hm.get(username());
		return user;
	}

    /*  getCustomerOrders Function gets  the Orders for the user*/
    public ArrayList<OrderItem> getCustomerOrders() {
        ArrayList<OrderItem> order = new ArrayList<OrderItem>();
        if (OrdersHashMap.orders.containsKey(username()))
            order = OrdersHashMap.orders.get(username());
        return order;
    }

    /*  getOrdersPaymentSize Function gets  the size of OrderPayment */
   	public int getOrderPaymentSize(){
		
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		int size=0;
		try
		{
			orderPayments =MySqlDataStoreUtilities.selectOrder();
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
				size=entry.getKey();
		}
			
		return size;		
	}

    /*  CartCount Function gets  the size of User Orders*/
    public int CartCount() {
        if (isLoggedin())
            return getCustomerOrders().size();
        return 0;
    }

    /* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

    public void storeProduct(String name, String type, String maker, String acc) {
        if (!OrdersHashMap.orders.containsKey(username())) {
            ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
            OrdersHashMap.orders.put(username(), arr);
        }
        ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		HashMap<String,Tv> alltvs = new HashMap<String,Tv> ();
			HashMap<String,SoundSystem> allsounds = new HashMap<String,SoundSystem> ();
            HashMap<String,Phone> allphones = new HashMap<String,Phone> ();
            HashMap<String,Laptop> alllaptops = new HashMap<String,Laptop> ();
            HashMap<String,VoiceAssistant> allvoices = new HashMap<String,VoiceAssistant> ();
            HashMap<String,FitnessWatch> allfitness = new HashMap<String,FitnessWatch> ();
            HashMap<String,SmartWatch> allsmarts = new HashMap<String,SmartWatch> ();
            HashMap<String,Headphones> allheadphones = new HashMap<String,Headphones> ();
            HashMap<String,WirelessPlan> allwireless = new HashMap<String,WirelessPlan> ();
			HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
        if (type.equals("tvs")) {
            Tv tv;
            try{
			alltvs = MySqlDataStoreUtilities.getTvs();
			}
			catch(Exception e){
				e.printStackTrace();
			}
            tv = alltvs.get(name);
            OrderItem orderitem = new OrderItem(tv.getName(), tv.getPrice(), tv.getImage(), tv.getRetailer());
            orderItems.add(orderitem);
        }
        if (type.equals("sounds")) {
            SoundSystem soundSystem;
            try{
			allsounds = MySqlDataStoreUtilities.getSounds();
			}
			catch(Exception e){
				e.printStackTrace();
			}
            soundSystem = allsounds.get(name);
            OrderItem orderitem = new OrderItem(soundSystem.getName(), soundSystem.getPrice(), soundSystem.getImage(), soundSystem.getRetailer());
            orderItems.add(orderitem);
        }
        if (type.equals("phones")) {
            Phone phone;
            try{
			allphones = MySqlDataStoreUtilities.getPhones();
			}
			catch(Exception e){
				e.printStackTrace();
			}
            phone = allphones.get(name);
            OrderItem orderitem = new OrderItem(phone.getName(), phone.getPrice(), phone.getImage(), phone.getRetailer());
            orderItems.add(orderitem);
        }
        if (type.equals("laptops")) {
            Laptop laptop;
            try{
			alllaptops = MySqlDataStoreUtilities.getLaptops();
			}
			catch(Exception e){
				e.printStackTrace();
			}
            laptop = alllaptops.get(name);
            OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer());
            orderItems.add(orderitem);
        }
        if (type.equals("voices")) {
            VoiceAssistant voiceAssistant;
            try{
			allvoices = MySqlDataStoreUtilities.getVoices();
			}
			catch(Exception e){
				e.printStackTrace();
			}
            voiceAssistant = allvoices.get(name);
            OrderItem orderitem = new OrderItem(voiceAssistant.getName(), voiceAssistant.getPrice(), voiceAssistant.getImage(), voiceAssistant.getRetailer());
            orderItems.add(orderitem);
        }
        if (type.equals("fitnesses")) {
            FitnessWatch fitnessWatch;
            try{
			allfitness = MySqlDataStoreUtilities.getFitnesses();
			}
			catch(Exception e){
				e.printStackTrace();
			}
            fitnessWatch = allfitness.get(name);
            OrderItem orderitem = new OrderItem(fitnessWatch.getName(), fitnessWatch.getPrice(), fitnessWatch.getImage(), fitnessWatch.getRetailer());
            orderItems.add(orderitem);
        }
        if (type.equals("smarts")) {
            SmartWatch smartWatch;
            try{
            allsmarts= MySqlDataStoreUtilities.getSmarts();
			}
			catch(Exception e){
				e.printStackTrace();
			}
            smartWatch = allsmarts.get(name);
            OrderItem orderitem = new OrderItem(smartWatch.getName(), smartWatch.getPrice(), smartWatch.getImage(), smartWatch.getRetailer());
            orderItems.add(orderitem);
        }
        if (type.equals("headphones")) {
            Headphones headphone;
            try{
			allheadphones = MySqlDataStoreUtilities.getHeadphones();
			}
			catch(Exception e){
				e.printStackTrace();
			}
            headphone = allheadphones.get(name);
            OrderItem orderitem = new OrderItem(headphone.getName(), headphone.getPrice(), headphone.getImage(), headphone.getRetailer());
            orderItems.add(orderitem);
        }
        if (type.equals("wireless")) {
            WirelessPlan wirelessPlan;
            try{
			allwireless = MySqlDataStoreUtilities.getWireless();
			}
			catch(Exception e){
				e.printStackTrace();
			}
            wirelessPlan = allwireless.get(name);
            OrderItem orderitem = new OrderItem(wirelessPlan.getName(), wirelessPlan.getPrice(), wirelessPlan.getImage(), wirelessPlan.getRetailer());
            orderItems.add(orderitem);
        }

    }

    // store the payment details for orders
    public void storePayment(int orderId,
                             String orderName, double orderPrice, String userAddress, String creditCardNo, String customer) {
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        
        // get the payment details file
        try
		{
			orderPayments=MySqlDataStoreUtilities.selectOrder();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        if (orderPayments == null) {
            orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        }
        // if there exist order id already add it into same list for order id or create a new record with order id

        if (!orderPayments.containsKey(orderId)) {
            ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
            orderPayments.put(orderId, arr);
        }

        ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);
        OrderPayment orderpayment = new OrderPayment(orderId, username(), orderName, orderPrice, userAddress, creditCardNo);
        listOrderPayment.add(orderpayment);

        // add order details into file
    
        try
		{	if(session.getAttribute("usertype").equals("retailer"))
			{
				MySqlDataStoreUtilities.insertOrder(orderId,customer,orderName,orderPrice,userAddress,creditCardNo);
            }
            else
			{
                MySqlDataStoreUtilities.insertOrder(orderId,username(),orderName,orderPrice,userAddress,creditCardNo);
            }
		}
		catch(Exception e)
		{
			System.out.println("inside exception file not written properly");
		}	
	}
     public String storeReview(String productname, String producttype,String productmaker,String reviewrating,String reviewdate,String reviewtext,String retailerpin,String price,String retailercity){
	String message=MongoDBDataStoreUtilities.insertReview(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,retailerpin,price,city);
		if(!message.equals("Successful"))
		{ 
            return "UnSuccessful";
		}
		else
		{
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try
		{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}
		catch(Exception e)
		{
			
		}
		if(reviews==null)
		{
			reviews = new HashMap<String, ArrayList<Review>>();
		}
			// if there exist product review already add it into same list for productname or create a new record with product name
			
		if(!reviews.containsKey(productname)){	
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(productname, arr);
		}
		ArrayList<Review> listReview = reviews.get(productname);		
        //review this later
        Review review = new Review(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,retailerpin,price,city, message, message, message, message, message, message);
		listReview.add(review);	
			
			// add Reviews into database
		
		return "Successful";	
		}
	}


    public HashMap<String, Tv> getTVsMap() {
        HashMap<String, Tv> hm = new HashMap<String, Tv>();
        hm.putAll(SaxParser4BestDealXMLdataStore1.tvs);
        return hm;
    }

    public HashMap<String, SoundSystem> getSoundSystemsMap() {
        HashMap<String, SoundSystem> hm = new HashMap<String, SoundSystem>();
        hm.putAll(SaxParser4BestDealXMLdataStore1.sounds);
        return hm;
    }

    public HashMap<String, Phone> getPhonesMap() {
        HashMap<String, Phone> hm = new HashMap<String, Phone>();
        hm.putAll(SaxParser4BestDealXMLdataStore1.phones);
        return hm;
    }

    public HashMap<String, Laptop> getLaptopsMap() {
        HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
        hm.putAll(SaxParser4BestDealXMLdataStore1.laptops);
        return hm;
    }

    public HashMap<String, VoiceAssistant> getVoiceAssistantsMap() {
        HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();
        hm.putAll(SaxParser4BestDealXMLdataStore1.voices);
        return hm;
    }

    public HashMap<String, FitnessWatch> getFitnessWatchesMap() {
        HashMap<String, FitnessWatch> hm = new HashMap<String, FitnessWatch>();
        hm.putAll(SaxParser4BestDealXMLdataStore1.fitnesses);
        return hm;
    }

    public HashMap<String, SmartWatch> getSmartWatchesMap() {
        HashMap<String, SmartWatch> hm = new HashMap<String, SmartWatch>();
        hm.putAll(SaxParser4BestDealXMLdataStore1.smarts);
        return hm;
    }

    public HashMap<String, Headphones> getHeadphonesMap() {
        HashMap<String, Headphones> hm = new HashMap<String, Headphones>();
        hm.putAll(SaxParser4BestDealXMLdataStore1.headphones);
        return hm;
    }

    public HashMap<String, WirelessPlan> getWirelessPlansMap() {
        HashMap<String, WirelessPlan> hm = new HashMap<String, WirelessPlan>();
        hm.putAll(SaxParser4BestDealXMLdataStore1.wirelesses);
        return hm;
    }

    public ArrayList<String> getTVsList() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, Tv> entry : getTVsMap().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    public ArrayList<String> getSoundSystemsList() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, SoundSystem> entry : getSoundSystemsMap().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    public ArrayList<String> getPhonesList() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, Phone> entry : getPhonesMap().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    public ArrayList<String> getLaptopsList() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, Laptop> entry : getLaptopsMap().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    public ArrayList<String> getVoiceAssistantsList() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, VoiceAssistant> entry : getVoiceAssistantsMap().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    public ArrayList<String> getFitnessWatchesList() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, FitnessWatch> entry : getFitnessWatchesMap().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    public ArrayList<String> getSmartWatchesList() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, SmartWatch> entry : getSmartWatchesMap().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    public ArrayList<String> getHeadphonesList() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, Headphones> entry : getHeadphonesMap().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }

    public ArrayList<String> getWirelessPlanList() {
        ArrayList<String> ar = new ArrayList<String>();
        for (Map.Entry<String, WirelessPlan> entry : getWirelessPlansMap().entrySet()) {
            ar.add(entry.getValue().getName());
        }
        return ar;
    }
}
