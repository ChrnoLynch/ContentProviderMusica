package com.example.lprub.musica.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.lprub.musica.actividades.Principal;
import com.example.lprub.musica.pojo.Album;
import com.example.lprub.musica.pojo.Artista;
import com.example.lprub.musica.pojo.Cancion;

/**
 * Created by lprub on 13/01/2016.
 */

/**
 * OBJETO SUPER CHULO
 */
public class FuenteMusic  {

    public void sincronizacion(Context context){
        sacarMusica(context);
        sacarAlbum(context);
        sacarArtista(context);
        SharedPreferences.Editor editor = Principal.prefs.edit();
        editor.putInt("sincro", 1);
        editor.commit();
    }

    public void sincronizacionBoton(Context context){
        context.getContentResolver().delete(ContratoMusic.TablaCancion.CONTENT_URI,null,null);
        context.getContentResolver().delete(ContratoMusic.TablaAlbum.CONTENT_URI,null,null);
        context.getContentResolver().delete(ContratoMusic.TablaArtista.CONTENT_URI, null, null);
        sacarMusica(context);
        sacarAlbum(context);
        sacarArtista(context);
    }

    private void sacarMusica(Context context) {
        Cursor cur = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Audio.Media.IS_MUSIC+ " = 1", null, null);
        while (cur.moveToNext()) {
            Cancion cancion = new Cancion();
            cancion.setCursorBack(cur);
            ContentValues cv = cancion.getContentValues();
            context.getContentResolver().insert(ContratoMusic.TablaCancion.CONTENT_URI, cv);
        }
        cur.close();
    }

    private void sacarAlbum(Context context) {
        Cursor cur = context.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cur.moveToNext()) {
            Album album=new Album();
            album.setCursorBack(cur);
            ContentValues cv= album.getContentValues();
            context.getContentResolver().insert(ContratoMusic.TablaAlbum.CONTENT_URI, cv);
        }
        cur.close();
    }

    private void sacarArtista(Context context) {

        Cursor cur = context.getContentResolver().query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cur.moveToNext()) {
            Artista artista= new Artista();
            artista.setCursorBack(cur);
            ContentValues cv = artista.getContentValues();
            context.getContentResolver().insert(ContratoMusic.TablaArtista.CONTENT_URI, cv);
        }
        cur.close();
    }
}
