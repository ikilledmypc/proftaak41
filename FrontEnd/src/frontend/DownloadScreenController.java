/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import classes.Photo;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Baya
 */
public class DownloadScreenController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    ObservableList<Photo> photos = FXCollections.observableArrayList();
    ObservableList<Photo> photos2 = FXCollections.observableArrayList();
    
    @FXML
    TextField codeEntry;
    @FXML
    TableView<Photo> photoTable;
    @FXML
    TableColumn colName;
    @FXML
    TableColumn colSize;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Photo photo1 = new Photo("123", "Photo1.jpg", "2mb");
        Photo photo2 = new Photo("123", "Photo2.jpg", "1.5mb");
        Photo photo3 = new Photo("123", "Photo3.jpg", "3mb");
        Photo photo4 = new Photo("321", "Photo4.jpg", "4mb");
        Photo photo5 = new Photo("321", "Photo5.jpg", "5.5mb");
        photos.add(photo1);
        photos.add(photo2);
        photos.add(photo3);
        photos.add(photo4);
        photos.add(photo5);
        colName.setCellValueFactory(new PropertyValueFactory<Photo,String>("name"));
        colSize.setCellValueFactory(new PropertyValueFactory<Photo,String>("size"));
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    @FXML
    public void handleEnterButtonAction(ActionEvent event) {
        for(int i=0; i<photos.size(); i++) {
            if(photos.get(i).code.equals(codeEntry.getText())) {
                photos2.add(photos.get(i));
                System.out.println(photos.get(i).code + "-" + photos.get(i).name + "-" + photos.get(i).size);
            }
        }
        photoTable.setItems(photos2);
    }
}
