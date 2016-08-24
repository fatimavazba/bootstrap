package utng.edu.mx.fbvazquez.webservice;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by FatyPC on 17/08/2016.
 */
public class ViewFrase extends AppCompatActivity implements View.OnClickListener {

    private EditText txt_idFrase;
    private EditText txt_autor;
    private EditText txt_frase;
    private EditText txt_tipo;
    private EditText txt_rating;

    private Button btnActualizar;
    private Button btnEliminar;
    private Button btnLista;

    private String idFrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_frase);

        Intent intent = getIntent();

        idFrase = intent.getStringExtra(Config.FRASE_IDFRASE);

        txt_idFrase = (EditText) findViewById(R.id.txt_idFrase);
        txt_autor = (EditText) findViewById(R.id.txt_autor);
        txt_frase = (EditText) findViewById(R.id.txt_frase);
        txt_tipo = (EditText) findViewById(R.id.txt_tipo);
        txt_rating = (EditText) findViewById(R.id.txt_rating);

        btnActualizar = (Button) findViewById(R.id.btn_actualizar);
        btnEliminar = (Button) findViewById(R.id.btn_eliminar);
        btnLista = (Button) findViewById(R.id.btn_ver);

        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnLista.setOnClickListener(this);

        txt_idFrase.setText(idFrase);

        getFrase();
    }

    private void getFrase(){
        class GetFrase extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewFrase.this,"Recuperando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showFrase(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_FRASE,idFrase);
                return s;
            }
        }
        GetFrase gf = new GetFrase();
        gf.execute();
    }

    private void showFrase(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String autor = c.getString(Config.TAG_AUTOR);
            String frase = c.getString(Config.TAG_FRASE);
            String tipo = c.getString(Config.TAG_TIPOFRASE);
            String rating = c.getString(Config.TAG_RATING);

            txt_autor.setText(autor);
            txt_frase.setText(frase);
            txt_tipo.setText(tipo);
            txt_rating.setText(rating);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateFrase(){
        final String autor = txt_autor.getText().toString().trim();
        final String frase = txt_frase.getText().toString().trim();
        final String tipo = txt_tipo.getText().toString().trim();
        final String rating = txt_rating.getText().toString().trim();

        class UpdateFrase extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewFrase.this,"Actualizando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewFrase.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_FRASE_ID,idFrase);
                hashMap.put(Config.KEY_FRASE_AUTOR,autor);
                hashMap.put(Config.KEY_FRASE_FRASE,frase);
                hashMap.put(Config.KEY_FRASE_TIPOFRASE,tipo);
                hashMap.put(Config.KEY_FRASE_RATING,rating);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_FRASE,hashMap);

                return s;
            }
        }

        UpdateFrase uf = new UpdateFrase();
        uf.execute();
    }

    private void deleteFrase(){
        class DeleteFrase extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewFrase.this, "Actualizando...", "Espere...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewFrase.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_FRASE, idFrase);
                return s;
            }
        }

        DeleteFrase df = new DeleteFrase();
        df.execute();
    }

    private void confirmDeleteFrase(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("¿Estas seguro de querer eliminar esta Frase?");

        alertDialogBuilder.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteFrase();
                        startActivity(new Intent(ViewFrase.this,ViewAllFrases.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnActualizar){
            updateFrase();
            listAll();
        }

        if(v == btnEliminar){
            confirmDeleteFrase();
        }

        if(v == btnLista){
            listAll();
        }
    }

    private void listAll(){
        startActivity(new Intent(this,ViewAllFrases.class));
    }
}
