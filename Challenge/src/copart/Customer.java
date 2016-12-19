package copart;

public class Customer {
	private String custId;
	private String excludeZip;
	
	public Customer(String custId, String excludeZip) {
		super();
		this.custId = custId;
		this.excludeZip = excludeZip;
	}
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getExcludeZip() {
		return excludeZip;
	}
	public void setExcludeZip(String excludeZip) {
		this.excludeZip = excludeZip;
	}
}
