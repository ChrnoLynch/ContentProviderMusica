package com.example.lprub.musica.pojo;

/**
 * Created by lprub on 13/01/2016.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.lprub.musica.dataBase.ContratoMusic;

public class Cancion {
    private int id, artist_id, album_id, track;
    private String _data, title, title_key;
    private long duration;

    public Cancion(){
        this(0,"","","",0,0,0,0);

    }
    public Cancion(int id, String _data, String title, String title_key,  long duration, int artist_id, int album_id, int track) {
        this.id = id;
        this._data = _data;
        this.title = title;
        this.title_key=title_key;
        this.duration= duration;
        this.artist_id=artist_id;
        this.album_id=album_id;
        this.track=track;
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

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_key() {
        return title_key;
    }

    public void setTitle_key(String title_key) {
        this.title_key = title_key;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", artist_id=" + artist_id +
                ", album_id=" + album_id +
                ", track=" + track +
                ", _data='" + _data + '\'' +
                ", title='" + title + '\'' +
                ", title_key='" + title_key + '\'' +
                ", duration=" + duration +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(ContratoMusic.TablaCancion._ID,this.id);
        cv.put(ContratoMusic.TablaCancion.DATA,this._data);
        cv.put(ContratoMusic.TablaCancion.TITULO,this.title);
        cv.put(ContratoMusic.TablaCancion.TITULOKEY,this.title_key);
        cv.put(ContratoMusic.TablaCancion.DURACION,this.duration);
        cv.put(ContratoMusic.TablaCancion.ARTISTAID,this.artist_id);
        cv.put(ContratoMusic.TablaCancion.ALBUMID,this.album_id);
        cv.put(ContratoMusic.TablaCancion.TRACK,this.track);
        return cv;
    }

    public void setCursorFront(Cursor c){ //A partir del cursor recuperar nombre, apellido y telefono
        this.id = c.getInt(c.getColumnIndex(ContratoMusic.TablaCancion._ID));
        this._data = c.getString(c.getColumnIndex(ContratoMusic.TablaCancion.DATA));
        this.title = c.getString(c.getColumnIndex(ContratoMusic.TablaCancion.TITULO));
        this.title_key=c.getString(c.getColumnIndex(ContratoMusic.TablaCancion.TITULOKEY));
        this.duration=c.getLong(c.getColumnIndex(ContratoMusic.TablaCancion.DURACION));
        this.artist_id=c.getInt(c.getColumnIndex(ContratoMusic.TablaCancion.ARTISTAID));
        this.album_id=c.getInt(c.getColumnIndex(ContratoMusic.TablaCancion.ALBUMID));
        this.track=c.getInt(c.getColumnIndex(ContratoMusic.TablaCancion.TRACK));
    }

    public void setCursorBack(Cursor c){ //A partir del cursor del Content Provider de Musica en el movil
        this.id = c.getInt(c.getColumnIndex(MediaStore.Audio.Media._ID));
        this._data = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
        this.title = c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));
        this.title_key=c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE_KEY));
        this.duration=c.getLong(c.getColumnIndex(MediaStore.Audio.Media.DURATION));
        this.artist_id=c.getInt(c.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
        this.album_id=c.getInt(c.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
        this.track=c.getInt(c.getColumnIndex(MediaStore.Audio.Media.TRACK));
    }




}
