package com.example.book.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.example.book.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailsActivity extends BaseActivity {


    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.book_name)
    EditText bookName;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.isbn)
    EditText isbn;
    @BindView(R.id.press)
    EditText press;
    @BindView(R.id.brief)
    EditText brief;
    @BindView(R.id.count)
    EditText count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_update);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        appTitle.setText(getTitle());
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
    }
}
