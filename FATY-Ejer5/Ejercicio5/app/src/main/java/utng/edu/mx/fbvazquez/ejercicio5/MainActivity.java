package utng.edu.mx.fbvazquez.ejercicio5;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Defining views
    private EditText txt_nombre;
    private EditText txt_apellidos;

    private Button btnAgregar;
    private Button btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing views
        txt_nombre = (EditText) findViewById(R.id.txt_nombre);
        txt_apellidos = (EditText) findViewById(R.id.txt_apellidos);

        btnAgregar = (Button) findViewById(R.id.btn_agregar);
        btnVer = (Button) findViewById(R.id.btn_ver);

        //Setting listeners to button
        btnAgregar.setOnClickListener(this);
        btnVer.setOnClickListener(this);
    }


    //Adding an employee
    private void addLibro() {

        final String nombre = txt_nombre.getText().toString().trim();
        final String apellidos = txt_apellidos.getText().toString().trim();

        class AddLibro extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Agregando...", "Espere...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_LIBRO_NOMBRE, nombre);
                params.put(Config.KEY_LIBRO_APELLIDOS, apellidos);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddLibro al = new AddLibro();
        al.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == btnAgregar) {
            addLibro();
        }

        if (v == btnVer) {
            startActivity(new Intent(this, ViewAllArchivos.class));
        }
    }
}
