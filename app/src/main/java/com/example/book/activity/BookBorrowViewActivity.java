package com.example.book.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.adapter.BookAdapter;
import com.example.book.db.DBManager;
import com.example.book.model.BookBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookBorrowViewActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.book_manager)
    ListView bookManager;
    @BindView(R.id.search_username)
    EditText searchUsername;
    @BindView(R.id.search)
    ImageView search;
    private List<BookBean> allBook;
    private String choose;
    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search_manager);
        ButterKnife.bind(this);
        choose = getIntent().getStringExtra("choose");
        username = getIntent().getStringExtra("username");
        appTitle.setText(getTitle());
        allBook = DBManager.getAllBorrowBook(username);
        initView();

    }

    private void initView() {
        bookManager.setAdapter(new BookAdapter(this, allBook));
        bookManager.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (!TextUtils.isEmpty(choose)) {
            Intent intent = new Intent();
            intent.putExtra("bookId", allBook.get(position).getId());
            intent.putExtra("bookName", allBook.get(position).getBookname());
            intent.putExtra("surplus", allBook.get(position).getSurplus());
            intent.putExtra("borrowId", allBook.get(position).getRemark());
            setResult(RESULT_OK,intent);
            finish();
        } else {
            Intent intent = new Intent(this, BookDetailsActivity.class);
            intent.putExtra("bookName", allBook.get(position).getBookname());
            intent.putExtra("choose", "choose");
            startActivity(intent);
        }
    }

    @OnClick(R.id.search)
    public void onViewClicked() {
        Intent intent = new Intent(this, BookSearchManagerActivity.class);
        intent.putExtra("bookName", searchUsername.getText().toString());
        startActivity(intent);
    }
}
