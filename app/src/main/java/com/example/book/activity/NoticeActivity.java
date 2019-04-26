package com.example.book.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.book.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeActivity extends BaseActivity {
    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.add_user)
    TextView addNotice;
    @BindView(R.id.noticeLv)
    ListView noticeLv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_activity);
        ButterKnife.bind(this);
        appTitle.setText(getTitle());
//        addNotice.setVisibility(View.VISIBLE);
        initData();
    }

    private void initData() {

    }
}
