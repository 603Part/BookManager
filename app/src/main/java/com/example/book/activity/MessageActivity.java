package com.example.book.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.adapter.MessageAdapter;
import com.example.book.db.DBManager;
import com.example.book.model.MessageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.app_title)
    TextView appTitle;
    @BindView(R.id.noticeLv)
    ListView noticeLv;
    @BindView(R.id.add_user)
    TextView addUser;
    private List<MessageBean> allMessage;
    private String role;
    private String userName;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
        ButterKnife.bind(this);
        appTitle.setText(getTitle());

        role = getIntent().getStringExtra("role");
        userName = getIntent().getStringExtra("userName");
        name = getIntent().getStringExtra("name");
        if (!"学生".equals(role)) {
            allMessage = DBManager.getAllMessage();
        } else {
            allMessage = DBManager.getMessageByUser(userName);
            addUser.setVisibility(View.VISIBLE);
        }

        initView();
    }

    private void initView() {
        noticeLv.setAdapter(new MessageAdapter(this, allMessage));
        noticeLv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MessageReplyActivity.class);
        intent.putExtra("messageId", allMessage.get(position).getId());
        intent.putExtra("message", allMessage.get(position).getMessage());
        intent.putExtra("role", role);
        intent.putExtra("reply", allMessage.get(position).getReply());
        intent.putExtra("status", allMessage.get(position).getStatus());
        startActivity(intent);
    }

    @OnClick(R.id.add_user)
    public void onViewClicked() {
        final EditText ed = new EditText(this);

        AlertDialog alert = new AlertDialog.Builder(this)
                .setView(ed)
                .setTitle("请输入你的留言信息")
                .setPositiveButton("发布", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = ed.getText().toString();

                        MessageBean messageBean = new MessageBean();
                        messageBean.setUser_id(userName);
                        messageBean.setUsername(name);
                        messageBean.setStatus("0");
                        messageBean.setMessage(s);
                        messageBean.setReply("待回复");
                        DBManager.insertMessage(messageBean);

                        if (!"学生".equals(role)) {
                            allMessage = DBManager.getAllMessage();
                        } else {
                            allMessage = DBManager.getMessageByUser(userName);
                        }


                        MessageAdapter noticeAdapter = new MessageAdapter(MessageActivity.this, allMessage);
                        noticeLv.setAdapter(noticeAdapter);
                        Toast.makeText(MessageActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
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
