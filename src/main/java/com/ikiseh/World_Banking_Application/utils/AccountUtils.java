package com.ikiseh.World_Banking_Application.utils;

import java.time.Year;

//utility are things that are not needed at all times, its good you save those kind of things in the utils package
public class AccountUtils {

    // NOTE, YOU CAN ALS DO ALL THESE WITH EXCEPTIONS
    public static final String ACCOUNT_EXISTS_CODE = "001";

    public static final String ACCOUNT_EXISTS_MESSAGE =
            "This User already has an account created!";

    public static final String ACCOUNT_CREATED_SUCCESS_CODE = "002";

    public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE=
            "Account has been created Successfully!";

    public static final String ACCOUNT_NUMBER_NON_EXISTS_CODE = "003";

    public static final String ACCOUNT_NUMBER_NON_EXISTS_MESSAGE =
            "Provided Account Number Does Not Exists!";

    public static final String ACCOUNT_NUMBER_FOUND_CODE = "004";

    public static final String ACCOUNT_NUMBER_FOUND_MESSAGE=
            "Account number found!";

    public static final String ACCOUNT_CREDITED_SUCCESS_CODE = "005";

    public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE=
            "Your Account has been Credited!";

    public static final String INSUFFICIENT_FUNDS_CODE = "006";

    public static final String INSUFFICIENT_FUNDS_MESSAGE=
            "Your Account Balance is insufficient!";

    public static final String ACCOUNT_DEBITED_SUCCESS_CODE = "007";

    public static final String ACCOUNT_DEBITED_SUCCESS_MESSAGE=
            "Your Account has been debited successfully!";





    public static String generateAccountNumber(){
        //this algorithm will assume that your account number wil b e a total of 10 digits
        //since we basically have 10 digits account number in Nigeria
        // STEPS:
        //1. GET THE CURRENT YEAR
        Year currentYear = Year.now();

        //2. GET 6 Random digits
        int min = 100000;
        int max = 999999;

        //GENERATE A RANDOM NUMBER BETWEEN MIN AND MAX
        //Math.floor rounds to the perfect whole number
        int randomNumber = (int) Math.floor(Math.random()*(max - min +1) + min);

        //convert current year and random 6 numbers to string and then concatenate them

        String year = String.valueOf(currentYear);
        String randomNum = String.valueOf(randomNumber);

        //append both year and random num to generate the 10 digits account number
        StringBuilder accountNumber = new StringBuilder();
        return accountNumber.append(year).append(randomNum).toString();

    }


}
