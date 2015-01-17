/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. b088a 8e130
 */
package Controller;

import com.google.gson.Gson;
import domain.*;
import frontend.FrontEnd;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.logging.*;
import javafx.beans.value.*;
import javafx.concurrent.Worker;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.*;
import workers.ThumbnailDownloadWorker;

/**
 *
 * @author Baya
 */
public class MainController extends ControlledAccountScreen implements Initializable {

    private ShoppingCart shoppingCart;
    //private ArrayList<Photo> ownedPhotos;
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


    @FXML
    Button BTN_back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TP_photoContainer.setHgap(10);
        TP_photoContainer.setVgap(10);
        this.recources = rb;
        BTN_back.setVisible(false);
        BTN_back.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                loadThumbs();
            }
        });
    }

    /**
     *
     */
    public void loadThumbs() {

        BTN_back.setVisible(false);
        this.TP_photoContainer.getChildren().clear();
        loggedInAccount.updateClaimedPhotos();
        if (this.loggedInAccount.getClaimedPhotos() != null) {
            for (PhotoGroup pg : this.loggedInAccount.getClaimedPhotos()) {
                ArrayList<Photo> photo = new ArrayList<Photo>();
                photo.add(pg.getPhotos().get(0));
                ThumbnailDownloadWorker tdm = new ThumbnailDownloadWorker(photo);
                tdm.stateProperty().addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                        if (newValue == Worker.State.SUCCEEDED) {
                            try {
                                HashMap<Photo, File> photofiles = (HashMap<Photo, File>) tdm.get();
                                Map.Entry<Photo, File> item = photofiles.entrySet().iterator().next();
                                TP_photoContainer.getChildren().add(buildPhotoGroupItem(item.getKey(), item.getValue(), pg.getGroupName()));
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ExecutionException ex) {
                                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
                new Thread(tdm).start();
                try {
                    this.parent.displaySplashProgress(tdm, "downloading group.." + pg.getGroupName());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    /**
     *
     * @param a
     */
    @Override
    public void setAccount(Account a) {
        this.loggedInAccount = a;
        LBL_username.setText(a.getName());


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
        if (!TF_code.getText().equals("")) {
            HttpController.excutePost(FrontEnd.HOST + "/redeemCode", "code=" + TF_code.getText() + "&accountID=" + this.loggedInAccount.getAccountID());
            loadThumbs();
        }
    }

    private void updateCart() {
        Gson gson = new Gson();
        String sCart = HttpController.excuteGet(FrontEnd.HOST + "/getCart?username=" + this.loggedInAccount.getUsername());
        ShoppingCart s = gson.fromJson(sCart, ShoppingCart.class);
        this.shoppingCart = s;
        BTN_cart.setText(recources.getString("shoppingcartButton") + "(" + s.getItemCount() + ")");
    }

    /**
     *
     */
    @FXML
    public void opencart() {
        this.parent.loadAccountScreen("cart", "/view/CartFXML.fxml", this.loggedInAccount);
        this.parent.setScreen("cart");
    }

    /**
     *
     * @param p
     * @param f
     * @return
     */
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
                        stage.setTitle(p.getName());
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
            Label name = new Label(p.getName());
            item.getChildren().add(photo);
            item.getChildren().add(name);
            return item;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public VBox buildPhotoGroupItem(final Photo p, File f, String groupname) {
        try {
            VBox item = new VBox(5);
            item.setPrefSize(250, 230);
            Image image = new Image(new FileInputStream(f));
            ImageView photo = new ImageView("resources/placeholderPhoto.jpg");
            photo.setImage(image);
            photo.setId(groupname);
            photo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
                @Override
                public void handle(Event event) {
                    viewGroup(loggedInAccount.getGroup(photo.getId()));
                }

            });
            photo.setFitWidth(250);
            photo.setFitHeight(200);
            Label name = new Label(groupname);
            item.getChildren().add(photo);
            item.getChildren().add(name);
            return item;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void viewGroup(PhotoGroup group) {
        BTN_back.setVisible(true);
        TP_photoContainer.getChildren().clear();
        ThumbnailDownloadWorker tdm = new ThumbnailDownloadWorker(group.getPhotos());
        tdm.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    try {
                        HashMap<Photo, File> photofiles = (HashMap<Photo, File>) tdm.get();
                        for (Map.Entry<Photo, File> entry : photofiles.entrySet()) {
                            TP_photoContainer.getChildren().add(buildPhotoItem(entry.getKey(), entry.getValue()));
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        new Thread(tdm).start();
        try {
            parent.displaySplashProgress(tdm, "downloading group.." + group.getGroupName());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void statsScreen() throws IOException {
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
