package com.example.book.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.adapter.ManagerAdapter;
import com.example.book.db.DBManager;
import com.example.book.model.UserBean;

import java.util.List;

public class ManagerActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private TextView title,addUser;
    private ListView manager;
    private List<UserBean> data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        title = findViewById(R.id.app_title);
        addUser = findViewById(R.id.add_user);
        manager = findViewById(R.id.manager);
        data = DBManager.getAllUser();
        initView();
        initListener();
    }

    private void initView() {
        title.setText(getTitle());
        addUser.setVisibility(View.VISIBLE);
        manager.setAdapter(new ManagerAdapter(this,data));
        manager.setOnItemClickListener(this);
    }

    private void initListener() {
        addUser.setOnClickListener(this);
        manager.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_user:
                break;
        }
    }
}
