package com.example.krskoe.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.krskoe.R;

public class CreateMatkulActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private static final String[] hari = {"Senin","Selasa","Rabu","Kamis","Jumat"};
    private static final String[] sesi = {"1","2","3","4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_matkul);

        spinner1 = (Spinner)findViewById(R.id.spinnerDosen);
        ArrayAdapter<String>adapter = new ArrayAdapter<>(CreateMatkulActivity.this,android.R.layout.simple_spinner_item,hari);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner2 = (Spinner)findViewById(R.id.spinnerSesi);
        ArrayAdapter<String>adapterSesi = new ArrayAdapter<>(CreateMatkulActivity.this,android.R.layout.simple_spinner_item,sesi);
        adapterSesi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterSesi);

//        Button btnSimpanMatkul = (Button)findViewById(R.id.btnSimpanMatkul);
//        btnSimpanMatkul.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CreateMatkulActivity.this, HomeAdmin.class);
//                startActivity(intent);
//            }
//        });

        Button btnSimpan = (Button)findViewById(R.id.btnSimpanMatkul);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateMatkulActivity.this);

                builder.setMessage("Tenan i meh disimpen ?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreateMatkulActivity.this, "Rasido Simpan", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(CreateMatkulActivity.this, HomeAdmin.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog dialog = builder.create(); dialog.show();
            }
        });
    }
}
