package com.example.feedingindia_admin;


public class UsersCharity {

    public String charity_name;
    public String email;
    public String charityReg;
    public String image;
    public String charity_address;
    public String thumb_image;

    public UsersCharity() {
    }

    public UsersCharity(String name, String email, String charityReg, String image, String status, String thumb_image) {
        this.charity_name = name;
        this.email = email;
        this.charityReg = charityReg;
        this.image = image;
        this.charity_address = status;
        this.thumb_image = thumb_image;
    }

    public void setName(String name) {
        this.charity_name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setReg(String charityReg) {
        this.charity_name = charityReg;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(String status) {
        this.charity_address = charity_address;
    }

    public String getName() {
        return charity_name;
    }

    public String getReg() {
        return charityReg;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return charity_address;
    }

    public void setThumb_image(String thumb_image) {
        this.thumb_image = thumb_image;
    }

    public String getThumb_image() {
        return thumb_image;
    }


}