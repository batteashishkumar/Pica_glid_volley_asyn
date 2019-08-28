package com.example.pica_glid_volley_asyn;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Listview_adapter extends BaseAdapter {

    ImageView imag,imag1,imag2,imag3;
    TextView tet;
    Context context;
    List<String> link;
    public Listview_adapter(Context context, List<String> link){
        this.context=context;
        this.link=link;

    }
    @Override
    public int getCount() {
        return link.size();
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
        Log.e("ashi","getview");
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.element_list,null);

        imag=convertView.findViewById(R.id.async);
        imag1=convertView.findViewById(R.id.picasso);
        imag2=convertView.findViewById(R.id.glide);
        imag3=convertView.findViewById(R.id.volley);
        new DownloadImageTask(imag,link.get(position)).execute();
        pica(link.get(position));
        gli(link.get(position));
        voll(link.get(position));
        return convertView;
    }

    public void voll(String s) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ImageRequest imageRequest = new ImageRequest(s, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        imag3.setImageBitmap(response);
                    }
                },
                0,
                0,
                ImageView.ScaleType.CENTER_CROP,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                    }
                }
        );


        requestQueue.add(imageRequest);

    }

    public void pica(String imageUri){

       Picasso.with(context).load(imageUri).into(imag1);
   }
   public void gli(String imageuri){
       Glide.with(context)
               .load(imageuri)
               .into(imag2);
   }


}
