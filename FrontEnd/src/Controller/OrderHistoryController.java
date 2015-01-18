/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Order;
import domain.Photo;
import domain.Product;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luke
 */
public class OrderHistoryController extends ControlledAccountScreen implements Initializable {
    @FXML
    private TreeView<String> tvOrders;
    @FXML
    private Button btnBack;

    private ArrayList<Order> orders;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
        /**
     *
     * @param screenPage
     */
    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.parent = screenPage;
        grabOrderHistory();
    }
    
    private void grabOrderHistory()
    {
                TreeItem rootnode = new TreeItem("Orders");
        orders = new ArrayList<Order>();
        tvOrders.setRoot(rootnode);
        rootnode.setExpanded(true);
        
        
        Gson gson = new Gson();
        String returnedPhotos = HttpController.excuteGet(FrontEnd.HOST + "/getOrderHistory" + "?accountID=" + this.loggedInAccount.getAccountID());
        if (!returnedPhotos.equalsIgnoreCase("")) {
            ArrayList<Photo> getPhotos = new ArrayList();
            orders = gson.fromJson(returnedPhotos, new TypeToken<ArrayList<Order>>() {
            }.getType());
            for(Order o : orders)
            {
                TreeItem orderItem = new TreeItem(o.toString());
                for(Product p : o.getProducts())
                {
                    TreeItem productItem = new TreeItem(p.toString());
                    orderItem.getChildren().add(productItem);
                }
                rootnode.getChildren().add(orderItem);
            }
        } else {
            System.out.println("Failed to load Order History");
        }
        
    }
    
    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
}
