package org.joelcano.projectes.examenm8uf1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ConfigActivity extends Activity implements ViewStub.OnClickListener{

    Spinner spinner, spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        String[] equips = getResources().getStringArray(R.array.equips);
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, equips);
        spinner.setAdapter(adaptador);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        spinner2.setAdapter(adaptador);
    }

    @Override
    public void onClick(View v) {
        String local= spinner.getSelectedItem().toString();
        String visitant= spinner2.getSelectedItem().toString();
        EditText etJornada = (EditText) findViewById(R.id.etJornada);
        String jornada= etJornada.getText().toString();

        Intent intent = new Intent(this, PartitActivity.class);
        intent.putExtra("local", local);
        intent.putExtra("visitant", visitant);
        intent.putExtra("jornada", jornada);

        //posible error REVISAR
        startActivityForResult(intent, 0);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        finish();
    }
}
