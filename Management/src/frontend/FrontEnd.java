/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import Controller.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mike Rooijackers
 */
public class FrontEnd extends Application {

    /**
     *
     */
    public static String HOST = "http://127.0.0.1:8080";

    /**
     *
     */
    public static String mainScreen = "main";

    /**
     *
     */
    public static String mainScreenFXML = "/view/Main.fxml";

    /**
     *
     */
    public static String loginScreen = "loginScreen";

    /**
     *
     */
    public static String loginScreenFXML = "/view/LoginScreen.fxml";

    /**
     *
     */
    public static String registerScreen = "registerScreen";

    /**
     *
     */
    public static String registerScreenFXML = "/view/RegisterScreen.fxml";

    /**
     *
     */
    public static String registerPhotographerScreen = "registerPhotographerScreen";

    /**
     *
     */
    public static String registerPhotographerScreenFXML = "/view/RegisterPhotographerScreen.fxml";

    /**
     *
     */
    public static String managementScreen = "managementPhotographer";

    /**
     *
     */
    public static String managementScreenFXML = "/view/ManagementPhotographer.fxml";

    /**
     *
     */
    public static String addProductScreen = "addProductScreen";

    /**
     *
     */
    public static String addProductScreenFXML = "/view/AddProductScreen.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {

        ScreensController mainContainer = new ScreensController(primaryStage);
        System.out.println("test");
        mainContainer.loadScreen(FrontEnd.loginScreen, FrontEnd.loginScreenFXML);
        mainContainer.loadScreen(FrontEnd.registerScreen, FrontEnd.registerScreenFXML);

        mainContainer.setScreen(FrontEnd.loginScreen);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
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
