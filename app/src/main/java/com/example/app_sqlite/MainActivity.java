package com.example.app_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_sqlite.modelo.DBHelper;
import com.example.app_sqlite.modelo.Persona;

public class MainActivity extends AppCompatActivity {
    Button btnCrear, btnGrabar, btn_eliminar, btn_ver, btnactualizar;

    EditText txtID, txtNombre, txtTelefono;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCrear=findViewById(R.id.Crear);
        btn_eliminar =findViewById(R.id.btn_eliminar);
        btn_ver=findViewById(R.id.btn_ver);
        btnactualizar=findViewById(R.id.btnActualizar);
        DB = new DBHelper(this);

        //grabar
        btnGrabar= findViewById(R.id.buttonGrabar);
        txtID= findViewById(R.id.editTextTextPersonID);
        txtNombre= findViewById(R.id.editTextTextPersonNombre);
        txtTelefono= findViewById(R.id.txtTelefono);
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
        txtTelefono= findViewById(R.id.txtTelefono);
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //grabar
                Persona p =new Persona();
                p.setId( Integer.parseInt( txtID.getText().toString()));
                p.setNombre(txtNombre.getText().toString());
                p.setTelefono(txtTelefono.getText().toString());
                p.guardar(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Persona Creada", Toast.LENGTH_LONG).show();

            }
        });

        // actualizar

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String apellido = txtTelefono.getText().toString();


                Boolean checkupdatedata = DB.updateuserdata(nombre,apellido);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Datos no Actualizados", Toast.LENGTH_SHORT).show();
            }        });


        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nombre);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Datos Eliminados", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Datos no eliminados", Toast.LENGTH_SHORT).show();
            }        });

        btn_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.query();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No existen datos", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("Nombre :"+res.getString(1)+"\n");
                    buffer.append("Telefono :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Datos de los Usuarios");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });


    }


}