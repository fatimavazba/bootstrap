package utng.edu.mx.fbvazquez.ejercicio5;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by FatyPC on 17/08/2016.
 */
public class ViewAllArchivos extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private Button btnAgregar;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_archivos);
        listView = (ListView) findViewById(R.id.listView);
        btnAgregar = (Button)findViewById(R.id.btn_agregar);

        btnAgregar.setOnClickListener(this);
        getJSON();
    }


    private void showArchivo(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String nombre = jo.getString(Config.TAG_NOMBRE);
                String apellidos = jo.getString(Config.TAG_APELLIDOS);

                HashMap<String,String> frases = new HashMap<>();
                frases.put(Config.TAG_ID,id);
                frases.put(Config.TAG_NOMBRE,nombre);
                frases.put(Config.TAG_APELLIDOS,apellidos);
                list.add(frases);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllArchivos.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_NOMBRE,Config.TAG_APELLIDOS},
                new int[]{R.id.txt_id, R.id.txt_nombre, R.id.txt_apellidos});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAllArchivos.this,"Recuperando Datos","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showArchivo();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == btnAgregar){
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
