/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Baya
 */
public class FrontEnd extends Application {
    public static String HOST = "http://127.0.0.1:8080";
    public static String mainScreen = "main";
    public static String mainScreenFXML = "Main.fxml";
    public static String loginScreen = "loginScreen";
    public static String loginScreenFXML = "LoginScreen.fxml";
    public static String registerScreen = "registerScreen";
    public static String registerScreenFXML = "RegisterScreen.fxml";
    public static String uploadScreen = "uploadScreen";
    public static String uploadScreenFXML = "UploadScreen.fxml";
    public static String downloadScreen = "downloadScreen";
    public static String downloadScreenFXML = "DownloadScreen.fxml";
    public static String managementScreen = "managementPhotographer";
    public static String managementScreenFXML = "ManagementPhotographer.fxml";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(FrontEnd.mainScreen, FrontEnd.mainScreenFXML);
        mainContainer.loadScreen(FrontEnd.loginScreen, FrontEnd.loginScreenFXML);
        mainContainer.loadScreen(FrontEnd.registerScreen, FrontEnd.registerScreenFXML);
        mainContainer.loadScreen(FrontEnd.uploadScreen, FrontEnd.uploadScreenFXML);
        mainContainer.loadScreen(FrontEnd.downloadScreen, FrontEnd.downloadScreenFXML);
        mainContainer.loadScreen(FrontEnd.managementScreen, FrontEnd.managementScreenFXML);
        
        mainContainer.setScreen(FrontEnd.mainScreen);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
