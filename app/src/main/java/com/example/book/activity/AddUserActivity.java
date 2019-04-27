package com.example.book.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.db.DBManager;
import com.example.book.model.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserActivity extends BaseActivity {
    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.ed_update_account)
    EditText edUpdateAccount;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.gender)
    RadioGroup gender;
    @BindView(R.id.add_user)
    TextView addUser;

    private String sex = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        appTitle.setText(getTitle());
        addUser.setVisibility(View.VISIBLE);
        initListener();
    }

    private void initListener() {
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.man:
                        sex = "1";
                        break;
                    case R.id.woman:
                        sex = "0";
                        break;
                }
            }
        });

    }

    @OnClick(R.id.btn_update)
    public void onViewClicked() {
        UserBean userBean = new UserBean();
        userBean.setUsername(edUpdateAccount.getText().toString());
        userBean.setName(name.getText().toString());
        userBean.setGender(sex);
        userBean.setPassword(pwd.getText().toString());
        userBean.setPhone(phone.getText().toString());
        userBean.setRole("3");
        userBean.setStatus("0");

        int i = DBManager.insertNewUser(userBean);
        if (i == 0) {
            Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
            finish();
        } else if (1 == i) {
            Toast.makeText(this, "已存在该用户", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "插入失败请重试", Toast.LENGTH_SHORT).show();
        }
    }
}
