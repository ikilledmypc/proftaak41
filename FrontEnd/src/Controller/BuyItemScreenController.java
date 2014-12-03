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

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    @FXML
    TextField TF_amount;
    @FXML
    Button btn_crop;

    private double cropX = 0;
    private double cropY = 0;
    private double cropWidth = 0;
    private double cropHeight = 0;
    private Rectangle croppingRectangle;
    private boolean cropping = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String itemsString = HttpController.excuteGet(FrontEnd.HOST + "/getAllProducts");
        Gson gson = new Gson();
        ArrayList<Product> items = gson.fromJson(itemsString, new TypeToken<ArrayList<Product>>() {
        }.getType());
        CMB_items.getItems().addAll(items);
        AnchorPane ap = (AnchorPane) CMB_items.getParent();

        CMB_items.valueProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
                LBL_productPrice.setText(" \u20ac" + newValue.getMaterialPrice() + "");
                if (photo != null) {
                    LBL_totalPrice.setText("\u20ac" + (newValue.getMaterialPrice() + photo.getPrice()));
                }
            }
        });
        btn_crop.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                btn_crop.setDisable(true);
                IMG_photo.setViewport(null);
                if (croppingRectangle != null) {
                    ap.getChildren().remove(croppingRectangle);
                }
                cropping = true;
            }
        });
        IMG_photo.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (cropping) {
                    cropX = event.getX();
                    cropY = event.getY();
                    croppingRectangle = new Rectangle(cropX + 14, cropY + 14, 0, 30);
                    croppingRectangle.setFill(Color.TRANSPARENT);
                    croppingRectangle.setStroke(Color.RED);
                    ap.getChildren().add(croppingRectangle);
                }
            }

        });
        IMG_photo.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (cropping) {
                    cropWidth = event.getX() - croppingRectangle.getX();
                    cropHeight = event.getY() - croppingRectangle.getY();
                    croppingRectangle.setWidth(cropWidth + 14);
                    croppingRectangle.setHeight(cropHeight + 14);
                }
            }

        });

        IMG_photo.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (cropping) {
                    cropping = false;
                    btn_crop.setDisable(false);
                    //ratio image scaling to fit in hight 
                    double scaleratio = IMG_photo.getImage().getHeight()/250;
                    System.out.println(scaleratio);
                    IMG_photo.setViewport(new Rectangle2D(cropX * scaleratio, cropY * scaleratio, (cropWidth + 14) * scaleratio, (cropHeight + 14) * scaleratio));
                    if (croppingRectangle != null) {
                        ap.getChildren().remove(croppingRectangle);
                    }
                }
            }

        });

    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
        LBL_photoPrice.setText("\u20ac" + photo.getPrice());
        try {
            IMG_photo.setImage(new Image(new FileInputStream(ThumbnailManager.getThumnail(this.photo.getPhotoID() + ""))));
        } catch (IOException ex) {
            Logger.getLogger(BuyItemScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    public void addToCart(Event ev) {
        Gson gson = new Gson();
        Product p = (Product) CMB_items.getSelectionModel().getSelectedItem();
        p.setPhoto(this.photo);
        if (Integer.parseInt(TF_amount.getText()) > 1) {
            p.addAmount(Integer.parseInt(TF_amount.getText()) - 1);
        }
        String sProduct = gson.toJson(p);
        HttpController.excutePost(FrontEnd.HOST + "/addToCart", "product=" + sProduct + "&username=" + this.loggedInAccount.getUsername());
        ((Node) ev.getSource()).getScene().getWindow().hide();
    }

}
