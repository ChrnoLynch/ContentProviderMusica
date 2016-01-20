package com.example.lprub.musica.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.lprub.musica.dataBase.ContratoMusic;

/**
 * Created by lprub on 16/01/2016.
 */
public class Album {
    private int id, artist_id, numsongs, year;
    private String album, album_art, album_key;


    public Album(){
        this(0,"","","",0,0,0);
    }
    public Album(int id, String album, String album_art, String album_key, int artist_id, int maxyear, int numsongs) {
        this.id = id;
        this.album = album;
        this.album_art = album_art;
        this.album_key=album_key;
        this.artist_id= artist_id;
        this.year=maxyear;
        this.numsongs=numsongs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public int getNumsongs() {
        return numsongs;
    }

    public void setNumsongs(int numsongs) {
        this.numsongs = numsongs;
    }

    public int getyear() {
        return year;
    }

    public void setyear(int maxyear) {
        this.year = maxyear;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbum_art() {
        return album_art;
    }

    public void setAlbum_art(String album_art) {
        this.album_art = album_art;
    }

    public String getAlbum_key() {
        return album_key;
    }

    public void setAlbum_key(String album_key) {
        this.album_key = album_key;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", artist_id=" + artist_id +
                ", numsongs=" + numsongs +
                ", year=" + year +
                ", album='" + album + '\'' +
                ", album_art='" + album_art + '\'' +
                ", album_key='" + album_key + '\'' +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoMusic.TablaAlbum._ID,this.id);
        cv.put(ContratoMusic.TablaAlbum.ALBUM,this.album);
        cv.put(ContratoMusic.TablaAlbum.ALBUMART,this.album_art);
        cv.put(ContratoMusic.TablaAlbum.ALBUMKEY,this.album_key);
        cv.put(ContratoMusic.TablaAlbum.ARTISTAID,this.artist_id);
        cv.put(ContratoMusic.TablaAlbum.YEAR,this.year);
        cv.put(ContratoMusic.TablaAlbum.NUMCANCIONES,this.numsongs);
        return cv;
    }

    public void setCursorFront(Cursor c){ //A partir del cursor recuperar nombre, apellido y telefono
        this.id = c.getInt(c.getColumnIndex(ContratoMusic.TablaAlbum._ID));
        this.album= c.getString(c.getColumnIndex(ContratoMusic.TablaAlbum.ALBUM));
        this.album_art = c.getString(c.getColumnIndex(ContratoMusic.TablaAlbum.ALBUMART));
        this.album_key=c.getString(c.getColumnIndex(ContratoMusic.TablaAlbum.ALBUMKEY));
        this.artist_id=c.getInt(c.getColumnIndex(ContratoMusic.TablaAlbum.ARTISTAID));
        this.year=c.getInt(c.getColumnIndex(ContratoMusic.TablaAlbum.YEAR));
        this.numsongs=c.getInt(c.getColumnIndex(ContratoMusic.TablaAlbum.NUMCANCIONES));
    }

    public void setCursorBack(Cursor c){ //A partir del cursor del Content Provider de Musica en el movil
        this.id = c.getInt(c.getColumnIndex(MediaStore.Audio.Albums._ID));
        this.album= c.getString(c.getColumnIndex(MediaStore.Audio.Albums.ALBUM));
        this.album_art = c.getString(c.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
        this.album_key=c.getString(c.getColumnIndex(MediaStore.Audio.Albums.ALBUM_KEY));
        this.artist_id=c.getInt(c.getColumnIndex("artist_id"));
        this.year=c.getInt(c.getColumnIndex(MediaStore.Audio.Albums.FIRST_YEAR));
        this.numsongs=c.getInt(c.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS));
    }
}
