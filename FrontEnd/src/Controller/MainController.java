/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import domain.Account;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Baya
 */
public class MainController extends ControlledAccountScreen implements Initializable {

    ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void handleUploadButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.uploadScreen);
    }

    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.downloadScreen);
    }

    @FXML
    private void handlePhotographerAction(ActionEvent event) {
        myController.setScreen(FrontEnd.managementScreen);
    }
    
    @FXML
    private void handleBuyAction(ActionEvent event) {
        myController.setScreen(FrontEnd.buyItemScreen);
    }
    @FXML
    public void opencart(){
        myController.loadAccountScreen("cart", "/view/CartFXML.fxml", this.loggedInAccount);
        myController.setScreen("cart");
    }
}
