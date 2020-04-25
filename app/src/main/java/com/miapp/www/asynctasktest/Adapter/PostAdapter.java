package com.miapp.www.asynctasktest.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//import com.miapp.www.asynctasktest.POJO.Post;
import com.miapp.www.asynctasktest.DataBase.Post;
import com.miapp.www.asynctasktest.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class PostAdapter extends BaseAdapter {

    ArrayList<Post> posts;
    Context context;
    PostViewHolder holder;


    public PostAdapter(ArrayList<Post> posts, Context context){
        this.posts = posts;
        this.context = context;
        holder = new PostViewHolder();
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
        return posts.indexOf(posts.get(position));
    }

    @Override
    public View getView(int position,
                        View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.post_row_layout, null);

            holder.userId = convertView.findViewById(R.id.userId);
            holder.id = convertView.findViewById(R.id.id);
            holder.tittle = convertView.findViewById(R.id.tittle);
            //holder.body = convertView.findViewById(R.id.body);
        }

        //Asignación de valores a filas en ListView
        holder.userId.setText(context.getResources().getString(R.string.user_id) + " "
                + posts.get(position).getUserId());
        holder.id.setText(context.getResources().getString(R.string.id) + " "
                + posts.get(position).getIPost());
        holder.tittle.setText(posts.get(position).getTitle());
        // holder.body.setText(context.getResources().getString(R.string.body) + " " + posts.get(position).getBody());
/*
        if (getItemId(position) % 2 == 0){
            Log.d("PostAdapter", "-" +getItemId(position) );
            holder.userId.setBackground(context.getDrawable(R.color.background_row_par));
            holder.id.setBackground(context.getDrawable(R.color.background_row_par));
            holder.tittle.setBackground(context.getDrawable(R.color.background_row_par));
            //holder.body.setBackground(context.getDrawable(R.color.colorPrimary));
        }*/

        return convertView;
    }

}

class PostViewHolder {
    public TextView userId,
            id,
            tittle,
            body;

}

