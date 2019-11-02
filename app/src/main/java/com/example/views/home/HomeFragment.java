package com.example.views.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adapter.CardEventoAdapter;
import com.example.adapter.CardPratoAdapter;
import com.example.interfaces.ClickEvento;
import com.example.interfaces.ClickPratos;
import com.example.login.R;
import com.example.model.ModelCard;
import com.example.model.ModelCardPratosHome;
import com.example.model.ModelEvento;
import com.example.views.eventos.DetalhesDoEventoActivity;
import com.example.views.eventos.ListaEventosActivity;
import com.example.views.pratos.DetalhesDoPratoActivity;
import com.example.views.pratos.ListaDePratosActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ClickEvento, ClickPratos {
    public static final String EVENTO_KEY = "evento";
    public static final String PRATO_KEY = "pratos";

    private RecyclerView recyclerViewPratos;
    private CardPratoAdapter adapter;

    private TextView txtVerTodos;
    private TextView txtVerMais;

    private FragmentActivity contextoViewPager;
    private ViewPager viewPager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        contextoViewPager = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);

        adapter = new CardPratoAdapter(listaDePratos(), this);

        //Setando o adapter para o componente recyclerView
        recyclerViewPratos.setAdapter(adapter);

        //Definição do layout da lista utilizando a classe LayoutManager
        recyclerViewPratos.setLayoutManager(new LinearLayoutManager(getContext()));


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewPratos.setLayoutManager(layoutManager);

        //PagerView
        List<ModelCard> listaModelo = new ArrayList<>();

        listaModelo.add(new ModelCard("Noite do Churros", "10/10/2019", CardEventoFragment.novaInstancia(R.drawable.churros_card, "Noite do Churros", "10/10/2019")));
        listaModelo.add(new ModelCard("Churrasco dos migos", "12/10/2019", CardEventoFragment.novaInstancia(R.drawable.churras, "Churrasco dos migos", "12/10/20199")));
        listaModelo.add(new ModelCard("Festa do Sorvete", "03/11/2019", CardEventoFragment.novaInstancia(R.drawable.sorvete, "Festa do Sorvete", "03/11/2019")));

        FragmentManager fragManager = contextoViewPager.getSupportFragmentManager();
        CardEventoAdapter eventoAdapter = new CardEventoAdapter(fragManager, listaModelo);

        viewPager.setAdapter(eventoAdapter);
        viewPager.setOffscreenPageLimit(listaModelo.size());


        txtVerTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verTodos();
            }
        });
        txtVerMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verMais();
            }
        });

        return view;
    }

    private void initViews(View view) {
        txtVerMais = view.findViewById(R.id.txt_ver_mais);
        txtVerTodos = view.findViewById(R.id.txt_ver_todos);
        recyclerViewPratos = view.findViewById(R.id.recycler_home);
        viewPager = view.findViewById(R.id.viewPager_home);

    }


    private List<ModelCardPratosHome> listaDePratos() {
        List<ModelCardPratosHome> pratos = new ArrayList<>();

        pratos.add(new ModelCardPratosHome(R.drawable.churras, "Churras"));
        pratos.add(new ModelCardPratosHome(R.drawable.churras, "Biscoito"));
        pratos.add(new ModelCardPratosHome(R.drawable.churras, "Sorvete"));

        return pratos;
    }

    private void verTodos() {
        startActivity(new Intent(getContext(), ListaEventosActivity.class));
    }

    private void detalhesDoEvento() {
        startActivity(new Intent(getContext(), DetalhesDoEventoActivity.class));
    }

    private void verMais() {
        startActivity(new Intent(getContext(), ListaDePratosActivity.class));

    }

    private void detalhesDoPrato() {
        startActivity(new Intent(getContext(), DetalhesDoPratoActivity.class));
    }

    @Override
    public void onClick(ModelEvento evento) {
        //Envio do objeto para a tela de detalhe
        Intent intent = new Intent(getContext(), DetalhesDoEventoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(EVENTO_KEY, evento);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(ModelCardPratosHome prato) {
        //Envio do objeto para a tela de detalhe
        Intent intent = new Intent(getContext(), DetalhesDoPratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRATO_KEY, prato);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
