package com.example.book.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.db.DBManager;
import com.example.book.model.BookBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookCreateActivity extends BaseActivity {
    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.book_name)
    EditText bookName;
    @BindView(R.id.isbn)
    EditText isbn;
    @BindView(R.id.author)
    EditText author;
    @BindView(R.id.brief)
    EditText brief;
    @BindView(R.id.count)
    EditText count;
    @BindView(R.id.press)
    EditText press;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);
        appTitle.setText(getTitle());

    }


    @OnClick(R.id.btn_update)
    public void onViewClicked() {
        BookBean book = new BookBean();
        String bname = bookName.getText().toString();
        String bAuthor = author.getText().toString();
        String bIsbn = isbn.getText().toString();
        String bBrief = brief.getText().toString();
        String bCount = count.getText().toString();
        String sur = count.getText().toString();

        book.setBookname(bname);
        book.setBookAuthor(bAuthor);
        book.setIsbn(bIsbn);
        book.setBrief(bBrief);
        book.setCount(bCount);
        book.setSurplus(sur);
        book.setPress(press.getText().toString());
        DBManager.insertBook(book);
        Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
