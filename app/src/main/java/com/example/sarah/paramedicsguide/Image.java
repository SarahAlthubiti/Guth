package com.example.sarah.paramedicsguide;

import com.google.firebase.storage.StorageReference;

public class Image {
    String key;
    StorageReference imageUri;

    public Image() {
    }

    public Image(StorageReference imageUri, String key) {
        this.imageUri = imageUri;
        this.key = key;
    }

    public StorageReference getFilepath() {
        return imageUri;
    }

    public String getKey() {
        return key;
    }

    public void setFilepath(StorageReference filepath) {
        this.imageUri = filepath;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
