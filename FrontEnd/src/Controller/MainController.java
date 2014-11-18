/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import domain.Account;
import domain.Photo;
import domain.ShoppingCart;
import frontend.FrontEnd;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import workers.ThumbnailDownloadWorker;

/**
 *
 * @author Baya
 */
public class MainController extends ControlledAccountScreen implements Initializable {


    private ShoppingCart shoppingCart;
    private ArrayList<Photo> ownedPhotos; 
    @FXML
    Label LBL_username;

    @FXML
    TilePane TP_photoContainer;

    @FXML
    Button BTN_cart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FileOutputStream fos = null;
        TP_photoContainer.setHgap(10);
        TP_photoContainer.setVgap(10); 
    }
    
    public void setRedeemedPhotos(ArrayList<Photo> photos){
        this.ownedPhotos = photos;
    }

    @Override
    public void setAccount(Account a) {
        this.loggedInAccount = a;
        LBL_username.setText(a.getName());
        this.ownedPhotos = DownloadScreenController.getOwnedPhotos(a.getAccountID());
        if(this.ownedPhotos!=null){
            HashMap<Photo,File> photofiles;
            ThumbnailDownloadWorker tdm = new ThumbnailDownloadWorker(this.ownedPhotos);            
            new Thread(tdm).start();
            try {
                this.parent.displaySplashProgress(tdm, "downloading photos...");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tdm.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
               if(newValue == Worker.State.SUCCEEDED){
//                   try {
//                       HashMap<Photo,File> photofiles = (HashMap<Photo,File>) tdm.get();
//                       for(Map.Entry<Photo,File> item :photofiles.entrySet()){
//                           TP_photoContainer.getChildren().add(buildPhotoItem(item.getKey(),item.getValue()));
//                       }
//                       
//                   } catch (InterruptedException ex) {
//                       Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//                   } catch (ExecutionException ex) {
//                       Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//                   }
              }
            }            
        });
//            for(Photo p: this.ownedPhotos){
//                try {
//                    TP_photoContainer.getChildren().add(buildPhotoItem(p,ThumbnailManager.getThumnail(p.getPhotoID()+".jpg")));
//                } catch (IOException ex) {
//                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }
        updateCart();
    }


    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        this.parent.setScreen(FrontEnd.loginScreen);
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        this.parent.setScreen(FrontEnd.registerScreen);
    }

    @FXML
    private void handleRegisterPhotographerButtonAction(ActionEvent event) {
        this.parent.setScreen(FrontEnd.registerPhotographerScreen);
    }

    @FXML
    private void handleUploadButtonAction(ActionEvent event) {
        this.parent.setScreen(FrontEnd.uploadScreen);
    }

    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        this.parent.setScreen(FrontEnd.downloadScreen);
    }



    private void updateCart() {
        Gson gson = new Gson();
        String sCart = HttpController.excuteGet(FrontEnd.HOST + "/getCart?username=" + this.loggedInAccount.getUsername());
        ShoppingCart s = gson.fromJson(sCart, ShoppingCart.class);
        this.shoppingCart = s;
        BTN_cart.setText("cart (" + s.getItemCount() + " items)");
    }

    @FXML
    public void opencart() {
        this.parent.loadAccountScreen("cart", "/view/CartFXML.fxml", this.loggedInAccount);
        this.parent.setScreen("cart");
    }

    public VBox buildPhotoItem(final Photo p, File f) {
        try {
            VBox item = new VBox(5);
            item.setPrefSize(250, 230);
            Image image = new Image(new FileInputStream(f));
            ImageView photo = new ImageView("resources/placeholderPhoto.jpg");
            photo.setImage(image);
            photo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
                @Override
                public void handle(Event event) {
                    try {
                        Parent root;
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/BuyItemScreen.fxml"));
                        root = loader.load();
                        BuyItemScreenController controller = loader.getController();
                        controller.setAccount(loggedInAccount);
                        controller.setPhoto(p);
                        Stage stage = new Stage();
                        stage.setTitle("My New Stage Title");
                        stage.setScene(new Scene(root, 640, 430));
                        stage.show();
                        stage.addEventHandler(WindowEvent.WINDOW_HIDING, new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                updateCart();
                            }

                        });
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            photo.setFitWidth(250);
            photo.setFitHeight(200);
            Label name = new Label(p.getPhotoID() + " " + p.getUploadDate());
            item.getChildren().add(photo);
            item.getChildren().add(name);
            return item;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
