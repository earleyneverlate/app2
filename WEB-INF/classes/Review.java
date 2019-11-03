import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private String productName;
	private String userName;
	private String productType;
	private String productMaker;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	private String retailerpin;
	private String retailername;
	private String price;
	private String retailercity;
	private String userId;
	private String userAge;
	private String userOcc;
	private String gender;
	private String productSale;
	private String productCount;
	
	public Review (String productName,String userName,String productType,
	String productMaker,String reviewRating,String reviewDate,String reviewText,
	String retailerpin,String price,String retailercity, String retailername,
	String userId, String userAge, String userOcc, String gender, String productSale){
		this.productName=productName;
		this.userName=userName;
		this.productType=productType;
		this.productMaker=productMaker;
	 	this.reviewRating=reviewRating;
		this.reviewDate=reviewDate;
	 	this.reviewText=reviewText;
		this.retailerpin=retailerpin;
		this.retailername=retailername;
		this.price= price;
		this.retailercity= retailercity;
		this.userId=userId;
		this.userAge=userAge;
		this.userOcc=userOcc;
		this.gender=gender;
		this.productSale=productSale;
	}

	public Review(String productName, String retailerpin, String reviewRating, String reviewText) {
       this.productName = productName;
       this.retailerpin = retailerpin;
       this.reviewRating = reviewRating;
       this.reviewText = reviewText;
    }
	public Review(String productName, String price, String productCount) {
       this.productName = productName;
       this.price = price;
       this.productCount = productCount;
	}

	public Review(String reviewDate,  String productCount) {
       this.reviewDate = reviewDate;
       this.productCount = productCount;
	}
	
	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public String getProductName() {
		return productName;
	}
	public String getUserName() {
		return userName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductMaker() {
		return productMaker;
	}

	public void setProductMaker(String productMaker) {
		this.productMaker = productMaker;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
    
		public String getRetailerPin() {
		return retailerpin;
	}

	public void setRetailerPin(String retailerpin) {
		this.retailerpin = retailerpin;
	}
			public String getRetailerCity() {
		return retailercity;
	}

	public void setRetailerCity(String retailercity) {
		this.retailercity = retailercity;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRetailerName() {
		return retailername;
	}

	public void setRetailerName(String retailername) {
		this.retailername = retailername;
	}

	public String getUserID() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}	

	public String getUserOccupation() {
		return userOcc;
	}

	public void setUserOccupation(String userOcc) {
		this.userOcc = userOcc;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProductonSale() {
		return productSale;
	}

	public void setProductonSale(String productSale) {
		this.productSale = productSale;
	}	
}


