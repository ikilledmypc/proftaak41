package domain;

public class Status {

	private int statusID;
	private String status;

	/**
	 * Constructor
	 * 
	 * @param status
	 */
	public Status(String status) {
		super();
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the statusID
	 */
	public int getStatusID() {
		return statusID;
	}

	/**
	 * @param statusID
	 *            the statusID to set
	 */
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
}
