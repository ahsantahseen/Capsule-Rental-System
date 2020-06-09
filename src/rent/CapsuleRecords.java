package rent;

public class CapsuleRecords {
	private String Capsule_ID;
	private String Capsule_Manufacturer;
	private String Capsule_Type;
	private String Capsule_Availible_for_rent;
	
	public CapsuleRecords(String id,String manufacturer,String type,String rental) {
		this.Capsule_ID=id;
		this.Capsule_Manufacturer=manufacturer;
		this.Capsule_Type=type;
		this.Capsule_Availible_for_rent=rental;
	}
	public String getCapsule_ID() {
		return Capsule_ID;
	}
	public String getCapsule_Manufacturer() {
		return Capsule_Manufacturer;
	}
	public String getCapsule_Type() {
		return Capsule_Type;
	}
	public String getCapsule_Availibility() {
		return Capsule_Availible_for_rent;
	}
	

}
