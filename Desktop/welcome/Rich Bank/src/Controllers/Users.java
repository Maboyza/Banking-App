/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Random;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author terry
 */
public class Users {

    //Random Generator
    private Random random,fun;
    private int account;
    private double dummyBalance;
    //User Field Variables
    private StringProperty firstName;
    private StringProperty lastName;
    private DoubleProperty balance;
    private IntegerProperty accountNo;
    private StringProperty city;
    private ObjectProperty<LocalDate> birthday;
    private DecimalFormat f;
    private IntegerProperty pinNo;
    private StringProperty country;
    private IntegerProperty phoneNo;
    private StringProperty transferMassage;
    private BooleanProperty hasTransferBeenUsed;
    // private int account;

    //Default and overloaderd Constuctors
    public Users() {

        this(null, null);
    }

    //Overloaded constructor
    public Users(String firstName, String lastName) {
        random = new Random();
       // unpredictable = new Random();
        fun = new Random();
       int rand = fun.nextInt(6);
       //part1 of user initialize data
        f = new DecimalFormat("##.00");
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.accountNo = new SimpleIntegerProperty(random.nextInt(900) + 1000);
        //part2 Test initail data
        this.city = new SimpleStringProperty(randomcityPicker(rand));
        this.balance = new SimpleDoubleProperty(random.nextInt(1000) + 100.0);
        this.birthday = new SimpleObjectProperty<>(LocalDate.of(2001, 10, 5));
        this.pinNo = new SimpleIntegerProperty(random.nextInt(100) + 100);
        //part3 of initialize data
        this.phoneNo = new SimpleIntegerProperty(random.nextInt(100000) + 270000000);
        this.country = new SimpleStringProperty("South Africa");
        this.hasTransferBeenUsed = new SimpleBooleanProperty(false);
        this.transferMassage = new SimpleStringProperty("");

    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * @return the balance
     */
    public Double getBalance() {

        return Double.parseDouble(f.format(balance.get()));
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    public DoubleProperty balanceProperty() {

        return balance;
    }

    /**
     * @return the accountNo
     */
    public int getAccountNo() {
        return accountNo.get();
    }

    /**
     * @param accountNo the accountNo to set
     */
    public void setAccountNo(int accountNo) {
        this.accountNo.set(accountNo);
    }

    public IntegerProperty accountNoProperty() {
        return accountNo;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city.get();
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }

    /**
     * @return the birthday
     */
    public LocalDate getBirthday() {
        return birthday.get();
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
    //Method will select a random city for my dummie account
    public final String randomcityPicker(int a) {
        switch (a) {
            case 0:
                return "Roodepoort";
            case 1:
                return "Johannesburg";
            case 2:
                return "ParkTown";
            case 3:
                return "Mayfair";
            case 4:
                return "Sandton";
            case 5:
                return "Bryanston";
        }
        return null;

    }

    /**
     * @return the pinNo set Pin
     */
     public int getPinNo() {
        return pinNo.get();
    }
    public IntegerProperty pinNoProperty() {
        return pinNo;
    }

    public void setPinNo(int pinNo) {
        this.pinNo.set(pinNo);
    }

    /**
     * @return the country and set pin
     */
    public String getCountry() {
        return country.get();
    }
    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    /**
     * @return the phoneNo and set pin
     */
    public int getPhoneNo() {
        return phoneNo.get();
    }
    public IntegerProperty phoneNoProperty() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo.set(phoneNo);
    }
    
    /**
     * @return the transfer Massage and set pin
     */
    public String getTransferMassage() {
        return transferMassage.get();
    }
    public StringProperty transferMassageProperty() {
        return transferMassage;
    }

    public void setTransferMassage(String meassage) {
        this.transferMassage.set(meassage);
    }
    /**
     * @return the transfer keep track of variable and set pin
     */
    public boolean getHasTransferBeenUsed() {
        return hasTransferBeenUsed.get();
    }
    public BooleanProperty hasTransferBeenUsedProperty() {
        return hasTransferBeenUsed;
    }

    public void setHasTransferBeenUsed(boolean meassage) {
        this.hasTransferBeenUsed.set(meassage);
    }
   
}

