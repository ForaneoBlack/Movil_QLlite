package com.example.app_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnCrear, btnGrabar, btn_eliminar, btn_ver, btnactualizar;

    EditText txtID, txtNombre, txtTelefono;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCrear=findViewById(R.id.btnCrear);
        btn_eliminar =findViewById(R.id.btn_eliminar);
        btn_ver=findViewById(R.id.btn_ver);
        btnactualizar=findViewById(R.id.btnActualizar);
        DB = new DBHelper(this);

        //grabar

        txtID= findViewById(R.id.txtId);
        txtNombre= findViewById(R.id.txtNombre);
        txtTelefono= findViewById(R.id.txtTelefono);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txtID.getText().toString();
                String nombre = txtNombre.getText().toString();
                String telefono = txtTelefono.getText().toString();


                Boolean checkinsertdata = DB.insertuserdata(id,nombre, telefono);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "Nuevos Datos Ingresados", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Datos no Ingresados", Toast.LENGTH_SHORT).show();
            }        });

        // actualizar

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txtID.getText().toString();
                String nombre = txtNombre.getText().toString();
                String telefono = txtTelefono.getText().toString();

                Boolean update = DB.updateuserdata(id,nombre,telefono);
                if(update==true)
                    Toast.makeText(MainActivity.this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Datos no Actualizados", Toast.LENGTH_SHORT).show();
            }        });


        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txtID.getText().toString();
                Boolean delete = DB.deletedata(id);
                if(delete==true)
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