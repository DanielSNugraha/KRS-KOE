package com.example.aplikasikrs.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aplikasikrs.R;

public class CreateMhsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mhs);
        this.setTitle("SI KRS - Hai Admin");

//        Button btnSimpanKrs = (Button)findViewById(R.id.btnCreateMhs);
//        btnSimpanKrs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CreateMhsActivity.this, HomeAdmin.class);
//                startActivity(intent);
//            }
//        });

        Button btnSimpan = (Button)findViewById(R.id.btnCreateMhs);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateMhsActivity.this);

                builder.setMessage("Tenan i meh di simpen ?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreateMhsActivity.this, "Rasido Simpan", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(CreateMhsActivity.this, HomeAdmin.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog dialog = builder.create(); dialog.show();
            }
        });
    }
}
