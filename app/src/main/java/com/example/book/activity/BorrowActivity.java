package com.example.book.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.db.DBManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BorrowActivity extends BaseActivity {
    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.add_user)
    TextView addUser;
    @BindView(R.id.book_name)
    EditText bookName;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.save)
    Button save;

    String bookname;
    String bookId;
    String realName;
    String userId;
    String surplus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);
        ButterKnife.bind(this);
        appTitle.setText(getTitle());
    }


    @OnClick({R.id.add_user, R.id.choose_user, R.id.choose_book, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_user:

                break;
            case R.id.choose_book:
                Intent book = new Intent(BorrowActivity.this, BookViewActivity.class);
                book.putExtra("choose", "choose");
                startActivityForResult(book,0x02);
                break;
            case R.id.choose_user:

                Intent name = new Intent(BorrowActivity.this, UserViewActivity.class);
                name.putExtra("choose", "choose");
                startActivityForResult(name,0x01);

                break;
            case R.id.save:
                int i = Integer.parseInt(surplus);
                i-=1;
                DBManager.updateBookCount(bookId,i+"");
                DBManager.insertBrow(bookId,userId,realName);
                Toast.makeText(this, "借阅成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {

            switch (requestCode) {
                case 0x01:
                    userId = data.getStringExtra("username");
                    realName = data.getStringExtra("name");
                    name.setText(realName);
                    break;
                case 0x02:
                    bookId = data.getStringExtra("bookId");
                    bookname = data.getStringExtra("bookName");
                    surplus = data.getStringExtra("surplus");
                    bookName.setText(bookname);
                    break;
            }
        }
    }
}
