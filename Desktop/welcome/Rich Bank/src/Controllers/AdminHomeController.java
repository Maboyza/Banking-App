/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import email.as.a.service.EmailService;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import richBank.util.DateUtil;

public class AdminHomeController implements Initializable {

    //-----------Customer variables---------------//
     DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     Date date = new Date();
    @FXML
    private AnchorPane StatmentPage;

    @FXML
    private Label lblResultStat;

    @FXML
    private JFXButton btnStatment;

    private String StatmentMassage;
    @FXML
    private JFXButton btnSignIn;
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
    private JFXButton btnFromUserToAdmin;

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
    @FXML
    private Label lblOfCustomer;

    Users user, user2;//well be back

    //Customer  withdraw page
    @FXML
    public void withdrawalbtn() {
        withDrawBlancetxt.setText("R" + Double.toString(this.user.getBalance()));
        ClearTextField();
        WithdrawalPage.toFront();
    }

    //Customer deposit page
    @FXML
    public void Deposibtn() {
        balanceDepositlbl.setText("R" + Double.toString(this.user.getBalance()));
        ClearTextField();
        DepositPage.toFront();
    }

    //This method will withdraw money it will validate first before doing the task!
    @FXML
    public void withdrawMoney() {
        withdrawlbl.setText(" ");
        try {
            Double amountSaver = Double.parseDouble(withdrawtxtfield.getText());
            if (amountSaver >= this.user.getBalance() | amountSaver <= 20) {
                withdrawlbl.setText("Insufficient Amount");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Withdraw conformation");
                alert.setHeaderText("Cornfirm Withdraw");
                alert.setContentText("Are you sure you want to withdraw: " + amountSaver + " ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    this.user.setBalance(this.user.getBalance() - amountSaver);
                    withDrawBlancetxt.setText("R" + this.user.getBalance());
                    StatmentMassage += amountSaver + "Withdrawn form at " + dateFormat.format(date)+"\n";
                    ClearTextField();
                }
            }
        } catch (NumberFormatException error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Ammount");
            alert.setContentText("Please fill in a vaild number");
            alert.setHeaderText("Invaild Number");
            alert.showAndWait();
        }

    }

    @FXML//diposit method 
    public void depositMoney() {
        depositLbl.setText(" ");
        try {
            Double amountSaver = Double.parseDouble(deposittxtfield.getText());
            if (amountSaver <= 99) {
                depositLbl.setText("Please deposit R100 or more");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deposit conformation");
                alert.setHeaderText("Cornfirm Deposit");
                alert.setContentText("Are you sure you want to deposit: " + amountSaver + " ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    this.user.setBalance(this.user.getBalance() + amountSaver);
                    balanceDepositlbl.setText("R" + this.user.getBalance());
                    StatmentMassage += amountSaver + "deposited at " + dateFormat.format(date)+"\n";
                    ClearTextField();
                }
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
    public void backBtutton() {
        UpDateBalanceText();
        MainDashBoard.toFront();
    }

    @FXML//Logs out of the customer home page
    public void signOut() {
        ClearTextField();
        userLogin.toFront();
    }

//----------------------------------------------------------------//////-----------------------------------------------/////
    //login page controls
    @FXML
    private AnchorPane pane;

    @FXML
    private Label label;

    @FXML
    private JFXTextField txt;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton usered;

//-------------controls for admin home page--------------------------------////
    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private AnchorPane AdminAnchor;
    @FXML
    private AnchorPane usersAnchor;
//Table view
    @FXML
    private TableView<Users> root;
//Table view coloumns    
    @FXML
    private TableColumn<Users, String> colName;
    @FXML
    private TableColumn<Users, String> colSurname;
    @FXML
    private TableColumn<Users, Double> colBalance;
//Lables
    @FXML
    private Label lblName;
    @FXML
    private Label lblLast;
    @FXML
    private Label lblAccount;
    @FXML
    private Label lblBirth;
    @FXML
    private Label lblBalance;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblCountry;

    @FXML
    private Label lblPhoneNo;

    @FXML
    private Label lblPinNo;
//btn in admin home page
    @FXML
    private JFXButton manageUsersBtn;
    @FXML
    private JFXButton btnBack;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnNew;
    @FXML
    private JFXButton adminLogOutbtn;

//Transfair Variabels
    @FXML
    private JFXTextField transferAmmount;

    @FXML
    private JFXButton transferBtn;

    @FXML
    private JFXTextField transferToAccount;

    @FXML
    private Label transferLable;

    @FXML
    private Label lblForgotPass;

    @FXML
    private JFXButton btnForgotPass;

    Main main;

    ObservableList<Users> userData = FXCollections.observableArrayList();
    Users person;
    AddUserPage page;

    //Initilization of controller class
    //Initializes controller class
//    @FXML
//    private void initialize(){
//        
//    }
    //CALLING THE INITIALIZER PREPAIRS DATA BEFORE IT IS DIPLAYS
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
       //Statment Page
       

        //-----Customeer page--------//
        btnSignIn.setOnAction((ActionEvent e) -> {
            outter:
            for (Users customer : userData) {
                String a, b;
                a = customer.getFirstName();
                b = SignInName.getText();

                if (a.equals(b)) {
                    try {
                        this.user = customer;
                        if (customer.getPinNo() == Integer.parseInt(signInAccount.getText())) {

                            MainDashBoard.toFront();
                            lblOfCustomer.setText("Logged in as: " + user.getFirstName() + " " + user.getLastName());
                            UpDateBalanceText();
                            System.out.println(user.getHasTransferBeenUsed());

                            if (user.getHasTransferBeenUsed()) {
                                user.getTransferMassage();
                                Notifications notify = Notifications.create()
                                        .title("Transfer")
                                        .text(user.getTransferMassage())
                                        .graphic(null)
                                        .hideAfter(Duration.seconds(12))
                                        .position(Pos.CENTER_LEFT)
                                        .onAction((ActionEvent e1) -> {
                                            user.setHasTransferBeenUsed(false);
                                        });
                                notify.showInformation();
                            }
                            //Set balance for withdrawpage
                            break;
                        } else {
                            lblForgotPass.setText("Forgot Password?");
                            btnForgotPass.setOnAction(value -> {
                                sendPinNo();
                            });
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

        //-------Customers endPage-----------//
        adminLogOutbtn.setOnAction(e -> {
            ClearTextField();
            pane.toFront();
        });
        //-----from user page go back to Admin page---//
        btnFromUserToAdmin.setOnAction(e -> rootAnchor.toFront());

        transferBtn.setOnAction(e -> {
            transferMethod();
        });

        //Admin login button
        login.setOnAction(e -> {
            if (txt.getText().equals("user") && pass.getText().equals("user")) {
                label.setText("Login succesfull");

                AdminAnchor.toFront();
            } else {
                label.setText("Login failed");
            }
        });
        //---------user button in admin page------------//
        usered.setOnAction(e -> rootUser.toFront());

//Mange user btn
        manageUsersBtn.setOnAction(e -> usersAnchor.toFront());
        //Back button in manage users 
        btnBack.setOnAction(e -> AdminAnchor.toFront());
        //DELTE BUTTON FOR MANGE USERS PAGE

        colName.setCellValueFactory(e -> e.getValue().firstNameProperty());
        colSurname.setCellValueFactory(t -> t.getValue().lastNameProperty());
        colBalance.setCellValueFactory(t -> t.getValue().balanceProperty().asObject());
        root.setItems(getUsersData());
        //clear User details
        showUserDetails(null);
        //LISTEN TO SELECTION AND CHANGES MADE
        root.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showUserDetails(newValue));
    }

//    @FXML //SIGIN BUTTTON ACTION FOR THE CUSTOMER in AdimPage
//    public void signInUser() {
//                    userLogin.toFront();                 
//    }
    public ObservableList<Users> getUsersData() {

        userData.add(new Users("Jane", "Grey"));
        userData.add(new Users("Charles", "Xzaviar"));
        userData.add(new Users("James", "Hawlet"));
        userData.add(new Users("Bruce", "Wanye"));
        userData.add(new Users("Clark", "Kent"));
        userData.add(new Users("Bruce", "Banner"));
        userData.add(new Users("Peter", "Parker"));
        userData.add(new Users("Thor", "Oden-Son"));
        userData.add(new Users("Wonder", "Maximoff"));
        userData.add(new Users("Tony", "Stark"));
        return userData;
    }

    //Delet button to mangae users
    @FXML
    private void handleDeleteUsers() {
        int selectedIndex = root.getSelectionModel().getSelectedIndex();
        //Error handling if no selection is made before clicking the delete button
        if (selectedIndex >= 0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete confirmation");
            alert.setHeaderText("Confirm Delete");
            alert.setContentText("Are you sure you want to delete this user");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                root.getItems().remove(selectedIndex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No user Selected");
            alert.setContentText("No users have been selected please select a user then click Delete");
            alert.setHeaderText("Selection");
            alert.showAndWait();
        }
    }

    //Show details about selected person
    private void showUserDetails(Users user) {
        if (user != null) {
            lblName.setText(user.getFirstName());
            lblLast.setText(user.getLastName());
            lblCity.setText(user.getCity());
            lblAccount.setText(Integer.toString(user.getAccountNo()));
            lblBalance.setText(Double.toString(user.getBalance()));
            lblBirth.setText(DateUtil.format(user.getBirthday()));
            lblCountry.setText(user.getCountry());
            lblPhoneNo.setText(Integer.toString(user.getPhoneNo()));
            lblPinNo.setText(Integer.toString(user.getPinNo()));

        } else {
            lblName.setText("");
            lblLast.setText("");
            lblCity.setText("");
            lblAccount.setText("");
            lblBalance.setText("");
            lblBirth.setText("");
            lblCountry.setText("");
            lblPinNo.setText("");
            lblPhoneNo.setText("");
        }
    }

    //Create new users
    public boolean showAddUserPage() {

        page = new AddUserPage();
        page.start(new Stage());

        return page.isOkCliked();
    }

    //Edit users
    public boolean showAddUserPage(Users usser) {

        page = new AddUserPage(usser);
        page.start(new Stage());

        return page.isOkCliked();
    }

    //New button event handler for adding new user
    @FXML
    public void handleNewUser() {

        boolean okClicked = showAddUserPage();
        if (okClicked) {
            userData.add(page.setUser());
        }
    }

    @FXML
    public void handleEditUser() {
        Users selectedUser = root.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            int index = root.getSelectionModel().getSelectedIndex();

            boolean okcliked = showAddUserPage(selectedUser);
            if (okcliked) {
                root.getItems().remove(index);
                userData.add(index, page.setUser());
                showUserDetails(selectedUser);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No User Selected");
            alert.setContentText("Please select a User in the table");
            alert.showAndWait();
        }

    }

    private void ClearTextField() {
        SignInName.clear();//Custtomer textfield
        signInAccount.clear();
        deposittxtfield.clear();
        lblForgotPass.setText("");
        withdrawtxtfield.clear();
        txt.clear();//Admin textfield
        pass.clear();
    }

    private void UpDateBalanceText() {
        //For Customer DashBoard
        transferAmmount.clear();
        transferLable.setText("     " + Integer.toString(this.user.getAccountNo()));
        MainCurrBalancetxt.setText(Double.toString(this.user.getBalance()));
        this.user.setBalance(user.getBalance() - 0.78);
        MainAvaBalancetxt.setText(Double.toString(this.user.getBalance()));

    }

    public void transferMethod() {
        for (Users customer : userData) {

            try {
                int user2Account, user1Account;
                double c;
                user2Account = customer.getAccountNo();
                user1Account = Integer.parseInt(transferToAccount.getText());
                c = Double.parseDouble(transferAmmount.getText());
                System.out.println(c);

                if (user2Account == user1Account && user.getAccountNo() != user2Account) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Transfare conformation");
                    alert.setHeaderText("Confirm Transfare");
                    alert.setContentText("Are you sure you want to transfer: " + c + " to account No: " + user1Account + " ?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {

                        System.out.println("Hello in there");
                        this.user2 = customer;
                        this.user2.setHasTransferBeenUsed(true);
                        System.out.println(user2.getHasTransferBeenUsed());//TESING
                        this.user2.setBalance(user2.getBalance() + c);
                        this.user.setBalance(user.getBalance() - c);
                        StatmentMassage += c + "Transfered at " + dateFormat.format(date)+"\n";
                        UpDateBalanceText();
                        this.user2.setTransferMassage(transferCornformation(c, user.getAccountNo()));

                        break;
                    }
                    //Set balance for withdrawpag
                } else {
                    System.out.println("Looping");
                }
            } catch (NumberFormatException messsage) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No number");
                alert.setContentText("Incorract user account please fill in a number");
                alert.setHeaderText("Invaild Number");
                alert.showAndWait();
                break;

            }

        }

    }

    //SET MASSAGE IF USER HAS RECIVED TRANSFER IT WILL NOTIFY
    public String transferCornformation(double money, int accountNumber) {
        return "You Have Received: R" + money + "\n" + "From Account Number: " + accountNumber;
    }
    //Send user password via email

    private void sendPinNo() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Forgot Password");
        dialog.setHeaderText("It seems you forgot your password");
        dialog.setContentText("Please enter your registered email");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            int pin = user.getPinNo();

            EmailService mail = new EmailService(result.get(), "No-Reply@RichBank", "GoodDay your requested PinNo is: " + pin
                    + "\n please make sure to protect your pin at all times");
            lblForgotPass.setText("Please check your email");
        }
    }
}
