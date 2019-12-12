package com.example.aplikasikrs.Admin;

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

import com.example.aplikasikrs.R;

public class CreateKrsActivity extends AppCompatActivity {
    private Spinner spinner1;
    private static final String[] dosen = {"Jong Jek Siang","Erick Kurniawan","Budi Sutedjo","Matahari","Wimmie Hanz"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_krs);
        this.setTitle("SI KRS - Hai Admin");

        spinner1 = (Spinner)findViewById(R.id.spinnerDosen);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateKrsActivity.this,android.R.layout.simple_spinner_item,dosen);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

//        Button btnSimpanKrs = (Button)findViewById(R.id.btnSimpanKrs);
//        btnSimpanKrs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CreateKrsActivity.this, HomeAdmin.class);
//                startActivity(intent);
//            }
//        });

        Button btnSimpan = (Button)findViewById(R.id.btnSimpanKrs);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateKrsActivity.this);

                builder.setMessage("Tenan i meh disimpen ?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreateKrsActivity.this, "Rasido Simpan", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(CreateKrsActivity.this, HomeAdmin.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog dialog = builder.create(); dialog.show();
            }
        });
    }
}
