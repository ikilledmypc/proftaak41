/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;

/**
 *
 * @author Joël
 */
public class PhotoGroup {
    
    private int photogroupID;
	private int accountID;
	private String code;
	private String groupName;
	private Boolean isPublic;
	private int parentPhotogroupID;
        private ArrayList<Photo> photos;

	/**
	 * Constructor
	 * 
	 * @param accountID
	 * @param code
	 * @param groupName
	 * @param isPublic
	 * @param parentPhotogroupID
         * @param p photos
	 */
	public PhotoGroup(int accountID, String code, String groupName,
			Boolean isPublic, int parentPhotogroupID,ArrayList<Photo> p) {
		super();
                this.photos = p;
		this.accountID = accountID;
		this.code = code;
		this.groupName = groupName;
		this.isPublic = isPublic;
		this.parentPhotogroupID = parentPhotogroupID;
	}
        
    /**
     *
     * @param accountID
     * @param code
     * @param groupName
     * @param isPublic
     * @param parentPhotogroupID
     */
    public PhotoGroup(int accountID, String code, String groupName,
			Boolean isPublic, int parentPhotogroupID) {
		super();
		this.accountID = accountID;
		this.code = code;
		this.groupName = groupName;
		this.isPublic = isPublic;
		this.parentPhotogroupID = parentPhotogroupID;
	}

	/**
	 * @return the parentPhotogroupID
	 */
	public int getParentPhotogroupID() {
		return parentPhotogroupID;
	}

	/**
	 * @param parentPhotogroupID
	 *            the parentPhotogroupID to set
	 */
	public void setParentPhotogroupID(int parentPhotogroupID) {
		this.parentPhotogroupID = parentPhotogroupID;
	}

	/**
	 * @return the isPublic
	 */
	public Boolean getIsPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID
	 *            the accountID to set
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	/**
	 * @return the photogroupID
	 */
	public int getPhotogroupID() {
		return photogroupID;
	}

	/**
	 * @param photogroupID
	 *            the photogroupID to set
	 */
	public void setPhotogroupID(int photogroupID) {
		this.photogroupID = photogroupID;
	}
}
