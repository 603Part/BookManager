package com.example.book.model;

public class BookBean {
    private String id;
    private String bookname;
    private String bookAuthor;
    private String isbn;
    private String press;
    private String brief;
    private String price;
    private String count;
    private String surplus;
    private String dep;
    private String remark;

    public BookBean() {
    }

    public BookBean(String id, String bookname, String bookAuthor, String isbn, String press, String brief, String price, String count, String surplus, String dep, String remark) {
        this.id = id;
        this.bookname = bookname;
        this.bookAuthor = bookAuthor;
        this.isbn = isbn;
        this.press = press;
        this.brief = brief;
        this.price = price;
        this.count = count;
        this.surplus = surplus;
        this.dep = dep;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "id='" + id + '\'' +
                ", bookname='" + bookname + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", press='" + press + '\'' +
                ", brief='" + brief + '\'' +
                ", price='" + price + '\'' +
                ", count='" + count + '\'' +
                ", surplus='" + surplus + '\'' +
                ", dep='" + dep + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
