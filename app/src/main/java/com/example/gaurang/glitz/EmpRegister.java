package com.example.gaurang.glitz;

/**
 * Created by yash on 12/3/18.
 */

public class EmpRegister {

    private String id;

    private String emailId;
    private String contactPersonName;
    private String deptDescription;
    private String services;
    private String contact;
    String cuser;

    public EmpRegister(String id,  String emailId, String contactPersonName, String deptDescription, String services, String contact,String cuser) {
        this.id = id;
this.cuser=cuser;

        this.emailId = emailId;
        this.contactPersonName = contactPersonName;
        this.deptDescription = deptDescription;
        this.services = services;
        this.contact = contact;

    }

    public String getCuser() {
        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getDeptDescription() {
        return deptDescription;
    }

    public void setDeptDescription(String deptDescription) {
        this.deptDescription = deptDescription;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
