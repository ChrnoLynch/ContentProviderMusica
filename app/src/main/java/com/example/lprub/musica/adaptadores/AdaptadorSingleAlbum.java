package com.example.lprub.musica.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lprub.musica.R;
import com.example.lprub.musica.actividades.SingleAlbum;
import com.example.lprub.musica.dataBase.ContratoMusic;
import com.example.lprub.musica.reproductor.Reproductor;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by lprub on 19/01/2016.
 */
public class AdaptadorSingleAlbum extends RecyclerView.Adapter<AdaptadorSingleAlbum.ViewHolder>  {
    private Cursor albumCanciones;
    private Context context;
    public AdaptadorSingleAlbum(Cursor albumCanciones, Context context) {
        this.albumCanciones = albumCanciones;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int pos) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_singlealbum, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int pos) {
        viewHolder.albumCancion.moveToPosition(pos);
        viewHolder.singleAlbumTitulo.setText(viewHolder.albumCancion.getString(viewHolder.albumCancion.getColumnIndex(ContratoMusic.TablaCancion.TITULO)));
        int millis=viewHolder.albumCancion.getInt(viewHolder.albumCancion.getColumnIndex("duration"));
        String minutos=String.format("%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
        viewHolder.singleAlbumDuracion.setText(minutos);
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.albumCancion.moveToPosition(pos);
                Intent i = new Intent(viewHolder.contexthol, SingleAlbum.class);
                String ruta = viewHolder.albumCancion.getString(viewHolder.albumCancion.getColumnIndex("_data"));
                Reproductor.reproduce(context,ruta);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumCanciones.getCount();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView singleAlbumTitulo;
        private TextView singleAlbumDuracion;
        private Cursor albumCancion;
        private Context contexthol;
        private View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            this.albumCancion=albumCanciones;
            contexthol=context;
            singleAlbumTitulo = (TextView) itemView.findViewById(R.id.singleAlbumTitulo);
            singleAlbumDuracion = (TextView) itemView.findViewById(R.id.singleAlbumDuracion);
        }
    }
}

