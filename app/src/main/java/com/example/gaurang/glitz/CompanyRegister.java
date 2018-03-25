package com.example.gaurang.glitz;


public class CompanyRegister {
    private String id;
    private String CIN;
    private String companyName;
    private String companyWebsite;
    private String emailId;
    private String contactPersonName;
    private String companyDescription;
    private String services;
    private String contact;
    String cuser;
    private  String Annual_revenue;
    private String Rating;

    public CompanyRegister(){}

    public CompanyRegister(String id, String CIN, String companyName, String companyWebsite, String emailId, String contactPersonName, String companyDescription, String services, String contact,String cuser ,String Rating,String Annual_revenue) {
        this.id = id;
        this.CIN = CIN;
        this.companyName = companyName;
        this.companyWebsite = companyWebsite;
        this.emailId = emailId;
        this.contactPersonName = contactPersonName;
        this.companyDescription = companyDescription;
        this.services = services;
        this.contact = contact;
        this.cuser=cuser;
        this.Annual_revenue=Annual_revenue;
        this.Rating=Rating;
    }





    public String getAnnual_revenue() {
        return Annual_revenue;
    }

    public void setAnnual_revenue(String annual_revenue) {
        Annual_revenue = annual_revenue;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getCuser() {

        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
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

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }
}
