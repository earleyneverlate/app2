import java.sql.*;
import java.util.*;

public class MySqlDataStoreUtilities {
    static Connection conn = null;
    static String message;

    public static String getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homework2?serverTimezone=UTC", "root", "password");
            System.out.println("successful");
            message = "Successful";
            return message;
        } catch (SQLException e) {
            message = "unsuccessful";
            System.out.println("unsuccessful");
            e.printStackTrace();
            return message;
        } catch (Exception e) {
            message = e.getMessage();
            e.printStackTrace();
            return message;
        }
    }

    public static void Insertproducts() {
        try {

            getConnection();

            String truncatetableacc = "delete from ProductAccessories;";
            PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
            pstt.executeUpdate();

            String truncatetableprod = "delete from  Productdetails;";
            PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
            psttprod.executeUpdate();


            String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
                    "VALUES (?,?,?,?,?,?,?,?);";
            for (Map.Entry<String, Accessory> entry : SaxParser4BestDealXMLdataStore1.accessories.entrySet()) {
                String name = "accessories";
                Accessory acc = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, acc.getId());
                pst.setString(3, acc.getName());
                pst.setDouble(4, acc.getPrice());
                pst.setString(5, acc.getImage());
                pst.setString(6, acc.getRetailer());
                pst.setString(7, acc.getCondition());
                pst.setDouble(8, acc.getDiscount());

                pst.executeUpdate();


            }

            for (Map.Entry<String, Tv> entry : SaxParser4BestDealXMLdataStore1.tvs.entrySet()) {
                Tv tv = entry.getValue();
                String name = "tvs";


                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, tv.getId());
                pst.setString(3, tv.getName());
                pst.setDouble(4, tv.getPrice());
                pst.setString(5, tv.getImage());
                pst.setString(6, tv.getRetailer());
                pst.setString(7, tv.getCondition());
                pst.setDouble(8, tv.getDiscount());

                pst.executeUpdate();
                try {
                    HashMap<String, String> acc = tv.getAccessories();
                    String insertAccessoryQurey = "INSERT INTO  ProductAccessories(productName,accessoriesName)" +
                            "VALUES (?,?);";
                    for (Map.Entry<String, String> accentry : acc.entrySet()) {
                        PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
                        pstacc.setString(1, tv.getId());
                        pstacc.setString(2, accentry.getValue());
                        pstacc.executeUpdate();
                    }
                } catch (Exception et) {
                    et.printStackTrace();
                }
            }
            for (Map.Entry<String, SoundSystem> entry : SaxParser4BestDealXMLdataStore1.sounds.entrySet()) {
                String name = "sounds";
                SoundSystem sound = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, sound.getId());
                pst.setString(3, sound.getName());
                pst.setDouble(4, sound.getPrice());
                pst.setString(5, sound.getImage());
                pst.setString(6, sound.getRetailer());
                pst.setString(7, sound.getCondition());
                pst.setDouble(8, sound.getDiscount());

                pst.executeUpdate();


            }
            for (Map.Entry<String, Phone> entry : SaxParser4BestDealXMLdataStore1.phones.entrySet()) {
                String name = "phones";
                Phone phone = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, phone.getId());
                pst.setString(3, phone.getName());
                pst.setDouble(4, phone.getPrice());
                pst.setString(5, phone.getImage());
                pst.setString(6, phone.getRetailer());
                pst.setString(7, phone.getCondition());
                pst.setDouble(8, phone.getDiscount());

                pst.executeUpdate();


            }
            for (Map.Entry<String, Laptop> entry : SaxParser4BestDealXMLdataStore1.laptops.entrySet()) {
                String name = "laptops";
                Laptop laptop = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, laptop.getId());
                pst.setString(3, laptop.getName());
                pst.setDouble(4, laptop.getPrice());
                pst.setString(5, laptop.getImage());
                pst.setString(6, laptop.getRetailer());
                pst.setString(7, laptop.getCondition());
                pst.setDouble(8, laptop.getDiscount());

                pst.executeUpdate();


            }
            for (Map.Entry<String, VoiceAssistant> entry : SaxParser4BestDealXMLdataStore1.voices.entrySet()) {
                String name = "voices";
                VoiceAssistant voice = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, voice.getId());
                pst.setString(3, voice.getName());
                pst.setDouble(4, voice.getPrice());
                pst.setString(5, voice.getImage());
                pst.setString(6, voice.getRetailer());
                pst.setString(7, voice.getCondition());
                pst.setDouble(8, voice.getDiscount());

                pst.executeUpdate();


            }
            for (Map.Entry<String, FitnessWatch> entry : SaxParser4BestDealXMLdataStore1.fitnesses.entrySet()) {
                String name = "fitnesses";
                FitnessWatch fitness = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, fitness.getId());
                pst.setString(3, fitness.getName());
                pst.setDouble(4, fitness.getPrice());
                pst.setString(5, fitness.getImage());
                pst.setString(6, fitness.getRetailer());
                pst.setString(7, fitness.getCondition());
                pst.setDouble(8, fitness.getDiscount());

                pst.executeUpdate();


            }
            for (Map.Entry<String, SmartWatch> entry : SaxParser4BestDealXMLdataStore1.smarts.entrySet()) {
                String name = "smarts";
                SmartWatch smart = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, smart.getId());
                pst.setString(3, smart.getName());
                pst.setDouble(4, smart.getPrice());
                pst.setString(5, smart.getImage());
                pst.setString(6, smart.getRetailer());
                pst.setString(7, smart.getCondition());
                pst.setDouble(8, smart.getDiscount());

                pst.executeUpdate();


            }
            for (Map.Entry<String, Headphones> entry : SaxParser4BestDealXMLdataStore1.headphones.entrySet()) {
                String name = "headphones";
                Headphones headphone = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, headphone.getId());
                pst.setString(3, headphone.getName());
                pst.setDouble(4, headphone.getPrice());
                pst.setString(5, headphone.getImage());
                pst.setString(6, headphone.getRetailer());
                pst.setString(7, headphone.getCondition());
                pst.setDouble(8, headphone.getDiscount());

                pst.executeUpdate();


            }
            for (Map.Entry<String, WirelessPlan> entry : SaxParser4BestDealXMLdataStore1.wirelesses.entrySet()) {
                String name = "wirelesses";
                WirelessPlan wireless = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1, name);
                pst.setString(2, wireless.getId());
                pst.setString(3, wireless.getName());
                pst.setDouble(4, wireless.getPrice());
                pst.setString(5, wireless.getImage());
                pst.setString(6, wireless.getRetailer());
                pst.setString(7, wireless.getCondition());
                pst.setDouble(8, wireless.getDiscount());

                pst.executeUpdate();


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Tv> getTvs() {
        HashMap<String, Tv> hm = new HashMap<String, Tv>();
        try {
        // System.out.println("************************************");
            getConnection();

            /*if (conn==null){
                System.out.println("************************************");
            }*/

            String selectTv = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTv);
            pst.setString(1, "tvs");
            ResultSet rs = pst.executeQuery();
           
            while (rs.next()) {
                Tv tv = new Tv(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), tv);
                tv.setId(rs.getString("Id"));

                try {
                    String selectaccessory = "Select * from ProductAccessories where productName=?";
                    PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
                    pstacc.setString(1, rs.getString("Id"));
                    ResultSet rsacc = pstacc.executeQuery();

                    HashMap<String, String> acchashmap = new HashMap<String, String>();
                    while (rsacc.next()) {
                        if (rsacc.getString("accessoriesName") != null) {

                            acchashmap.put(rsacc.getString("accessoriesName"), rsacc.getString("accessoriesName"));
                            tv.setAccessories(acchashmap);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String, SoundSystem> getSounds() {
        HashMap<String, SoundSystem> hm = new HashMap<String, SoundSystem>();
        try {
            getConnection();

            String selectSound = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectSound);
            pst.setString(1, "sounds");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                SoundSystem sound = new SoundSystem(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), sound);
                sound.setId(rs.getString("Id"));

            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String, Phone> getPhones() {
        HashMap<String, Phone> hm = new HashMap<String, Phone>();
        try {
            getConnection();

            String selectPhone = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectPhone);
            pst.setString(1, "phones");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Phone phone = new Phone(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), phone);
                phone.setId(rs.getString("Id"));

            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String, Laptop> getLaptops() {
        HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
        try {
            getConnection();

            String selectLaptop = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectLaptop);
            pst.setString(1, "laptops");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Laptop laptop = new Laptop(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), laptop);
                laptop.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String, VoiceAssistant> getVoices() {
        HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();
        try {
            getConnection();

            String selectVoice = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectVoice);
            pst.setString(1, "voices");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                VoiceAssistant voice = new VoiceAssistant(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), voice);
                voice.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String, FitnessWatch> getFitnesses() {
        HashMap<String, FitnessWatch> hm = new HashMap<String, FitnessWatch>();
        try {
            getConnection();

            String selectFitness = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectFitness);
            pst.setString(1, "fitnesses");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                FitnessWatch fitness = new FitnessWatch(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), fitness);
                fitness.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String, SmartWatch> getSmarts() {
        HashMap<String, SmartWatch> hm = new HashMap<String, SmartWatch>();
        try {
            getConnection();

            String selectSmart = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectSmart);
            pst.setString(1, "smarts");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                SmartWatch smart = new SmartWatch(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), smart);
                smart.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String, Headphones> getHeadphones() {
        HashMap<String, Headphones> hm = new HashMap<String, Headphones>();
        try {
            getConnection();

            String selectHeadphones = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectHeadphones);
            pst.setString(1, "headphones");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Headphones headphone = new Headphones(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), headphone);
                headphone.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String, WirelessPlan> getWireless() {
        HashMap<String, WirelessPlan> hm = new HashMap<String, WirelessPlan>();
        try {
            getConnection();

            String selectWireless = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectWireless);
            pst.setString(1, "wirelesses");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                WirelessPlan wireless = new WirelessPlan(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), wireless);
                wireless.setId(rs.getString("Id"));
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String, Accessory> getAccessories() {
        HashMap<String, Accessory> hm = new HashMap<String, Accessory>();
        try {
            getConnection();

            String selectAcc = "select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectAcc);
            pst.setString(1, "accessories");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Accessory acc = new Accessory(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), acc);
                acc.setId(rs.getString("Id"));

            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return hm;
    }

    public static String addproducts(String producttype, String productId, String productName, double productPrice, String productImage, String productManufacturer, String productCondition, double productDiscount, String prod) {
        String msg = "Product is added successfully";
        try {

            getConnection();
            String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
                    "VALUES (?,?,?,?,?,?,?,?);";

            String name = producttype;

            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setString(1, name);
            pst.setString(2, productId);
            pst.setString(3, productName);
            pst.setDouble(4, productPrice);
            pst.setString(5, productImage);
            pst.setString(6, productManufacturer);
            pst.setString(7, productCondition);
            pst.setDouble(8, productDiscount);

            pst.executeUpdate();
            try {
                if (!prod.isEmpty()) {
                    String addaprodacc = "INSERT INTO  ProductAccessories(productName,accessoriesName)" +
                            "VALUES (?,?);";
                    PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
                    pst1.setString(1, prod);
                    pst1.setString(2, productId);
                    pst1.executeUpdate();

                }
            } catch (Exception e) {
                msg = "Error cannot add product. Try again.";
                e.printStackTrace();

            }

        } catch (Exception e) {
            msg = "Error cannot add product. Try again.";
            e.printStackTrace();
        }
        return msg;
    }

    public static String updateproducts(String producttype, String productId, String productName, double productPrice, String productImage, String productManufacturer, String productCondition, double productDiscount) {
        String msg = "Product is updated successfully";
        try {

            getConnection();
            String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=? where Id =?;";


            PreparedStatement pst = conn.prepareStatement(updateProductQurey);

            pst.setString(1, productName);
            pst.setDouble(2, productPrice);
            pst.setString(3, productImage);
            pst.setString(4, productManufacturer);
            pst.setString(5, productCondition);
            pst.setDouble(6, productDiscount);
            pst.setString(7, productId);
            pst.executeUpdate();


        } catch (Exception e) {
            msg = "Product cannot be updated";
            e.printStackTrace();

        }
        return msg;
    }

    public static String deleteproducts(String productId) {
        String msg = "Product is deleted successfully";
        try {

            getConnection();
            String deleteproductsQuery = "Delete from Productdetails where Id=?";
            PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
            pst.setString(1, productId);

            pst.executeUpdate();
        } catch (Exception e) {
            msg = "Proudct cannot be deleted";
             e.printStackTrace();
        }
        return msg;
    }

    public static void deleteOrder(int orderId, String orderName) {
        try {

            getConnection();
            String deleteOrderQuery = "Delete from CustomerOrders where OrderId=? and orderName=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1, orderId);
            pst.setString(2, orderName);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertOrder(int orderId, String userName, String orderName, double orderPrice, String userAddress, String creditCardNo) {
        try {

            getConnection();

            String insertIntoCustomerOrderQuery = "INSERT INTO CustomerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo) "
                    + "VALUES (?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1, orderId);
            pst.setString(2, userName);
            pst.setString(3, orderName);
            pst.setDouble(4, orderPrice);
            pst.setString(5, userAddress);
            pst.setString(6, creditCardNo);
            pst.execute();
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder() {

        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();

        try {

            getConnection();
            //select the table
            String selectOrderQuery = "select * from CustomerOrders";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();
            ArrayList<OrderPayment> orderList = new ArrayList<OrderPayment>();
            while (rs.next()) {
                if (!orderPayments.containsKey(rs.getInt("OrderId"))) {
                    ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
                    orderPayments.put(rs.getInt("orderId"), arr);
                }
                ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));


                //add to orderpayment hashmap
                OrderPayment order = new OrderPayment(rs.getInt("OrderId"), rs.getString("userName"), rs.getString("orderName"), rs.getDouble("orderPrice"), rs.getString("userAddress"), rs.getString("creditCardNo"));
                listOrderPayment.add(order);

            }


        } catch (Exception e) {
             e.printStackTrace();
        }
        return orderPayments;
    }


    public static void insertUser(String username, String password, String repassword, String usertype) {
        try {
            getConnection();
            System.out.println("registration1");
            String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
                    + "VALUES (?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, repassword);
            pst.setString(4, usertype);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, User> selectUser() {
        HashMap<String, User> hm = new HashMap<String, User>();
        try {
            getConnection();
            System.out.println("registration2");
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "select * from  Registration";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
                hm.put(rs.getString("username"), user);
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return hm;
    }
    public static HashMap<String, Product> productsOnSales(){
		try{
				getConnection();
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="select * from Productdetails where quantity>0";
				PreparedStatement pst = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()){
					Product product = new Product();					
					product.setName(rs.getString("productName"));
					product.setPrice(rs.getDouble("productPrice"));
					
					
					products.put(product.getName(),product);
				}
				
				pst.close();
				conn.close();
				return products;
				
			
			}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
    public static HashMap<String, Product> manufacturerRebates(){                                                          (){
		try{
				getConnection();
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="select * from Productdetails where rebates >0";
				PreparedStatement pst = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()){
					Product product = new Product();					
					//product.setId(rs.getString("id"));
					product.setName(rs.getString("productName"));
					product.setRebates(rs.getDouble("rebates"));		
					products.put(product.getName(),product);
				}	
				pst.close();
				conn.close();
				return products;	
			}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
    }

    public static HashMap<String, Product> getsales(){
		try{
				
				getConnection();	
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="select orderName, orderPrice, count(orderName) as count, sum(orderPrice) as total from customerorders group by orderName;";
				PreparedStatement pst1 = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst1.executeQuery();
				
				while(rs.next()){
					Product product = new Product();
					product.setName(rs.getString("orderName"));
					product.setPrice(rs.getDouble("orderPrice"));
					product.setProductCount(rs.getDouble("count"));
					product.setTotal(rs.getDouble("total"));

					products.put(product.getName(),product);
				}	
				pst1.close();
				conn.close();
				return products;
			}
		    catch(Exception e){
			    e.printStackTrace();
			    return null;
            }	
        }    	

    public static HashMap<String, Product> getProductInventory(){
		try{
				
			    getConnection();			
				HashMap<String, Product> products=new HashMap<String, Product>();
				String selectProductQuery ="select * from Productdetails;";
				PreparedStatement pst1 = conn.prepareStatement(selectProductQuery);
				ResultSet rs = pst1.executeQuery();
				
				while(rs.next()){
					Product product = new Product();
					product.setName(rs.getString("productName"));
					product.setPrice(rs.getDouble("productPrice"));
					product.setProductCount(rs.getDouble("ProductQuantity"));
					
					products.put(product.getName(),product);
				}
				
				pst1.close();
				conn.close();
				return products;
				
			}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
}   