package com.example.views.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.adapter.CardEventoAdapter;
import com.example.adapter.CardPratoAdapter;
import com.example.login.R;
import com.example.model.ModelCard;
import com.example.model.ModelCardPratosHome;
import com.example.views.eventos.DetalhesDoEventoActivity;
import com.example.views.eventos.ListaEventosActivity;
import com.example.views.pratos.DetalhesDoPratoActivity;
import com.example.views.pratos.ListaDePratosActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPratos;
    private CardPratoAdapter adapter;
    private Toolbar toolbar;


    private TextView txtVerTodos;
    private TextView txtVerMais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

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

        listaModelo.add(new ModelCard("Noite do Churros", "10/10/2019", CardEventoFragment.novaInstancia(R.drawable.churros_card,"Noite do Churros","10/10/2019")));
        listaModelo.add(new ModelCard("Churrasco dos migos", "12/10/2019", CardEventoFragment.novaInstancia(R.drawable.churras,"Churrasco dos migos", "12/10/20199")));
        listaModelo.add(new ModelCard("Festa do Sorvete", "03/11/2019", CardEventoFragment.novaInstancia(R.drawable.sorvete,"Festa do Sorvete", "03/11/2019")));

        CardEventoAdapter adapter = new CardEventoAdapter(getSupportFragmentManager(), listaModelo);

        viewPager.setAdapter(adapter);

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

    }

    private void initViews(){

        txtVerMais = findViewById(R.id.txt_ver_mais);
        txtVerTodos = findViewById(R.id.txt_ver_todos);

    }


    private List<ModelCardPratosHome> listaDePratos(){
        List<ModelCardPratosHome> pratos = new ArrayList<>();

        pratos.add(new ModelCardPratosHome("Churras"));
        pratos.add(new ModelCardPratosHome("Biscoito"));
        pratos.add(new ModelCardPratosHome("Sorvete"));

        return pratos;
    }

    private void verTodos(){
        startActivity(new Intent(HomeActivity.this, ListaEventosActivity.class));
    }

    private void detalhesDoEvento(){
        startActivity(new Intent(HomeActivity.this, DetalhesDoEventoActivity.class));
    }

    private void verMais(){
        startActivity(new Intent(HomeActivity.this, ListaDePratosActivity.class));

    }

    private void detalhesDoPrato(){
        startActivity(new Intent(HomeActivity.this, DetalhesDoPratoActivity.class));
    }

}
