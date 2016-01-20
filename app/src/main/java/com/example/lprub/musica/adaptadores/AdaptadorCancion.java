package com.example.lprub.musica.adaptadores;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lprub.musica.R;

import com.example.lprub.musica.fragmentos.FragmentoCancion;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class AdaptadorCancion extends RecyclerView.Adapter<AdaptadorCancion.ViewHolder> {

    private final Cursor canciones;
    private final Cursor albumes;
    private final FragmentoCancion.OnListFragmentInteractionListener mListener;

    public AdaptadorCancion(Cursor items, Cursor items2, FragmentoCancion.OnListFragmentInteractionListener listener) {
        canciones = items;
        albumes = items2;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_itemcancion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        System.out.println("Tamaño" + holder.cancion.getCount());
        System.out.println("Posiciones" + position);
        holder.cancion.moveToPosition(position);
        System.out.println("Correcto" + holder.album.getCount());
        holder.album.moveToFirst();
        while(holder.cancion.getInt(7)!=holder.album.getInt(0)){
            holder.album.moveToNext();
        }
        try {
            File imgFile = new File(holder.album.getString(holder.album.getColumnIndex("album_art")));

            if (imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                holder.mArt.setImageBitmap(myBitmap);

            }
        }catch (RuntimeException e){
            holder.mArt.setImageResource(R.drawable.album);
        };

        holder.mtitulo.setText(holder.cancion.getString(holder.cancion.getColumnIndex("title")));
        holder.malbum.setText(holder.album.getString(holder.album.getColumnIndex("album")));
        int millis=holder.cancion.getInt(holder.cancion.getColumnIndex("duration"));

        String minutos=String.format("%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
        holder.mduracion.setText(minutos);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    holder.cancion.moveToPosition(position);
                    mListener.onListFragmentInteraction(holder.cancion.getString(holder.cancion.getColumnIndex("_data")),1);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return canciones.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mArt;
        public final TextView mtitulo;
        public final TextView malbum;
        public final TextView mduracion;
        public Cursor cancion;
        public Cursor album;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            this.cancion=canciones;
            this.album=albumes;
            mtitulo = (TextView) view.findViewById(R.id.cancionTitulo);
            malbum = (TextView) view.findViewById(R.id.cancionAlbum);
            mArt = (ImageView) view.findViewById(R.id.cancionArt);
            mduracion = (TextView) view.findViewById(R.id.duracion) ;
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "mView=" + mView +
                    ", mArt=" + mArt +
                    ", mtitulo=" + mtitulo +
                    ", mduracion=" + mduracion +
                    '}';
        }
    }
}
