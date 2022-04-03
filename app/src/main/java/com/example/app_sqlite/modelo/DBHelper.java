package com.example.app_sqlite.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NOMBRE="prueba_movil.db";
    private static final int DATABASE_VERSION=2;
    private static final String TABLA_PERSONAS="personas";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE,null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql= "CREATE TABLE "+ TABLA_PERSONAS + " (" +
                "id INTEGER NOT NULL," +
                " nombre TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                " correo TEXT," +
                "edad INTEGER " +
                ")";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sqld ="DROP TABLE " + TABLA_PERSONAS;
        sqLiteDatabase.execSQL(sqld);
        onCreate(sqLiteDatabase);

    }

    public void no_query (String nsql) {
        this.getWritableDatabase().execSQL(nsql);


    }
    public Boolean updateuserdata(String nombre,String apellido)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("apellido", apellido);
        Cursor cursor = DB.rawQuery("Select * from TABLA_PERSONAS where name = ?", new String[]{nombre});
        if (cursor.getCount() > 0) {
            long result = DB.update("TABLA_PERSONAS", contentValues, "name=?", new String[]{nombre});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String nombre)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from TABLA_PERSONAS where name = ?", new String[]{nombre});
        if (cursor.getCount() > 0) {
            long result = DB.delete("TABLA_PERSONAS", "name=?", new String[]{nombre});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor query(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Personas", null);
        return cursor;
    }



}
