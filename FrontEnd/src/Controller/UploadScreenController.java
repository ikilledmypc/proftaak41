/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.HttpController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.PhotoGroup;
import frontend.FrontEnd;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Baya
 */
public class UploadScreenController extends ControlledAccountScreen implements Initializable {
    
    ScreensController myController;
    ArrayList<String> paths;
    boolean isPublic = true;
    Gson gson = new Gson();

    @FXML
    TextField uploadPath;
    @FXML
    TextField multipleUploadPath;
    @FXML
    TextField groupCode;
    @FXML
    TextField groupNameField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    @FXML
    public void handleBrowseButtonAction(ActionEvent event) {
        String path = myController.chooseFile();
        uploadPath.setText(path);
    }
    
    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.mainScreen);
    }
    
    @FXML
    public void handleUploadButtonAction(ActionEvent event) {
        
        String bla = HttpController.excuteGet("http://localhost:8080/upload");
        System.out.println(bla);
        
        HttpController.postFile("http://localhost:8080/upload?photoID=" + 1 , uploadPath.getText());
    }
    
    @FXML
    public void handleBrowseButtonAction2(ActionEvent event) {
        paths = myController.chooseMulitpleFiles();
        String path = "";
        for(String s : paths){
            path = path + "'" + s;
        }
        multipleUploadPath.setText(path);
    }
    
    @FXML
    public void handleCreateGroupButtonAction(ActionEvent event){
        String code = generateCode();
        String groupName = groupNameField.getText();
        String photogroupid = HttpController.excutePost(FrontEnd.HOST+"/createPhotoGroup", "photogroup=" + 
                gson.toJson(new PhotoGroup(this.loggedInAccount.getAccountID(), code, groupName, isPublic, 0)));
        if(!photogroupid.equalsIgnoreCase("")){
            int groupID = gson.fromJson(photogroupid, new TypeToken<Integer>(){}.getType());
        }
        int id = 10;
        for(String s : paths){
            HttpController.postFile("http://localhost:8080/upload?photoID=" + id, s);
            id++;
        }
        groupCode.setText(code);
    }
    
    public String generateCode(){
        Random rand = new Random(); 
        int code = rand.nextInt(899999) + 100000;
        String hashcode = Integer.toHexString(code);
        String bezet = HttpController.excuteGet(FrontEnd.HOST + "/checkCodeavailability?hashcode=" + hashcode );
        if(bezet.equals("true")){
            generateCode();
        }
        else{
            return hashcode; 
        }
        return "";
    }
}
