package domain;

import Controller.HttpController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import frontend.FrontEnd;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mikerooijackers
 */
public class Account {

    private int accountID;
    private String username;
    String password;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String email;
    private String telephone;
    private HashMap<String,PhotoGroup> claimedPhotos;

    /**
     * Constructor
     *
     * @param username
     * @param name
     * @param address
     * @param zipcode
     * @param city
     * @param email
     * @param password
     * @param telephone
     */
    public Account(String username, String name, String address,
            String zipcode, String city, String email, String telephone, String password) {
        super();
        this.username = username;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        claimedPhotos = new HashMap<>();
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
     */
    public Account(String username, String name, String address,
            String zipcode, String city, String email, String telephone) {
        super();
        this.username = username;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
        this.telephone = telephone;
        claimedPhotos = new HashMap<>();
    }

    /**
     * Constructor
     *
     * @param username
     */
    public Account(String username) {
        super();
        this.username = username;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the accountID
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<PhotoGroup> getClaimedPhotos() {
        return (ArrayList<PhotoGroup>)new ArrayList(claimedPhotos.values());
    }

    public void setClaimedPhotos(ArrayList<PhotoGroup> claimedPhotos) {
        for(PhotoGroup pg : claimedPhotos){
            this.claimedPhotos.put(pg.getGroupName(),pg);
        }
    }
    
    public PhotoGroup getGroup(String name){
        return this.claimedPhotos.get(name);
    }
    
    public void updateClaimedPhotos(){
        Gson gson = new Gson();
        String returnedPhotos = HttpController.excuteGet(FrontEnd.HOST + "/getPreviousRedeemed?accountID=" + this.accountID);
        if (!returnedPhotos.equalsIgnoreCase("")) {
            ArrayList<PhotoGroup> getPhotos = new ArrayList();
            getPhotos = gson.fromJson(returnedPhotos, new TypeToken<ArrayList<PhotoGroup>>() {
            }.getType());
            this.setClaimedPhotos( getPhotos);
        }
    }

}
