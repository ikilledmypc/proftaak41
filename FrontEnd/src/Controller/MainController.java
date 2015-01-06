/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import domain.Account;
import domain.Photo;
import domain.Photographer;
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
import javafx.scene.control.TextField;
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
    private ResourceBundle recources;
    @FXML
    Label LBL_username;

    @FXML
    TilePane TP_photoContainer;

    @FXML
    Button BTN_cart;

    @FXML
    Button uploadButton;
    
    @FXML
    Button statsButton;
    
    @FXML 
    TextField TF_code;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TP_photoContainer.setHgap(10);
        TP_photoContainer.setVgap(10);
        this.recources = rb;
    }

    public void setRedeemedPhotos(ArrayList<Photo> photos) {
        this.ownedPhotos = photos;
    }

    public void loadThumbs() {
        this.TP_photoContainer.getChildren().clear();
        this.ownedPhotos = DownloadScreenController.getOwnedPhotos(this.loggedInAccount.getAccountID());
        if (this.ownedPhotos != null) {
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
                    if (newValue == Worker.State.SUCCEEDED) {
                        try {
                            HashMap<Photo, File> photofiles = (HashMap<Photo, File>) tdm.get();
                            for (Map.Entry<Photo, File> item : photofiles.entrySet()) {
                                TP_photoContainer.getChildren().add(buildPhotoItem(item.getKey(), item.getValue()));
                            }

                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex) {
                            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void setAccount(Account a) {
        this.loggedInAccount = a;
        LBL_username.setText(a.getName());
        this.ownedPhotos = DownloadScreenController.getOwnedPhotos(a.getAccountID());
        if (a instanceof Photographer) {
            this.BTN_cart.setVisible(false);
            this.statsButton.setVisible(true);
            TF_code.setVisible(false);
            this.uploadButton.setText(recources.getString("uploadPhotosButton"));
            this.uploadButton.setOnAction((ActionEvent event) -> {
                try {
                    uploadPhotos();
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } else {
            this.statsButton.setVisible(false);
            this.uploadButton.setText(this.recources.getString("enterKeyButton"));
            this.uploadButton.setOnAction((ActionEvent event) -> {
                try {
                    enterKey();
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
        loadThumbs();
        updateCart();
    }

    private void uploadPhotos() throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/UploadScreen.fxml"));
        loader.setResources(recources);
        root = loader.load();
        UploadScreenController controller = loader.getController();
        controller.setAccount(loggedInAccount);
        controller.setScreenParent(parent);
        Stage stage = new Stage();
        stage.setTitle("upload photos");
        stage.setScene(new Scene(root));
        stage.addEventHandler(WindowEvent.WINDOW_HIDING, new EventHandler() {
            @Override
            public void handle(Event event) {
                loadThumbs();
            }

        });
        String defaultPrice = HttpController.excuteGet(FrontEnd.HOST + "/getDefaultPricePhoto?accountID=" + this.loggedInAccount.getAccountID());
        controller.defaultField.setText(defaultPrice);
        stage.show();
    }

    private void enterKey() throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/DownloadScreen.fxml"));
        loader.setResources(recources);
        root = loader.load();
        DownloadScreenController controller = loader.getController();
        controller.setAccount(loggedInAccount);
        Stage stage = new Stage();
        stage.setTitle("enter key");
        stage.setScene(new Scene(root));
        stage.addEventHandler(WindowEvent.WINDOW_HIDING, new EventHandler() {
            @Override
            public void handle(Event event) {
                loadThumbs();
            }

        });
        stage.show();
    }

    private void updateCart() {
        Gson gson = new Gson();
        String sCart = HttpController.excuteGet(FrontEnd.HOST + "/getCart?username=" + this.loggedInAccount.getUsername());
        ShoppingCart s = gson.fromJson(sCart, ShoppingCart.class);
        this.shoppingCart = s;
        BTN_cart.setText(recources.getString("shoppingcartButton")+"(" + s.getItemCount()+")");
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
                        loader.setResources(recources);
                        root = loader.load();
                        BuyItemScreenController controller = loader.getController();
                        controller.setAccount(loggedInAccount);
                        controller.setPhoto(p);
                        Stage stage = new Stage();
                        stage.setTitle(p.getUploadDate().toString());
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
    
    @FXML
    private void statsScreen()throws IOException{
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/StatsScreen.fxml"));
        loader.setResources(recources);
        root = loader.load();
        statsScreenController controller = loader.getController();
        controller.setAccount(loggedInAccount);
        controller.setScreenParent(parent);
        Stage stage = new Stage();
        stage.setTitle("Statistieken");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
