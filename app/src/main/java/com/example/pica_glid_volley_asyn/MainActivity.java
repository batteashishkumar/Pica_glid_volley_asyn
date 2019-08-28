package com.example.pica_glid_volley_asyn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;
    private static RequestQueue requestQueue;
    Listview_adapter adapter;
    StringBuilder sb=null;
    String uri = "https://api.androidhive.info/json/glide.json";
    List<String> bitimagearray ;
    ImageView image1;
    ListView list;

    ArrayList<Bitmap> image_bitmaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bitimagearray = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        list=findViewById(R.id.List);
        sb=new StringBuilder();
        instance=this;
    }
    public static RequestQueue getsingleInstance() {
        return requestQueue;
    }
    public static MainActivity getInstance() {
        return instance;
    }


    public void call(View view){
        HttpPostAsyncTask asyncTask=new HttpPostAsyncTask(uri,MainActivity.this);
        asyncTask.execute();
    }
    public void get_names_links(List<String> name , List<String> link){
//        Log.e("ashish",""+name.get(2)+link.get(2));


        List<String> abc = link;
        link.addAll(abc);
        link.addAll(abc);


        adapter=new Listview_adapter(this,link);
        list.setAdapter(adapter);
    }
}