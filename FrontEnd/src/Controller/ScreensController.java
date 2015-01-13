/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import domain.Account;
import java.io.*;
import java.util.*;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.*;
import javafx.concurrent.*;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.util.Duration;

/**
 *
 * @author Baya
 */
public class ScreensController extends StackPane {

    /**
     *
     */
    public Locale locale = Locale.ENGLISH;
    private HashMap<String, Node> screens = new HashMap<>();
    private Stage stage;

    /**
     *
     * @param s
     */
    public ScreensController(Stage s) {
        super();
        this.stage = s;

    }

    /**
     *
     * @param name
     * @param screen
     */
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    /**
     *
     * @param name
     * @return
     */
    public Node getScreen(String name) {
        return screens.get(name);
    }

    /**
     *
     * @param name
     * @param resource
     * @return
     */
    public boolean loadScreen(String name, String resource) {
        try {
            this.readLanguageProperties();
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

    /**
     *
     * @param name
     * @param resource
     * @param a
     * @return
     */
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

    /**
     *
     * @param l locale
     */
    public void setLanguage(Locale l) {
        this.locale = l;
    }

    /**
     *
     * @param name
     * @return
     */
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
                                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                                //set Stage boundaries to the lower right corner of the visible bounds of the main screen
                                //found : http://www.coderanch.com/t/620036/JavaFX/java/Stage-corner-screen 
                                stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() / 2 - (n.getPrefWidth() / 2));
                                stage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() / 2 - (n.getPrefHeight() / 2));
                                stage.setWidth(n.getPrefWidth());
                                stage.setHeight(n.getPrefHeight());
                                getChildren().add(0, n);

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

    /**
     *
     * @param name
     * @return
     */
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param task
     * @param taskname
     * @throws FileNotFoundException
     */
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

    /**
     *
     * @return
     */
    public List<File> chooseFile() {
        Stage newStage = new Stage();
        FileChooser chooser = new FileChooser();
        return chooser.showOpenMultipleDialog(newStage);
    }

    /**
     *
     * @return
     */
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
    
    /**
     *
     * @throws IOException
     */
    public void readLanguageProperties() throws IOException{
        Properties props = new Properties();
        InputStream in = null;
        in = new FileInputStream("defaultLanguage.properties");
        if(in != null){
            props.load(in);
        }
        else{
            throw new FileNotFoundException("property file 'defaultLanguage.properties' not found in the classpath");
        }
        if(!props.isEmpty()){
            String langTag = props.getProperty("language");
            Locale lang = new Locale(langTag);
            this.setLanguage(lang);
        }
    }
}
