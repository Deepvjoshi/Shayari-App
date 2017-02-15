package com.deepjoshi.shayar;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int[] veg = new int[]{R.drawable.moti,R.drawable.valti,R.drawable.love,R.drawable.fship,R.drawable.life,R.drawable.images,R.drawable.positive};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);

        new Gridclass().execute("http://rapidans.esy.es/test/getallcat.php");
    }


    class Gridclass extends AsyncTask<String,Void,String> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);//new URL("https://jsonplaceholder.typicode.com/posts");
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return bufferString;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ArrayList<Post> postArrayList = new ArrayList<>();
//            ArrayList<categories> cats = new ArrayList<>();
            try {
                JSONObject obj1 = new JSONObject(s);
                JSONArray jsonarray = obj1.getJSONArray("data");
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject obj2 = jsonarray.getJSONObject(i);

                    int id = obj2.getInt("id");
                    String name = obj2.getString("name");

                    Post post = new Post();
                    post.setId(id);
                    post.setName(name);
                    postArrayList.add(post);
                }


            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            CustomAdapter adapter1 = new CustomAdapter(MainActivity.this, postArrayList,veg);
            GridView gridview = (GridView)findViewById(R.id.gridview);
            gridview.setAdapter(adapter1);

        }
    }
}

