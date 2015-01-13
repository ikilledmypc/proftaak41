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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Baya
 */
public class LoginScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    ResourceBundle recources;
    @FXML
    TextField TF_username;
    @FXML
    TextField TF_password;
    @FXML
    Label LB_error;
    @FXML
    ComboBox cmb_language;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.recources = rb;

        //cmb_language.setPromptText("English");
        cmb_language.setValue(rb.getString("language"));
        cmb_language.setItems(FXCollections.observableArrayList(FrontEnd.avaliableLocale.keySet()));
        cmb_language.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                myController.setLanguage(FrontEnd.avaliableLocale.get(newValue));
                try {
                    saveLanguage();
                } catch (IOException ex) {
                    Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                myController.unloadScreen(FrontEnd.loginScreen);
                myController.loadScreen(FrontEnd.loginScreen, FrontEnd.loginScreenFXML);
                myController.setScreen(FrontEnd.loginScreen);
            }
        });
    }

    @FXML
    public void login() throws IOException {
        try {
            Gson gson = new Gson();
            MessageDigest msgd = MessageDigest.getInstance("MD5");
            String password = new BigInteger(1, msgd.digest(TF_password.getText().getBytes())).toString(16);
            String accounts = HttpController.excuteGet(FrontEnd.HOST + "/authenticateAndGet?username=" + TF_username.getText() + "&password=" + password);
            Account a;
            if (!accounts.isEmpty()) {
                try {
                    a = gson.fromJson(accounts, Photographer.class);
                    if (((Photographer) a).getPhotographerID() == 0) {
                        a = gson.fromJson(accounts, Account.class);
                    }
                } catch (ClassCastException e) {
                    a = gson.fromJson(accounts, Account.class);
                }

                if (a instanceof Photographer) {
                    System.out.println("Hello Photographer " + a.getName() + "!");
                    LB_error.setText("Hello Photographer " + a.getName() + "!");
                } else {
                    System.out.println("Hello User " + a.getName() + "!");
                    LB_error.setText("Hello User " + a.getName() + "!");
                }
                myController.loadAccountScreen(FrontEnd.mainScreen, FrontEnd.mainScreenFXML, a);
                myController.loadAccountScreen(FrontEnd.uploadScreen, FrontEnd.uploadScreenFXML, a);
                myController.loadAccountScreen(FrontEnd.downloadScreen, FrontEnd.downloadScreenFXML, a);
                myController.loadAccountScreen(FrontEnd.buyItemScreen, FrontEnd.buyItemScreenFXML, a);
                myController.setScreen(FrontEnd.mainScreen);
            } else {
                LB_error.setText(this.recources.getString("loginError"));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    public void register() {
        myController.setScreen(FrontEnd.registerScreen);
    }
    
    public void saveLanguage() throws IOException{
        Properties props = new Properties();
        File properties = new File("defaultLanguage.properties");
        if(!properties.exists()){
            properties.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(properties);
        props.setProperty("language", myController.locale.toLanguageTag());
        props.store(out, null);
        out.close();
    }

}
