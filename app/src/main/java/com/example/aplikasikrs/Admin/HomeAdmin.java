package com.example.aplikasikrs.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplikasikrs.Mahasiswa.HomeDosen;
import com.example.aplikasikrs.MainActivity;
import com.example.aplikasikrs.R;

public class HomeAdmin extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menulogout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout){
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeAdmin.this);

            builder.setMessage("Tenan i meh di simpen ?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(HomeAdmin.this, "Rasido Logout", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences prefs = HomeAdmin.this.getSharedPreferences("prefs_file",MODE_PRIVATE);

                            String statusLogin = prefs.getString("isLogin",null);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("isLogin" , null);
                            edit.commit();

                            Intent intent = new Intent(HomeAdmin.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });

            AlertDialog dialog = builder.create(); dialog.show();
        }
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        this.setTitle("SI KRS - Hai Admin");

        ImageButton btnDaftarDosen = (ImageButton)findViewById(R.id.btnDaftarDosen);
        btnDaftarDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, RecyclerViewDaftarDosen.class);
                startActivity(intent);
            }
        });

        ImageButton btnDaftarMhs = (ImageButton)findViewById(R.id.btnDaftarMhs);
        btnDaftarMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, RecyclerViewDaftarMhs.class);
                startActivity(intent);
            }
        });

        ImageButton btnDaftarMatkul = (ImageButton)findViewById(R.id.btnDaftarMatkul);
        btnDaftarMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, RecyclerViewDaftarMatkul.class);
                startActivity(intent);
            }
        });

        ImageButton btnDaftarKrs = (ImageButton)findViewById(R.id.btnKelolaKrs);
        btnDaftarKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, RecyclerViewDaftarKrs.class);
                startActivity(intent);
            }
        });

        ImageButton btnDataDiri = (ImageButton)findViewById(R.id.iBtnDataDiriAdmin);
        btnDataDiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdmin.this, CreateDosenActivity.class);
                startActivity(intent);
            }
        });
    }
}
