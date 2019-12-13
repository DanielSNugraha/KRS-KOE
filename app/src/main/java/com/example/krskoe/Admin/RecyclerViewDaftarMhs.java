package com.example.krskoe.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.krskoe.Admin.Adapter.MahasiswaAdapter;
import com.example.krskoe.Admin.Model.Mahasiswa;
import com.example.krskoe.R;

import java.util.ArrayList;

public class RecyclerViewDaftarMhs extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucreate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu1){
            Intent intent = new Intent(RecyclerViewDaftarMhs.this,CreateMhsActivity.class);
            startActivity(intent);
        }
        return  true;
    }

    private RecyclerView recyclerView;
    private MahasiswaAdapter mhsAdapter;
    private ArrayList<Mahasiswa> mhsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_daftar_mhs);
        this.setTitle("SI KRS - Hai Admin");
        tambahData();

        recyclerView = findViewById(R.id.rvMhs);
        mhsAdapter = new MahasiswaAdapter(mhsList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewDaftarMhs.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mhsAdapter);
    }

    private void tambahData(){
        mhsList = new ArrayList<>();
        mhsList.add(new Mahasiswa("72170117","Daniel","daniel.surya@si.ukdw.ac.id","Jalan Wates",R.drawable.logo));
        mhsList.add(new Mahasiswa("72170117","Daniel","daniel.surya@si.ukdw.ac.id","Jalan Wates",R.drawable.logo));
        mhsList.add(new Mahasiswa("72170117","Daniel","daniel.surya@si.ukdw.ac.id","Jalan Wates",R.drawable.logo));
    }
}
