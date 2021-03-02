package com.example.donner;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Kebab> misFilas= new ArrayList<Kebab>();

    DBInterface dbInterface;

    AdaptadorRecycler adaptador;
    int posicionLongClick=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dbInterface = new DBInterface(this);
        dbInterface.abre();
        cargarKebab();

        adaptador = new AdaptadorRecycler(misFilas);
        RecyclerView miRecycler = findViewById(R.id.RecyclerViewKebab);
        miRecycler.setAdapter(adaptador);

        miRecycler.setLayoutManager(new LinearLayoutManager(this));


        adaptador.setOnItemLongClickListener
                (new OnRecyclerViewLongItemClickListener() {
                    @Override
                    public void onItemLongClick(View view, int pos) {
                        posicionLongClick = pos;
                        openContextMenu(view);
                    }
                });

        registerForContextMenu(miRecycler);

    }


    public void cargarKebab(){
        misFilas.clear();
        Cursor c = dbInterface.obtenerKebab();
        if (c==null){
            Toast.makeText(this,"Tabla vacía",Toast.LENGTH_LONG).show();
        }else{
            if (c.moveToFirst()){
                do{
                    boolean miCheck= true;

                    if (c.getInt(2)==0){
                        miCheck=false;
                    }
                    misFilas.add(new Kebab(c.getLong(0),
                            c.getString(1),
                            miCheck,
                            c.getString(3)));
                }while(c.moveToNext());
            }
        }
    }
    @Override
    public void onStop(){
        super.onStop();
        dbInterface.cierra();

    }

    @Override
    public void onResume(){
        super.onResume();
        dbInterface.abre();
        cargarKebab();
        adaptador.notifyDataSetChanged();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){
        Activity activity = (Activity ) v.getContext();
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuBorrar:
                dbInterface.borrarKebab(misFilas.get(posicionLongClick).getId());
                misFilas.remove(posicionLongClick);

                adaptador.notifyDataSetChanged();
                break;

            case   R.id.menuModificar:
                /*Intent intent = new Intent(this,ModificarBBDD.class);
                intent.putExtra("id",misFilas.get(posicionLongClick).getId());
                intent.putExtra("marca",misFilas.get(posicionLongClick).getMarca());


                startActivity(intent);*/
                break;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menutoolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.menuInsertar){
            startActivity(new Intent(this, InsertarBBDD.class));
        }


        return  true;
    }




}