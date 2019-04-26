package com.example.book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.model.UserBean;

import java.util.List;

public class ManagerAdapter extends BaseAdapter {

    private Context context;
    private List<UserBean> data;

    public ManagerAdapter(Context context, List<UserBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manager, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.username.setText(String.format(context.getResources().getString(R.string.username),data.get(position).getUsername()));
        holder.nickname.setText(String.format(context.getResources().getString(R.string.nickname),data.get(position).getName()));
        holder.sex.setText(String.format(context.getResources().getString(R.string.gender),data.get(position).getGender().equals("1") ? "男" : "女"));
        holder.phone.setText(String.format(context.getResources().getString(R.string.phone),data.get(position).getPhone()));
        holder.role.setText(String.format(context.getResources().getString(R.string.role),data.get(position).getRole()));
        return convertView;
    }

    public class ViewHolder {
        TextView username;
        TextView nickname;
        TextView sex;
        TextView phone;
        TextView role;

        public ViewHolder(View itemView) {
            username = (TextView) itemView.findViewById(R.id.tv_name);
            nickname = (TextView) itemView.findViewById(R.id.nickname);
            sex = (TextView) itemView.findViewById(R.id.sex);
            phone = (TextView)itemView.findViewById(R.id.tv_phone);
            role = (TextView)itemView.findViewById(R.id.tv_role);
        }
    }

}
