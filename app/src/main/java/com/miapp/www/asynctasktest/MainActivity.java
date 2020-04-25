package com.miapp.www.asynctasktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.miapp.www.asynctasktest.Adapter.TodoAdapter;
import com.miapp.www.asynctasktest.DataBase.Post;
import com.miapp.www.asynctasktest.DataBase.Todo;
import com.miapp.www.asynctasktest.Utils.Constants;
import com.miapp.www.asynctasktest.Utils.GetPostServer;
import com.miapp.www.asynctasktest.Utils.GetTodoServer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTodos;
    PostDaoImplement postDaoImplement;
    TodoDaoImplement todoDaoImplement;
    Post mPost;
    Todo mTodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.title_mainactivity));

        lvTodos = findViewById(R.id.lv_todos);

        GetTodoServer getData = new GetTodoServer(this, lvTodos);


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            try {
                URL url = new URL(Constants.urlTodos);
                getData.execute(url);
            }catch (MalformedURLException e)
            {
                Log.d("MAINACTIVITY", "Error en URL ");
            }

        } else {

            /*todoDaoImplement = TodoDaoImplement.get(this);
            ArrayList<Todo> todos = todoDaoImplement.getTodos();

            if(posts.size() > 0) {
                PostAdapter pAdapter = new PostAdapter(posts, this);
                lvPost.setAdapter(pAdapter);
            }*/

            todoDaoImplement = TodoDaoImplement.get(this);
            ArrayList<Todo> todos = todoDaoImplement.getTodos();

           /* if(todos.size() > 0) {
                PostAdapter pAdapter = new PostAdapter(posts, this);
                lvPost.setAdapter(pAdapter);
            }*/

            if(todos.size() > 0) {
                TodoAdapter tAdapter = new TodoAdapter(todos, this);
                lvTodos.setAdapter(tAdapter);
            }




        }



    }

}


class GetData extends AsyncTask<String, Void, String> {
    Context context;
    String dato;
    public GetData(Context context){
        this.context = context;
    }
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        Log.d("AsyncTask", "onPreExcecute111111111111111111111111111111");
    }
    @Override
    protected String doInBackground(String... datos){
        dato = datos[0] + "doInBackground";
        return dato;
    }

    @Override
    protected  void onPostExecute(String s){
        super.onPostExecute(s);
        Log.d("AsynctASK", "onPostExecute");
        Toast.makeText(context, dato, Toast.LENGTH_LONG).show();
    }
}