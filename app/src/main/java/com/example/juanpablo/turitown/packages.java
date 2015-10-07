package com.example.juanpablo.turitown;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Juan Pablo on 04/10/2015.
 */
public class packages extends Activity
{
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        new getCiudades().execute();
        setContentView(R.layout.packages);
    }
    class getCiudades extends AsyncTask<String, String, Integer> {

        private final HttpClient Client = new DefaultHttpClient();
        private ProgressDialog Dialog = new ProgressDialog(packages.this);
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

        public void onPostExecute(Integer integer_p) {
            Dialog.dismiss();
            JSONObject jsonResponse;
            JSONArray _json_array = null;
            try {
                _json_array = new JSONArray(Content);





                String[ ]  arreglo = new  String[_json_array.length()];

                ListView lista = (ListView) findViewById(R.id.lista);
                for(int x = 0; x < _json_array.length(); ++x)
                {
                    JSONObject object = _json_array.getJSONObject(x);
                    String a = object.getString("nombre");
                    String b = object.getString("id");
                    arreglo[x]=a;
                    ArrayAdapter<String> adapter = new ArrayAdapter<String> (getApplicationContext(),R.layout.custom,arreglo );
                    lista.setAdapter(adapter);
                }







            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
