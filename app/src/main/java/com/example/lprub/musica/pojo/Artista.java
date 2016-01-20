package com.example.lprub.musica.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.lprub.musica.dataBase.ContratoMusic;

/**
 * Created by lprub on 16/01/2016.
 */
public class Artista {
    private int id, number_of_albums, number_of_tracks;
    private String artist, artistkey;


    public Artista(){
        this(0,"","",0,0);

    }
    public Artista(int id, String artist, String artistkey, int number_of_albums, int number_of_tracks) {
        this.id = id;
        this.artist = artist;
        this.artistkey = artistkey;
        this.number_of_albums=number_of_albums;
        this.number_of_tracks= number_of_tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_of_albums() {
        return number_of_albums;
    }

    public void setNumber_of_albums(int number_of_albums) {
        this.number_of_albums = number_of_albums;
    }

    public int getNumber_of_tracks() {
        return number_of_tracks;
    }

    public void setNumber_of_tracks(int number_of_tracks) {
        this.number_of_tracks = number_of_tracks;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtistkey() {
        return artistkey;
    }

    public void setArtistkey(String artistkey) {
        this.artistkey = artistkey;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", number_of_albums=" + number_of_albums +
                ", number_of_tracks=" + number_of_tracks +
                ", artist='" + artist + '\'' +
                ", artistkey='" + artistkey + '\'' +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoMusic.TablaArtista._ID,this.id);
        cv.put(ContratoMusic.TablaArtista.ARTISTA,this.artist);
        cv.put(ContratoMusic.TablaArtista.ARTISTAKEY,this.artistkey);
        cv.put(ContratoMusic.TablaArtista.NUMTRACKS,this.number_of_tracks);
        cv.put(ContratoMusic.TablaArtista.NUMALBUMS,this.number_of_albums);
        return cv;
    }

    public void setCursorFront(Cursor c){ //A partir del cursor recuperar nombre, apellido y telefono
        this.id = c.getInt(c.getColumnIndex(ContratoMusic.TablaArtista._ID));
        this.artist = c.getString(c.getColumnIndex(ContratoMusic.TablaArtista.ARTISTA));
        this.artistkey = c.getString(c.getColumnIndex(ContratoMusic.TablaArtista.ARTISTAKEY));
        this.number_of_tracks=c.getInt(c.getColumnIndex(ContratoMusic.TablaArtista.NUMTRACKS));
        this.number_of_albums=c.getInt(c.getColumnIndex(ContratoMusic.TablaArtista.NUMALBUMS));
    }

    public void setCursorBack(Cursor c){ //A partir del cursor del Content Provider de Musica en el movil
        this.id = c.getInt(c.getColumnIndex(MediaStore.Audio.Artists._ID));
        this.artist = c.getString(c.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
        this.artistkey = c.getString(c.getColumnIndex(MediaStore.Audio.Artists.ARTIST_KEY));
        this.number_of_tracks=c.getInt(c.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS));
        this.number_of_albums=c.getInt(c.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS));
    }
}
