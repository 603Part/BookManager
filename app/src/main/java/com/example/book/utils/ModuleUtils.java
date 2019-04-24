package com.example.book.utils;

import com.example.book.R;
import com.example.book.model.HomeItem;

import java.util.ArrayList;
import java.util.List;

public class ModuleUtils {
    private static List<HomeItem> mHomeItems = new ArrayList<>();

    public static final List<HomeItem> getAllModule() {
        mHomeItems.clear();
        mHomeItems.add(new HomeItem(R.drawable.usermanager,"用户管理"));
        mHomeItems.add(new HomeItem(R.drawable.bookmanager,"图书管理"));
        mHomeItems.add(new HomeItem(R.drawable.borrow,"借阅管理"));
        mHomeItems.add(new HomeItem(R.drawable.returned,"归还管理"));
        mHomeItems.add(new HomeItem(R.drawable.look,"图书浏览"));
        mHomeItems.add(new HomeItem(R.drawable.booksearch, "图书查询"));
        return mHomeItems;
    }
}
