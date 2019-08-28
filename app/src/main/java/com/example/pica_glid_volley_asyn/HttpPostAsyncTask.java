package com.example.pica_glid_volley_asyn;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpPostAsyncTask extends AsyncTask<String, Void, Void> {
    String urlString, responce;
    Context context;

    List<String> names;
    List<String> images;

    public HttpPostAsyncTask(String url, Context context) {
        this.urlString = url;
        this.context=context;
    }

    @Override
    protected Void doInBackground(String... params) {

        try {
            URL myUrl = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();
            responce = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        names=new ArrayList<>();
        images=new ArrayList<>();

        super.onPostExecute(aVoid);
        try {

            JSONArray jsonArray = new JSONArray(responce);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String name=jsonObject.getString("name");
                JSONObject object2 = (JSONObject) jsonObject.get("url");
                String img=object2.getString("large");
                names.add(name);
                images.add(img);
            }
            MainActivity.getInstance().get_names_links(names,images);
        } catch (Exception e) {

        }


    }
}
