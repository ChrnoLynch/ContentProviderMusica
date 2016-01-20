package com.example.lprub.musica.reproductor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Chrno on 20/01/2016.
 */
public class Reproductor {
    public static void reproduce(Context c, String ruta){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        Uri data = Uri.parse("file://" + ruta);
        intent.setDataAndType(data, "audio/mp3");
        c.startActivity(intent);

    }
}
