/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. b1c98 
*/
package Controller;

import com.google.gson.Gson;
import domain.*;
import frontend.FrontEnd;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author tim
 */
public class CartFXMLController extends ControlledAccountScreen implements Initializable {

    @FXML
    TilePane TP_productContainer;
    @FXML
    VBox HB_product;
    @FXML
    ScrollPane SP_scroll;
    private ShoppingCart shoppingcart;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SP_scroll.setStyle("-fx-background-color: white; -fx-background-insets: 0; -fx-padding: 0;");
        //TP_productContainer.setStyle("-fx-background-color: black;");
        TP_productContainer.setPadding(new Insets(10, 10, 10, 10));

    }

    /**
     * set account
     * @param a account
     */
    @Override
    public void setAccount(Account a) {
        this.loggedInAccount = a;
        Gson gson = new Gson();
        String scart = HttpController.excuteGet(FrontEnd.HOST + "/getCart?username=" + a.getUsername());
        ShoppingCart cart = gson.fromJson(scart, ShoppingCart.class);
        this.shoppingcart = cart;
        ArrayList<Product> products = cart.GetProducts();
        for (Product p : products) {
            try {
                TP_productContainer.getChildren().add(this.buildItem(p));
            } catch (IOException ex) {
                Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * cleart shopping cart
     */
    @FXML
    public void clearCart() {
        HttpController.excuteGet(FrontEnd.HOST + "/clearCart?username=" + this.loggedInAccount.getUsername());
        TP_productContainer.getChildren().clear();
    }

    /**
     * checkout shopping cart
     */
    @FXML
    public void checkOut() {
        this.parent.loadAccountScreen("checkout", "/view/CheckOutScreen.fxml", this.loggedInAccount);
        this.parent.setScreen("checkout");
        placeOrder();
    }

    /**
     * place order
     */
    public void placeOrder() {
        Gson gson = new Gson();
        HttpController.excutePost(FrontEnd.HOST + "/placeOrder", "cart=" + gson.toJson(this.shoppingcart) + "&accountID=2");
    }

    /**
     * remove products
     * @param event action event
     */
    @FXML
    public void removeProduct(Event event) throws IOException {
        String key = ((Button) event.getSource()).getId();
        HttpController.excutePost(FrontEnd.HOST + "/removeProduct", "username=" + this.loggedInAccount.getUsername() + "&key=" + key);
        this.shoppingcart.removeProduct(key);
        this.TP_productContainer.getChildren().clear();
        for (Product p : this.shoppingcart.GetProducts()) {
            this.TP_productContainer.getChildren().add(buildItem(p));
        }
    }

    private HBox buildItem(Product p) throws IOException {
        HBox hb = new HBox();
        hb.setPadding(new Insets(10, 0, 10, 0));
        hb.setSpacing(20);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setStyle("-fx-background-color: lightblue;  -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        //hb.setAlignment(Pos.CENTER);
        ImageView iv = new ImageView(new Image(new FileInputStream(ThumbnailManager.getThumnail(p.getPhoto().getPhotoID()+""))));
        iv.setPreserveRatio(true);
        iv.fitHeightProperty().set(130);
        Label l = new Label();
        l.setPrefWidth(100);
        //l.alignmentProperty().setValue(Pos.CENTER_LEFT);
        l.setText(p.getName());
        l.setPadding(new Insets(0, 20, 0, 0));
        Label l2 = new Label();
        //l2.alignmentProperty().setValue(Pos.CENTER_LEFT);
        l2.setPrefWidth(100);
        l2.setText("\u20ac" + (p.getMaterialPrice() + p.getPhoto().getPrice()));
        l2.setPadding(new Insets(0, 20, 0, 0));
        Label l3 = new Label();
        //l3.setAlignment(Pos.CENTER_RIGHT);
        l3.setPrefWidth(70);
        l3.setText("aantal:");
        TextField tb = new TextField();
        tb.setText(p.getAmount() + "");
        //tb.setPrefWidth(50);
        Button b = new Button();
        b.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                try {
                    removeProduct(event);
                } catch (IOException ex) {
                    Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        b.setId(p.getIdentifier());
        b.setPrefSize(10, 10);
        b.setText("X");
        b.setAlignment(Pos.CENTER_RIGHT);

        HBox ivcont = new HBox();
        ivcont.setPrefWidth(200);
        ivcont.getChildren().add(iv);
        ivcont.setAlignment(Pos.CENTER);
        b.setStyle("-fx-background-color: red; -fx-background-radius: 20; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        hb.getChildren().addAll(b,ivcont, l, l2, l3, tb);
        return hb;
    }

}
