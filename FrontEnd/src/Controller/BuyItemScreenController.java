/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Photo;
import domain.Product;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author tim
 */
public class BuyItemScreenController extends ControlledAccountScreen implements Initializable {

    /**
     * Initializes the controller class.
     */
    ScreensController myController;
    Photo photo;
    @FXML
    ComboBox CMB_items;
    @FXML
    ImageView IMG_photo;
    @FXML
    Label LBL_productPrice;
    @FXML
    Label LBL_photoPrice;
    @FXML
    Label LBL_totalPrice;
     
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       String itemsString = HttpController.excuteGet(FrontEnd.HOST+"/getAllProducts");
       Gson gson = new Gson();
       ArrayList<Product> items =gson.fromJson(itemsString,new TypeToken<ArrayList<Product>>(){}.getType());
       CMB_items.getItems().addAll(items);
       IMG_photo.setImage(new Image("/resources/placeholderPhoto.jpg"));
       CMB_items.valueProperty().addListener(new ChangeListener<Product>(){
           @Override
           public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
               LBL_productPrice.setText("\u20ac"+newValue.getMaterialPrice()+"");
           }           
       });
       
       
    } 
    
    public void setPhoto(Photo photo){
        this.photo = photo;
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    @FXML
    public void addToCart(){
        
        Gson gson = new Gson();
        Product p = (Product) CMB_items.getSelectionModel().getSelectedItem();
        p.setPhoto(this.photo);
        String sProduct = gson.toJson(p);
        HttpController.excutePost(FrontEnd.HOST+"/addToCart", "product="+sProduct+"&username="+this.loggedInAccount.getUsername());
    }
    
}
