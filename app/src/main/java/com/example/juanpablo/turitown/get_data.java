package com.example.juanpablo.turitown;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Juan Pablo on 04/10/2015.
 */
public class get_data extends Activity{
    public void data(){
        new getCiudades().execute();
    }
    class getCiudades extends AsyncTask<String, String, Integer> {

        private final HttpClient Client = new DefaultHttpClient();
        private ProgressDialog Dialog = new ProgressDialog(get_data.this);
        private String Content;
        private String Error = null;

        @Override
        protected Integer doInBackground(String... url) {


            BufferedReader reader = null;

            try {
                URL urls = new URL("http://packmore.com.mx/turitown/ciudades.php");
                URLConnection conn = urls.openConnection();
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {

                    sb.append(line + " ");
                }
                Content = sb.toString();
                System.out.println("el json es: " + Content);
            } catch (Exception ex) {
                Error = ex.getMessage();
                System.out.println("errorrrrrrrrrrrrrrrrrrrrrr: " + Error);
            } finally {
                try {

                    reader.close();
                } catch (Exception ex) {
                }
            }
            return 1;
        }

        protected void onPreExecute() {
            Dialog.setMessage("Please wait..");
            Dialog.show();

        }

        protected void onPostExecute(Integer integer_p) {
            Dialog.dismiss();
            JSONObject jsonResponse;
            try {

                jsonResponse = new JSONObject(Content);


                JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");
                int length = jsonMainNode.length();
                String[ ]  arreglo = new  String[5];

                for(int i = 0; i < 4; ++i) {
                    arreglo[i]="hola";

                    //JSONObject json = jsonMainNode.getJSONObject(i);
                    //String nombre= json.optString("nombre").toString();
                    //arreglo[i]=nombre;

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
