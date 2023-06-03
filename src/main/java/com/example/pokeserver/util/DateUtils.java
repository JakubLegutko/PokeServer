package com.example.pokeserver.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@UtilityClass
public class DateUtils {

    public Date createDateFromDateString(String dateString){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        if(StringUtils.hasText(dateString)){
           try{
               date = format.parse(dateString);
           } catch (ParseException e) {
               date = new Date();
           }
        }else{
            date = new Date();
        }
        return date;
    }

    public String getLocalDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}