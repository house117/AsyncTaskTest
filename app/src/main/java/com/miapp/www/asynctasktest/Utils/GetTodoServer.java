package com.miapp.www.asynctasktest.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.miapp.www.asynctasktest.Adapter.PostAdapter;
import com.miapp.www.asynctasktest.Adapter.TodoAdapter;
import com.miapp.www.asynctasktest.DataBase.Post;
import com.miapp.www.asynctasktest.DataBase.Todo;
import com.miapp.www.asynctasktest.PostDaoImplement;
import com.miapp.www.asynctasktest.R;
import com.miapp.www.asynctasktest.TodoDaoImplement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetTodoServer extends AsyncTask<URL, Void, ArrayList<Todo>> {

    Context context;
    ListView lvPost;
    Activity activity;

    public GetTodoServer(Activity activity, ListView lvPost){
        this.activity = activity;
        this.context = activity;
        this.lvPost = lvPost;
    }

    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(activity.getResources().getString(R.string.donwloading));
        if(!this.progressDialog.isShowing()){
            this.progressDialog.show();
        }
    }

    @Override
    protected ArrayList<Todo> doInBackground(URL... urls) {

        ArrayList<Todo> todos = null;
        HttpURLConnection con = null;

        Log.d("onDoInBackG-GetPost", "Listo");
        try {
            // Establecer la conexión
            con = (HttpURLConnection)urls[0].openConnection();
            // Obtener el estado del recurso
            int statusCode = con.getResponseCode();

            if(statusCode!=200) {
                todos = null;
                Log.d("GETPOST", "STATUSCODE: "+statusCode);
                //todos.add("El recurso no está disponible");
                return todos;
            }
            else{
                /* Parsear el flujo con formato JSON  */
                InputStream in = new BufferedInputStream(con.getInputStream());
                InputStreamReader isReader = new InputStreamReader(in);

                BufferedReader reader = new BufferedReader(isReader);
                StringBuffer sb = new StringBuffer();
                String str;
                while((str = reader.readLine())!= null){
                    sb.append(str);
                }

                Log.d("GETPOST", sb.toString());
///--->
//Recorriendo la Respuesta JSON del servidor, creando el ArrayList para el adaptador
                JSONArray jsonArray = new JSONArray(sb.toString());
                JSONObject jsonObject;

                Todo todo;
                TodoDaoImplement todoDaoImplement = TodoDaoImplement.get(context);

                todos = new ArrayList<Todo>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    todo = new Todo();
                    jsonObject = (JSONObject) jsonArray.get(i);
                    todo.setUserId(jsonObject.getString("userId"));
                    todo.setITodo(jsonObject.getString("id"));
                    todo.setTitle(jsonObject.getString("title"));
                    todo.setCompleted(jsonObject.getString("completed"));

                    todoDaoImplement.addTodo(todo);
                    todos.add(i, todo);

                }
///-->
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            con.disconnect();
        }

        return todos;
    }


    @Override
    protected void onPostExecute(ArrayList<Todo> todos) {
        super.onPostExecute(todos);

        if (todos.isEmpty()){

            Toast.makeText(activity.getApplicationContext(),
                    activity.getResources().getString(R.string.label_posts_not_found),
                    Toast.LENGTH_LONG);

        }else {
            Log.d("onPostExecute-GetPost", "Listo");
            TodoAdapter todoAdapter = new TodoAdapter(todos, context);
            lvPost.setAdapter(todoAdapter);
        }

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }
}
