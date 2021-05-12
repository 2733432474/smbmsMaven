package com.smbms.controller.dataFormat;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        Date date=null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//转换成简单日期格式
        try{
            date=dateFormat.parse(s);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}