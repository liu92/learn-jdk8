package com.learn.jdk.chapter12;

import java.util.List;

/**
 * 公司
 * @ClassName: Company
 * @Description:
 * @Author: lin
 * @Date: 2019/12/30 15:22
 * History:
 * @<version> 1.0
 */
public class Company {
    private String name;
    private List<Employee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
