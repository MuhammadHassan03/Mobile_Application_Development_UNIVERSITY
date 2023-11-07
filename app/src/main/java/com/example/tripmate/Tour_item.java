package com.example.tripmate;

public class Tour_item {
    int image;
    String Title;
    String Description;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Tour_item(int image, String title, String description) {
        this.image = image;
        Title = title;
        Description = description;
    }
}
