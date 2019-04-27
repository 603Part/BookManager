package com.example.book.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.adapter.NoticeAdapter;
import com.example.book.db.DBManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeActivity extends BaseActivity {
    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.add_user)
    TextView addNotice;
    @BindView(R.id.noticeLv)
    ListView noticeLv;
    private List<String> allNotice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_activity);
        ButterKnife.bind(this);
        appTitle.setText(getTitle());
        String role = getIntent().getStringExtra("role");
        if (!"学生".equals(role)) {
            addNotice.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        allNotice = DBManager.getAllNotice();
        initData();
    }

    private void initData() {
        NoticeAdapter noticeAdapter = new NoticeAdapter(this, allNotice);
        noticeLv.setAdapter(noticeAdapter);
    }


    @OnClick(R.id.add_user)
    public void onViewClicked() {
        final EditText ed = new EditText(this);

        AlertDialog alert = new AlertDialog.Builder(this)
                .setView(ed)
                .setTitle("发布公告")
                .setPositiveButton("发布", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = ed.getText().toString();
                        DBManager.insertNotice(s);
                        Toast.makeText(NoticeActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
                        allNotice = DBManager.getAllNotice();
                        NoticeAdapter noticeAdapter = new NoticeAdapter(NoticeActivity.this, allNotice);
                        noticeLv.setAdapter(noticeAdapter);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        alert.show();

    }
}
