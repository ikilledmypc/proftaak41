/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.PhotoGroup;
import domain.Photo;
import frontend.FrontEnd;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Baya
 */
public class UploadScreenController extends ControlledAccountScreen implements Initializable {

    ScreensController myController;
    ArrayList<String> paths;
    Gson gson = new Gson();
    List<File> files;

    @FXML
    TextField uploadPath;
    @FXML
    CheckBox isPublic;
    @FXML
    TextField groupCode;
    @FXML
    TextField groupNameField;

    private HashMap<File, TextField> selectedPhotos = new HashMap<>();
    @FXML
    TilePane TP_photos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TP_photos.setVgap(20);
        TP_photos.setHgap(20);
//        for(int i=0;i<4;i++){
//            TP_photos.getChildren().add(buildItem("/resources/placeholderPhoto.jpg"));
//        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    public void handleBrowseButtonAction(ActionEvent event) {
        files = myController.chooseFile();
        for (File f : files) {
            VBox h = buildItem(f);
            if(h!=null)
            TP_photos.getChildren().add(h);
        }
    }

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.mainScreen);
    }

    @FXML
    public void handleUploadButtonAction(ActionEvent event) throws IOException {
        String photogroupid = "";
        // if (selectedPhotos.size() > 1) {
        String code = generateCode();
        String groupName = groupNameField.getText();
        photogroupid = HttpController.excutePost(FrontEnd.HOST + "/createPhotoGroup", "photogroup="
                + gson.toJson(new PhotoGroup(this.loggedInAccount.getAccountID(), code, groupName, isPublic.isSelected(), 0)));
        groupCode.setText(code);
        //}
        for (Map.Entry pairs : selectedPhotos.entrySet()) {
            File p = ((File) pairs.getKey());
            BufferedImage image = ImageIO.read(p);
            Photo photo = new Photo(p.getName(), new Date(), Float.parseFloat(((TextField) pairs.getValue()).getText()), image.getHeight(), image.getWidth());

            //if (selectedPhotos.size() > 1) {
            String photoid = HttpController.excutePost(FrontEnd.HOST + "/uploadGroupPhoto", "photo=" + gson.toJson(photo) + "&photogroupID=" + photogroupid);
            photoid = photoid.trim();
            String bla = HttpController.postFile("http://localhost:8080/upload", p.getPath(), Integer.parseInt(photoid));
            System.out.println(bla);
            // }
        }

    }


    public String generateCode() {
        Random rand = new Random();
        int code = rand.nextInt(899999) + 100000;
        String hashcode = Integer.toHexString(code);
        String bezet = HttpController.excuteGet(FrontEnd.HOST + "/checkCodeavailability?hashcode=" + hashcode);
        if (bezet.equals("true")) {
            generateCode();
        } else {
            return hashcode;
        }
        return "";
    }

    private VBox buildItem(File file) {
        try {
            VBox item = new VBox();
            item.setStyle("-fx-border-color: black;");
            item.setSpacing(10);
            ImageView iv = new ImageView();
            Image image = new Image(new FileInputStream(file));
            iv.setPreserveRatio(true);
            iv.setFitWidth(400);
            iv.setImage(image);
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            TextField price = new TextField();
            price.setId(file.getName());
            price.setText("6.50");
            Label priceLabel = new Label();
            priceLabel.setText("price:  \u20ac");
            hbox.getChildren().addAll(priceLabel, price);
            item.getChildren().addAll(iv, hbox);
            if (selectedPhotos.get(file) != null) {
                return null;
            }
            selectedPhotos.putIfAbsent(file, price);
            return item;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
