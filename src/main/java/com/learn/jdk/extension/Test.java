package com.learn.jdk.extension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String s = "31 NET OA专用";
        String[] nets = s.split(" ");
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);

        for (String net : nets) {
            Matcher m = p.matcher(net);
            System.out.println(m.replaceAll("").trim());
        }

        Pattern p1 = Pattern.compile(regEx);
        Matcher m = p1.matcher(s);
        System.out.println( "===========" + m.replaceAll("").trim());


          int to = 20;
          String tp = "-20";
          if(to > Integer.parseInt(tp)){

              System.out.println("大于。。。。。。。。。。。。。。" + Integer.valueOf(tp));
          }
        String rate = ".1";

        String ratioString=keepRandomPoint(1/Double.valueOf(rate),9);
        System.out.println("ratioString========="+ ratioString);
        double v = new BigDecimal(ratioString).doubleValue();
        System.out.println("v=============="+ v);

        int keyNumber = 81;
        Double ratio = 6.4 ;
        String subNumber =keepRandomPoint(keyNumber/ratio,9);
        System.out.println("subNumber========="+Double.valueOf(subNumber));

        Double stockNum = 12.65625;
        Double occupyStock =0.0;
        Double numTransferPlanOut=0.0;
        Double stock = calSellAbleStock(stockNum, occupyStock, numTransferPlanOut);
        System.out.println("calSellAbleStock============" + stock);

    }


    public static String getLastMonth() {
        LocalDate today = LocalDate.now();
        today = today.minusMonths(1L);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        return formatters.format(today);
    }

    public static String keepRandomPoint(Double value, int n) {
        if (value == null) {
            value = 0.0D;
            return (new BigDecimal(value)).setScale(n, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        } else {
            return (new BigDecimal(value)).setScale(n, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        }
    }

    public static Double calSellAbleStock(Double stockNum, Double occupyStock, Double numTransferPlanOut) {
        Double result=add(stockNum,-occupyStock,-numTransferPlanOut);
        return result;
    }


    public static Double add(Double... params) {
        BigDecimal result = new BigDecimal(0);
        Double[] var2 = params;
        int var3 = params.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Double param = var2[var4];
            result = result.add( new BigDecimal(param.toString()));
        }

        return result.doubleValue();
    }
}
