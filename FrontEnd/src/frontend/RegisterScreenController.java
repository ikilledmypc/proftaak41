/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    TextField TB_sirname;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void checkAvailability(){
       
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
