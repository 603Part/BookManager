package com.example.book.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.adapter.ManagerAdapter;
import com.example.book.db.DBManager;
import com.example.book.model.UserBean;

import java.util.List;

public class UserViewActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private TextView title,addUser;
    private ListView manager;
    private List<UserBean> data;
    private String choose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        choose = getIntent().getStringExtra("choose");
        title = findViewById(R.id.app_title);
        addUser = findViewById(R.id.add_user);
        manager = findViewById(R.id.manager);
        title.setText(getTitle());

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
        if (!TextUtils.isEmpty(choose)) {
            Intent intent = new Intent();
            intent.putExtra("username", data.get(position).getUsername());
            intent.putExtra("name", data.get(position).getName());
            setResult(Activity.RESULT_OK,intent);
            finish();
        }
//        intent.putExtra("id", data.get(position).getId());
//        startActivity(intent);
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
