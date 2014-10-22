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
import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SP_scroll.setStyle("-fx-background-color: white;");
        TP_productContainer.setStyle("-fx-background-color: white;");
        TP_productContainer.setPadding(new Insets(10, 10, 10, 10));
        
        
    }
    
    @Override
    public void setAccount(Account a){
        this.loggedInAccount = a;
        Gson gson = new Gson();
        String scart = HttpController.excuteGet(FrontEnd.HOST+"/getCart?username="+a.getUsername());
        ShoppingCart cart = gson.fromJson(scart,ShoppingCart.class);
        this.shoppingcart = cart;
        ArrayList<Product> products = cart.GetProducts();
        for(Product p : products){            
          TP_productContainer.getChildren().add(this.buildItem(p));
        }
    }
    
    @FXML
    public void placeOrder(){
        Gson gson = new Gson();
        HttpController.excutePost(FrontEnd.HOST+"/placeOrder","cart="+gson.toJson(this.shoppingcart)+"&accountID=2");
    }
    
    private HBox buildItem(Product p){
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
            l2.setText("\u20ac"+(p.getMaterialPrice()+p.getPhoto().getPrice()));
            l2.setPadding(new Insets(0, 20, 0, 0));
            Label l3 = new Label();
            l3.setAlignment(Pos.CENTER_RIGHT);
            l3.setPrefWidth(70);
            l3.setText("aantal:");
            TextField tb = new TextField();
            tb.setText(p.getAmount()+"");
            tb.setPrefWidth(50);
            Button b = new Button();
            b.setPrefSize(10, 10);
            b.setText("X");
            Pane delteCont= new Pane();
            //p.setPadding(new Insets(0,0,0,20));
            delteCont.setPrefWidth(60);
            delteCont.getChildren().add(b);
            b.setAlignment(Pos.CENTER);
            b.setStyle("-fx-background-color: red; -fx-background-radius: 20; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
            
            hb.getChildren().addAll(delteCont, l, l2,l3,tb);
            return hb;
    }
    
}
