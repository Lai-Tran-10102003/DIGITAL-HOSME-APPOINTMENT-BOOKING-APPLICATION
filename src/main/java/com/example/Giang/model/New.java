package com.example.Giang.model;

public class New {
    int newThumb;
    String newName;
    String newAuthor;
    String newTime;

    public New(int newThumb, String newName, String newAuthor, String newTime) {
        this.newThumb = newThumb;
        this.newName = newName;
        this.newAuthor = newAuthor;
        this.newTime = newTime;
    }

    public int getNewThumb() {
        return newThumb;
    }

    public void setNewThumb(int newThumb) {
        this.newThumb = newThumb;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewAuthor() {
        return newAuthor;
    }

    public void setNewAuthor(String newAuthor) {
        this.newAuthor = newAuthor;
    }

    public String getNewTime() {
        return newTime;
    }

    public void setNewTime(String newTime) {
        this.newTime = newTime;
    }
}
