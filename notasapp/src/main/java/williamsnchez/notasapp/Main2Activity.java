package williamsnchez.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private EditText eProy,ePrac,eExpo;
    private Button bGuardar,bLimpiar;
    private TextView Avisar;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("proy1", String.valueOf(eProy.getText().toString()));
        outState.putString("prac1",String.valueOf(ePrac.getText().toString()));
        outState.putString("expo1",String.valueOf(eExpo.getText().toString()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         final EditText eExpo = (EditText) findViewById(R.id.epExpo);
         final EditText ePrac = (EditText) findViewById(R.id.epPractica);
         final EditText eProy = (EditText) findViewById(R.id.epProyec);
         Button bGuardar = (Button) findViewById(R.id.guardar);
        bLimpiar=(Button)findViewById(R.id.VLimpiar);
        Avisar=(TextView)findViewById(R.id.avisar);


        Bundle extras = getIntent().getExtras();

        if(savedInstanceState!=null) {
            eExpo.setText(savedInstanceState.getString("expo1"));
            eProy.setText(savedInstanceState.getString("proy1"));
            ePrac.setText(savedInstanceState.getString("prac1"));
        }

        bLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eProy.setText("");
                ePrac.setText("");
                eExpo.setText("");
                Avisar.setVisibility(View.INVISIBLE);
            }
        });

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalPorcentaje;
                totalPorcentaje = Double.parseDouble(eProy.getText().toString()) + Double.parseDouble(ePrac.getText().toString()) + Double.parseDouble(eExpo.getText().toString());
                if (totalPorcentaje == 100) {
                    Avisar.setVisibility(View.INVISIBLE);
                    Intent i = new Intent();
                    i.putExtra("proy1", eProy.getText().toString());
                    i.putExtra("Prac1", ePrac.getText().toString());
                    i.putExtra("expo1", eExpo.getText().toString());
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    Avisar.setVisibility(View.VISIBLE);

                }

            }
        });
}
}
