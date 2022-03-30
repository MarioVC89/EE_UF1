package es.ifp.ee_uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    protected TextView label1;

    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label1= (TextView) findViewById(R.id.label1_start);

        TimerTask tt= new TimerTask() {
            @Override
            public void run() {
                pasarPantalla= new Intent(StartActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }

        };
        Timer t = new Timer();
        t.schedule(tt, 3000);

    }
}