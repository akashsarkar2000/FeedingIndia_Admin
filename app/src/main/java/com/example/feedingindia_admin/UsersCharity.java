package com.example.feedingindia_admin;

public class UsersCharity {
    public String charity_name;
    public String email;
    public String charityReg;
    public String image;
    public String charity_address;
    public String thumb_image;
    public String requirements;
    private String post_image;
    String phone;
    String post_description;
    String description;

    public UsersCharity() {
    }

    public String getCharity_name() {
        return charity_name;
    }
    public void setCharity_name(String charity_name) {
        this.charity_name = charity_name;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
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


    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public String getPost_description() {
        return post_description;
    }
    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }


    public String getPost_image() {
        return post_image;
    }
    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }


    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }


    public String getRequirements() {
        return requirements;
    }
    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

}