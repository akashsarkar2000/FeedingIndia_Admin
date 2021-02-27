package com.example.feedingindia_admin;


public class UsersCharity {

    public String charity_name;
    public String email;
    public String charityReg;
    public String image;
    public String charity_address;
    public String thumb_image;
    public String requirements;
    String phone;
    String post_description;
    String description;

    public UsersCharity() {
    }

    public UsersCharity(String name, String email, String charityReg, String image, String status, String thumb_image) {
        this.charity_name = name;
        this.email = email;
        this.charityReg = charityReg;
        this.image = image;
        this.charity_address = status;
        this.thumb_image = thumb_image;
        this.description = description;
    }

    public void setName(String name) {
        this.charity_name = name;
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

    public String getCharity_name() {
        return charity_name;
    }

    public void setCharity_name(String charity_name) {
        this.charity_name = charity_name;
    }

    public String getCharityReg() {
        return charityReg;
    }

    public void setCharityReg(String charityReg) {
        this.charityReg = charityReg;
    }

    public String getCharity_address() {
        return charity_address;
    }

    public void setCharity_address(String charity_address) {
        this.charity_address = charity_address;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPost_description() {
        return post_description;
    }

    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegNo() {
        return charityReg;
    }

    public void setRegNo(String charityReg) {
        this.charityReg = charityReg;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}