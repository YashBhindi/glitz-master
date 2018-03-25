package com.example.gaurang.glitz;

/**
 * Created by BHIMANI585 on 3/10/2018.
 */
import java.io.Serializable;

public class UserData implements Serializable {


        private String  id,name, inquiry_type,email,contact;


        public UserData() {

        }


        public UserData(String id,String email, String name, String inquiry_type,String contact) {
            this.id=id;
            this.name = name;
            this.email = email;
            this.inquiry_type = inquiry_type;
            this.contact=contact;
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

     public void setEmail(String email) {
        this.email = email;
    }

        public String getName() {
            return name;
        }

    public void setName(String name) {
        this.name = name;
    }


    public String getContact() {
            return contact;
        }



        public void setContact(String contact) {
            this.contact = contact;
        }


    public String getInquiry_type() {
        return inquiry_type;
    }



    public void setInquiry_type(String inquiry_type) {
        this.inquiry_type = inquiry_type;
    }
}

