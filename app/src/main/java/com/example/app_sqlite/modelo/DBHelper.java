package com.example.app_sqlite.modelo;

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

    public Cursor query(String sql){


        return this.getReadableDatabase().rawQuery(sql,null);

        }



}
