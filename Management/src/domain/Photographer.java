package domain;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mikerooijackers
 */
public class Photographer extends Account {

    private int photographerID;
    private String companyName;
    private String bankAccount;
    private Boolean active;
    private ArrayList<Photographer> photographers;
    private ResultSet rs;

    /**
     * Constructor
     *
     * @param username
     * @param name
     * @param address
     * @param zipcode
     * @param city
     * @param email
     * @param telephone
     * @param password
     * @param companyName
     * @param bankAccount
     */
    public Photographer(String username, String name, String address,
            String zipcode, String city, String email, String telephone, String password,
            String companyName, String bankAccount) {
        super(username, name, address, zipcode, city, email, telephone, password);
        this.companyName = companyName;
        this.bankAccount = bankAccount;
        this.active = false;
    }

    /**
     * Constructor
     *
     * @param username
     * @param name
     * @param address
     * @param zipcode
     * @param city
     * @param email
     * @param telephone
     * @param companyName
     * @param bankAccount
     * @param isActive
     */
    public Photographer(String username, String name, String address,
            String zipcode, String city, String email, String telephone,
            String companyName, String bankAccount, boolean isActive) {
        super(username, name, address, zipcode, city, email, telephone);
        this.companyName = companyName;
        this.bankAccount = bankAccount;
        this.active = isActive;
    }

    /**
     * Constructor
     *
     * @param username
     * @param isActive
     */
    public Photographer(String username, boolean isActive) {
        super(username);
        this.active = isActive;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the bankAccount
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * @param bankAccount the bankAccount to set
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the photographerID
     */
    public int getPhotographerID() {
        return photographerID;
    }

    /**
     * @param photographerID the photographerID to set
     */
    public void setPhotographerID(int photographerID) {
        this.photographerID = photographerID;
    }
}
