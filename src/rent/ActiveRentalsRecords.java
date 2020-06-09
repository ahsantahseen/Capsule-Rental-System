package rent;


import java.sql.Date;

public class ActiveRentalsRecords {
	private String Order_ID;
	private String Capsule_ID;
	
	private String Customer_Name;
	private String Rental_Fee;
	private String order_date;
	private String days;
	
	public ActiveRentalsRecords(String Order_ID,String Cap_ID,String Customer_Name,String Fee,String days,String Order_Date) {
		this.Order_ID=Order_ID;
		this.Capsule_ID=Cap_ID;
		this.Customer_Name=Customer_Name;
        this.days=days;
        this.order_date=Order_Date;
		this.Rental_Fee=Fee;
	}
	public String getOrder_ID() {
		return Order_ID;
	}
	public String getCapsule_ID() {
		return Capsule_ID;
	}
	
	
	public String getCustomer_Name() {
		return Customer_Name;
	}
	public String getRental_Fee() {
		return Rental_Fee;
	}
	public String getorder_date() {
		return order_date;
	}
	public String getdays() {
		return days;
	}

}
