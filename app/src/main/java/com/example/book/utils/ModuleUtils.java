package com.example.book.utils;

import com.example.book.R;
import com.example.book.activity.BookManagerActivity;
import com.example.book.activity.BookSearchManagerActivity;
import com.example.book.activity.BookViewActivity;
import com.example.book.activity.BorrowActivity;
import com.example.book.activity.ManagerActivity;
import com.example.book.activity.MessageActivity;
import com.example.book.activity.NoticeActivity;
import com.example.book.activity.ReturnedActivity;
import com.example.book.model.HomeItem;

import java.util.ArrayList;
import java.util.List;

public class ModuleUtils {
    private static List<HomeItem> mHomeItems = new ArrayList<>();

    public static final List<HomeItem> getAllModule() {
        mHomeItems.clear();
        mHomeItems.add(new HomeItem(R.drawable.usermanager,"用户管理", ManagerActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.bookmanager,"图书管理", BookManagerActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.borrow,"借阅管理", BorrowActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.returned,"归还管理", ReturnedActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.look,"图书浏览", BookViewActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.notice, "公告", NoticeActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.message, "留言中心", MessageActivity.class));
        return mHomeItems;
    }

    public static final List<HomeItem> admin() {
        mHomeItems.clear();
        mHomeItems.add(new HomeItem(R.drawable.usermanager,"用户管理", ManagerActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.notice, "公告", NoticeActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.look,"图书浏览", BookViewActivity.class));
        return mHomeItems;
    }

    public static final List<HomeItem> book() {
        mHomeItems.clear();
        mHomeItems.add(new HomeItem(R.drawable.bookmanager,"图书管理", BookManagerActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.borrow,"借阅管理", BorrowActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.returned,"归还管理", ReturnedActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.look,"图书浏览", BookViewActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.notice, "公告", NoticeActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.message, "留言中心", MessageActivity.class));
        return mHomeItems;
    }

    public static final List<HomeItem> student() {
        mHomeItems.clear();
        mHomeItems.add(new HomeItem(R.drawable.look,"图书浏览", BookViewActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.notice, "公告", NoticeActivity.class));
        mHomeItems.add(new HomeItem(R.drawable.message, "留言中心", MessageActivity.class));
        return mHomeItems;
    }
}
