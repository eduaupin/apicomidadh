package com.example.views.eventos;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.model.Evento;
import com.example.model.ModelCardPratosHome;
import com.example.model.Participante;
import com.example.model.Prato;
import com.example.views.adapter.CardEventoAdapter;
import com.example.views.adapter.CardFavoritosAdapter;
import com.example.views.adapter.RecyclerViewEventoAdapter;
import com.example.views.home.CardEventoFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;

import com.example.login.R;

import java.util.ArrayList;
import java.util.List;

public class ListaEventosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton btnAdd;
    private RecyclerView recyclerView;
    List<Evento> exemploEventos = new ArrayList<>();
    private RecyclerViewEventoAdapter adapter;

    private List<Prato> listaPratosPopulares = new ArrayList<>();

    private CardEventoAdapter eventoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        initViews();



        recyclerView = findViewById(R.id.recycler_eventos);

        adapter = new RecyclerViewEventoAdapter(testarRecycler());

        //Setando o adapter para o componente recyclerView
        recyclerView.setAdapter(adapter);

        //Definição do layout da lista utilizando a classe LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);





        //TODO: Implementar recyclerview

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarEvento();
            }
        });



        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow));
        toolbar.setTitle("Eventos");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(toolbar);

    }

    public List<Evento> testarRecycler() {

        List<Participante> exemploParticipantes = new ArrayList<>();

        exemploParticipantes.add(new Participante("Eduardo Pinheiro"));
        exemploParticipantes.add(new Participante("Nina Lofrese"));
        exemploParticipantes.add(new Participante("Thais Camargo"));


        exemploEventos.add(new Evento(R.drawable.eventos_noite_churros, "Noite do Churros", "10/10/2020", "Avenida Brasil, 200", listaPratosPopulares, exemploParticipantes, CardEventoFragment.novaInstancia(R.drawable.eventos_noite_churros, "Noite do Churros", "10/10/2020")));
        exemploEventos.add(new Evento(R.drawable.churras, "Churrasco dos Amigos", "20/10/2020", "Avenida Brasil, 200", listaPratosPopulares, exemploParticipantes, CardEventoFragment.novaInstancia(R.drawable.churras, "Churrasco dos Amigos", "20/10/2020")));
        exemploEventos.add(new Evento(R.drawable.sorvete, "Festa do Sorvete", "20/12/2020", "Avenida Brasil, 200", listaPratosPopulares, exemploParticipantes, CardEventoFragment.novaInstancia(R.drawable.sorvete, "Festa do Sorvete", "20/12/2020")));

        return exemploEventos;
    }


    private void initViews(){
        btnAdd = findViewById(R.id.floatingActionButton3);
        recyclerView = findViewById(R.id.recycler_eventos);
    }

    private void criarEvento(){
        startActivity(new Intent(ListaEventosActivity.this,CriarEventoActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
