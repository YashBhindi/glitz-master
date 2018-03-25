package com.example.gaurang.glitz;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gaurang on 11-03-2018.
 */

public class User_publish_messsage implements Serializable{

    private String  Title,Desc,company;


    public User_publish_messsage() {

    }


    public User_publish_messsage(String Title, String Desc,String company) {
        this.Title = Title;
        this.Desc = Desc;
        this.company=company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
