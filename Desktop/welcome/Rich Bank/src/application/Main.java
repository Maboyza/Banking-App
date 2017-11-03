package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.dialog.ExceptionDialog;


public class Main extends Application {
    //OBSERVABLE DATA

   
    //RETURNS DATA A^S OBSERVABLE LIST
    
    
	@Override
	public void start(Stage primaryStage) {
		try {
                        Parent root= FXMLLoader.load(getClass().getResource("/fxml/AdminHome.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
                        
                        primaryStage.setScene(scene);
			primaryStage.show();
		} catch(IOException e) {
                    e.printStackTrace();
                    ExceptionDialog exceptionDialog = new ExceptionDialog (e); 
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
        
       
}
