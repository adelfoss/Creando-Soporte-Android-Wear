package com.example.adelfo.miscontactos.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.adelfo.miscontactos.pojo.Contacto;
import com.example.adelfo.miscontactos.pojo.Usuario;

import java.util.ArrayList;

/**
 * Created by Adelfo on 21/11/2016.
 */

public class BaseDatos extends SQLiteOpenHelper{

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaContacto = "CREATE TABLE "+ConstantesBaseDatos.TABLE_CONTACTS + "("+
                                        ConstantesBaseDatos.TABLE_CONTACTS_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE   + " TEXT, " +
                                        ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO + " TEXT, " +
                                        ConstantesBaseDatos.TABLE_CONTACTS_EMAIL    + " TEXT, " +
                                        ConstantesBaseDatos.TABLE_CONTACTS_FOTO     + " INTEGER" +
                                        ")";

        String queryCrearTablaLikesContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT + "("+
                                        ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                        ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + " INTEGER, "+
                                        ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES + " INTEGER, "+
                                        "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + ") "+
                                        "REFERENCES " + ConstantesBaseDatos.TABLE_CONTACTS + "(" + ConstantesBaseDatos.TABLE_CONTACTS_ID + ")" +
                                        ")";

        db.execSQL(queryCrearTablaContacto);
        db.execSQL(queryCrearTablaLikesContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES_CONTACT);
        onCreate(db);
    }

    public ArrayList<Contacto> obtenerTodosLosContactos(){
        ArrayList<Contacto> contactos = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        db.close();

        return contactos;
    }

    public ArrayList<Usuario> obtenerTodosLosContactos2(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        return usuarios;
    }

    public void insertarCotacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_CONTACTS, null, contentValues);
        db.close();
    }

    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_CONTACT, null, contentValues);
        db.close();
    }

    public int obtenerLikesContacto(Contacto contacto){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES+")" +
                        " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACT +
                        " WHERE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + "=" + contacto.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }
}
