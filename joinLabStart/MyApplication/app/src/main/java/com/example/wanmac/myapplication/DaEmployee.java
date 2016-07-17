package com.example.wanmac.myapplication;

/**
 * Created by ubun1604 on 7/16/16.
 */
public class DaEmployee {
    private String ssn, fname, lname, yBirth, city;

    public DaEmployee(String ssn, String fname, String lname, String yBirth, String city) {
        this.ssn = ssn; this.fname = fname; this.lname = lname;
        this.yBirth = yBirth; this.city = city;
    }

    public String getSSN() {
        return ssn;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }
    /////////////////////////////////////////////
    public String getFname() {
        return fname;
    }

    public void setFname(String str) {
        this.fname = str;
    }
    /////////////////////////////////////////////
    public String getLname() {
        return lname;
    }

    public void setLname(String ssn) {
        this.lname = ssn;
    }
    /////////////////////////////////////////////
    public String getyBirth() {
        return yBirth;
    }

    public void setyBirth(String ssn) {
        this.yBirth = ssn;
    }
    /////////////////////////////////////////////
    public String getCity() {
        return city;
    }

    public void setCity(String ssn) {
        this.ssn = ssn;
    }
    /////////////////////////////////////////////

}
