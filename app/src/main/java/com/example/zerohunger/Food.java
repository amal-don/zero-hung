package com.example.zerohunger;

public class Food {
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDnumber() {
        return dnumber;
    }

    public void setDnumber(String dnumber) {
        this.dnumber = dnumber;
    }

    public String getDaddress() {
        return daddress;
    }

    public void setDaddress(String daddress) {
        this.daddress = daddress;
    }

    public String getDquandity() {
        return dquandity;
    }

    public void setDquandity(String dquandity) {
        this.dquandity = dquandity;
    }

    public String getDtype() {
        return dtype;
    }

    public Food(String dname, String dnumber, String daddress, String dquandity, String dtype) {
        this.dname = dname;
        this.dnumber = dnumber;
        this.daddress = daddress;
        this.dquandity = dquandity;
        this.dtype = dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    private String dname,dnumber,daddress,dquandity,dtype;

    public Food() {
    }
}
