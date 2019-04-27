package com.example.book.activity;

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

public class MessageReplyActivity extends BaseActivity {
    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.message)
    EditText message;
    @BindView(R.id.reply)
    EditText reply;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    private String messageId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reply_activity);
        ButterKnife.bind(this);
        appTitle.setText(getTitle());
        messageId = getIntent().getStringExtra("messageId");
        String message = getIntent().getStringExtra("message");
        String role = getIntent().getStringExtra("role");
        String status = getIntent().getStringExtra("status");
        String replys = getIntent().getStringExtra("reply");
        this.message.setText(message);
        reply.setText(replys);
        if (role.equals("学生")) {
            btnUpdate.setVisibility(View.GONE);
            this.message.setEnabled(false);
            reply.setEnabled(false);
        } else if ("1".equals(status)) {
            btnUpdate.setVisibility(View.GONE);
            this.message.setEnabled(false);
            reply.setEnabled(false);
        }
    }

    @OnClick(R.id.btn_update)
    public void onViewClicked() {
        DBManager.updateMessage(messageId,reply.getText().toString());
        Toast.makeText(this, "回复成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
