/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.ControlledScreen;
import domain.Account;
import frontend.FrontEnd;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Baya
 */
public class ScreensController extends StackPane {
    public Locale locale = new Locale("nl","NL");
    private HashMap<String, Node> screens = new HashMap<>();
    private Stage stage;

    public ScreensController(Stage s) {
        super();
        this.stage = s;

    }

    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    public Node getScreen(String name) {
        return screens.get(name);
    }

    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            myLoader.setResources(ResourceBundle.getBundle("Bundles.lang", locale));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadAccountScreen(String name, String resource, Account a) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            myLoader.setResources(ResourceBundle.getBundle("Bundles.lang", locale));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledAccountScreen myScreenController = ((ControlledAccountScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            myScreenController.setAccount(a);

            addScreen(name, loadScreen);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void setLanguage(Locale l){
        this.locale = l ;
    }

    public boolean setScreen(final String name) {
        if (screens.get(name) != null) {
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(500), new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent t) {
                                getChildren().remove(0);
                                AnchorPane n = (AnchorPane) screens.get(name);
                                getChildren().add(0, n);

                                stage.setWidth(n.getPrefWidth());
                                stage.setHeight(n.getPrefHeight());
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(400), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }

                        }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                setOpacity(0.0);

                AnchorPane n = (AnchorPane) screens.get(name);
                stage.setWidth(n.getPrefWidth());
                stage.setHeight(n.getPrefHeight());
                getChildren().add(n);
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(1250), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("Screen hasn't been loaded \n");
            return false;
        }
    }

    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }

    public void displaySplashProgress(Task task, String taskname) throws FileNotFoundException {
        Group root = new Group();
        Stage s = new Stage(StageStyle.UNDECORATED);
        VBox sp = new VBox();
        sp.setAlignment(Pos.CENTER);
        ImageView iv = new ImageView(new Image(this.getClass().getResourceAsStream("/resources/progress.gif")));
        Label tf = new Label(taskname);
        tf.setFont(new Font(25));
        ProgressBar pb = new ProgressBar();
        pb.progressProperty().bind(task.progressProperty());
        pb.setPrefSize(400, 50);
        sp.getChildren().addAll(iv, tf, pb);
        root.getChildren().add(sp);
        Scene sc = new Scene(root);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to the lower right corner of the visible bounds of the main screen
        //found : http://www.coderanch.com/t/620036/JavaFX/java/Stage-corner-screen 
        s.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() / 2 - 100);
        s.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() / 2 - 200);
        s.setScene(sc);
        s.setHeight(200);
        s.setWidth(400);
        //s.setAlwaysOnTop(true);

        s.show();
        task.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    s.close();
                }
            }
        });
    }

    public List<File> chooseFile() {
        Stage newStage = new Stage();
        FileChooser chooser = new FileChooser();
        return chooser.showOpenMultipleDialog(newStage);
    }

    public ArrayList<String> chooseMulitpleFiles() {
        Stage newStage = new Stage();
        FileChooser chooser = new FileChooser();
        List<File> file = (List<File>) chooser.showOpenMultipleDialog(newStage);
        ArrayList<String> filePaths = new ArrayList<String>();
        for (File f : file) {
            filePaths.add(f.getAbsolutePath().toString());
        }
        return filePaths;
    }
}
