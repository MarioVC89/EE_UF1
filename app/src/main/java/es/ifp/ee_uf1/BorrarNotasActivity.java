package es.ifp.ee_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class BorrarNotasActivity extends AppCompatActivity {

    protected Button boton1;
    protected Button boton2;

    protected BaseDatosSQL db;

    private Intent pasarPantalla;

    private ArrayList<String> notas= new ArrayList<String>();
    private ArrayAdapter<String> adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_notas);

        boton1= (Button) findViewById(R.id.boton1_borrar);
        boton2= (Button) findViewById(R.id.boton2_borrar);

        db= new BaseDatosSQL(this);


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               db.deleteAllNotes();
               db.close();
               Toast.makeText(BorrarNotasActivity.this, getString(R.string.toast1_borrar_text), Toast.LENGTH_SHORT).show();
               pasarPantalla= new Intent(BorrarNotasActivity.this, ListadoActivity.class);
               finish();
               startActivity(pasarPantalla);

            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarPantalla= new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }
}