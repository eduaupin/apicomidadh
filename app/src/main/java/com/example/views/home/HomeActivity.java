package com.example.views.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.adapter.CardEventoAdapter;
import com.example.adapter.CardPratoAdapter;
import com.example.login.R;
import com.example.model.ModelCard;
import com.example.model.ModelCardPratosHome;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPratos;
    private CardPratoAdapter adapter;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu_black_24dp));
        toolbar.setTitle(" ");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(toolbar);

        recyclerViewPratos = findViewById(R.id.recycler_home);

        adapter = new CardPratoAdapter(listaDePratos());

        //Setando o adapter para o componente recyclerView
        recyclerViewPratos.setAdapter(adapter);

        //Definição do layout da lista utilizando a classe LayoutManager
        recyclerViewPratos.setLayoutManager(new LinearLayoutManager(this));



        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewPratos.setLayoutManager(layoutManager);



        //PagerView
        List<ModelCard> listaModelo = new ArrayList<>();

        ViewPager viewPager = findViewById(R.id.viewPager_home);

        listaModelo.add(new ModelCard("Noite do Churros", "10/10/2019", CardEventoFragment.novaInstancia(R.drawable.churros,"Noite do Churros","10/10/2019")));
        listaModelo.add(new ModelCard("Churrasco dos migos", "12/10/2019", CardEventoFragment.novaInstancia(R.drawable.churras,"Churrasco dos migos", "12/10/20199")));
        listaModelo.add(new ModelCard("Festa do Sorvete", "03/11/2019", CardEventoFragment.novaInstancia(R.drawable.sorvete,"Festa do Sorvete", "03/11/2019")));




        CardEventoAdapter adapter = new CardEventoAdapter(getSupportFragmentManager(), listaModelo);


        viewPager.setAdapter(adapter);



        viewPager.setOffscreenPageLimit(listaModelo.size());

       // tabLayout.setupWithViewPager(viewPager);

    }


    private List<ModelCardPratosHome> listaDePratos(){
        List<ModelCardPratosHome> pratos = new ArrayList<>();


        pratos.add(new ModelCardPratosHome("Churras"));
        pratos.add(new ModelCardPratosHome("Biscoito"));
        pratos.add(new ModelCardPratosHome("Sorvete"));

        return pratos;
    }
}
