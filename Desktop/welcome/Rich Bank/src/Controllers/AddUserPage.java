/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author terry
 */
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.ExceptionDialog;
import richBank.util.DateUtil;

public class AddUserPage implements Initializable{

    @FXML
    private AnchorPane dialogAdd;

    @FXML
    private JFXTextField textName;

    @FXML
    private JFXTextField textLastname;

    @FXML
    private TextField textAccount;

    @FXML
    private JFXTextField textCity;

    @FXML
    private JFXTextField  textBirth;

   @FXML
    private JFXTextField textBalance;
    //new Variables
    
  @FXML
    private JFXTextField textCountry;

    @FXML
    private JFXTextField textMobileNo;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;
    /* I cant use this variable properly without making them staic 
    must find out why */
    private static Stage dialogStage;
    private static Users user;
    private static boolean okClicked;
    private static boolean editor;
    
    public AddUserPage(){}
    
    public AddUserPage(Users user){
        AddUserPage.user = user;
        editor = true;
    }
    
 

    public void start(Stage primaryStage) {
        AddUserPage.dialogStage = primaryStage;
        try {
            //CHOMBO BOX
            
           
            //this.dialogStage = primaryStage;
            dialogStage.setTitle("Add Users");

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddUser.fxml"));
            
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            dialogStage.initModality(Modality.APPLICATION_MODAL);
            //dialogStage.setResizable(false);
            

            dialogStage.showAndWait();

        } catch (IOException e) {
             ExceptionDialog exceptionDialog = new ExceptionDialog (e);
            exceptionDialog.setTitle("Hola baba");
            exceptionDialog.setContentText("You clled me baba so whats up");
            exceptionDialog.showAndWait();
        }

    }

//    public void setDialogStage(Stage dialogStage) {
//        this.dialogStage = dialogStage;
//    }
    @FXML
    private void clickCancel() {
        okClicked = false;
       dialogStage.close();
    }
    public void displayEdit(){
        
    }

    //Sets the person to be added
    public Users setUser() {
      
      return user;
    }

    //Button for OK Button
    public boolean isOkCliked() {
        
        return AddUserPage.okClicked;
    }

    @FXML
    private void handleOk() {

        if (isInputVaild()) {
            Users users = new Users();
            users.setFirstName(textName.getText());
            users.setLastName(textLastname.getText());
            users.setCity(textCity.getText());
            users.setPhoneNo(Integer.parseInt(textMobileNo.getText()));
            users.setBalance(Double.parseDouble(textBalance.getText()));
            users.setBirthday(DateUtil.parse(textBirth.getText()));
            users.setCountry(textCountry.getText());
            AddUserPage.user = users;
            
            AddUserPage.okClicked = true;
            dialogStage.close();
            
        }
    }

//Validates the inputs entered
    private boolean isInputVaild() {
        String errorMessage = "";
        double checker = 100;
        //Name validation
        if (textName.getText() == null || textName.getText().length() == 0) {
            errorMessage += "No vaild User Name\n";
        }
        //Last Name validaton
        if (textLastname.getText() == null || textLastname.getText().length() == 0) {
            errorMessage += "No invaild Last Name\n";
        }
        //City validation
        if (textCity.getText() == null || textCity.getText().length() == 0) {
            errorMessage += "No vaild City Name inserted\n";
        }
        //Birth validation
        if (textBirth.getText() == null || textBirth.getText().length() == 0) {
            errorMessage += "Invaild date of birth\n";
        } else if (!DateUtil.vaildDate(textBirth.getText())) {
            errorMessage += "Invald date of birth plese use format dd.mm.yyyy\n";
        }
       
        //Balance Validation
        if (textBalance.getText() == null || textBalance.getText().length() == 0) {
            errorMessage += "Invaild Balance number\n";
        } else {
            try {
                checker = Double.parseDouble(textBalance.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No vaild Balance (It maust me an Double e.g 494.6)\n";
            }
        }
        //CHECK FOR MOBILE NUMBER
        if (textMobileNo.getText() == null || textMobileNo.getText().length() == 0) {
            errorMessage += "Invaild phone number\n";
        } else {
            try {
                Integer.parseInt(textMobileNo.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invaild Phone number (It must be an integer)\n";
            }
        }
        //Country validation
        if (textCountry.getText()== null || textCountry.getText().length() == 0) {
            errorMessage += "No vaild Country Name inserted\n";
        }
        //CHECK IF BALANCE ENTERED IS GREATER THAN 100 WHICH WILL BE THE INITIAL BALANCE
        if (checker < 100) {
            errorMessage += "Balance must be R100 or more!\n";
        }
        //Input validation checking if error found the alert warning is diplayed
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invaild Inputs");
            alert.setHeaderText("Please correct invaild inputs");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
    private void editUserDetails(){
        textName.setText(user.getFirstName());
        textLastname.setText(user.getLastName());
        textBirth.setText(DateUtil.format(user.getBirthday()));
        textCity.setText(user.getCity());
        textCountry.setText(user.getCountry());
        textMobileNo.setText(Integer.toString(user.getPhoneNo()));
        textBalance.setText(Double.toString(user.getBalance()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(editor){
            editUserDetails();
            editor = false;
            }
    }

}
