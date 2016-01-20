package com.example.lprub.musica.adaptadores;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lprub.musica.fragmentos.FragmentoAlbum;
import com.example.lprub.musica.fragmentos.FragmentoArtista;
import com.example.lprub.musica.fragmentos.FragmentoCancion;

/**
 * Created by lprub on 17/01/2016.
 */
public class AdaptadorTabs extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] =
            new String[] { "Canciones", "Albums", "Artistas"};
    public AdaptadorTabs(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        android.support.v4.app.Fragment f = null;

        switch(position) {
            case 0:
                f = FragmentoCancion.newInstance();
                break;
            case 1:
                f = FragmentoAlbum.newInstance();
                break;
            case 2:
                f = FragmentoArtista.newInstance();
                break;
        }

        return f;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
