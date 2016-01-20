package com.example.lprub.musica.actividades;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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
import com.example.lprub.musica.adaptadores.AdaptadorSingleAlbum;
import com.example.lprub.musica.dataBase.ContratoMusic;

import java.io.File;
import java.util.ArrayList;

public class SingleAlbum extends AppCompatActivity {

    private RecyclerView recView;
    private Cursor albumCanciones;
    private Cursor album;
    private Cursor artistas;
    private int id;
    private ImageView albumArt;
    private TextView titulo;
    private TextView artista;
    private TextView año;
    private TextView track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_album);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle= getIntent().getExtras();
        id=bundle.getInt("id");


        albumArt= (ImageView) this.findViewById(R.id.singleAlbumArt);
        titulo= (TextView) this.findViewById(R.id.singleAlbumTitulo);
        artista= (TextView) this.findViewById(R.id.singleAlbumArtista);
        año= (TextView) this.findViewById(R.id.singleAlbumAño);
        track=(TextView) this.findViewById(R.id.singleAlbumNumCanciones);

        albumCanciones=this.getContentResolver().query(ContratoMusic.TablaCancion.CONTENT_URI,null,ContratoMusic.TablaCancion.ALBUMID+ "='" + id + "'",null,null);
        album=this.getContentResolver().query(ContratoMusic.TablaAlbum.CONTENT_URI, null, ContratoMusic.TablaAlbum._ID + "='" + id + "'", null, null);
        while (album.moveToNext()) {
            for (int i = 0; i < album.getColumnCount(); i++) {
                Log.v("log", i + " " + album.getColumnName(i));
                Log.v("log", i+" "+album.getString(i));
            }
        }
        album.moveToFirst();

        int idartista=album.getInt(album.getColumnIndex("artist_id"));
        artistas=this.getContentResolver().query(ContratoMusic.TablaArtista.CONTENT_URI, null, ContratoMusic.TablaArtista._ID + "='" + idartista + "'", null, null);

        artistas.moveToFirst();

        titulo.setText(album.getString(album.getColumnIndex(ContratoMusic.TablaAlbum.ALBUM)));
        artista.setText(artistas.getString(artistas.getColumnIndex(ContratoMusic.TablaArtista.ARTISTA)));
        año.setText("Año: "+album.getString(album.getColumnIndex(ContratoMusic.TablaAlbum.YEAR)));
        track.setText("Tracks:"+album.getString(album.getColumnIndex(ContratoMusic.TablaAlbum.NUMCANCIONES)));
        try {
            File imgFile = new File(album.getString(album.getColumnIndex(ContratoMusic.TablaAlbum.ALBUMART)));
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                albumArt.setImageBitmap(myBitmap);
            }
        }
        catch (RuntimeException a){};

        recView = (RecyclerView) findViewById(R.id.albumrecycler);
        recView.setHasFixedSize(true);
        final AdaptadorSingleAlbum adaptador = new AdaptadorSingleAlbum(albumCanciones, this);
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
    }




}
