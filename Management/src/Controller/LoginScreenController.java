/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import domain.Account;
import domain.Photographer;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mike Rooijackers
 */
public class LoginScreenController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    TextField TF_username;
    @FXML
    TextField TF_password;
    @FXML
    Label LB_error;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void login() {
        try {
            Gson gson = new Gson();
            String password = TF_password.getText();
            String accounts = HttpController.excuteGet(FrontEnd.HOST + "/authenticateAndGet?username=" + TF_username.getText() + "&password=" + password);
            Account account;
            if (!accounts.isEmpty()) {
                try
                { account = gson.fromJson(accounts, Photographer.class);
                    if(((Photographer)account).getPhotographerID() == 0)
                    {
                        account = gson.fromJson(accounts, Account.class);
                    }
                }
                catch(ClassCastException e)
                {
                    account = gson.fromJson(accounts, Account.class);
                }
                
                if(account instanceof Photographer)
                {
                   System.out.println("Hello Photographer " + account.getName() + "!");
                    LB_error.setText("Hello Photographer " + account.getName() + "!"); 
                }
                else
                {
                   System.out.println("Hello User " + account.getName() + "!");
                    LB_error.setText("Hello User " + account.getName() + "!");  
                }
                
                myController.loadAccountScreen(FrontEnd.mainScreen, FrontEnd.mainScreenFXML, account);
                myController.setScreen(FrontEnd.mainScreen);
            } else {
                LB_error.setText("Wrong username/password");
            }
        } catch (Exception ex) {
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

}
