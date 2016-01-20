package com.example.lprub.musica.dataBase;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by lprub on 13/01/2016.
 */
public class ContratoMusic {

    private ContratoMusic() {
    }

    public static abstract class TablaCancion implements BaseColumns {

        public static final String TABLACANCION = "Cancion";
        public static final String DATA = "_data";
        public static final String TITULO = "title";
        public static final String TITULOKEY = "title_key";
        public static final String DURACION = "duration";
        public static final String ARTISTAID = "artist_id";
        public static final String ALBUMID = "album_id";
        public static final String TRACK = "track";


        //La autoridad es la cadena q identifica a qué contentprovider se llama
        public final static String AUTHORITY = "com.example.lprub.musica.ProveedorMusic";
        //Definir como llego a la tabla cliente (a q tabla estoy llegando)
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLACANCION);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLACANCION;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLACANCION;
    }

    public static abstract class TablaAlbum implements BaseColumns {
        public static final String  TABLAALBUM = "Album";

        public static final String  ALBUM = "album";
        public static final String  ARTISTAID = "artist_id";
        public static final String  NUMCANCIONES = "numsongs";
        public static final String  ALBUMART = "album_art";
        public static final String  ALBUMKEY = "album_key";

        public static final String  YEAR = "maxyear";


        public final static String AUTHORITY = "com.example.lprub.musica.ProveedorMusic";

        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLAALBUM);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLAALBUM;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLAALBUM;
    }

    public static abstract class TablaArtista implements BaseColumns {
        public static final String  TABLAARTISTA= "Artista";
        public static final String  ARTISTA = "artist";
        public static final String  NUMALBUMS = "number_of_albums";
        public static final String  NUMTRACKS = "number_of_tracks";
        public static final String  ARTISTAKEY = "artist_key";

        public final static String AUTHORITY = "com.example.lprub.musica.ProveedorMusic";

        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLAARTISTA);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLAARTISTA;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLAARTISTA;

    }

}
