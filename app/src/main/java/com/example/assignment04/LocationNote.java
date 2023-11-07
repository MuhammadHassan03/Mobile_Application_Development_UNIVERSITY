package com.example.assignment04;

public class LocationNote {
    String noteId;
    String locationName;
    String locationDescription;
    String imageUrl;

    public LocationNote() {
    }

    public LocationNote(String noteId, String locationName, String locationDescription, String imageUrl) {
        this.noteId = noteId;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.imageUrl = imageUrl;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
