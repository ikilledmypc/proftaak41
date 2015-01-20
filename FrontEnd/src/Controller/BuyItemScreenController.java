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

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.*;
import javafx.beans.value.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
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
    @FXML
    CheckBox CB_normaalfilter;
    @FXML
    CheckBox CB_zwfilter;
    @FXML
    CheckBox CB_sephiafilter;

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
                    double scaleratio = IMG_photo.getImage().getHeight() / 250;
                    System.out.println(scaleratio);
                    IMG_photo.setViewport(new Rectangle2D(cropX * scaleratio, cropY * scaleratio, (cropWidth + 14) * scaleratio, (cropHeight + 14) * scaleratio));
                    if (croppingRectangle != null) {
                        ap.getChildren().remove(croppingRectangle);
                    }
                }
            }

        });

        CB_normaalfilter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CB_sephiafilter.setSelected(false);
                CB_zwfilter.setSelected(false);
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setSaturation(0);
                IMG_photo.setEffect(colorAdjust);
            }
        });

        CB_sephiafilter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CB_normaalfilter.setSelected(false);
                CB_zwfilter.setSelected(false);
                ColorAdjust colorAdjust = new ColorAdjust();
                IMG_photo.setEffect(new SepiaTone());
            }
        });

        CB_zwfilter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CB_sephiafilter.setSelected(false);
                CB_normaalfilter.setSelected(false);
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setSaturation(-1);
                IMG_photo.setEffect(colorAdjust);
            }
        });
    }

    /**
     * set photo and price by photo
     * @param photo photo
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
        LBL_photoPrice.setText("\u20ac" + photo.getPrice());
        try {
            IMG_photo.setImage(new Image(new FileInputStream(ThumbnailManager.getThumnail(this.photo.getPhotoID() + ""))));
        } catch (IOException ex) {
            Logger.getLogger(BuyItemScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * set screen to mycontroller
     * @param screenPage screenpage
     */
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    /**
     * add products to shopping cart
     * @param ev a action
     */
    @FXML
    public void addToCart(Event ev) {
        Gson gson = new Gson();
        Product p = (Product) CMB_items.getSelectionModel().getSelectedItem();
        p.setPhoto(this.photo);
        p.setBlackWhite(CB_zwfilter.isSelected());
        p.setSepia(CB_sephiafilter.isSelected());
        if(croppingRectangle!=null){
            double scaleratio = IMG_photo.getImage().getHeight() / 250;
            p.setCropX((int) (cropX * scaleratio));
            p.setCropY((int) (cropY * scaleratio));
            p.setCropWidth((int) ((cropWidth + 14) * scaleratio));
            p.setCropHeight((int) ((cropHeight + 14) * scaleratio));
        }
        if (Integer.parseInt(TF_amount.getText()) > 1) {
            p.addAmount(Integer.parseInt(TF_amount.getText())-1);
        }
        String sProduct = gson.toJson(p);
        HttpController.excutePost(FrontEnd.HOST + "/addToCart", "product=" + sProduct + "&username=" + this.loggedInAccount.getUsername());
        ((Node) ev.getSource()).getScene().getWindow().hide();
    }

}
