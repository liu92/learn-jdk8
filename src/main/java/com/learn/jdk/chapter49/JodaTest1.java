package com.learn.jdk.chapter49;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * chapter49
 * @ClassName: JodaTest1
 * @Description: joda-time使用
 * @Author: lin
 * @Date: 2020/1/20 13:29
 * History:
 * @<version> 1.0
 */
public class JodaTest1 {
    public static void main(String[] args) {
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);

        System.out.println(today.toString("yyyy-MM-dd"));
        System.out.println(tomorrow.toString("yyyy-MM-dd"));
        System.out.println("-----------");

        //将天设置为1
        DateTime d1 = today.withDayOfMonth(1);

        System.out.println(d1.toString("yyyy-MM-dd"));
        System.out.println("-----------");

        LocalDate localDate = new LocalDate();
        //当前时区日期
        System.out.println(localDate);
        System.out.println("-----------");
        //表示当前日期后的2个月,其中最大的日期
        localDate = localDate.plusMonths(2).dayOfMonth().withMaximumValue();
        System.out.println(localDate);

        System.out.println("-----------");
        //计算两年前第3个月最后1天的日期
        DateTime dateTime = new DateTime();
        DateTime dateTime1 = dateTime.minusYears(2).monthOfYear().
                setCopy(3).dayOfMonth().withMinimumValue();
        System.out.println(dateTime1.toString("yyyy-MM-dd"));
    }





}
