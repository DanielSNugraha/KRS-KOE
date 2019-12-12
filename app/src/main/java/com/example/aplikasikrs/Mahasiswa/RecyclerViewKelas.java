package com.example.aplikasikrs.Mahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aplikasikrs.Mahasiswa.Adapter.KelasAdapter;
import com.example.aplikasikrs.Mahasiswa.Model.Kelas;
import com.example.aplikasikrs.R;

import java.util.ArrayList;

public class RecyclerViewKelas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KelasAdapter kelasAdapter;
    private ArrayList<Kelas> kelasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_kelas);

        this.setTitle("SI KRS - Hai Mahasiswa");

        tambahData();

        recyclerView = findViewById(R.id.rvKelas);
        kelasAdapter = new KelasAdapter(kelasList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewKelas.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(kelasAdapter);
    }

    public void tambahData(){
        kelasList = new ArrayList<>();
        kelasList.add(new Kelas("SI1","DDP","3","Minggu","K-Ton","1"));
        kelasList.add(new Kelas("SI2","KTI","3","Minggu","Will-Y","2"));
        kelasList.add(new Kelas("SI3","Progmob","3","Minggu","Arch-Go","3"));
        kelasList.add(new Kelas("SI4","PSI","3","Minggu","Oemie","4"));
    }
}
