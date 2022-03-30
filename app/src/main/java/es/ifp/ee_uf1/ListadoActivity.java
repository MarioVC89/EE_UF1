package es.ifp.ee_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    protected ListView lista1;

    private ArrayList<String> notas= new ArrayList<String>();
    private ArrayAdapter<String> adaptador;

    private Intent pasarPantalla;

    protected BaseDatosSQL db;

    private String contenidoItem;
    private String[] partes;
    private int identificador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        db= new BaseDatosSQL(this);

        lista1= (ListView) findViewById(R.id.lista1_Listado);

        notas= db.getAllNotes();
        adaptador= new ArrayAdapter<String>(ListadoActivity.this, android.R.layout.simple_list_item_1, notas);
        lista1.setAdapter(adaptador);

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListadoActivity.this, getString(R.string.toast_listado_text), Toast.LENGTH_SHORT).show();
                contenidoItem= parent.getItemAtPosition(position).toString();

                if(contenidoItem.length()>=1)
                {
                    Note n = db.getNote(contenidoItem);
                    if(n!= null)
                    {
                        identificador= n.id;
                        pasarPantalla= new Intent(ListadoActivity.this, VerNotaActivity.class);
                        pasarPantalla.putExtra("ID", Integer.toString(n.getId()));
                        pasarPantalla.putExtra("NOTA", n.getNota());
                        finish();
                        startActivity(pasarPantalla);
                    }
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1_opciones:
                pasarPantalla= new Intent(ListadoActivity.this,BorrarNotasActivity.class);
                finish();
                startActivity(pasarPantalla);

                return true;
            case R.id.item2_crear:
                pasarPantalla= new Intent(ListadoActivity.this,CrearNotaActivity.class);
                finish();
                startActivity(pasarPantalla);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}