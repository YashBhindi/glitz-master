package com.example.gaurang.glitz;

import java.io.Serializable;

public class User_msg_by_cpy implements Serializable{

    private String  id,Title,Desc;


    public User_msg_by_cpy() {

    }


    public User_msg_by_cpy(String id, String Title,String Desc) {
        this.id=id;
        this.Title = Title;
        this.Desc = Desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
