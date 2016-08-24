package utng.edu.mx.fbvazquez.webservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText txt_autor;
    private EditText txt_frase;
    private EditText txt_tipo;
    private EditText txt_rating;

    private Button btnAgregar;
    private Button btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing views
        txt_autor = (EditText) findViewById(R.id.txt_autor);
        txt_frase = (EditText) findViewById(R.id.txt_frase);
        txt_tipo = (EditText) findViewById(R.id.txt_tipo);
        txt_rating = (EditText) findViewById(R.id.txt_rating);

        btnAgregar = (Button) findViewById(R.id.btn_agregar);
        btnVer = (Button) findViewById(R.id.btn_ver);

        //Setting listeners to button
        btnAgregar.setOnClickListener(this);
        btnVer.setOnClickListener(this);
    }


    //Adding an employee
    private void addEmployee(){

        final String autor = txt_autor.getText().toString().trim();
        final String frase = txt_frase.getText().toString().trim();
        final String tipo = txt_tipo.getText().toString().trim();
        final String rating = txt_rating.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Agregando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_FRASE_AUTOR,autor);
                params.put(Config.KEY_FRASE_FRASE,frase);
                params.put(Config.KEY_FRASE_TIPOFRASE,tipo);
                params.put(Config.KEY_FRASE_RATING,rating);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == btnAgregar){
            addEmployee();
        }

        if(v == btnVer){
            startActivity(new Intent(this,ViewAllFrases.class));
        }
    }
}
