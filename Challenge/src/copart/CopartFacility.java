package copart;

public class CopartFacility {
	private String state;
	private String city;
	private String zipcode;
	private double customerDistance;
	private  String latitude;
	private  String [] location;
	private  String longitude;
	public String getLatitude() {
		
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	

	public CopartFacility(String state, String city, String zipcode,String[] parse) {
		super();
		this.state = state;
		this.city = city;
		this.zipcode = zipcode;
		this.location=parse;
		this.latitude=parse[0];
		this.longitude=parse[1];
	}

	public String[] getLocation() {
		return location;
	}
	
	

	@Override
	public String toString() {
		return "CopartFacility [state=" + state + ", city=" + city + ", zipcode=" + zipcode + ", customerDistance="
				+ customerDistance + "]";
	}

	public void setLocation(String[] location) {
		this.location = location;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public CopartFacility(String state, String city, String zipcode, double customerDistance) {
		super();
		this.state = state;
		this.city = city;
		this.zipcode = zipcode;
		this.customerDistance = customerDistance;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public double getCustomerDistance() {
		return customerDistance;
	}
	public void setCustomerDistance(double customerDistance) {
		this.customerDistance = customerDistance;
	}
}


