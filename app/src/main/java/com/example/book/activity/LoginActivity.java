package com.example.book.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.db.DBManager;
import com.example.book.model.UserLogin;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.ed_account)
    EditText edAccount;
    @BindView(R.id.ed_pass)
    AppCompatEditText edPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initPermission();
    }


    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            ArrayList<String> permissions = new ArrayList<>();
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            } else {
                DBManager.copyDb(this, "");
                DBManager.dbManager(this);
            }

            if (permissions.size() != 0) {
                requestPermissionsForM(permissions);
            }
        }
    }

    private void requestPermissionsForM(final ArrayList<String> per) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(per.toArray(new String[per.size()]), 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                DBManager.copyDb(this, "");
                DBManager.dbManager(this);
                break;
        }
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (TextUtils.isEmpty(edAccount.getText().toString())) {
            Toast.makeText(this, "用户名不可为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(edPass.getText().toString())) {
            Toast.makeText(this, "密码不可为空", Toast.LENGTH_SHORT).show();
            return;
        }
        UserLogin login = DBManager.login(edAccount.getText().toString(), edPass.getText().toString());
        if (login != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id",login.getId());
            intent.putExtra("name", login.getName());
            intent.putExtra("role", login.getRole_name());
            startActivity(intent);
            finish();
        }
    }
}
