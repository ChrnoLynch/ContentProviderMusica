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

//import com.example.lprub.musica.fragmentos.ItemFragment.OnListFragmentInteractionListener;
import com.example.lprub.musica.R;
import com.example.lprub.musica.dataBase.ContratoMusic;
import com.example.lprub.musica.fragmentos.FragmentoAlbum;
import com.example.lprub.musica.pojo.Album;

import java.io.File;
import java.util.NoSuchElementException;


public class AdaptadorAlbum extends RecyclerView.Adapter<AdaptadorAlbum.ViewHolder>{

    private Cursor albumes;
    private final FragmentoAlbum.OnListFragmentInteractionListener mListener;


    public AdaptadorAlbum(Cursor album, FragmentoAlbum.OnListFragmentInteractionListener listener) {
        albumes = album;
        mListener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_itemalbum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.album.moveToPosition(position);
        holder.albumTitulo.setText(holder.album.getString(holder.album.getColumnIndex(ContratoMusic.TablaAlbum.ALBUM)));
        holder.albumArtista.setText(holder.album.getString(holder.album.getColumnIndex(ContratoMusic.TablaArtista.ARTISTA)));
        holder.albumTracks.setText(holder.album.getString(holder.album.getColumnIndex(ContratoMusic.TablaAlbum.NUMCANCIONES))+" tracks");
        try {
            File imgFile = new File(holder.album.getString(holder.album.getColumnIndex(ContratoMusic.TablaAlbum.ALBUMART)));
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                holder.albumArt.setImageBitmap(myBitmap);
            }
        }
        catch (RuntimeException a){};

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    holder.album.moveToPosition(position);
                    mListener.onListFragmentInteraction(holder.album.getInt(0),2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumes.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView albumTitulo;
        public final TextView albumArtista;
        public final TextView albumTracks;
        public final ImageView albumArt;
        public Cursor album;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            album=albumes;
            albumArt= (ImageView) view.findViewById(R.id.albumArt);
            albumTitulo = (TextView) view.findViewById(R.id.albumTitulo);
            albumArtista = (TextView) view.findViewById(R.id.albumArtista);
            albumTracks = (TextView) view.findViewById(R.id.albumTracks);
        }


    }
}
