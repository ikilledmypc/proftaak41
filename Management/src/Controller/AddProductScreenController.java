/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import domain.Product;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Mike
 */
public class AddProductScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    Gson gson;
    
    @FXML
    Button btnBack;
    @FXML
    Button btnSave;
    @FXML
    TextField tbName;
    @FXML
    TextField tbPrice;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     *
     * @param screenPage
     */
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    /**
     *
     * @param event
     */
    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.mainScreen);
    }
    
    /**
     *
     * @param event
     */
    @FXML
    public void handleSaveButtonAction(ActionEvent event) {
        try {
            gson = new Gson();
            Product product = new Product(tbName.getText(), Float.parseFloat(tbPrice.getText()));
            HttpController.excutePost(FrontEnd.HOST + "/addProduct", "product=" + gson.toJson(product));
            myController.setScreen(FrontEnd.addProductScreen);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
