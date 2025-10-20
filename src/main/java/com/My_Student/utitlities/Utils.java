package com.My_Student.utitlities;

public class Utils {
    private Utils(){
        // restrict instantiation
    }
    public static String STATUS_201 = "201";
    public static String MESSAGE_201 = "congratulations!!";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";
    public static String STATUS_409 = "409";
    public static String MESSAGE_409 = "student already exists in database";
    public static String NOT_EXISTS_CODE = "505";
    public static String NOT_EXISTS_MESSAGE = "student information not exists in database";
    public static final String  STATUS_417 = "417";
    public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
    public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
    public static final String  STATUS_500 = "500";
    public static final String  MESSAGE_500 = "An error occurred. Please try again or contact Dev team";
}
