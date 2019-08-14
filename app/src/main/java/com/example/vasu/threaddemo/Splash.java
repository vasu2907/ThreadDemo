package com.example.vasu.threaddemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        fetchdata process = new fetchdata();
        process.execute();

    }

    private class fetchdata extends AsyncTask<Void,Void,String>{

        String result;
        public fetchdata() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            MainActivity.data.setText(result);
//            String action;
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("Data",result);
            startActivity(intent);
            finish();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.myjson.com/bins/t6qyd");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while(line!=null)
                {
                    line = bufferedReader.readLine();
                    result = result + line;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
