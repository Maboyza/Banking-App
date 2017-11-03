/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author terry
 */
public class LoginController implements Initializable {
    
       @FXML
    private AnchorPane rootFirst;
    @FXML
    private JFXTextField txt;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXButton login, user;
    @FXML
    private Label label;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Admin login button
        login.setOnAction(e -> {
            if (txt.getText().equals("user") && pass.getText().equals("user")) {
                label.setText("Login succesfull");
                show(); 
            } else {
                label.setText("Login failed");
            }
        });
    }
    //Creats Stage for Add
    public void show() {
        
        try {
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminHome.fxml"));
                    Stage primaryStage = new Stage();
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.initModality(Modality.APPLICATION_MODAL);
                    primaryStage.setOnCloseRequest(e->{e.consume();
                    System.out.println("Hello");
                    primaryStage.hide();
                    });
                    primaryStage.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    @FXML//SIGIN BUTTTON ACTION FOR THE CUSTOMER
    public void signIn() {
        
        try {
                    Parent root = FXMLLoader.load(getClass().getResource("/application2/Customerdash.fxml"));
                    Stage primaryStage = new Stage();
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    
                    primaryStage.initModality(Modality.APPLICATION_MODAL);
                    //primaryStage.setFullScreen(true);
                    primaryStage.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

}
