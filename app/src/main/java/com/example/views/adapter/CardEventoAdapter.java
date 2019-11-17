package com.example.views.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.model.Evento;

import java.util.List;

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
}