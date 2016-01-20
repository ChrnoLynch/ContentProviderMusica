package com.example.lprub.musica.actividades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lprub.musica.adaptadores.AdaptadorTabs;
import com.example.lprub.musica.dataBase.FuenteMusic;
import com.example.lprub.musica.ProveedorMusic;
import com.example.lprub.musica.R;
import com.example.lprub.musica.fragmentos.FragmentoAlbum;
import com.example.lprub.musica.fragmentos.FragmentoArtista;
import com.example.lprub.musica.fragmentos.FragmentoCancion;
import com.example.lprub.musica.reproductor.Reproductor;


public class Principal extends AppCompatActivity implements FragmentoCancion.OnListFragmentInteractionListener,
        FragmentoArtista.OnListFragmentInteractionListener, FragmentoAlbum.OnListFragmentInteractionListener{

    private ProveedorMusic pm;
    public static SharedPreferences prefs;
    public FuenteMusic origen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new AdaptadorTabs(
                getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.musicTabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);




        prefs=getSharedPreferences("Sincro", Context.MODE_PRIVATE);
        pm = new ProveedorMusic();
        origen=new FuenteMusic();
        if(prefs.getInt("sincro",0)==0)
        origen.sincronizacion(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actualizar) {
            origen.sincronizacionBoton(this);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onListFragmentInteraction(int id, int match) {
        switch (match){

            case 2:
                Intent i = new Intent(this, SingleAlbum.class );
                Bundle bundle=new Bundle();
                System.out.println("EL ID:"+id);
                bundle.putInt("id",id);
                i.putExtras(bundle);
                startActivity(i);
                break;
            case 3:
                Intent in = new Intent(this, MultiAlbum.class );
                Bundle bundlein=new Bundle();
                bundlein.putInt("id", id);
                in.putExtras(bundlein);
                startActivity(in);
                break;
        }
        System.out.println();

    }

    @Override
    public void onListFragmentInteraction(String ruta, int match) {
        switch (match) {
            case 1:
                Reproductor.reproduce(this, ruta);
                break;
        }
    }
}
