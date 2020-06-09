package rent;

public class CustomerRecords {
	private String Customer_ID;
	private String Customer_Name;
	private String Customer_Cnic;
	private String Customer_Contact;
	
	public CustomerRecords(String id,String name,String cnic,String contact) {
		this.Customer_ID=id;
		this.Customer_Name=name;
		this.Customer_Cnic=cnic;
		this.Customer_Contact=contact;
	}
	public String getCustomer_ID() {
		return Customer_ID;
	}
	public String getCustomer_Name() {
		return Customer_Name;
	}
	public String getCustomer_Cnic() {
		return Customer_Cnic;
	}
	public String getCustomer_Contact() {
		return Customer_Contact;
	}
	

}
