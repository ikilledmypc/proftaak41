/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.PhotoGroup;
import domain.Photo;
import frontend.FrontEnd;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;

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
    List<File> files;

    @FXML
    TextField uploadPath;
    @FXML
    TextField multipleUploadPath;
    @FXML
    TextField groupCode;
    @FXML
    TextField groupNameField;
    
    private HashMap<File,TextField> selectedPhotos = new HashMap<>();
    @FXML
    TilePane TP_photos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TP_photos.setVgap(20);
        TP_photos.setHgap(20);
//        for(int i=0;i<4;i++){
//            TP_photos.getChildren().add(buildItem("/resources/placeholderPhoto.jpg"));
//        }
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    @FXML
    public void handleBrowseButtonAction(ActionEvent event) {
        files = myController.chooseFile();
        for(File f :files){
            TP_photos.getChildren().add(buildItem(f));
        }
    }
    
    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        myController.setScreen(FrontEnd.mainScreen);
    }
    
    @FXML
    public void handleUploadButtonAction(ActionEvent event) throws IOException {
        
        //String bla = HttpController.excuteGet("http://localhost:8080/upload");
        //System.out.println(bla);
        Iterator it = selectedPhotos.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
           File p =((File) pairs.getKey());
//            p.setName(((TextField)pairs.getValue()).getId());
//            p.setPrice(Float.parseFloat(((TextField)pairs.getValue()).getText()));                    
//            photos.add(p);
            String bla = HttpController.postFile("http://localhost:8080/upload", p.getPath());
            System.out.println(bla);
            it.remove(); // avoids a ConcurrentModificationException
        }
        /*
        int id = 10;
        for(File f : files){
            HttpController.postFile("http://localhost:8080/upload?photoID=" + id , f.getAbsolutePath());
            java.awt.Image img = ImageIO.read(f).getScaledInstance(100, 100, BufferedImage.SCALE_SMOOTH);
            HttpController.postFile("http://localhost:8080/uploadThumbnail?file= " + img + "&photoID=" + id , f.getAbsolutePath());
            id++;
        }
        */
        
        String bla1 = HttpController.excuteGet("http://localhost:8080/upload");
        System.out.println(bla1);
        /*
        for(Photo p:photos){
            System.out.println(p.getPrice()+" "+p.getName());
            String bla = HttpController.postFile("http://localhost:8080/upload", "C:\\Users\\Mr. Jin\\Downloads\\cover.jpg");
        }*/
                 
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
        
      //  HttpController.postFile("http://localhost:8080/upload", uploadPath.getText());
    
    
    
    private VBox buildItem(File file){
        try {
            VBox item = new VBox();
            item.setStyle("-fx-border-color: black;");
            item.setSpacing(10);
            ImageView iv = new ImageView();
            Image image = new Image(new FileInputStream(file));
            iv.setPreserveRatio(true);
            iv.setFitWidth(400);
            iv.setImage(image);
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            TextField price = new TextField();
            price.setId(file.getName());
            price.setText("6.50");
            Label priceLabel = new Label();
            priceLabel.setText("price:  \u20ac");
            hbox.getChildren().addAll(priceLabel,price);
            item.getChildren().addAll(iv,hbox);
            selectedPhotos.put(file, price);
            return item;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
