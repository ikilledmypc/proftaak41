/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

/**
 *
 * @author Mike Rooijackers
 */
public class MainController extends ControlledAccountScreen implements Initializable {

    ScreensController myController;
    
    @FXML
    Label LBL_username;

    @FXML
    TilePane TP_photoContainer;
    
    @FXML
    Button BTN_cart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }


    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.loginScreen);
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.registerScreen);
    }

    @FXML
    private void handleRegisterPhotographerButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.registerPhotographerScreen);
    }

    @FXML
    private void handlePhotographerAction(ActionEvent event) {
        myController.setScreen(FrontEnd.managementScreen);
    }
}
