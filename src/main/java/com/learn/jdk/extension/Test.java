package com.learn.jdk.extension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName: Test
 * @Description:
 * @Author: lin
 * @Date: 2020/1/6 9:58
 * History:
 * @<version> 1.0
 */
public class Test {
    public static void main(String[] args) {
        String lastUpdateTime= getLastMonth();
        System.out.println("最后更新时间"+lastUpdateTime);

    }


    public static String getLastMonth() {
        LocalDate today = LocalDate.now();
        today = today.minusMonths(1L);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        return formatters.format(today);
    }
}
