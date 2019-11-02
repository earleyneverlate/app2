import java.io.*;
public class InventoryData
{
	String productName ;
	String count;
	String productPrice ;
	String productDiscount;
	String productQuantity;

	public  InventoryData(String productName,String productPrice, String productQuantity)
	{
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity= productQuantity;
    }
    
	public InventoryData(String productName, String productDiscount){
		this.productName = productName;
		this.productDiscount = productDiscount;	
	}
	
	public String getProductQuantity(){
		return productQuantity;
    }
    
	public void setProductQuantity(String productQuantity){
		this.productQuantity=productQuantity;	
	}

	public String getProductPrice(){
	 return productPrice;
	}
	
	public void setProductPrice(String productPrice){
	  this.productPrice=productPrice;
	}

    public String getproductDiscount(){
	 return productDiscount;
	}

	public String getProductName(){
	 return productName;
	}
	
	public void setproductDiscount(String productDiscount){
	  this.productDiscount=productDiscount;
	}
	
	public void setProductName(String productName){
	  this.productName=productName;
	}

	public void setCount(String count) {
	  this.count=count;
	}
	
	public String getCount() {
	 return count;
	}
}