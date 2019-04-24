package com.example.book.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.book.R;
import com.example.book.model.HomeItem;

import java.util.List;

public class HomeAdapter extends BaseAdapter {

    private List<HomeItem> homeItems;

    public HomeAdapter(List<HomeItem> homeItems) {
        this.homeItems = homeItems;
    }

    @Override
    public int getCount() {
        return homeItems.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.img.setImageResource(homeItems.get(position).getImg());
        holder.title.setText(homeItems.get(position).getTitle());
        return view;
    }

    public final class  ViewHolder {
        TextView title;
        ImageView img;
        public ViewHolder(View v) {
            img = v.findViewById(R.id.item_img);
            title = v.findViewById(R.id.item_content);
        }
    }
}
