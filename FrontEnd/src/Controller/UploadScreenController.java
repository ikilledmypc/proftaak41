/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.HttpController;
import frontend.FrontEnd;
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
public class UploadScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    
    @FXML
    TextField uploadPath;

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
    public void handleBrowseButtonAction(ActionEvent event) {
        String path = myController.chooseFile();
        uploadPath.setText(path);
    }
    
    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.mainScreen);
    }
    
    @FXML
    public void handleUploadButtonAction(ActionEvent event) {
        
        String bla = HttpController.excuteGet("http://localhost:8080/upload");
        System.out.println(bla);
        
        HttpController.postFile("http://localhost:8080/upload", uploadPath.getText());
    }
}
