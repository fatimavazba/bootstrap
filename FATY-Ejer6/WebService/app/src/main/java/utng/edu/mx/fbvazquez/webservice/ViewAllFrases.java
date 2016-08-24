package utng.edu.mx.fbvazquez.webservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
public class ViewAllFrases extends AppCompatActivity implements ListView.OnItemClickListener,View.OnClickListener {

    private ListView listView;
    private Button btnAgregar;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_frases);
        listView = (ListView) findViewById(R.id.listView);
        btnAgregar = (Button)findViewById(R.id.btn_agregar);

        btnAgregar.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showFrase(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String idFrase = jo.getString(Config.TAG_ID);
                String autor = jo.getString(Config.TAG_AUTOR);
                String frase = jo.getString(Config.TAG_FRASE);

                HashMap<String,String> frases = new HashMap<>();
                frases.put(Config.TAG_ID,idFrase);
                frases.put(Config.TAG_AUTOR,autor);
                frases.put(Config.TAG_FRASE,frase);
                list.add(frases);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllFrases.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_AUTOR,Config.TAG_FRASE},
                new int[]{R.id.txt_idFrase, R.id.txt_autor, R.id.txt_frase});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAllFrases.this,"Recuperando Datos","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showFrase();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewFrase.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String idFrase = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.FRASE_IDFRASE,idFrase);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v == btnAgregar){
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
