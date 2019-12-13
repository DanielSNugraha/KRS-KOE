package com.example.krskoe.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.krskoe.Network.DefaultResult;
import com.example.krskoe.Network.GetDataService;
import com.example.krskoe.Network.RetrofitClientInstance;
import com.example.krskoe.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CreateMhsActivity extends AppCompatActivity {

    EditText edtNama, edtNim ,edtAlamat,edtEmail;
    Button btnSave, btnBrowse;
    ImageView imgFoto;
    private Boolean isUpdate = false;
    String idMhs ="", Img;
    GetDataService service;
    ProgressDialog progressDialog;
    Bitmap bitmap;
    static final int IMG_REQ = 58;
    static final int FILE_ACCESS_REQUEST_CODE = 58;
    byte[] imagByte;



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

        /*Button btnSimpan = (Button)findViewById(R.id.btnCreateMhs);
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

                AlertDialog dialog = builder.create(); dialog.show();*/
        if (ActivityCompat.checkSelfPermission(CreateMhsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CreateMhsActivity.this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, FILE_ACCESS_REQUEST_CODE);
        }

        edtNama = (EditText)findViewById(R.id.edtNamaMhs);
        edtNim = (EditText)findViewById(R.id.edtNimMhs);
        edtAlamat = (EditText)findViewById(R.id.edtAlamatMhs);
        edtEmail = (EditText)findViewById(R.id.edtEmailMhs);
        imgFoto = (ImageView)findViewById(R.id.imgFotoMahasiswaPre);
        btnBrowse = (Button)findViewById(R.id.btnBrowseMhs);

        checkUpdate();

        btnSave = (Button)findViewById(R.id.btnCreateMhs);
        if(isUpdate){
            btnSave.setText("Update");
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isValid = true;

                //Validation

                if(edtNama.getText().toString().matches("")){
                    edtNama.setError("Silahkan mengisi Nama Mahasiswa");
                    isValid = false;
                }

                if(edtNim.getText().toString().matches("")){
                    edtNim.setError("Silahkan mengisi NIM Mahasiswa");
                    isValid = false;
                }

                if(edtAlamat.getText().toString().matches("")){
                    edtAlamat.setError("Silahkan mengisi Alamat Mahasiswa");
                    isValid = false;
                }

                if(edtEmail.getText().toString().matches("")){
                    edtEmail.setError("Silahkan mengisi Email Mahasiswa");
                    isValid = false;
                }

                if(!isUpdate){
                    if(isValid){
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateMhsActivity.this);

                        builder.setMessage("Apakah anda yakin untuk menyimpan?")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(CreateMhsActivity.this, "Batal Simpan", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestInsertMhs();
                                    }
                                });

                        AlertDialog dialog = builder.create(); dialog.show();
                    } else {
                        Toast.makeText(CreateMhsActivity.this,"Silahkan Isi Data",Toast.LENGTH_LONG).show();
                    }
                }else if(isUpdate){
                    if (isValid) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateMhsActivity.this);

                        builder.setMessage("Apakah anda yakin untuk menyimpan?")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(CreateMhsActivity.this, "Batal Simpan", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestUpdateMhs();

                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else {
                        Toast.makeText(CreateMhsActivity.this,"Silahkan Isi Data",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();

            }
        });
    }

    private void requestInsertMhs(){
        String gambar = imageToString();
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        progressDialog =  ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        Call<DefaultResult> call =  service.insert_mhs(
                edtNama.getText().toString(),
                edtNim.getText().toString(),
                edtAlamat.getText().toString(),
                edtEmail.getText().toString(),
                gambar,
                "72170177");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateMhsActivity.this,"Berhasil Insert",Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(CreateMhsActivity.this, RecyclerViewDaftarMhs.class);
                startActivity(refresh);
                finish();

            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateMhsActivity.this,"Error cuy",Toast.LENGTH_SHORT);
            }
        });
    }

    private void requestUpdateMhs(){
        String gambar = imageToString();
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        progressDialog = ProgressDialog.show(CreateMhsActivity.this, null, "Harap Tunggu...", true, false);

        Call<DefaultResult> call = service.update_mhs(idMhs, edtNama.getText().toString(), edtNim.getText().toString(),
                edtAlamat.getText().toString(), edtEmail.getText().toString(), gambar,
                "72170177");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CreateMhsActivity.this, "Berhasil Update", Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(CreateMhsActivity.this, RecyclerViewDaftarMhs.class);
                startActivity(refresh);
                finish();

            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CreateMhsActivity.this, "Error cuy", Toast.LENGTH_SHORT);
            }
        });
    }

    void checkUpdate(){
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return;
        }

        isUpdate = extras.getBoolean("is_update");
        idMhs = extras.getString("id_mhs");
        edtNama.setText(extras.getString("nama_mhs"));
        edtNim.setText(extras.getString("nim"));
        edtAlamat.setText(extras.getString("alamat_mhs"));
        edtEmail.setText(extras.getString("email_mhs"));
        //edtFoto.setText(extras.getString("foto"));

        /*imagByte = Base64.decode(extras.getString("foto"), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imagByte, 0, imagByte.length);
        imgFoto.setImageBitmap(decodedImage);*/
        Picasso.with(CreateMhsActivity.this)
                .load("https://kpsi.fti.ukdw.ac.id/progmob/" + extras.getString("foto_mhs"))
                .into(imgFoto);



    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        //Setimage jpeg
        String[] mimeTypes = {"image/jpeg"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQ && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgFoto.setImageBitmap(bitmap);
                imgFoto.setVisibility(View.VISIBLE);
                btnBrowse.setEnabled(true);
                btnSave.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imagByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imagByte, Base64.DEFAULT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case FILE_ACCESS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED){
                    //
                }
                break;
        }
    }
}
