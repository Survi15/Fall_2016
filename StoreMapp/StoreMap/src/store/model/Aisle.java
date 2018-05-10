package store.model;

public class Aisle {
	protected int aisleId;
	protected Attendant attendant;
	public int getAisleId() {
		return aisleId;
	}
	public void setAisleId(int aisleId) {
		this.aisleId = aisleId;
	}
	public Attendant getAttendant() {
		return attendant;
	}
	public void setAttendant(Attendant attendant) {
		this.attendant = attendant;
	}
	public Aisle(int aisleId, Attendant attendant) {
		super();
		this.aisleId = aisleId;
		this.attendant = attendant;
	}
	
	

}
