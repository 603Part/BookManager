package com.example.book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.model.BookBean;

import java.util.List;

public class NoticeAdapter extends BaseAdapter {
    private static final String TAG = "ManagerAdapter";
    private Context context;
    private List<String> data;

    public NoticeAdapter(Context context, List<String> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.notice.setText(String.format(context.getResources().getString(R.string.notice),data.get(position)));

        return convertView;
    }

    public class ViewHolder {
        TextView notice;


        public ViewHolder(View itemView) {
            notice = (TextView) itemView.findViewById(R.id.notice_tv);

        }
    }

}
