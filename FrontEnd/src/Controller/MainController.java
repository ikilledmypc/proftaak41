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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author Baya
 */
public class MainController extends ControlledAccountScreen implements Initializable {

    ScreensController myController;
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
//        for (int i = 0; i < 30; i++) {
//            try {
//                TP_photoContainer.getChildren().add(buildPhotoItem(new Photo(i, new Date(), (float) 2.3),ThumbnailManager.getThumnail("KxUHaU0.jpg")));
//            } catch (IOException ex) {
//                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
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
            for(Photo p: this.ownedPhotos){
                try {
                    TP_photoContainer.getChildren().add(buildPhotoItem(p,ThumbnailManager.getThumnail(p.getPhotoID()+".jpg")));
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        updateCart();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.loginScreen);
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.registerScreen);
    }

    @FXML
    private void handleRegisterPhotographerButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.registerPhotographerScreen);
    }

    @FXML
    private void handleUploadButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.uploadScreen);
    }

    @FXML
    private void handleLoadButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.downloadScreen);
    }

    @FXML
    private void handlePhotographerAction(ActionEvent event) {
        myController.setScreen(FrontEnd.managementScreen);
    }

    @FXML
    private void handleBuyAction(ActionEvent event) {
        myController.setScreen(FrontEnd.buyItemScreen);
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
        myController.loadAccountScreen("cart", "/view/CartFXML.fxml", this.loggedInAccount);
        myController.setScreen("cart");
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
