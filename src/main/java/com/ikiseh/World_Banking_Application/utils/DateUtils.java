package com.ikiseh.World_Banking_Application.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String dateToString(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern("yyy-MM-dd'T'HH:mm:ss"));
    }
}
