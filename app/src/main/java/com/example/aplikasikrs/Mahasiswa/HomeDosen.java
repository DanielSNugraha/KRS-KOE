package com.example.aplikasikrs.Mahasiswa;

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

import com.example.aplikasikrs.Admin.HomeAdmin;
import com.example.aplikasikrs.Admin.RecyclerViewDaftarKrs;
import com.example.aplikasikrs.MainActivity;
import com.example.aplikasikrs.R;

public class HomeDosen extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menulogout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout){
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeDosen.this);

            builder.setMessage("Tenan i meh logout?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(HomeDosen.this, "Rasido Logout", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences prefs = HomeDosen.this.getSharedPreferences("prefs_file",MODE_PRIVATE);

                            String statusLogin = prefs.getString("isLogin",null);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("isLogin" , null);
                            edit.commit();

                            Intent intent = new Intent(HomeDosen.this, MainActivity.class);
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
        setContentView(R.layout.activity_home_dosen);
        this.setTitle("SI KRS - Hai Mahasiswa");

        ImageButton btnDaftarKelas = (ImageButton)findViewById(R.id.btnLihatKelas);
        btnDaftarKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDosen.this, RecyclerViewKelas.class);
                startActivity(intent);
            }
        });

        ImageButton btnDaftarKrs = (ImageButton)findViewById(R.id.btnDaftarKrs);
        btnDaftarKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDosen.this, RecyclerViewDaftarKrs.class);
                startActivity(intent);
            }
        });

        ImageButton btnDataDiri = (ImageButton)findViewById(R.id.btnDataDiriDosen);
        btnDataDiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDosen.this, DataDiriMhsActivity.class);
                startActivity(intent);
            }
        });
    }
}
