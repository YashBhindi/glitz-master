package com.example.gaurang.glitz;

import java.io.Serializable;

/**
 * Created by yash on 25/3/18.
 */

public class Admin_blog_bean implements Serializable {

    String a_name,a_reply,a_cuser;
    public Admin_blog_bean(){}

    public Admin_blog_bean(String a_name,String a_cuser, String a_reply) {
        this.a_name = a_name;
        this.a_reply = a_reply;

        this.a_cuser = a_cuser;
    }

    public String getA_cuser() {
        return a_cuser;
    }

    public void setA_cuser(String a_cuser) {
        this.a_cuser = a_cuser;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_reply() {
        return a_reply;
    }

    public void setA_reply(String a_reply) {
        this.a_reply = a_reply;
    }
}
