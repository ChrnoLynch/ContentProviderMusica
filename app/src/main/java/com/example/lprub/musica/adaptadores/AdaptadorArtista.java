package com.example.lprub.musica.adaptadores;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lprub.musica.R;
import com.example.lprub.musica.dataBase.ContratoMusic;
import com.example.lprub.musica.fragmentos.FragmentoArtista;


public class AdaptadorArtista extends RecyclerView.Adapter<AdaptadorArtista.ViewHolder> {

//    private final List<DummyItem> mValues;
    private Cursor artistas;
    private final FragmentoArtista.OnListFragmentInteractionListener mListener;

    public AdaptadorArtista(Cursor artista, FragmentoArtista.OnListFragmentInteractionListener listener) {
        this.artistas = artista;
        mListener = listener;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_itemartista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.artista.moveToPosition(position);
        holder.artistaNombre.setText(holder.artista.getString(holder.artista.getColumnIndex(ContratoMusic.TablaArtista.ARTISTA)));
        holder.artistaAlbumNum.setText(holder.artista.getInt(holder.artista.getColumnIndex(ContratoMusic.TablaArtista.NUMALBUMS)) + " Albums");
        holder.artistaCancionesNum.setText(holder.artista.getInt(holder.artista.getColumnIndex(ContratoMusic.TablaArtista.NUMTRACKS))+ " Tracks");
//        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);
//
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    holder.artista.moveToPosition(position);
                    mListener.onListFragmentInteraction(holder.artista.getInt(0),3);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistas.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView artistaNombre;
        public final TextView artistaAlbumNum;
        public final TextView artistaCancionesNum;
        public Cursor artista;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            artista=artistas;
            artistaNombre = (TextView) view.findViewById(R.id.artistaNombre);
            artistaAlbumNum = (TextView) view.findViewById(R.id.artistaAlbum);
            artistaCancionesNum = (TextView) view.findViewById(R.id.artistaTracks);
        }

    }
}
