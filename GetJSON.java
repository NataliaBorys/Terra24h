package com.example.natalka.terra24h;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class GetJSON extends AsyncTask <String, Void, String> {

    ArrayList<Data> receivedData;
    AlertDialog alertDialog;
    Context context;
    GetJSON (Context ctx)
    {
        context=ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog = new AlertDialog.Builder(context).create();
    }

    //this method will be called after execution

    @Override
    protected void onPostExecute(String s) {
        String type=s.substring(s.length()-1);
        String json=s.substring(0,s.length()-1);
       ArrayList <Data> lista=new ArrayList<>();
        try {
            lista=loadIntoArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (type.equals("l")) {
            Intent intent = new Intent(context, Result_list.class);
            intent.putExtra("Dane", lista);
            context.startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(context, Result_chart.class);
            intent.putExtra("Dane", lista);
            context.startActivity(intent);
        }
    }


    //in this method we are fetching the json string
    @Override
    protected String doInBackground(String... params) {


        try {
            String NR_Term = params[0];
            String data_start = params[1];
            String data_end = params[2];
            String where = params[3];
            //creating a URL
            URL url = new URL("http://serwer1880481.home.pl/getData.php");

            //Opening the URL using HttpURLConnection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //StringBuilder object to read the string from the service
            StringBuilder sb = new StringBuilder();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            OutputStream outputStream = con.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("NR_Term", "UTF-8") + "=" + URLEncoder.encode(NR_Term, "UTF-8") + "&"
                    + URLEncoder.encode("data_start", "UTF-8") + "=" + URLEncoder.encode(data_start, "UTF-8") + "&"
                    + URLEncoder.encode("data_end", "UTF-8") + "=" + URLEncoder.encode(data_end, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();

            //We will use a buffered reader to read the string from service
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            //A simple string to read values from each line
            String line;

            //reading until we don't find null
            while ((line = bufferedReader.readLine()) != null) {

                //appending it to string builder
                sb.append(line + "\n");
            }

            //finally returning the read string
            return sb.toString().trim()+where;
        } catch (Exception e) {
            return null;
        }

    }


    private ArrayList loadIntoArray(String json) throws JSONException {
        //creating a json array from the json string

        JSONArray jsonArray = new JSONArray(json);

        //creating a string array for listview
        ArrayList<Data> received = new ArrayList<>();
        Data objekt= new Data();

        //looping through all the elements in json array
        for (int i = 0; i < jsonArray.length(); i++) {

            //getting json object from the json array
            JSONObject obj = jsonArray.getJSONObject(i);

            //getting the name from the json object and putting it inside string array
             objekt.setDate(obj.getString("Data"));
             objekt.setTempString(obj.getString("Temp"));
             objekt.setTempe(Double.parseDouble(obj.getString("Temp")));
             received.add(objekt);
        }

       return received; }


}




