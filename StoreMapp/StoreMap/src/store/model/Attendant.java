package store.model;

public class Attendant {
	protected int attendantId;
	protected String attendantName;
	protected String phoneNumber;
	public int getAttendantId() {
		return attendantId;
	}
	public void setAttendantId(int attendantId) {
		this.attendantId = attendantId;
	}
	public String getAttendantName() {
		return attendantName;
	}
	public void setAttendantName(String attendantName) {
		this.attendantName = attendantName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Attendant(int attendantId, String attendantName, String phoneNumber) {
		super();
		this.attendantId = attendantId;
		this.attendantName = attendantName;
		this.phoneNumber = phoneNumber;
	}
	
	

}
