package com.example.book.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.adapter.BookAdapter;
import com.example.book.db.DBManager;
import com.example.book.model.BookBean;

import java.util.List;

public class BookSearchManagerActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private TextView title,addUser;
    private ListView manager;
    private List<BookBean> bookBeans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        String bookName = getIntent().getStringExtra("bookName");
        bookBeans = DBManager.findBookByLikeName(bookName);
        title = findViewById(R.id.app_title);
        addUser = findViewById(R.id.add_user);
        manager = findViewById(R.id.book_manager);
        title.setText(getTitle());
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initListener();
    }

    private void initView() {

        manager.setAdapter(new BookAdapter(this,bookBeans));
        manager.setOnItemClickListener(this);
    }

    private void initListener() {
        addUser.setOnClickListener(this);
        manager.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("bookName", bookBeans.get(position).getBookname());
        intent.putExtra("choose", "choose");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_user:
                Intent intent = new Intent(this, BookCreateActivity.class);

                startActivity(intent);
                break;
        }
    }
}
