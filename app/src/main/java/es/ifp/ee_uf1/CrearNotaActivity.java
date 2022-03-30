package es.ifp.ee_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CrearNotaActivity extends AppCompatActivity {

    protected TextView label1;
    protected EditText caja1;
    protected Button boton1;
    protected Button boton2;

    protected String contenidoCaja1;
    private int contador= 0;
    private boolean encontrado;

    protected BaseDatosSQL db;

    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);

        db= new BaseDatosSQL(this);

        label1= (TextView) findViewById(R.id.label1_crear);
        caja1= (EditText) findViewById(R.id.caja1_crear);
        boton1= (Button) findViewById(R.id.boton1_crear);
        boton2= (Button) findViewById(R.id.boton2_crear);


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pasarPantalla= new Intent(CrearNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);

            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contenidoCaja1= caja1.getText().toString();
                if(contenidoCaja1.equals(""))
                {
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast1_crear_text), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.insertNote(contenidoCaja1);
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast2_crear_text), Toast.LENGTH_SHORT).show();
                    pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                }


            }
        });
    }
}