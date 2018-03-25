package com.example.gaurang.glitz;

/**
 * Created by yash on 11/3/18.
 */

public class UserRegister {

    private String id;
    private String name;
    private String email;
    private String mobile_no;

    public UserRegister(String id, String name, String email, String mobile_no) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile_no = mobile_no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
