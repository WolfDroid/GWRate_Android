package com.enveriesagestudios.gwrate.model;
/**
 * Customer Class.
 * Description : This class is used for getting and setting the Customer information.
 *
 * @author Ilham Mulya Rafid
 * @version 20.03.2020
 *
 * @param for every mutator and every variables that called in Constructor
 * @param id = Unique number of the Customer for Identifying
 * @param name = Name of the Customer
 * @param email = Email of the Customer
 * @param password = Password of the Customer Account
 * @param joinDate = Date of the Customer when joining the platform
 *
 * @return for every class with it's parameter
 * @return id = returning the unique value ID of the customer
 * @return name = returning the value name of the Customer
 * @return email = returning name of the Customer
 * @return password = returning the value of the customer password
 * @return joinDate = returning the value of the customer joining date
 *
 */

//External Library
import com.google.firebase.auth.FirebaseAuth;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User{
    // instance variables
    private String uid;
    private String name;
    private String email;
    private String password;
    private Calendar joinDate;
    private String profilePicture;
    private String coverPicture;
    private String address;
    private String phoneNumber;

    //Constructor
    public User(String uid, String name, String email, String password, Calendar joinDate){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.joinDate = joinDate;
    }

    public User(String uid, String email, String name, String password, int year, int month, int dayOfMonth){
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.password = password;
        this.joinDate = new GregorianCalendar(year, month-1,dayOfMonth);
    }

    //Constructor without Password for Register
    public User(String uid, String email, String name){
        this.uid = uid;
        this.setEmail(email);
        this.name = name;
    }

    //Constructor for Profile
    public User(String uid, String name, String email, String password, String profilePicture, String coverPicture, String address, String phoneNumber) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.coverPicture = coverPicture;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //Accessor
    public String getUid(){
        return uid;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public Calendar getJoinDate(){
        return joinDate;
    }
    public String getProfilePicture() { return profilePicture; }
    public String getCoverPicture() { return coverPicture; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }

    //Mutator
    public void setUid(String uid){
        this.uid = uid;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        Pattern pattern = Pattern.compile("^[\\w%&_*~]+(?:\\.[\\w&_*~]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher match = pattern.matcher(email);
        //Error check case
        if(match.find()){
            this.email = email;
        }
        else{
            this.email = "";
        }
    }
    public void setPassword(String password){
        String polaPassword ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern pattern = Pattern.compile(polaPassword);
        Matcher match = pattern.matcher(password);
        //Error check case
        if(match.find()){
            this.password = password;
        }
        else{
            this.password = "";
        }
    }
    public void setJoinDate(Calendar joinDate){
        this.joinDate = joinDate;
    }
    public void setJoinDate(int year, int month, int dayOfMonth){
        this.joinDate = new GregorianCalendar(year, month-1,dayOfMonth);
    }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
    public void setCoverPicture(String coverPicture){ this.coverPicture = coverPicture; }
    public void setAddress(String address) { this.address = address; }
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber; }
}
