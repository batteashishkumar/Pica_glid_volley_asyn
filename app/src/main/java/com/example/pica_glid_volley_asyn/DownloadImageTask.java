package com.example.pica_glid_volley_asyn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;
    String image_links;
    //List<String> image_strings;

    public DownloadImageTask(ImageView bmImage, String image_links) {
        this.bmImage = bmImage;
        this.image_links = image_links;
    }

    protected Bitmap doInBackground(String... urls) {
        Bitmap mIcon11 =null;


            try {
                InputStream in = new java.net.URL(image_links).openStream();
                mIcon11=BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }


        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
        bmImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

}