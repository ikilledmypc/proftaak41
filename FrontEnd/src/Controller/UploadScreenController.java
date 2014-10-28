/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.HttpController;
import domain.Photo;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Baya
 */
public class UploadScreenController extends ControlledAccountScreen implements Initializable {
    
    ScreensController myController;
    
    @FXML
    TextField uploadPath;
    @FXML
    TilePane TP_photos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TP_photos.setVgap(20);
        for(int i=0;i<4;i++){
            TP_photos.getChildren().add(buildItem("/resources/placeholderPhoto.jpg"));
        }
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
    
    
    private HBox buildItem(String location){
        HBox item = new HBox();
        ImageView iv = new ImageView(location);
        iv.setFitHeight(100);
        iv.setFitWidth(200);
        VBox box = new VBox();
        TextField prijs = new TextField();
        prijs.setText("6.50");
        box.getChildren().addAll(prijs);
        item.getChildren().addAll(iv,box);
        return item;
    }
}
