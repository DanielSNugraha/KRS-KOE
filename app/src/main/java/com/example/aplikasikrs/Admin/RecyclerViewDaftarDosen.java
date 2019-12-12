package com.example.aplikasikrs.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplikasikrs.Admin.Adapter.DosenAdapter;
import com.example.aplikasikrs.Admin.Model.Dosen;
import com.example.aplikasikrs.MainActivity;
import com.example.aplikasikrs.Network.DefaultResult;
import com.example.aplikasikrs.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response; //menghasilkan data JSON
import com.example.aplikasikrs.Network.GetDataService;
import com.example.aplikasikrs.Network.RetrofitClientInstance;

public class RecyclerViewDaftarDosen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DosenAdapter dosenAdapter;
    private ArrayList<Dosen> dosenList;
    ProgressDialog progressDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucreate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu1){
            Intent intent = new Intent(RecyclerViewDaftarDosen.this,CreateDosenActivity.class);
            startActivity(intent);
        }
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_daftar_dosen);
        this.setTitle("SI KRS - Hai Admin");

        recyclerView = (RecyclerView)findViewById(R.id.rvDosen);
        //tambahData();
        //addData
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<Dosen>> call = service.getDosenAll("72170117");
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                progressDialog.dismiss();

                dosenList = response.body();
                dosenAdapter = new DosenAdapter(response.body());

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewDaftarDosen.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dosenAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RecyclerViewDaftarDosen.this,"Error",Toast.LENGTH_SHORT);
            }
        });

        registerForContextMenu(recyclerView);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Dosen dosen = dosenList.get(item.getGroupId());
        if (item.getTitle()== "Ganti Data Dosen"){
            Intent intent = new Intent(RecyclerViewDaftarDosen.this, CreateDosenActivity.class);
            intent.putExtra("id_dosen",dosen.getId()); //(key, value) -> ketika manggil Dosen harus sama
            intent.putExtra("nama",dosen.getNamaDosen());
            intent.putExtra("nidn",dosen.getNidn());
            intent.putExtra("alamat",dosen.getAlamat());
            intent.putExtra("email",dosen.getEmail());
            intent.putExtra("gelar",dosen.getGelar());
            intent.putExtra("foto",dosen.getFoto());
            intent.putExtra("is_update",true);
            startActivity(intent);

        }else if(item.getTitle() == "Brusak Data Dosen"){
            //Toast.makeText(RecyclerViewDaftarDosen.this,"Hapus",Toast.LENGTH_LONG).show();

            progressDialog = new ProgressDialog(RecyclerViewDaftarDosen.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_dosen(
                    dosen.getId(), "72170117");
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerViewDaftarDosen.this,"Iso Menghapus",Toast.LENGTH_SHORT).show();
                    recreate();
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RecyclerViewDaftarDosen.this,"Raiso Hapus",Toast.LENGTH_SHORT).show();
                }
            });
        }

        return super.onContextItemSelected(item);
    }

    //Data Dummy
    /* private void tambahData(){
        dosenList = new ArrayList<>();
        dosenList.add(new Dosen("001","77777","Jong Jek Siang", "Proffesor","jjs@staff.ukdw.ac.id","Jl. Magelang",R.drawable.logo));
        dosenList.add(new Dosen("001","77777","Jong Jek Siang", "Proffesor","jjs@staff.ukdw.ac.id","Jl. Magelang",R.drawable.logo));
        dosenList.add(new Dosen("001","77777","Jong Jek Siang", "Proffesor","jjs@staff.ukdw.ac.id","Jl. Magelang",R.drawable.logo));
    }*/
}
