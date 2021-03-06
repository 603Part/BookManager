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
        title.setText(getTitle());
        addUser.setVisibility(View.VISIBLE);

        addUser.setOnClickListener(this);
        manager.setOnItemClickListener(this);
        manager.setOnItemClickListener(this);
    }

    private void initView() {

        manager.setAdapter(new ManagerAdapter(this,data));

    }

    private void initListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        data = DBManager.getAllUser();
        initView();
        initListener();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,UserUpdateActivity.class);
        intent.putExtra("id", data.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_user:
                Intent intent = new Intent(this,AddUserActivity.class);
                startActivity(intent);
                break;
        }
    }
}
