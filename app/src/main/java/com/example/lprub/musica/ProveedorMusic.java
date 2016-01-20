package com.example.lprub.musica;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lprub.musica.dataBase.Ayudante;
import com.example.lprub.musica.dataBase.ContratoMusic;

/**
 * Created by lprub on 13/01/2016.
 */
public class ProveedorMusic extends ContentProvider {

    //La Uri es cm una URL. LLegamos a un mismo sitio. UriMatcher establece si es un 1 haz una cosa, si es 2 haz otra cosa.
    public static final UriMatcher uriSwitch;
    public static final int CANCIONES = 1;
    public static final int IDCANCION = 2;

    public static final int ALBUM = 3;
    public static final int IDALBUM = 4;


    public static final int ARTISTA = 5;
    public static final int IDARTISTA = 6;





    static{
        uriSwitch = new UriMatcher(UriMatcher.NO_MATCH);
        uriSwitch.addURI(ContratoMusic.TablaCancion.AUTHORITY, ContratoMusic.TablaCancion.TABLACANCION, CANCIONES);//Le damos la instrucción de qué hacer a la URI
        uriSwitch.addURI(ContratoMusic.TablaAlbum.AUTHORITY, ContratoMusic.TablaAlbum.TABLAALBUM, ALBUM);
        uriSwitch.addURI(ContratoMusic.TablaArtista.AUTHORITY, ContratoMusic.TablaArtista.TABLAARTISTA, ARTISTA);
        uriSwitch.addURI(ContratoMusic.TablaCancion.AUTHORITY, ContratoMusic.TablaCancion.TABLACANCION + "/#", IDCANCION);
        uriSwitch.addURI(ContratoMusic.TablaAlbum.AUTHORITY, ContratoMusic.TablaAlbum.TABLAALBUM + "/#", IDALBUM);
        uriSwitch.addURI(ContratoMusic.TablaArtista.AUTHORITY, ContratoMusic.TablaArtista.TABLAARTISTA + "/#", IDARTISTA);

    }

    private Ayudante abd;

    @Override
    public boolean onCreate() {
        abd = new Ayudante(getContext());
        abd.getReadableDatabase();
        return true;
    }



    @Nullable
    @Override
    public String getType(Uri uri) {//Devuelve el tipo mime que corresponde a la uri con que se ha llamado
        switch (uriSwitch.match(uri)){
            case CANCIONES:
                return ContratoMusic.TablaCancion.MJLTIPLE_MIME;
            case IDCANCION:
                return ContratoMusic.TablaCancion.SINGLE_MIME;
            case ALBUM:
                return ContratoMusic.TablaAlbum.MJLTIPLE_MIME;
            case IDALBUM:
                return ContratoMusic.TablaAlbum.SINGLE_MIME;
            case ARTISTA:
                return ContratoMusic.TablaArtista.MJLTIPLE_MIME;
            case IDARTISTA:
                return ContratoMusic.TablaArtista.SINGLE_MIME;
            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida " + uri);
        }
    }

    //METODO INSERT
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Comprobar que la uri utilizada para hacer la insercion es correcta
        if (uriSwitch.match(uri) != CANCIONES && uriSwitch.match(uri) != ALBUM && uriSwitch.match(uri) != ARTISTA
                && uriSwitch.match(uri) != IDCANCION && uriSwitch.match(uri) != IDALBUM && uriSwitch.match(uri) != IDARTISTA) {
            throw new IllegalArgumentException("URI desconocida : " + uri + uriSwitch.match(uri) );//SI no es correcta la Uri
        }

        //Si el ContentValues es nulo, crea un ContentValues
        ContentValues contentValues;
        if (values == null) {
            throw new IllegalArgumentException("Resultados null ");
        }
        //Validar
        SQLiteDatabase db = abd.getWritableDatabase();//Conectamos a la base de datos en modo escritura
        // Inserción de nueva fila
        switch (uriSwitch.match(uri)) {
            case CANCIONES:
            long rowId = db.insert(ContratoMusic.TablaCancion.TABLACANCION, null, values);
            if (rowId > 0) {
                //Si se ha insertado el elemento correctamente, entonces devolvemos la uri del elemento que se acaba de insertar
                Uri uri_actividad = ContentUris.withAppendedId(ContratoMusic.TablaCancion.CONTENT_URI, rowId);
                getContext().getContentResolver().notifyChange(uri_actividad, null);
                return uri_actividad;
            }
            throw new SQLException("Error al insertar fila en : " + uri);

            case ALBUM:

                long rowId2 = db.insert(ContratoMusic.TablaAlbum.TABLAALBUM, null, values);
                if (rowId2 > 0) {
                    //Si se ha insertado el elemento correctamente, entonces devolvemos la uri del elemento que se acaba de insertar
                    Uri uri_actividad = ContentUris.withAppendedId(ContratoMusic.TablaCancion.CONTENT_URI, rowId2);
                    getContext().getContentResolver().notifyChange(uri_actividad, null);
                    return uri_actividad;
                }
                throw new SQLException("Error al insertar fila en : " + uri);

            case ARTISTA:
                long rowId3 = db.insert(ContratoMusic.TablaArtista.TABLAARTISTA, null, values);
                if (rowId3 > 0) {
                    //Si se ha insertado el elemento correctamente, entonces devolvemos la uri del elemento que se acaba de insertar
                    Uri uri_actividad = ContentUris.withAppendedId(ContratoMusic.TablaCancion.CONTENT_URI, rowId3);
                    getContext().getContentResolver().notifyChange(uri_actividad, null);
                    return uri_actividad;
                }
                throw new SQLException("Error al insertar fila en : " + uri);
                
                default: return null;
        }
    }

    //METODO BORRAR
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();// Vuelve a abrir la base de datos para conectar con ella en modo escritura
        int match = uriSwitch.match(uri);//Obtengo la uri
        int affected;
        switch (match) {
            case CANCIONES: //Muchas canciones
                affected = db.delete(ContratoMusic.TablaCancion.TABLACANCION, selection, selectionArgs);
                break;
            case ALBUM:
                affected = db.delete(ContratoMusic.TablaAlbum.TABLAALBUM, selection, selectionArgs);
                break;
            case ARTISTA:
                affected = db.delete(ContratoMusic.TablaArtista.TABLAARTISTA, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Elemento actividad desconocido: " +
                        uri);
        }
        // Notificar cambio asociado a la urigetContext().getContentResolver().notifyChange(uri, null);
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;//Devuelve el numero de filas borradas
         }



    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();
        int affected;
        switch (uriSwitch.match(uri)) {
            case CANCIONES:
                affected = db.update(ContratoMusic.TablaCancion.TABLACANCION, values, selection, selectionArgs);
                break;
            case IDCANCION:
                String idActividad = uri.getPathSegments().get(1);
                affected = db.update(ContratoMusic.TablaCancion.TABLACANCION, values,
                        ContratoMusic.TablaCancion._ID + "= ?", new String[]{idActividad});
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // Obtener base de datos
        SQLiteDatabase db = abd.getReadableDatabase();
        // Comparar Uri
        int match = uriSwitch.match(uri);

        Cursor c;

        switch (match) {
            case CANCIONES:
                // Consultando todos los registros
                c = db.query(ContratoMusic.TablaCancion.TABLACANCION, projection, selection, selectionArgs, null, null, sortOrder);
                Log.v("Camino", "nos hemos metido por el camino del case CLIENTE_ID");
                break;
            case IDCANCION:
                // Consultando un solo registro basado en el Id del Uri
                long idActividad = ContentUris.parseId(uri);
                c = db.query(ContratoMusic.TablaCancion.TABLACANCION, projection, ContratoMusic.TablaCancion._ID + "= ? " , new String [] {idActividad + ""},
                        null, null, sortOrder);
                break;
            case ALBUM:
                // Consultando todos los registros
                if (sortOrder!="join"){
                c = db.query(ContratoMusic.TablaAlbum.TABLAALBUM, projection, selection, selectionArgs, null, null, sortOrder);
                }else{
                    String s="Select * from album LEFT JOIN artista ON (album.artist_id = artista._id) order by album";
                    c=db.rawQuery(s,null);
                }
                Log.v("Camino", "nos hemos metido por el camino del case CLIENTE_ID");
                break;
            case IDALBUM:
                // Consultando un solo registro basado en el Id del Uri
                long idActividad1 = ContentUris.parseId(uri);
                c = db.query(ContratoMusic.TablaAlbum.TABLAALBUM, projection, ContratoMusic.TablaCancion._ID + "= ? " , new String [] {idActividad1 + ""},
                        null, null, sortOrder);
                break;
            case ARTISTA:
                // Consultando todos los registros
                c = db.query(ContratoMusic.TablaArtista.TABLAARTISTA, projection, selection, selectionArgs, null, null, sortOrder);
                Log.v("Camino", "nos hemos metido por el camino del case CLIENTE_ID");
                break;
            case IDARTISTA:
                // Consultando un solo registro basado en el Id del Uri
                long idActividad2 = ContentUris.parseId(uri);
                c = db.query(ContratoMusic.TablaArtista.TABLAARTISTA, projection, ContratoMusic.TablaCancion._ID + "= ? " , new String [] {idActividad2 + ""},
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        switch (match) {
            case CANCIONES: case IDCANCION:
                c.setNotificationUri(getContext().getContentResolver(),ContratoMusic.TablaCancion.CONTENT_URI);
                    break;
                case ALBUM: case IDALBUM:
                    c.setNotificationUri(getContext().getContentResolver(),ContratoMusic.TablaAlbum.CONTENT_URI);
                    break;
                case ARTISTA: case IDARTISTA:
                    c.setNotificationUri(getContext().getContentResolver(),ContratoMusic.TablaArtista.CONTENT_URI);
                    break;
            }
        return c;
    }
}
