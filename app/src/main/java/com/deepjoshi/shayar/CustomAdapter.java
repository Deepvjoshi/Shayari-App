package com.deepjoshi.shayar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.AbstractList;
import java.util.ArrayList;



public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Post> posts;
    int[] Veg;

    public CustomAdapter(Context context, ArrayList<Post> posts, int[] Veg) {
        this.context = context;
        this.posts = posts;
        this.Veg = Veg;

//        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
       // TextView iid;
        ImageView im;
        TextView name;
    }
 
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.single_row_grid_view, parent, false);
       // holder.iid = (TextView) convertView.findViewById(R.id.list_line);
        holder.name = (TextView) convertView.findViewById(R.id.grid_cat);
          holder.im=(ImageView)convertView.findViewById(R.id.gris_img);
           //
        //TV1 = (TextView) convertView.findViewById(R.id.grid_cat);

        convertView.setTag(holder);
//        return convertView;
    }else

    {
        holder = (ViewHolder) convertView.getTag();
    }
        holder.name.setText(posts.get(position).getName());
holder.im.setImageResource(Veg[position]);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gridintent = new Intent(context,ListActivity.class);
                gridintent.putExtra("listquotes",posts.get(position).getId());
                context.startActivity(gridintent);
            }
        });

    return convertView;
        }
}
