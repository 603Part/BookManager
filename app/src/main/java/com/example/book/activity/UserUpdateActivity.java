package com.example.book.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.db.DBManager;
import com.example.book.model.UserBean;
import com.example.book.model.UserLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserUpdateActivity extends BaseActivity {
    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.man)
    RadioButton man;
    @BindView(R.id.woman)
    RadioButton woman;
    @BindView(R.id.gender)
    RadioGroup gender;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.pwd_linear)
    LinearLayout pwdLinear;
    @BindView(R.id.add_user)
    TextView addUser;
    private UserLogin userById;
    private String sex;
    private String uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);
        ButterKnife.bind(this);
        String formData = getIntent().getStringExtra("from");
        uid = getIntent().getStringExtra("id");
        addUser.setVisibility(View.GONE);
        if ("userSetting".equals(formData)) {
            btnDelete.setVisibility(View.GONE);
            pwdLinear.setVisibility(View.VISIBLE);
        }
        userById = DBManager.findUserById(uid);
        initView();
        initListener();
    }

    private void initView() {
        appTitle.setText(getTitle());
        username.setText(userById.getUsername());
        name.setText(userById.getName());
        phone.setText(userById.getPhone());
        pwd.setText(userById.getPassword());

        sex = userById.getGender();
        if ("1".equals(sex)) {
            man.setChecked(true);
            woman.setChecked(false);
        } else if ("0".equals(sex)) {
            woman.setChecked(true);
            man.setChecked(false);
        }
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

    @OnClick({R.id.btn_save, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                UserBean userLogin = new UserBean();
                userLogin.setId(userById.getId());
                userLogin.setUsername(username.getText().toString());
                userLogin.setName(name.getText().toString());
                userLogin.setPhone(phone.getText().toString());
                userLogin.setGender(sex);
                userLogin.setPassword(pwd.getText().toString());
                DBManager.updateUser(userLogin);
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btn_delete:
                UserBean user = new UserBean();
                user.setId(userById.getId());
                user.setUsername(username.getText().toString());
                user.setName(name.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setGender(sex);
                user.setPassword(pwd.getText().toString());
                user.setStatus("1");
                DBManager.deleteUserById(userById.getId());
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
