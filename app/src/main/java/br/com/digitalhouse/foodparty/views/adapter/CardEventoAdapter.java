package br.com.digitalhouse.foodparty.views.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.views.home.NaoExistemEventosFragment;

public class CardEventoAdapter extends FragmentStatePagerAdapter {

    private List<Evento> modeloList;

    public CardEventoAdapter(FragmentManager fm, List<Evento> modeloList) {
        super(fm);
        this.modeloList = modeloList;
    }

    @Override
    public Fragment getItem(int position) {
        return modeloList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return modeloList.size();
    }

    public void atualizaViewPager(List<Evento> novaLista) {
        this.modeloList = novaLista;
        notifyDataSetChanged();
    }
}