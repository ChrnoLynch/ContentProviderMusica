package com.example.lprub.musica.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lprub.musica.R;
import com.example.lprub.musica.actividades.SingleAlbum;
import com.example.lprub.musica.dataBase.ContratoMusic;

import java.io.File;

/**
 * Created by Chrno on 20/01/2016.
 */
public class AdaptadorMultiAlbum extends RecyclerView.Adapter<AdaptadorMultiAlbum.ViewHolder> {
        private Cursor album;
        private Context context;

        public AdaptadorMultiAlbum(Cursor album, Context context) {
            this.album = album;
            this.context = context;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int pos) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_multialbum, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int pos) {
            viewHolder.albums.moveToPosition(pos);
            viewHolder.multiAlbumTitulo.setText(viewHolder.albums.getString(viewHolder.albums.getColumnIndex(ContratoMusic.TablaAlbum.ALBUM)));
            viewHolder.multiAlbumTracks.setText("Tracks" + viewHolder.albums.getString(viewHolder.albums.getColumnIndex(ContratoMusic.TablaAlbum.NUMCANCIONES)));
            try {
                File imgFile = new File(viewHolder.albums.getString(viewHolder.albums.getColumnIndex(ContratoMusic.TablaAlbum.ALBUMART)));
                if(imgFile.exists()){
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    viewHolder.multiAlbumArt.setImageBitmap(myBitmap);
                }
            }
            catch (RuntimeException a){};
            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.albums.moveToPosition(pos);
                    Intent i = new Intent(viewHolder.contexthol, SingleAlbum.class );
                    int id=viewHolder.albums.getInt(viewHolder.albums.getColumnIndex("_id"));
                    Bundle bundle=new Bundle();
                    bundle.putInt("id", id);
                    i.putExtras(bundle);
                    context.startActivity(i);
                    }
            });
        }

        @Override
        public int getItemCount() {
            return album.getCount();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView multiAlbumArt;
            private TextView multiAlbumTitulo;
            private TextView multiAlbumTracks;
            private Context contexthol;
            public final View mView;
            private Cursor albums;
            public ViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
                this.albums=album;
                contexthol=context;
                multiAlbumArt= (ImageView) itemView.findViewById(R.id.multiArt);
                multiAlbumTitulo = (TextView) itemView.findViewById(R.id.multiTitulo);
                multiAlbumTracks = (TextView) itemView.findViewById(R.id.multiTracks);
            }
        }
    }
