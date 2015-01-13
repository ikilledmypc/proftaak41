/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import domain.Photographer;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Mike Rooijackers
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
    @FXML
    Label lbUsernameText;
    @FXML
    TextField tbName;
    @FXML
    TextField tbAddress;
    @FXML
    TextField tbZipcode;
    @FXML
    TextField tbCity;
    @FXML
    TextField tbTelephone;
    @FXML
    TextField tbEmail;
    @FXML
    TextField tbBank;
    @FXML
    TextField tbCompany;

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

    /**
     *
     * @param screenPage
     */
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    /**
     *
     * @param event
     */
    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.mainScreen);
    }

    /**
     *
     * @param event
     */
    @FXML
    public void handleSearchButtonAction(ActionEvent event) {
        gson = new Gson();
        String account = HttpController.excuteGet(FrontEnd.HOST + "/searchPhotographer?username=" + tbSearch.getText());
        try {
            Photographer photographer = gson.fromJson(account, Photographer.class);
            lbUsernameText.setText(photographer.getUsername());
            tbAddress.setText(photographer.getAddress());
            tbBank.setText(photographer.getBankAccount());
            tbCity.setText(photographer.getCity());
            tbCompany.setText(photographer.getCompanyName());
            tbEmail.setText(photographer.getEmail());
            tbName.setText(photographer.getName());
            tbZipcode.setText(photographer.getZipcode());
            tbTelephone.setText(photographer.getTelephone());
            if (photographer.getActive()) {
                True.setSelected(true);
            } else {
                False.setSelected(true);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    public void handleSaveButtonAction(ActionEvent event) {
        try {
            gson = new Gson();
            boolean isActive;
            if (True.isSelected()) {
                isActive = true;
            } else {
                isActive = false;
            }
            Photographer photographer = new Photographer(lbUsernameText.getText(), tbName.getText(), tbAddress.getText(), tbZipcode.getText(), tbCity.getText(), tbEmail.getText(), tbTelephone.getText(), tbCompany.getText(), tbBank.getText(), isActive);
            HttpController.excutePost(FrontEnd.HOST + "/editPhotographer", "photographer=" + gson.toJson(photographer));
            myController.setScreen(FrontEnd.managementScreen);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
