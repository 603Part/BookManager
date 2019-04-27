package com.example.book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.model.MessageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends BaseAdapter {
    private static final String TAG = "ManagerAdapter";
    private Context context;
    private List<MessageBean> data;

    public MessageAdapter(Context context, List<MessageBean> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.messageTv.setText(String.format(context.getResources().getString(R.string.message), data.get(position).getMessage()));
        holder.replyTv.setText(String.format(context.getResources().getString(R.string.reply), data.get(position).getReply()));

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.message_tv)
        TextView messageTv;
        @BindView(R.id.reply_tv)
        TextView replyTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
