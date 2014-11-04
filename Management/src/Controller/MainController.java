/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Mike
 */
public class MainController implements Initializable, ControlledScreen {

    ScreensController myController;
    Gson gson;
    
    @FXML
    Button btnEdit;
    @FXML
    Button btnRegister;
    @FXML
    Button btnAddProduct;
    @FXML
    Button btnEditProduct;
    @FXML
    Button btnDeleteProduct;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    @FXML
    public void handleRegisterButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.registerPhotographerScreen);
    }
    
    @FXML
    public void handleEditButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.managementScreen);
    }
    
    @FXML
    public void handleAddProductButtonAction(ActionEvent event) {
         myController.setScreen(FrontEnd.addProductScreen);
    }
    
    @FXML
    public void handleEditProductButtonAction(ActionEvent event) {
    }
    
    @FXML
    public void handleDeleteProductButtonAction(ActionEvent event) {
    }
    
}
