/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.HttpController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Photo;
import frontend.FrontEnd;
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
    ObservableList<Photo> photos;
    
    @FXML
    TextField codeEntry;
    @FXML
    TableView<Photo> photoTable;
    @FXML
    TableColumn colName;
    @FXML
    TableColumn colPrice;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colName.setCellValueFactory(new PropertyValueFactory<Photo,String>("photoID"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Photo,String>("price"));
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    @FXML
    public void handleEnterButtonAction(ActionEvent event) {
        Gson gson = new Gson();
        if(MainController.loggedInAccount == null)
        {
            System.out.println("User not logged in");
            return;
        }
        String returnedPhotos = HttpController.excuteGet(FrontEnd.HOST+"/getAllPhotos?code="+codeEntry.getText()+"&accountID="+MainController.loggedInAccount.getAccountID());
        if(!returnedPhotos.equalsIgnoreCase("")) {
            ArrayList<Photo> getPhotos = new ArrayList();
            getPhotos = gson.fromJson(returnedPhotos, new TypeToken<ArrayList<Photo>>(){}.getType());
            photos = FXCollections.observableArrayList(getPhotos);
            photoTable.setItems(photos);
        } else {
            System.out.println("Failed to load photos");
        }
   }
}
