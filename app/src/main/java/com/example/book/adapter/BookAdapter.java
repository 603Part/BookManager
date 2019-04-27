package com.example.book.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.model.BookBean;
import com.example.book.model.UserBean;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private static final String TAG = "ManagerAdapter";
    private Context context;
    private List<BookBean> data;

    public BookAdapter(Context context, List<BookBean> data) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_manager, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bookname.setText(String.format(context.getResources().getString(R.string.book_name),data.get(position).getBookname()));
        holder.author.setText(String.format(context.getResources().getString(R.string.author_name),data.get(position).getBookAuthor()));
        holder.isbn.setText(String.format(context.getResources().getString(R.string.ISBN),data.get(position).getIsbn()));
        holder.press.setText(String.format(context.getResources().getString(R.string.press_name),data.get(position).getPress()));
        holder.count.setText(String.format(context.getResources().getString(R.string.count),data.get(position).getCount()));
        holder.sur.setText(String.format(context.getResources().getString(R.string.sur),data.get(position).getSurplus()));
        return convertView;
    }

    public class ViewHolder {
        TextView bookname;
        TextView author;
        TextView isbn;
        TextView press;
        TextView count;
        TextView sur;

        public ViewHolder(View itemView) {
            bookname = (TextView) itemView.findViewById(R.id.book_name);
            author = (TextView) itemView.findViewById(R.id.author);
            isbn = (TextView) itemView.findViewById(R.id.isbn);
            press = (TextView)itemView.findViewById(R.id.press);
            count = (TextView)itemView.findViewById(R.id.count);
            sur = (TextView)itemView.findViewById(R.id.sur);
        }
    }

}
