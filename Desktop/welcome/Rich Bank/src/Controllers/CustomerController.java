/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author terry
 */
public class CustomerController extends AdminHomeController {
    @FXML
    private AnchorPane rootUser;

    @FXML
    private AnchorPane DepositPage;

    @FXML
    private AnchorPane userLogin;

    @FXML
    private JFXTextArea SignInName;

    @FXML
    private JFXPasswordField signInAccount;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private AnchorPane MainDashBoard;

    @FXML
    private AnchorPane Dashboard;

    @FXML
    private AnchorPane Balancescreen;

    @FXML
    private Label MainAvaBalancetxt;

    @FXML
    private Label MainCurrBalancetxt;

    @FXML
    private AnchorPane Withdrawbtn;

    @FXML
    private JFXButton Withdrawalbtn;

    @FXML
    private AnchorPane Transfers;

    @FXML
    private AnchorPane Depositbtn;

    @FXML
    private JFXButton Deposibtn;

    @FXML
    private AnchorPane WithdrawalPage;

    @FXML
    private Label withDrawBlancetxt;

    @FXML
    private JFXTextField withdrawtxtfield;

    @FXML
    private JFXButton withdrawConfirmbtn;
    
    @FXML
    private Label withdrawlbl;
    @FXML
    private JFXButton backbtn2;
    @FXML
    private JFXButton backbtn1;
    @FXML
    private Label balanceDepositlbl;

    @FXML
    private JFXButton depositBtn;

    @FXML
    private Label depositLbl;
    @FXML
    private JFXTextField deposittxtfield;
    
    @FXML
    private JFXButton customerLogout;
   



    Users user;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AdminHomeController controller = new AdminHomeController();
        //Custumer vaildation login page
        btnSignIn.setOnAction((ActionEvent e) -> {
            outter:
            for (Users user : getUsersData()) {
                String a, b;
                a = user.getFirstName();
                b = SignInName.getText();

                if (a.equals(b)) {
                    try {
                        if (user.getAccountNo() == Integer.parseInt(signInAccount.getText())) {
                            this.user = user;
                            MainDashBoard.toFront();
                            MainCurrBalancetxt.setText(Double.toString(this.user.getBalance()));
                            this.user.setBalance(user.getBalance() - 12);
                            MainAvaBalancetxt.setText(Double.toString(this.user.getBalance()));
                            //Set balance for withdrawpage

                            break;
                        }
                    } catch (NumberFormatException messsage) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("No number");
                        alert.setContentText("Incorract user account please fill in a number");
                        alert.setHeaderText("Invaild Number");
                        alert.showAndWait();
                        break;

                    }
                } else {
                    System.out.println("Controllers.CustomerController.initialize()");
                }

            }

        });
        

    }

    @FXML
    public void withdrawalbtn() {
        withDrawBlancetxt.setText("R" + Double.toString(this.user.getBalance()));
        WithdrawalPage.toFront();
    }

    @FXML
    public void Deposibtn() {
        balanceDepositlbl.setText("R"+Double.toString(this.user.getBalance()));
        DepositPage.toFront();
    }

    //This method will withdraw money it will validate first before doing the task!
    @FXML
    public void withdrawMoney() {
        withdrawlbl.setText(" ");
        try {
            Double amountSaver = Double.parseDouble(withdrawtxtfield.getText());
            if(amountSaver >= this.user.getBalance()|amountSaver<=20){
                withdrawlbl.setText("Insufficient Amount");
            }else{
                this.user.setBalance(this.user.getBalance()-amountSaver);
                withDrawBlancetxt.setText("R"+this.user.getBalance());
            }
        } catch (NumberFormatException error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Ammount");
            alert.setContentText("Please fill in a vaild number");
            alert.setHeaderText("Invaild Number");
            alert.showAndWait();
        }
      
    }
      @FXML
    public void depositMoney() {
        depositLbl.setText(" ");
        try {
            Double amountSaver = Double.parseDouble(deposittxtfield.getText());
            if(amountSaver <=99){
                depositLbl.setText("Please deposit R100 or more");
            }else{
                this.user.setBalance(this.user.getBalance()+amountSaver);
                balanceDepositlbl.setText("R"+this.user.getBalance());
            }
        } catch (NumberFormatException error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Ammount");
            alert.setContentText("Please fill in a vaild number");
            alert.setHeaderText("Invaild Number");
            alert.showAndWait();
        }
    }
    
    @FXML
   public void backBtutton(){
       MainDashBoard.toFront();
    }
    @FXML//SIGOUT BUTTTON ACTION FOR THE CUSTOMER
    public void signOut() {
        
        try {
                    AnchorPane loader = FXMLLoader.load(getClass().getResource("/fxml/AdminHome.fxml"));
                    rootUser.autosize();
                    rootUser.getChildren().setAll(loader);
                 
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

}
