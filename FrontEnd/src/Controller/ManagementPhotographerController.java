/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.HttpController;
import com.google.gson.Gson;
import domain.Account;
import domain.Photographer;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Mike
 */
public class ManagementPhotographerController implements Initializable, ControlledScreen {

    ScreensController myController;
    ToggleGroup group;
    Gson gson;

    @FXML
    Button btnSearch;

    @FXML
    TextField tbSearch;

    @FXML
    RadioButton False;

    @FXML
    RadioButton True;

    @FXML
    Button btnBack;

    @FXML
    Button btnSave;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        group = new ToggleGroup();
        True.setToggleGroup(group);
        False.setToggleGroup(group);
        False.setSelected(true);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.mainScreen);
    }

    @FXML
    public void handleSearchButtonAction(ActionEvent event) {
        gson = new Gson();
        String accountActive = HttpController.excuteGet(FrontEnd.HOST + "/searchPhotographerActive?username=" + tbSearch.getText());
        accountActive = accountActive.trim();
        if (Boolean.parseBoolean(accountActive)) {
            True.setSelected(true);
        }
        else {
            False.setSelected(true);
        }
    }

    @FXML
    public void handleSaveButtonAction(ActionEvent event) {
        try {
            gson = new Gson();
            boolean accountActive;
            if(True.isSelected()) {
                accountActive = true;
            }
            else {
                accountActive = false;
            }
            Photographer photographer = new Photographer(tbSearch.getText(), accountActive);
            HttpController.excutePost(FrontEnd.HOST+"/editPhotographerActive", "photographer="+gson.toJson(photographer));
            myController.setScreen(FrontEnd.managementScreen);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
