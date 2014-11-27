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
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import workers.PhotoUploadWorker;

/**
 * FXML Controller class
 *
 * @author Baya
 */
public class UploadScreenController extends ControlledAccountScreen implements Initializable {

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
    @FXML
    Button uploadButton;

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
        uploadButton.setDisable(true);
    }

    @FXML
    public void handleBrowseButtonAction(ActionEvent event) {
        files = this.parent.chooseFile();
        if (files != null) {
            for (File f : files) {
                VBox h = buildItem(f);
                if (h != null) {
                    TP_photos.getChildren().add(h);
                }
            }
        }
        if (this.selectedPhotos.size() > 0) {
            uploadButton.setDisable(false);
        }
    }

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        this.parent.setScreen(FrontEnd.mainScreen);
    }

    @FXML
    public void handleUploadButtonAction(ActionEvent event) throws IOException, InterruptedException, ExecutionException {
        PhotoUploadWorker puw = new PhotoUploadWorker(groupNameField.getText(), selectedPhotos, loggedInAccount);
        this.parent.displaySplashProgress(puw, "uploading...");
        new Thread(puw).start();
        puw.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {

                    try {
                        groupCode.setText((String) puw.get());
                        HttpController.excuteGet(FrontEnd.HOST + "/getAllPhotos?code=" + groupCode.getText() + "&accountID=" + loggedInAccount.getAccountID());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UploadScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(UploadScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

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
