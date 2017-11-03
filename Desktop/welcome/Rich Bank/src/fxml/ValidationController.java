/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author terry
 */
public class ValidationController implements Initializable {
   
    @FXML
    private ImageView slider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        slid();
    }   
    
    private void slid(){
        
        Path path = new Path(new MoveTo(100, 169));
        PathTransition translaton = new PathTransition(Duration.seconds(7), path);
        translaton.setCycleCount(7);
        translaton.setInterpolator(Interpolator.EASE_IN);
        translaton.setAutoReverse(true);
        translaton.play();
        
    }
    
}
