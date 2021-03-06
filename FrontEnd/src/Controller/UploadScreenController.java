/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import frontend.FrontEnd;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.*;
import javafx.beans.value.*;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import workers.PhotoUploadWorker;

/**
 * FXML Controller class
 *
 * @author Baya
 */
public class UploadScreenController extends ControlledAccountScreen implements Initializable {

    ArrayList<String> paths;
    Gson gson = new Gson();
    List<File> files;

    @FXML
    TextField uploadPath;
    @FXML
    CheckBox isPublic;
    @FXML
    TextField groupCode;
    @FXML
    TextField groupNameField;
    @FXML
    Button uploadButton;

    private HashMap<File, TextField> selectedPhotos = new HashMap<>();
    @FXML
    TilePane TP_photos;
    
    @FXML
    Button defaultButton;
    
    @FXML
    TextField defaultField;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TP_photos.setVgap(20);
        TP_photos.setHgap(20);
        uploadButton.setDisable(true);
    }

    /**
     *
     * @param event
     */
    @FXML
    public void handleBrowseButtonAction(ActionEvent event) {
        files = this.parent.chooseFile();
        if (files != null) {
            for (File f : files) {
                VBox h = buildItem(f);
                if (h != null) {
                    TP_photos.getChildren().add(h);
                }
            }
        }
        if (this.selectedPhotos.size() > 0) {
            uploadButton.setDisable(false);
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        this.parent.setScreen(FrontEnd.mainScreen);
    }

    /**
     *
     * @param event
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @FXML
    public void handleUploadButtonAction(ActionEvent event) throws IOException, InterruptedException, ExecutionException {
        PhotoUploadWorker puw = new PhotoUploadWorker(groupNameField.getText(), selectedPhotos, loggedInAccount);
        this.parent.displaySplashProgress(puw, "uploading...");
        new Thread(puw).start();
        puw.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {

                    try {
                        groupCode.setText((String) puw.get());
                        HttpController.excuteGet(FrontEnd.HOST + "/getAllPhotos?code=" + groupCode.getText() + "&accountID=" + loggedInAccount.getAccountID());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UploadScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(UploadScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

    }

    private VBox buildItem(File file) {
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
            price.setText(HttpController.excuteGet(FrontEnd.HOST + "/getDefaultPricePhoto?accountID=" + this.loggedInAccount.getAccountID()));
            Label priceLabel = new Label();
            priceLabel.setText("price:  \u20ac");
            hbox.getChildren().addAll(priceLabel, price);
            item.getChildren().addAll(iv, hbox);
            if (selectedPhotos.get(file) != null) {
                return null;
            }
            selectedPhotos.putIfAbsent(file, price);
            return item;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     *
     * @param event
     */
    @FXML
    public void handleDefaultButtonAction(ActionEvent event){
        double price = Double.parseDouble(defaultField.getText());
        HttpController.excutePost(FrontEnd.HOST + "/editDefaultPricePhoto", "accountID=" + this.loggedInAccount.getAccountID() + "&defaultPricePhoto=" + price);
        VBox vbox;
        HBox hbox;
        TextField defaultPrice;
        int counter = 0;
        for(Node item : TP_photos.getChildren()){
            vbox = (VBox)item;
            counter = 0;
            for(Node i : vbox.getChildren()){
                if(counter == 1){
                    hbox = (HBox)i;
                    int anothercounter = 0;
                    for(Node n : hbox.getChildren()){
                        if(anothercounter == 1){
                           defaultPrice = (TextField)n;
                           defaultPrice.setText(String.valueOf(price));
                        }
                        anothercounter++;
                    }
                }
                counter++;
            }
        }
    }
}
