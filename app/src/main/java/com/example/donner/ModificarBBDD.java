package com.example.donner;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ModificarBBDD extends AppCompatActivity implements View.OnClickListener{

    EditText editTextPrecio;
    CheckBox checkBoxCarne;
    Button modificar;
    DBInterface dbInterface;
    RadioButton radioButton1, radioButton2, radioButton3;
    String tipoKebab;
    String precio;
    boolean checked;

    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_b_b_d_d);

        editTextPrecio = findViewById(R.id.txtPrecioMod);
        checkBoxCarne=findViewById(R.id.insertacheckBoxMod);
        modificar = findViewById(R.id.btnModificar);

        radioButton1 = (RadioButton) findViewById(R.id.radioButtonMod);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2Mod);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3Mod);


        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);
        modificar.setOnClickListener(this);


        Intent i = getIntent();


        if (i != null) {
            id=i.getLongExtra("id",0);

            precio=i.getStringExtra("precio");

            checked=i.getBooleanExtra("check",true);

            editTextPrecio.setText(precio);
            checkBoxCarne.setChecked(checked);


        }


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.radioButtonMod:
                if (radioButton1.isChecked())
                    tipoKebab = radioButton1.getText().toString();


                break;
            case R.id.radioButton2Mod:
                if (radioButton2.isChecked())
                    tipoKebab = radioButton2.getText().toString();

                break;

            case R.id.radioButton3Mod:
                if (radioButton3.isChecked())
                    tipoKebab = radioButton3.getText().toString();

                break;
        }



        if (v.getId() == R.id.btnModificar) {
            int check=0;
            if (checkBoxCarne.isChecked()) check=1;
            precio = editTextPrecio.getText().toString();

            dbInterface = new DBInterface(this);
            dbInterface.abre();
            if (dbInterface.modificaKebab(id,tipoKebab,check,precio)==-1){
                Toast.makeText(getBaseContext(),"Error en la inserccion",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getBaseContext(),"Deporte insertado",Toast.LENGTH_LONG).show();

            }
            dbInterface.cierra();
            finish();

        }


    }
}