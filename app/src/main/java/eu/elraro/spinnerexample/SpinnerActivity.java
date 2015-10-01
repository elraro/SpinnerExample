package eu.elraro.spinnerexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity {

    private Spinner list;
    private ArrayList<String> data;
    //String[] data = {"Select pay method", "Cash", "Card"};

    //boton de añadir
    private Button button;

    //EditText para añadir la opcion
    private EditText opcion;

    //el adapter
    private ArrayAdapter<String> adapter;

    //el toast
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        //Leemos array.xml y lo vamos a convertir a ArrayList para manejarlo mejor
        String[] dataxml = getResources().getStringArray(R.array.list);
        data = new ArrayList<String>();
        for(int i = 0; i < dataxml.length; i++) {
            data.add(dataxml[i]);
        }

        this.list = (Spinner) findViewById(R.id.spinner);
        this.opcion = (EditText) findViewById(R.id.editText);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        list.setAdapter(adapter);
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        Intent in = new Intent(SpinnerActivity.this, NewActivity.class);
                        startActivity(in);
                        break;
                    default:
                        toast = Toast.makeText(getApplicationContext(), data.get(i), Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String opc = opcion.getText().toString();
                data.add(opc);
                adapter = new ArrayAdapter<String>(SpinnerActivity.this, android.R.layout.simple_spinner_item, data);
                list.setAdapter(adapter);
                toast = Toast.makeText(getApplicationContext(), "Añadida la opcion " + opc, Toast.LENGTH_SHORT);
                toast.show();

                //Cerramos el teclado despues de hacer click al boton
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

    }
}
