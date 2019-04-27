package com.example.book.model;

public class BorrowBean {
    private String id;
    private String bookId;
    private String username;
    private String name;
    private String isReturn;
    private String bookName;
    private String surPlus;
    private String count;

    public BorrowBean() {
    }

    public BorrowBean(String id, String bookId, String username, String name, String isReturn, String bookName, String surPlus, String count) {
        this.id = id;
        this.bookId = bookId;
        this.username = username;
        this.name = name;
        this.isReturn = isReturn;
        this.bookName = bookName;
        this.surPlus = surPlus;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSurPlus() {
        return surPlus;
    }

    public void setSurPlus(String surPlus) {
        this.surPlus = surPlus;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
