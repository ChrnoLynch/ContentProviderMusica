package com.example.lprub.musica.fragmentos;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lprub.musica.R;
import com.example.lprub.musica.adaptadores.AdaptadorCancion;
import com.example.lprub.musica.dataBase.ContratoMusic;


/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FragmentoCancion extends Fragment{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private AdaptadorCancion adaptadorCancion;
    private Context superContext;
    private Cursor canciones;
    private Cursor album;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentoCancion() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FragmentoCancion newInstance() {
        FragmentoCancion fragment = new FragmentoCancion();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canciones=getContext().getContentResolver().query(ContratoMusic.TablaCancion.CONTENT_URI, null, null, null, "title");
        album=getContext().getContentResolver().query(ContratoMusic.TablaAlbum.CONTENT_URI, null, null, null,null);
        mColumnCount=canciones.getCount();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itemcancion_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setLayoutManager(
                    new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

            adaptadorCancion=new AdaptadorCancion(canciones, album, mListener);
            recyclerView.setAdapter(adaptadorCancion);
//            while (canciones.moveToNext()) {
//                for (int i = 0; i < canciones.getColumnCount(); i++) {
//                    Log.v("Canciones", i + " " + canciones.getColumnName(i));
//                    Log.v("Canciones", i+" "+canciones.getString(i));
//                }
//            }





        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        superContext=context;
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    @Override
//    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        switch (id) {
//            case 1:
//                return new CursorLoader(getActivity(), ContratoMusic.TablaCancion.CONTENT_URI, null, null, null, null);
//            case 2:
//                return new CursorLoader(getActivity(), ContratoMusic.TablaAlbum.CONTENT_URI, null, null, null, null);
//            case 3:
//                return new CursorLoader(getActivity(), ContratoMusic.TablaArtista.CONTENT_URI, null, null, null, null);
//            default:
//                return null;
//        }
//    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String ruta, int match);
    }


}
