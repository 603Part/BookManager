package com.example.book.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.book.R;

public class BookManagerActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private TextView title,addUser;
    private ListView manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        title = findViewById(R.id.app_title);
        addUser = findViewById(R.id.add_user);
        manager = findViewById(R.id.book_manager);
        initView();
        initListener();
    }

    private void initView() {
        title.setText(getTitle());
        addUser.setVisibility(View.VISIBLE);
    }

    private void initListener() {
        addUser.setOnClickListener(this);
        manager.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_user:
                break;
        }
    }
}
