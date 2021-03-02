package com.example.donner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBInterface {
    // Constantes
    public static final String CAMPO_ID = "_id";
    public static final String CAMPO_TIPO = "tipo";
    public static final String CAMPO_CHECK = "chequeo";
    public static final String CAMPO_PRECIO = "precio";

    public static final String TAG = "DBInterface";

    public static final String BD_NOMBRE = "BDKebab";
    public static final String BD_TABLA = "kebab";
    public static final int VERSION = 1;

    public static final String BD_CREATE =
            "create table " + BD_TABLA + "(" + CAMPO_ID +
                    " integer primary key autoincrement, "+
                    CAMPO_TIPO + " text not null," +
                    CAMPO_CHECK + " int not null," +
                    CAMPO_PRECIO + " text not null); ";

    private final Context contexto;
    private AyudaDB ayuda;
    private SQLiteDatabase bd;

    public DBInterface (Context con)
    {
        this.contexto = con;
        Log.w(TAG, "creando ayuda" );
        ayuda = new AyudaDB(contexto);
    }

    public DBInterface abre () throws SQLException {
        Log.w(TAG, "abrimos base de datos" );
        bd = ayuda.getWritableDatabase();
        return this;
    }

    // Cierra la BD
    public void cierra () {
        ayuda.close();
    }

    public long insertarKebab(String tipo,int check, String precio)
    {
        ContentValues initialValues = new ContentValues ();
        initialValues.put(CAMPO_TIPO, tipo);
        initialValues.put(CAMPO_CHECK, check);
        initialValues.put(CAMPO_PRECIO, precio);
        return bd.insert(BD_TABLA, null,
                initialValues);
    }

    // Devuelve todos los Contactos
    public Cursor obtenerKebab(){
        return bd.query(BD_TABLA, new String []
                        { CAMPO_ID,CAMPO_TIPO, CAMPO_CHECK,CAMPO_PRECIO},
                null,null, null, null,
                null);
    }

    public long modificaKebab(long id,String tipo, int check, String precio)
    {
        ContentValues newValues = new ContentValues();
        newValues.put(CAMPO_TIPO, tipo);
        newValues.put(CAMPO_CHECK, check);
        newValues.put(CAMPO_PRECIO, precio);
        return bd.update(BD_TABLA, newValues, CAMPO_ID + "=" + id, null);
    }

    public long borrarKebab(long id)
    {

        return bd.delete(BD_TABLA, CAMPO_ID + "=" + id, null);
    }

    public class AyudaDB extends SQLiteOpenHelper {

        public AyudaDB(Context con){
            super (con, BD_NOMBRE, null, VERSION);
            Log.w(TAG, "constructor de ayuda");
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.w(TAG, "creando la base de datos "+BD_CREATE );
                db.execSQL(BD_CREATE);
            } catch (SQLException e) {
                e.printStackTrace ();
            }
        }
        @Override
        public void onUpgrade (SQLiteDatabase db,
                               int VersionAntigua, int VersionNueva) {
            Log.w(TAG, "Actualizando Base de datos de la versión" +
                    VersionAntigua + "A" + VersionNueva + ". Destruirá todos los datos");
            db.execSQL("DROP TABLE IF EXISTS " + BD_TABLA);
            onCreate(db);
        }
    }

}