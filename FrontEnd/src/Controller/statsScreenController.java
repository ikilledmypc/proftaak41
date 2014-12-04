/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.text.Text;

/**
 *
 * @author MSI
 */
public class statsScreenController extends ControlledAccountScreen implements Initializable {

    private ResourceBundle recources;
    private ObservableList pieChartData;
    
    @FXML
    PieChart pieChart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.recources = rb;
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.parent = screenPage;
        buildPieChartPhotographer();
    }
    
    public void buildPieChartPhotographer() {
        String dataPieChartPhotos = HttpController.excuteGet(FrontEnd.HOST + "/getNumberOfOrderedPhotos?accountID=" + this.loggedInAccount.getAccountID());
        pieChartData = FXCollections.observableArrayList(dataPieChartPhotos);
        pieChart.getData().add(new PieChart.Data("Foto's", Double.parseDouble(dataPieChartPhotos)));
        
        String dataPieChartProducts = HttpController.excuteGet(FrontEnd.HOST + "/getNumberOfOrderedProducts?accountID=" + this.loggedInAccount.getAccountID());
        pieChartData = FXCollections.observableArrayList(dataPieChartProducts);
        pieChart.getData().add(new PieChart.Data("Producten", Double.parseDouble(dataPieChartProducts)));
        
        for(Node node : pieChart.lookupAll("Text.chart-pie-label")){
            if(node instanceof Text){
                for(PieChart.Data data : pieChart.getData()){
                    if(data.getName().equals(((Text)node).getText())){
                        ((Text)node).setText(String.format("%,.0f", data.getPieValue()));
                    }
                }
            }
        }
    }
}
