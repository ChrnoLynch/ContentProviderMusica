package com.example.lprub.musica.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lprub on 12/01/2016.
 */
public class Ayudante extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="music.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        String sql="drop table if exists "
                + ContratoMusic.TablaCancion.TABLACANCION;
        db.execSQL(sql);
        String sq2="drop table if exists "
                + ContratoMusic.TablaAlbum.TABLAALBUM;
        db.execSQL(sq2);
        String sq3="drop table if exists "
                + ContratoMusic.TablaArtista.TABLAARTISTA;
        db.execSQL(sq3);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//Cuando se baja la aplicación y se crea por primera vez(no hay versión previa de la aplicación)-
        String sql;
        sql="create table "+ContratoMusic.TablaCancion.TABLACANCION+ " ("+
                ContratoMusic.TablaCancion._ID+ " integer primary key, "+
                ContratoMusic.TablaCancion.DATA+" text, "+
                ContratoMusic.TablaCancion.TITULO+" text, "+
                ContratoMusic.TablaCancion.TITULOKEY+" text, "+
                ContratoMusic.TablaCancion.DURACION+" text, "+
                ContratoMusic.TablaCancion.TRACK+" integer, "+
                ContratoMusic.TablaCancion.ARTISTAID+" integer, "+
                ContratoMusic.TablaCancion.ALBUMID+" integer)";
                db.execSQL(sql);
        String sq2;
        sq2="create table "+ContratoMusic.TablaAlbum.TABLAALBUM+ " ("+
                ContratoMusic.TablaAlbum._ID+ " integer primary key, "+
                ContratoMusic.TablaAlbum.ALBUM+" text, "+
                ContratoMusic.TablaAlbum.NUMCANCIONES+" integer, "+
                ContratoMusic.TablaAlbum.YEAR+" integer, "+
                ContratoMusic.TablaAlbum.ARTISTAID+" integer, "+
                ContratoMusic.TablaAlbum.ALBUMKEY+" text, "+
                ContratoMusic.TablaAlbum.ALBUMART+" text)";
        db.execSQL(sq2);
        String sq3;
        sq3="create table "+ContratoMusic.TablaArtista.TABLAARTISTA+ " ("+
                ContratoMusic.TablaArtista._ID+ " integer primary key, "+
                ContratoMusic.TablaArtista.ARTISTA+" text, "+
                ContratoMusic.TablaArtista.NUMALBUMS+" integer, "+
                ContratoMusic.TablaArtista.NUMTRACKS+" integer, "+
                ContratoMusic.TablaArtista.ARTISTAKEY+" text) ";
        db.execSQL(sq3);
    }
}
