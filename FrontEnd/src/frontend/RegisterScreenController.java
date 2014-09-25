/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import Controller.HttpController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Baya
 */
public class RegisterScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    @FXML
    TextField TB_name;
    @FXML
    TextField TB_surname;
    @FXML
    TextField TB_zip;
    @FXML
    TextField TB_address;
    @FXML
    TextField TB_city;
    @FXML
    TextField TB_email;
    @FXML
    TextField TB_telepone;
    @FXML
    TextField TB_username;
    @FXML
    TextField TB_password;
    @FXML
    Label LB_error;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void register(){
        if(checkAvailability(TB_username.getText())){
            if(validateInput()){
                
            }
        }else{
            System.out.println("gebruikersnaam bezet");
            LB_error.setText("gebruikersnaam is al in gebruik");
            TB_username.requestFocus();
        }
    }
    
    
    private boolean checkAvailability(String username){
        String s = HttpController.excuteGet("http://127.0.0.1:8080/checkAvailable?username="+username);
        s = s.trim();
       return Boolean.parseBoolean(s);
    }
    
    private boolean validateInput(){
        if(!TB_name.getText().matches("^[\\p{L}\\s'.-]+$")){
            TB_name.requestFocus();
            LB_error.setText("geen geldige naam");
            return false;
        } 
        if(!TB_surname.getText().matches("^[\\p{L}\\s'.-]+$")){
            TB_surname.requestFocus();
            LB_error.setText("geen geldige achternaam");
            return false;
        }
        if(!TB_address.getText().matches("^[\\w]*[\\s][0-9]*[A-z]*")){
            TB_address.requestFocus();
            LB_error.setText("geen geldig adres");
            return false;
        }
        if(!TB_city.getText().matches("^[\\p{L}\\s'.-]+$")){
            TB_city.requestFocus();
            LB_error.setText("geen geldige stad");
            return false;
        }
        if(!TB_email.getText().matches("^[a-z0-9._-]*@[a-z0-9-.]*[.][a-z]{2,3}$")){
            TB_email.requestFocus();
            LB_error.setText("geen geldige email");
            return false;
        }
        if(!TB_username.getText().matches("^[\\p{L}\\s'.-]+$")){
            TB_username.requestFocus();
            LB_error.setText("geen geldige gebruikersnaam");
            return false;
        }
        if(!TB_telepone.getText().matches("^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*$")){
            TB_telepone.requestFocus();
            LB_error.setText("geen geldig telefoonnummer");
            return false;
        }
        if(!TB_password.getText().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{4,8}$")){
            TB_password.requestFocus();
            LB_error.setText("wachtwoord moet minimaal 1 hoofdletter, kleine letter, en cijfer bevatten en 6-13 lang zijn");
            return false;
        }      
        
        return true;
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    @FXML
    public void handleBackButtonAction(ActionEvent even) {
        myController.setScreen(FrontEnd.mainScreen);
    }
    
}
