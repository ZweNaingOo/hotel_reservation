package studentregistration.utils;

public class EmailData {
	private String email;
	private int reason;
	
	public EmailData(String email, int reason) {
		this.email = email;
		this.reason = reason;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getReason() {
		return reason;
	}
	public void setReason(int reason) {
		this.reason = reason;
	}
	
}
