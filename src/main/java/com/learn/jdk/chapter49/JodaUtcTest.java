package com.learn.jdk.chapter49;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 *  joda-time 和
 *  java8 时间和日期对象api 都是不可变对象
 *
 * @ClassName: JodaUtcTest
 * @Description: UTC(统一协调时间转换)
 * @Author: lin
 * @Date: 2020/1/20 14:29
 * History:
 * @<version> 1.0
 */
public class JodaUtcTest {
    /**
     * 标准UTC时间转换为一个日期
     * 如 标准UTC时间：2020-01-20T08:45:43.764Z
     * @param utcDate
     * @return
     */
    public static Date convertUTC2Date(String utcDate){
         try {
             DateTime dateTime = DateTime.parse(utcDate,
                     DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
             return dateTime.toDate();
         }catch (Exception ex){
             return null;
         }
    }

    /**
     * 将一个日期转换成标准UTC时间
     * @param date
     * @return
     */
    public static  String convertDate2UTC(Date date){
        DateTime dateTime = new DateTime(date, DateTimeZone.UTC);
        return  dateTime.toString();
    }

    public  static  String convertDateToLocalByDaTeFormat(Date date, String dateFormat){
        DateTime dateTime = new DateTime(date);
        return  dateTime.toString(dateFormat);
    }

    public static void main(String[] args) {
        System.out.println(JodaUtcTest.convertUTC2Date("2020-01-20T08:45:43.764Z"));
        System.out.println(JodaUtcTest.convertDate2UTC(new Date()));
        System.out.println(JodaUtcTest.convertDateToLocalByDaTeFormat(new Date(),
                "yyyy-MM-dd HH:mm:ss"));
    }
}
