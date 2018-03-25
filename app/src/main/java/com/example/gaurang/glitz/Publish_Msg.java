package com.example.gaurang.glitz;

import java.io.Serializable;

/**
 * Created by gaurang on 11-03-2018.
 */

public class Publish_Msg implements Serializable{

    private String  Title,Desc;


    public Publish_Msg() {

    }


    public Publish_Msg(String Title, String Desc) {
        this.Title = Title;
        this.Desc = Desc;
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
