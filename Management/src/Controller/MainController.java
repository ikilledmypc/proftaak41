/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Mike
 */
public class MainController implements Initializable, ControlledScreen {

    ScreensController myController;
    Gson gson;
    //PIE CHART DATA
    private ObservableList pieChartData;
    
    @FXML
    Button btnEdit;
    @FXML
    Button btnRegister;
    @FXML
    Button btnAddProduct;
    @FXML
    Button btnEditProduct;
    @FXML
    Button btnDeleteProduct;
    @FXML
    PieChart pieChart;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
        myController = screenPage;
        buildPieChartPhotographer();
    }
    
    /**
     *
     * @param event
     */
    @FXML
    public void handleRegisterButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.registerPhotographerScreen);
    }
    
    /**
     *
     * @param event
     */
    @FXML
    public void handleEditButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.managementScreen);
    }
    
    /**
     *
     * @param event
     */
    @FXML
    public void handleAddProductButtonAction(ActionEvent event) {
         myController.setScreen(FrontEnd.addProductScreen);
    }
    
    /**
     *
     * @param event
     */
    @FXML
    public void handleEditProductButtonAction(ActionEvent event) {
    }
    
    /**
     *
     * @param event
     */
    @FXML
    public void handleDeleteProductButtonAction(ActionEvent event) {
    }
    
    /**
     *
     */
    public void buildPieChartPhotographer() {
        String dataPieChartInActive = HttpController.excuteGet(FrontEnd.HOST + "/buildPieChartPhotographersInActive");
        System.out.println(dataPieChartInActive);
        pieChartData = FXCollections.observableArrayList(dataPieChartInActive);
        System.out.println(dataPieChartInActive);
        pieChart.getData().add(new PieChart.Data("inactive", Double.parseDouble(dataPieChartInActive)));
        
        String dataPieChartActive = HttpController.excuteGet(FrontEnd.HOST + "/buildPieChartPhotographersActive");
        System.out.println(dataPieChartActive);
        pieChartData = FXCollections.observableArrayList(dataPieChartActive);
        System.out.println(dataPieChartActive);
        pieChart.getData().add(new PieChart.Data("active", Double.parseDouble(dataPieChartActive)));
        
    }
    
}
