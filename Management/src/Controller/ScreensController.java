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
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Mike Rooijackers
 */
public class ScreensController extends StackPane {

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
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
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
            Parent loadScreen = (Parent) myLoader.load();
            ControlledAccountScreen myScreenController = ((ControlledAccountScreen) myLoader.getController());
            myScreenController.setAccount(a);
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
}
