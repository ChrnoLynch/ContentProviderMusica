package com.example.lprub.musica.actividades;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lprub.musica.R;
import com.example.lprub.musica.adaptadores.AdaptadorMultiAlbum;
import com.example.lprub.musica.adaptadores.AdaptadorSingleAlbum;
import com.example.lprub.musica.dataBase.ContratoMusic;

import java.io.File;

public class MultiAlbum extends AppCompatActivity {

    private RecyclerView recView;
    private Cursor albumCanciones;
    private Cursor album;
    private Cursor artistas;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_album);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle= getIntent().getExtras();
        id=bundle.getInt("id");


        album=this.getContentResolver().query(ContratoMusic.TablaAlbum.CONTENT_URI, null, ContratoMusic.TablaAlbum.ARTISTAID + "='" + id + "'", null, null);

        recView =(RecyclerView) findViewById(R.id.recMultiAlbum);
        recView.setHasFixedSize(true);
        final AdaptadorMultiAlbum adaptador = new AdaptadorMultiAlbum(album,this);
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
    }
}


