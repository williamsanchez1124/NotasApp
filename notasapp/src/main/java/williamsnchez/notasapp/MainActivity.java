package williamsnchez.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private double expo1,prac1,proy1;
    EditText eExpo, ePrac, eProy, eNota;
    Button bcalcular;
    private Button bCalcular,bLimpiar;
    private TextView WProyect,WPractic,WExpo;
    double NProyecto,NExposicion,NPractica,NFinal;

    @Override
    protected void onSaveInstanceState(Bundle outState) {


        super.onSaveInstanceState(outState);
        outState.putDouble("pProy", proy1);
        outState.putDouble("pPrac", prac1);
        outState.putDouble("pExpo", expo1);
        outState.putString("notaExpo",(eExpo.getText().toString()));
        outState.putString("notaPrac", (ePrac.getText().toString()));
        outState.putString("notaProy", (eProy.getText().toString()));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eExpo = (EditText) findViewById(R.id.expo);
        ePrac = (EditText) findViewById(R.id.practica);
        eProy = (EditText) findViewById(R.id.proyec);
        eNota = (EditText) findViewById(R.id.notafinal);
        bcalcular = (Button) findViewById(R.id.calcular);

        bCalcular = (Button) findViewById(R.id.calcular);
        bLimpiar = (Button) findViewById(R.id.bLimpiar);
        WProyect = (TextView) findViewById(R.id.textProyecto);
        WExpo = (TextView) findViewById(R.id.textExpocision);
        WPractic = (TextView) findViewById(R.id.textPractica);

        if(savedInstanceState!=null)
        { proy1=savedInstanceState.getDouble("proy1");
            prac1=savedInstanceState.getDouble("prac1");
            expo1=savedInstanceState.getDouble("expo1");
            WPractic.setText("(" + String.valueOf(prac1) + "%)");
            WProyect.setText("("+String.valueOf(proy1)+"%)");
            WExpo.setText("("+String.valueOf(expo1)+"%)");

            eProy.setText(String.valueOf(savedInstanceState.getString("notaProy")));
            ePrac.setText(String.valueOf(savedInstanceState.getString("notaPrac")));
            eExpo.setText(String.valueOf(savedInstanceState.getString("notaExpo")));
     }
        else {


            expo1 = 10;
            prac1 = 40;
            proy1 = 50;
        }
        bLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eProy.setText("");
                ePrac.setText("");
                eExpo.setText("");
                eNota.setText("");
            }
        });

        bCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!eProy.getText().toString().equals("") && !ePrac.getText().toString().equals("") && !eExpo.getText().toString().equals("") )
                {
                    NProyecto=Double.parseDouble(eProy.getText().toString());
                    NExposicion=Double.parseDouble(eExpo.getText().toString());
                    NPractica=Double.parseDouble(ePrac.getText().toString());
                    if(NProyecto>=0 && NProyecto<=5)
                    {
                        if(NExposicion>=0 && NExposicion<=5)
                        {
                            if(NPractica>=0 && NPractica<=5)
                            {
                                NFinal=(expo1/100)*NExposicion+(proy1/100)*NPractica+NProyecto*(proy1/100);
                                String s;

                                eNota.setText(String.valueOf(NFinal).substring(0,3));
                            }
                            else
                            {
                                eNota.setText("Nota de practica invalida");

                            }

                        }
                        else
                        {
                            eNota.setText("Nota de exposición invalida");

                        }


                    }
                    else
                    {
                        eNota.setText("Nota de proyecto invalida");

                    }

                }
                else
                {

                    eNota.setText("Por favor ingrese notal");

                }
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();
        if(id== R.id.mconfigurar){

            Intent intent = new Intent(this,Main2Activity.class);
            intent.putExtra("epExpo",15);
            intent.putExtra("epPractica",50);
            intent.putExtra("epProyec",15);
            startActivityForResult(intent, 1234);

        }
        else
        {
            Toast.makeText(this, "Presione Calcular", Toast.LENGTH_SHORT).show();


        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String exp,prac,pro;
        if(requestCode==1234 && resultCode== RESULT_OK){

            WPractic.setText("("+String.valueOf(data.getExtras().getString("prac1"))+"%)");
           WExpo.setText("("+String.valueOf(data.getExtras().getString("expo1"))+"%)");
            WProyect.setText("("+String.valueOf(data.getExtras().getString("proy1"))+"%)");
           proy1=Double.parseDouble(data.getExtras().getString("proy1"));
            expo1=Double.parseDouble(data.getExtras().getString("expo1"));
           prac1=Double.parseDouble(data.getExtras().getString("prac1"));

            exp=data.getExtras().getString("npExpo");
            prac=data.getExtras().getString("npPrac");
            pro=data.getExtras().getString("npProyec");

        }

        super.onActivityResult(requestCode, resultCode, data);
        }
    }

