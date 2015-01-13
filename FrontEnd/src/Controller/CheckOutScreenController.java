/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import domain.Account;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mr. Jin
 */
public class CheckOutScreenController extends ControlledAccountScreen implements Initializable{
    ResourceBundle recources;
    @FXML
    TextField tf_name;
    @FXML
    TextField tf_address;
    @FXML
    TextField tf_zipcode;
    @FXML
    TextField tf_city;
    @FXML
    TextField tf_email;
    @FXML
    TextField tf_telephone;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.recources = rb;
    }    
    
    @Override
    public void setAccount(Account a) {
        this.loggedInAccount = a;
        tf_name.setText(a.getName());
        tf_address.setText(a.getAddress());
        tf_zipcode.setText(a.getZipcode());
        tf_city.setText(a.getCity());
        tf_email.setText(a.getEmail());
        tf_telephone.setText(a.getTelephone());
    }
    
}
