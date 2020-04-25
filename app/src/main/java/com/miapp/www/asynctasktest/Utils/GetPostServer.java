package com.miapp.www.asynctasktest.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.miapp.www.asynctasktest.Adapter.PostAdapter;
import com.miapp.www.asynctasktest.MainActivity;
//import com.miapp.www.asynctasktest.POJO.Post;
import com.miapp.www.asynctasktest.PostDaoImplement;
import com.miapp.www.asynctasktest.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.miapp.www.asynctasktest.DataBase.Post;

public class GetPostServer extends AsyncTask <URL, Void, ArrayList<Post>> {

    Context context;
    ListView lvPost;
    Activity activity;

    public GetPostServer(Activity activity, ListView lvPost){
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
    protected ArrayList<Post> doInBackground(URL... urls) {

        ArrayList<Post> posts = null;
        HttpURLConnection con = null;

        Log.d("onDoInBackG-GetPost", "Listo");
        try {
            // Establecer la conexión
            con = (HttpURLConnection)urls[0].openConnection();
            // Obtener el estado del recurso
            int statusCode = con.getResponseCode();

            if(statusCode!=200) {
                posts = null;
                Log.d("GETPOST", "STATUSCODE: "+statusCode);
                //posts.add("El recurso no está disponible");
                return posts;
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

                Post post;
                PostDaoImplement postDaoImplement = PostDaoImplement.get(context);

                posts = new ArrayList<Post>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    post = new Post();
                    jsonObject = (JSONObject) jsonArray.get(i);
                    post.setUserId(jsonObject.getString("userId"));
                    post.setIPost(jsonObject.getString("id"));
                    post.setTitle(jsonObject.getString("title"));
                    post.setBody(jsonObject.getString("body"));

                    postDaoImplement.addPost(post);
                    posts.add(i, post);

                }
///-->
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            con.disconnect();
        }

        return posts;
    }


    @Override
    protected void onPostExecute(ArrayList<Post> posts) {
        super.onPostExecute(posts);

        if (posts.isEmpty()){

            Toast.makeText(activity.getApplicationContext(),
                    activity.getResources().getString(R.string.label_posts_not_found),
                    Toast.LENGTH_LONG);

        }else {
            Log.d("onPostExecute-GetPost", "Listo");
            PostAdapter postAdapter = new PostAdapter(posts, context);
            lvPost.setAdapter(postAdapter);
        }

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }
}


/*

 String respuesta;

        for (int i=0; i< posts.size(); i++){
            respuesta = "Titulo: " + posts.get(i).getTitle() +" - " +
                    "Contenido: " + posts.get(i).getBody();
            Log.d("GetPostResponse", respuesta);
        }

* */