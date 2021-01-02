package com.example.feedingindia_admin;

public class Users {

    public String donor_name;
    public String image;
    public String email;
    public String profession;
    public String thumb_image;

    public Users() {
    }


    public Users(String name, String email, String image, String profession, String thumb_image) {
        this.donor_name = name;
        this.email = email;
        this.image = image;
        this.profession = profession;
        this.thumb_image = thumb_image;
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






}