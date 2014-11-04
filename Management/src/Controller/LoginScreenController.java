/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mike Rooijackers
 */
public class LoginScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    TextField TF_username;
    @FXML
    TextField TF_password;
    @FXML
    Label LB_error;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void login() {
        try {
            if ("admin".equals(TF_username.getText()) && "admin".equals(TF_password.getText())) {
                
                myController.loadScreen(FrontEnd.mainScreen, FrontEnd.mainScreenFXML);
                myController.loadScreen(FrontEnd.registerPhotographerScreen, FrontEnd.registerPhotographerScreenFXML);
                myController.loadScreen(FrontEnd.managementScreen, FrontEnd.managementScreenFXML);
                myController.loadScreen(FrontEnd.addProductScreen, FrontEnd.addProductScreenFXML);
                myController.setScreen(FrontEnd.mainScreen);
            } else {
                LB_error.setText("Wrong username/password");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

}
