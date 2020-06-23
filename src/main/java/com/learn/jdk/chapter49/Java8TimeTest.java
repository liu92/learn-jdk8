package com.learn.jdk.chapter49;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 *  java8 时间和日期api使用
 * @ClassName: Java8TimeTest
 * @Description:
 * @Author: lin
 * @Date: 2020/1/20 14:49
 * History:
 * @<version> 1.0
 */
public class Java8TimeTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();

        LocalDate todayTime = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当前月份的第一天 todayTime: "+todayTime);

        System.out.println("nowDate: " + localDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = localDate.format(formatter);
        System.out.println("format: "+ format);

        LocalDate beforeDate = localDate.plusMonths(-1);
        System.out.println("beforeDate:" + beforeDate);

        LocalDate afterDate = localDate.plusMonths(1);
        System.out.println("afterDate:" + afterDate);

        // 注意: 这里localDate是借鉴了joda-time来的，并且月份进行改良，现在是从1开始，而不是从0开始了
        System.out.println(localDate.getYear() + ", " + localDate.getMonthValue() +
                ", " + localDate.getDayOfMonth());

        System.out.println("-------------");

        LocalDate localDate1 = LocalDate.of(2019, 12,19);
        System.out.println("localDate1: "+  localDate1);

        System.out.println("---------------");

        LocalDate localDate2 = LocalDate.of(2018, 11, 23);
        // 表示月和天,但是没有年份
        MonthDay monthDay = MonthDay.of(localDate2.getMonth(), localDate2.getDayOfMonth());
        MonthDay monthDay1 = MonthDay.from(LocalDate.of(2019, 11, 23));

        if(monthDay.equals(monthDay1)){
            System.out.println("equals");
        }else {
            System.out.println("not equals");
        }
        System.out.println("---------------");
        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalTime time1 = time.plusHours(3).plusMinutes(10);

        System.out.println(time1);
        System.out.println("---------------");


        //加
        LocalDate localDate3 = localDate.plus(2, ChronoUnit.WEEKS);
        System.out.println("plus"+localDate3);
        //减
        LocalDate localDate4 = localDate.minus(2, ChronoUnit.MONTHS);
        System.out.println(localDate4);
        System.out.println("---------------");

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());

        System.out.println("---------------");

        LocalDate localDate5 = LocalDate.now();

        LocalDate localDate6 = LocalDate.of(2019, 3,28);
        System.out.println(localDate5.isAfter(localDate6));
        System.out.println(localDate5.isBefore(localDate6));
        System.out.println(localDate5.equals(localDate6));
        System.out.println("---------------");

        //时区
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.stream().forEach(System.out::println);
        //排序
        Set<String> treeSet = new TreeSet<String>(){
            {
                addAll(set);
            }
        };

        treeSet.stream().forEach(System.out::println);
        System.out.println("---------------");

        //时区
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(zonedDateTime);
        System.out.println("---------------");

        //年份和月份时间对象
        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.lengthOfMonth());
        //是否是闰年
        System.out.println(yearMonth.isLeapYear());
        System.out.println("---------------");

        YearMonth yearMonth1 = YearMonth.of(2019,3);
        System.out.println(yearMonth1);
        System.out.println(yearMonth1.lengthOfMonth());
        System.out.println(yearMonth1.lengthOfYear());
        System.out.println(yearMonth1.isLeapYear());
        System.out.println("---------------");

        LocalDate localDate7 = LocalDate.now();
        LocalDate localDate8 = LocalDate.of(2020,1,25);

        //时间的间隔
        Period period = Period.between(localDate7, localDate8);
        //这里打印的是间隔的 月和天，如果有年份间隔那么可以将年份打印出来。
        //得到间隔的月份
        System.out.println(period.getMonths());
        //间隔天数
        System.out.println(period.getDays());


        Date date = new Date();
        LocalDate localDate9 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(30);
        System.out.println("localDate9=============="+localDate9);
    }
}
