package com.example.book.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.db.DBManager;
import com.example.book.model.BookBean;

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
    @BindView(R.id.btn_save)
    Button btnSave;
    private BookBean bookByName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_update);
        ButterKnife.bind(this);
        String choose = getIntent().getStringExtra("choose");
        if (!TextUtils.isEmpty(choose)) {
            btnSave.setVisibility(View.GONE);
        }
        String bookName = getIntent().getStringExtra("bookName");
        bookByName = DBManager.findBookByName(bookName);
        initView();
    }

    private void initView() {
        appTitle.setText(getTitle());
        bookName.setText(bookByName.getBookname());
        name.setText(bookByName.getBookAuthor());
        isbn.setText(bookByName.getIsbn());
        press.setText(bookByName.getPress());
        brief.setText(bookByName.getBrief());
        count.setText(bookByName.getCount());
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        bookByName.setBookname(bookName.getText().toString());
        bookByName.setBookAuthor(name.getText().toString());
        bookByName.setIsbn(isbn.getText().toString());
        bookByName.setPress(press.getText().toString());
        bookByName.setBrief(brief.getText().toString());
        bookByName.setCount(count.getText().toString());
        DBManager.updateBook(bookByName);
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
