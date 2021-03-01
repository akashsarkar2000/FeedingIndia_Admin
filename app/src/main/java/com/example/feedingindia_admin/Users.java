package com.example.feedingindia_admin;

public class Users {

    public String donor_name;
    public String image;
    public String email;
    public String profession;
    public String thumb_image;
    public String phone;
    public String address;
    public String status;

    public Users() {
    }

    public void setName(String name) {
        this.donor_name = name;
    }
    public String getName() {
        return donor_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getProfession() {
        return profession;
    }

    public void setThumb_image(String thumb_image) {
        this.thumb_image = thumb_image;
    }
    public String getThumb_image() {
        return thumb_image;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}