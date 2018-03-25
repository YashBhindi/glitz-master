package com.example.gaurang.glitz;

import java.io.Serializable;


public class User_Data implements Serializable {
         String  id,name,mobile_no,email;


        public User_Data() {

        }


            public User_Data(String id,String name, String email, String mobile_no) {
            this.name = name;
            this.email = email;
            this.mobile_no = mobile_no;
            this.id=id;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
            return email;
        }


        public String getName() {
            return name;
        }

        public String getMobile_no() {
            return mobile_no;
        }


        public void setName(String name) {this.name = name;}

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
