package com.learn.jdk.chapter12;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * chapter12
 * @ClassName: OptionalDemo2
 * @Description: optional 使用
 * @Author: lin
 * @Date: 2019/12/30 15:23
 * History:
 * @<version> 1.0
 */
public class OptionalDemo2 {
    public static void main(String[] args) {
      Employee employee = new Employee();
      employee.setName("lisa");

      Employee employee1 = new Employee();
      employee1.setName("zairian");

      Company company = new Company();
      company.setName("company1");
      List<Employee> employees  = Arrays.asList(employee, employee1);
      company.setEmployees( employees);
      // 如果company中不包含 任何employee信息, 那么就返回 一个空的集合
      // 如果存在就返回一个列表
      List<Employee> list = company.getEmployees();

      Optional<Company> optionals = Optional.ofNullable(company);
      //
      System.out.println(optionals.map(Company::getEmployees).
                orElse(Collections.emptyList()));
    }


    //Tips: 不要将Optional试图作为方法参数，也不要在类中定义Optional成员变量，
    // Optional 通常只作为方法的返回值，用来规避空指针异常这样一个问题
    // 编写Optional 一定要使用函数式的方式来编写
}

