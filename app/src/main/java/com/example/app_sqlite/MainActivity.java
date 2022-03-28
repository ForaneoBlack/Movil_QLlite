package com.example.app_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_sqlite.modelo.DBHelper;
import com.example.app_sqlite.modelo.Persona;

public class MainActivity extends AppCompatActivity {
    Button btnCrear;
    Button btnGrabar;
    EditText txtID, txtNombre, txtCorreo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCrear=findViewById(R.id.Crear);
        //grabar
        btnGrabar= findViewById(R.id.buttonGrabar);
        txtID= findViewById(R.id.editTextTextPersonID);
        txtNombre= findViewById(R.id.editTextTextPersonNombre);
        txtCorreo= findViewById(R.id.editTextTextPersonCorreo);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                   //crear Base de Datos

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db != null) {
                    Toast.makeText(getApplicationContext(), "BASE CREADA", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "ERROR AL CREAR LA BASE", Toast.LENGTH_LONG).show();
                }
            }
        });

        //grabar
        btnGrabar= findViewById(R.id.buttonGrabar);
        txtID= findViewById(R.id.editTextTextPersonID);
        txtNombre= findViewById(R.id.editTextTextPersonNombre);
        txtCorreo= findViewById(R.id.editTextTextPersonCorreo);
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //grabar
                Persona p =new Persona();
                p.setId( Integer.parseInt( txtID.getText().toString()));
                p.setNombre(txtNombre.getText().toString());
                p.setCorreo(txtCorreo.getText().toString());
                p.guardar(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Persona Creada", Toast.LENGTH_LONG).show();

            }
        });







    }


}