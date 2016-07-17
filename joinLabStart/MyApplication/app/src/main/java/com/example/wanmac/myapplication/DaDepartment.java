package com.example.wanmac.myapplication;

/**
 * Created by ubun1604 on 7/16/16.
 */
public class DaDepartment {
    private String ssn, company;
    private int salary, expericence;

    public DaDepartment(String ssn, String company, int salary, int expericence) {
        this.ssn = ssn; this.company = company; this.salary = salary;
        this.expericence = expericence;
    }

    public String getSSN() {
        return ssn;
    }

    public void setSSN(String str) {
        ssn = str;
    }///////////////////////////////
    public String getCompany() {
        return company;
    }

    public void setCompany(String str) {
        company = str;
    }///////////////////////////////
    public int getSalary() {
        return salary;
    }

    public void setSalary(int num) {
        salary = num;
    }///////////////////////////////
    public int getExpericence() {
        return expericence;
    }

    public void setExpericence(int num) {
        expericence = num;
    }
}
