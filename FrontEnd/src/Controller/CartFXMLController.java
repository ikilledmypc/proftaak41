/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import domain.*;
import frontend.FrontEnd;
import java.net.URL;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.control.*;
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
            TP_productContainer.getChildren().add(this.buildItem(p));
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
    public void removeProduct(Event event) {
        String key = ((Button) event.getSource()).getId();
        HttpController.excutePost(FrontEnd.HOST + "/removeProduct", "username=" + this.loggedInAccount.getUsername() + "&key=" + key);
        this.shoppingcart.removeProduct(key);
        this.TP_productContainer.getChildren().clear();
        for (Product p : this.shoppingcart.GetProducts()) {
            this.TP_productContainer.getChildren().add(buildItem(p));
        }
    }

    private HBox buildItem(Product p) {
        HBox hb = new HBox();
        hb.setPadding(new Insets(10, 0, 10, 0));
        hb.setStyle("-fx-background-color: lightblue;  -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        hb.setAlignment(Pos.CENTER);
        Label l = new Label();
        l.setPrefWidth(100);
        l.alignmentProperty().setValue(Pos.CENTER_LEFT);
        l.setText(p.getName());
        l.setPadding(new Insets(0, 20, 0, 0));
        Label l2 = new Label();
        l2.alignmentProperty().setValue(Pos.CENTER_LEFT);
        l2.setPrefWidth(100);
        l2.setText("\u20ac" + (p.getMaterialPrice() + p.getPhoto().getPrice()));
        l2.setPadding(new Insets(0, 20, 0, 0));
        Label l3 = new Label();
        l3.setAlignment(Pos.CENTER_RIGHT);
        l3.setPrefWidth(70);
        l3.setText("aantal:");
        TextField tb = new TextField();
        tb.setText(p.getAmount() + "");
        tb.setPrefWidth(50);
        Button b = new Button();
        b.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                removeProduct(event);
            }

        });
        b.setId(p.getProductID() + "" + p.getPhoto().getPhotoID());
        b.setPrefSize(10, 10);
        b.setText("X");
        Pane delteCont = new Pane();
        //p.setPadding(new Insets(0,0,0,20));
        delteCont.setPrefWidth(60);
        delteCont.getChildren().add(b);
        b.setAlignment(Pos.CENTER);
        b.setStyle("-fx-background-color: red; -fx-background-radius: 20; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        hb.getChildren().addAll(delteCont, l, l2, l3, tb);
        return hb;
    }

}
