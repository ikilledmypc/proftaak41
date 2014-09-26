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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mike
 */
public class ManagementPhotographerController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    @FXML
    Button btnSearch;
    
    @FXML
    TextField tbSearch;
    
    @FXML
    RadioButton False;
    
    @FXML
    RadioButton True;
    
    @FXML
    Button btnBack;
    
    @FXML
    Button btnSave;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.mainScreen);
    }
    
    @FXML
    public void handleSearchButtonAction(ActionEvent event) {
        
    }
    
    @FXML
    public void handleSaveButtonAction(ActionEvent event) {
        
    }
    
}
