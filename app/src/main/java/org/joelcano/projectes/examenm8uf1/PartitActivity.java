package org.joelcano.projectes.examenm8uf1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class PartitActivity extends Activity implements ViewStub.OnClickListener{

    String equipLocal, equipVisitant,jornada;
    Spinner nLocal, nVisitant;
    TextView tvVisitant, tvLocal;
    int totalLocal = 0, totalVisitant = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partit);

        //RECLLINT DADES
        Intent intent = getIntent();
        TextView nomLocal = (TextView) findViewById(R.id.nomLocal);
        equipLocal = intent.getStringExtra("local");
        nomLocal.setText(equipLocal);
        TextView nomVisitant = (TextView) findViewById(R.id.nomVisitant);
        equipVisitant = intent.getStringExtra("visitant");
        nomVisitant.setText(equipVisitant);
        jornada = intent.getStringExtra("jornada");

        //CLICK LISTENERS
        Button btEnviar = (Button) findViewById(R.id.btEnviar);
        btEnviar.setOnClickListener(this);
        Button btFinal = (Button) findViewById(R.id.btFinal);
        btFinal.setOnClickListener(this);
        Button sumarLocal = (Button) findViewById(R.id.sumarLocal);
        sumarLocal.setOnClickListener(this);
        Button restarLocal = (Button) findViewById(R.id.restarLocal);
        restarLocal.setOnClickListener(this);
        Button sumarVisitant = (Button) findViewById(R.id.sumarVisitant);
        sumarVisitant.setOnClickListener(this);
        Button restarVisitant = (Button) findViewById(R.id.restarVisitant);
        restarVisitant.setOnClickListener(this);

        nLocal = (Spinner)findViewById(R.id.nLocal);
        final String[] punts =
                new String[]{"1","2","3"};
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, punts);
        nLocal.setAdapter(adaptador);
        nVisitant = (Spinner) findViewById(R.id.nVisitant);
        nVisitant.setAdapter(adaptador);
        tvVisitant = (TextView) findViewById(R.id.tvVisitant);
        tvLocal = (TextView) findViewById(R.id.tvLocal);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btEnviar) {
            //Guardamos las preferencias
            SharedPreferences prefs =
            getSharedPreferences("Resultat", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("equipLocal", equipLocal);
            editor.putString("equipVisitant", equipVisitant);
            editor.putString("jornada", jornada);
            editor.putInt("totalLocal", totalLocal);
            editor.putInt("totalVisitant", totalVisitant);
            editor.commit();

        }//CAMBIAR LOCAL
        else if (v.getId()== R.id.sumarLocal){
            String n = nLocal.getSelectedItem().toString();
            totalLocal += Integer.valueOf(n);
            tvLocal.setText(String.valueOf(totalLocal));
        }
        else if (v.getId()== R.id.restarLocal){
            String n = nLocal.getSelectedItem().toString();
            totalLocal -= Integer.valueOf(n);
            tvLocal.setText(String.valueOf(totalLocal));
        }//CAMBIAR VISITANT
        else if (v.getId()== R.id.sumarVisitant){
            String n = nVisitant.getSelectedItem().toString();
            totalVisitant += Integer.valueOf(n);
            tvVisitant.setText(String.valueOf(totalVisitant));
        }
        else if (v.getId()== R.id.restarVisitant){
            String n = nVisitant.getSelectedItem().toString();
            totalVisitant -= Integer.valueOf(n);
            tvVisitant.setText(String.valueOf(totalVisitant));
        }
        else if(v.getId()== R.id.btFinal) {
            //Guardamos las preferencias
            SharedPreferences prefs =
                    getSharedPreferences("Resultat", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("equipLocal", equipLocal);
            editor.putString("equipVisitant", equipVisitant);
            editor.putString("jornada", jornada);
            editor.putInt("totalLocal", totalLocal);
            editor.putInt("totalVisitant", totalVisitant);
            editor.commit();

            setResult(RESULT_OK, null);
            finish();

        }


    }
}
