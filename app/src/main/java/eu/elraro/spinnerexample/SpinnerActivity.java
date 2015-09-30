package eu.elraro.spinnerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    //opcion para añadir
    private EditText opcion;

    private ArrayAdapter<String> adapter;


    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

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
            }
        });

    }
}
