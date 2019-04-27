package com.example.book.model;

public class HomeItem {
    private int img;
    private String title;
    private Class clazz;

    public HomeItem(int img, String title, Class clazz) {
        this.img = img;
        this.title = title;
        this.clazz = clazz;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
