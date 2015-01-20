/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import domain.Account;
import domain.Product;
import domain.ShoppingCart;
import frontend.FrontEnd;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Mr. Jin
 */
public class CheckOutScreenController extends ControlledAccountScreen implements Initializable {

    ResourceBundle recources;
    @FXML
    TextField tf_name;
    @FXML
    TextField tf_address;
    @FXML
    TextField tf_zipcode;
    @FXML
    TextField tf_city;
    @FXML
    TextField tf_email;
    @FXML
    TextField tf_telephone;
    @FXML
    TextField tf_totalPrice;
    @FXML
    TilePane tp_product;
    @FXML
    VBox HB_product;
    @FXML
    ScrollPane sp_scroll;
    @FXML
    Button btn_back;

    private ShoppingCart shoppingcart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.recources = rb;

        sp_scroll.setStyle("-fx-background-color: white; -fx-background-insets: 0; -fx-padding: 0;");
        //TP_productContainer.setStyle("-fx-background-color: black;");
        tp_product.setPadding(new Insets(10, 10, 10, 10));
    }

    @Override
    public void setAccount(Account a) {
        this.loggedInAccount = a;
        tf_name.setText(a.getName());
        tf_address.setText(a.getAddress());
        tf_zipcode.setText(a.getZipcode());
        tf_city.setText(a.getCity());
        tf_email.setText(a.getEmail());
        tf_telephone.setText(a.getTelephone());

        float total = 0;
        Gson gson = new Gson();
        String scart = HttpController.excuteGet(FrontEnd.HOST + "/getCart?username=" + a.getUsername());
        ShoppingCart cart = gson.fromJson(scart, ShoppingCart.class);
        this.shoppingcart = cart;
        ArrayList<Product> products = cart.GetProducts();
        for (Product p : products) {
            try {
                tp_product.getChildren().add(this.buildItem(p));
                total += (p.getMaterialPrice() + p.getPhoto().getPrice());
            } catch (IOException ex) {
                Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tf_totalPrice.setText(Float.toString(total));
    }

    @FXML
    public void removeProduct(Event event) throws IOException {
        String key = ((Button) event.getSource()).getId();
        HttpController.excutePost(FrontEnd.HOST + "/removeProduct", "username=" + this.loggedInAccount.getUsername() + "&key=" + key);
        this.shoppingcart.removeProduct(key);
        this.tp_product.getChildren().clear();
        float total = 0;
        for (Product p : this.shoppingcart.GetProducts()) {
            this.tp_product.getChildren().add(buildItem(p));
            total += (p.getMaterialPrice() + p.getPhoto().getPrice());
        }
        tf_totalPrice.setText(Float.toString(total));
    }

    private HBox buildItem(Product p) throws IOException {
        HBox hb = new HBox();
        hb.setPadding(new Insets(10, 0, 10, 0));
        hb.setSpacing(20);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setStyle("-fx-background-color: lightblue;  -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        //hb.setAlignment(Pos.CENTER);
        ImageView iv = new ImageView(new Image(new FileInputStream(ThumbnailManager.getThumnail(p.getPhoto().getPhotoID() + ""))));
        iv.setPreserveRatio(true);
        iv.fitHeightProperty().set(130);
        Label l = new Label();
        l.setPrefWidth(60);
        //l.alignmentProperty().setValue(Pos.CENTER_LEFT);
        l.setText(p.getName());
        l.setPadding(new Insets(0, 20, 0, 0));
        Label l2 = new Label();
        //l2.alignmentProperty().setValue(Pos.CENTER_LEFT);
        l2.setPrefWidth(50);
        l2.setText("\u20ac" + (p.getMaterialPrice() + p.getPhoto().getPrice()));
        l2.setPadding(new Insets(0, 20, 0, 0));
        Label l3 = new Label();
        //l3.setAlignment(Pos.CENTER_RIGHT);
        l3.setPrefWidth(50);
        l3.setText("aantal:");
        TextField tb = new TextField();
        tb.setPrefSize(40, 25);
        tb.setEditable(false);
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
        hb.getChildren().addAll(b, ivcont, l, l2, l3, tb);
        return hb;
    }

    @FXML
    public void openCart() {
        this.parent.loadAccountScreen("cart", "/view/CartFXML.fxml", this.loggedInAccount);
        this.parent.setScreen("cart");
    }
}
