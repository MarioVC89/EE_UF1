package es.ifp.ee_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VerNotaActivity extends AppCompatActivity {

    protected TextView label1;
    protected TextView label2;
    protected Button boton1;
    protected Button boton2;

    protected BaseDatosSQL db;

    protected Intent pasarPantalla;
    private String paquete1;
    private String paquete2;

    private Bundle extras;

    private ArrayList<String> notaObtenida;
    private ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nota);

        label1= (TextView) findViewById(R.id.label1_vernota);
        label2= (TextView) findViewById(R.id.label2_vernota);
        boton1= (Button) findViewById(R.id.boton1_verNota);
        boton2= (Button) findViewById(R.id.boton2_verNota);

        db= new BaseDatosSQL(this);


        extras=getIntent().getExtras();
        if(extras!=null) {
            paquete1 = extras.getString("ID");
            paquete2= extras.getString("NOTA");
            label1.setText(paquete2);
        }

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarPantalla= new Intent(VerNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id= Integer.parseInt(paquete1);
                db.deleteNote(id);
                Toast.makeText(VerNotaActivity.this, "Nota borrada correctamente", Toast.LENGTH_SHORT).show();
                pasarPantalla= new Intent(VerNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);

            }
        });




    }
}