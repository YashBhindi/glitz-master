package com.example.gaurang.glitz;

import java.io.Serializable;

/**
 * Created by yash on 25/3/18.
 */

public class Admin_blog_tbean implements Serializable {

    String a_title,a_desc;

    public  Admin_blog_tbean(){}

    public String getA_title() {
        return a_title;
    }

    public void setA_title(String a_title) {
        this.a_title = a_title;
    }

    public String getA_desc() {
        return a_desc;
    }

    public void setA_desc(String a_desc) {
        this.a_desc = a_desc;
    }

    public Admin_blog_tbean(String a_title, String a_desc) {
        this.a_title = a_title;
        this.a_desc = a_desc;
    }


}
