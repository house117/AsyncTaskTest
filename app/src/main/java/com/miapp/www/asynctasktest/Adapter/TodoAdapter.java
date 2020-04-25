package com.miapp.www.asynctasktest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miapp.www.asynctasktest.DataBase.Post;
import com.miapp.www.asynctasktest.DataBase.Todo;
import com.miapp.www.asynctasktest.R;

import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {
    ArrayList<Todo> todos;
    Context context;
    TodoViewHolder holder;


    public TodoAdapter(ArrayList<Todo> todos, Context context) {
        this.todos = todos;
        this.context = context;
        holder = new TodoViewHolder();
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return todos.indexOf(todos.get(position));
    }

    @Override
    public View getView(int position,
                        View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.todo_row_layout, null);

            holder.userId = convertView.findViewById(R.id.userId);
            holder.id = convertView.findViewById(R.id.id);
            holder.tittle = convertView.findViewById(R.id.tittle);
            holder.completed = convertView.findViewById(R.id.completed);
            //holder.body = convertView.findViewById(R.id.body);
        }

        //Asignación de valores a filas en ListView
        holder.userId.setText(context.getResources().getString(R.string.user_id) + " "
                + todos.get(position).getUserId());
        holder.id.setText(context.getResources().getString(R.string.id) + " "
                + todos.get(position).getITodo());
        holder.tittle.setText(todos.get(position).getTitle());
        //holder.completed.setText("Completed: "+todos.get(position).getCompleted());
        if(todos.get(position).getCompleted().equals("false")){
            holder.completed.setBackground(context.getDrawable(R.color.background_false));
            holder.completed.setText("Terminado: NO");
        }
        if(todos.get(position).getCompleted().equals("true")){
            holder.completed.setBackground(context.getDrawable(R.color.background_true));
            holder.completed.setText("Terminado: SI");
        }
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
class TodoViewHolder {
    public TextView userId,
            id,
            tittle,
            completed;

}
