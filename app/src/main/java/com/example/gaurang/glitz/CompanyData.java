package com.example.gaurang.glitz;

import java.io.Serializable;

/**
 * Created by BHIMANI585 on 3/13/2018.
 */

public class CompanyData implements Serializable {


        private String  c_name, c_website,c_email,id;


        public CompanyData() {

        }

    public CompanyData(String id,String c_name, String c_website, String c_email) {
        this.c_name = c_name;
        this.c_website = c_website;
        this.c_email = c_email;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_website() {
        return c_website;
    }

    public void setC_website(String c_website) {
        this.c_website = c_website;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }
}
