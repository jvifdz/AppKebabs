package com.example.donner;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class InsertarBBDD extends AppCompatActivity implements View.OnClickListener{

    EditText editTextPrecio;
    CheckBox checkBoxCarne;
    Button anadir;
    DBInterface dbInterface;
    RadioButton radioButton1, radioButton2, radioButton3;
    String tipoKebab;
    String precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_b_b_d_d);
        editTextPrecio =  (EditText) findViewById(R.id.txtPrecio);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);

        checkBoxCarne = findViewById(R.id.insertacheckBox);

        anadir = (Button) findViewById(R.id.btnAnadir);

        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);
        anadir.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radioButton:
                if (radioButton1.isChecked())
                    tipoKebab = radioButton1.getText().toString();


                break;
            case R.id.radioButton2:
                if (radioButton2.isChecked())
                    tipoKebab = radioButton2.getText().toString();

                break;

            case R.id.radioButton3:
                if (radioButton3.isChecked())
                    tipoKebab = radioButton3.getText().toString();

                break;
        }

        if (v.getId() == R.id.btnAnadir) {
            int check=0;
            if (checkBoxCarne.isChecked()) check=1;
            precio = editTextPrecio.getText().toString();

            dbInterface = new DBInterface(this);
            dbInterface.abre();
            if (dbInterface.insertarKebab(tipoKebab,check,precio)==-1){
                Toast.makeText(getBaseContext(),"Error en la inserccion",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getBaseContext(),"Kebab insertado",Toast.LENGTH_LONG).show();

            }
            dbInterface.cierra();
            finish();

        }

    }
}