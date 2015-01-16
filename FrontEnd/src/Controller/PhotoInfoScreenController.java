/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import com.google.gson.Gson;
import domain.Photo;
import frontend.FrontEnd;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class PhotoInfoScreenController extends ControlledAccountScreen implements Initializable {

    /**
     * Initializes the controller class.
     */
    ScreensController myController;
    Photo photo;
    @FXML
    ImageView IMG_photo;
    @FXML
    Label LBL_resolutie;
    @FXML
    TextField TF_price;
    @FXML
    Label LBL_uploadDate;
    @FXML
    Label LBL_size;
    @FXML
    TextField TF_name;
    @FXML
    Button BTN_save;
    @FXML
    Label LBL_changesSaved;
    
    ResourceBundle rb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
        TF_price.setText(String.valueOf(photo.getPrice()));
        try {
            IMG_photo.setImage(new Image(new FileInputStream(ThumbnailManager.getThumnail(this.photo.getPhotoID() + ""))));
        } catch (IOException ex) {
            Logger.getLogger(BuyItemScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TF_name.setText(photo.getName());
        LBL_resolutie.setText(String.valueOf(photo.getwidth()) + "x" + String.valueOf(photo.getHeight()));
        LBL_size.setText("12");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        LBL_uploadDate.setText(sdf.format(photo.getUploadDate().getTime()));
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    public void save(){
        String name = TF_name.getText();
        String price = TF_price.getText();
        if(!name.isEmpty() && !price.isEmpty()){
            Photo p = this.photo;
            p.setName(name);
            p.setPrice(Float.parseFloat(price));
            Gson gson = new Gson();
            boolean isUpdated = Boolean.parseBoolean(HttpController.excutePost(FrontEnd.HOST + "/saveChangesPhoto", "photo=" + gson.toJson(p)));
            if(!isUpdated){
                LBL_changesSaved.setText(rb.getString("savePrompt"));
            }
            else{
                LBL_changesSaved.setText(rb.getString("saveFailedPrompt"));
            }
        }
        else{
            LBL_changesSaved.setText(rb.getString("saveFailedPrompt"));
        }  
    }
    
}
