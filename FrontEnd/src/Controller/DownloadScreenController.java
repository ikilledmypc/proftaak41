/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.*;
import frontend.FrontEnd;
import java.net.URL;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Baya
 */
public class DownloadScreenController extends ControlledAccountScreen implements Initializable {

    ScreensController myController;
    ObservableList<Photo> photos;
    ArrayList<Photo> photoslist;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colName.setCellValueFactory(new PropertyValueFactory<Photo, String>("uploadDate"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Photo, String>("price"));
        photoslist = new ArrayList<>();

    }

    /**
     *
     * @param screenPage screenpage
     */
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    /**
     * set account
     * @param a account
     */
    @Override
    public void setAccount(Account a){
        this.loggedInAccount = a;
        this.photoslist =getRedeemed();
        photos = FXCollections.observableArrayList(this.photoslist);
        photoTable.setItems(photos);
    }

    /**
     * 
     * @param event action event
     */
    @FXML
    public void handleEnterButtonAction(ActionEvent event) {
        Gson gson = new Gson();
        String returnedPhotos = HttpController.excuteGet(FrontEnd.HOST + "/getAllPhotos?code=" + codeEntry.getText() + "&accountID=" + this.loggedInAccount.getAccountID());
        if (!returnedPhotos.equalsIgnoreCase("")) {
            ArrayList<Photo> getPhotos = new ArrayList();
            getPhotos = gson.fromJson(returnedPhotos, new TypeToken<ArrayList<Photo>>() {
            }.getType());
            photos = FXCollections.observableArrayList(getPhotos);
            photoTable.setItems(photos);
        } else {
            System.out.println("Failed to load photos");
        }
    }
    
    /**
     * 
     * @return arraylist of photos
     */
    public ArrayList<Photo> getPhotosList(){
        return this.photoslist;
    }

    private ArrayList<Photo> getRedeemed() {
        Gson gson = new Gson();
        String returnedPhotos = HttpController.excuteGet(FrontEnd.HOST + "/getPreviousRedeemed?accountID=" + this.loggedInAccount.getAccountID());
        if (!returnedPhotos.equalsIgnoreCase("")) {
            ArrayList<Photo> getPhotos = new ArrayList();
            getPhotos = gson.fromJson(returnedPhotos, new TypeToken<ArrayList<Photo>>() {
            }.getType());
            return getPhotos;
        }
        return null;
    }
    

}
