package br.com.digitalhouse.foodparty.views.adapter;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.views.home.NaoExistemEventosFragment;

import java.util.List;

public class CardEventoAdapter extends FragmentStatePagerAdapter {

    private List<Evento> modeloList;

    public CardEventoAdapter(FragmentManager fm, List<Evento> modeloList) {
        super(fm);
        this.modeloList = modeloList;
    }

    @Override
    public Fragment getItem(int position) {
        if (modeloList.size() == 0) {
            return new NaoExistemEventosFragment();
        } else {
            return modeloList.get(position).getFragment();
        }
    }

    @Override
    public int getCount() {
        return modeloList.size() == 0 ? 1 : modeloList.size();
    }

    public void atualizaViewPager(List<Evento> novaLista) {
        this.modeloList.clear();
        this.modeloList = novaLista;
        notifyDataSetChanged();
    }
}