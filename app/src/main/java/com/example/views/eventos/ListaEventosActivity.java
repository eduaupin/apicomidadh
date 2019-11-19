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
import com.example.views.interfaces.ClickEvento;
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

import static com.example.views.home.HomeFragment.EVENTO_KEY;
import static java.security.AccessController.getContext;

public class ListaEventosActivity extends AppCompatActivity implements ClickEvento {

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
        setupRecycler();
        setupToolbar();
        clickBtnAdd();

    }


    private void initViews(){
        toolbar = findViewById(R.id.my_toolbar);
        btnAdd = findViewById(R.id.floatingActionButton3);
        recyclerView = findViewById(R.id.recycler_eventos);
        recyclerView = findViewById(R.id.recycler_eventos);
        adapter = new RecyclerViewEventoAdapter(testarRecycler(),this);
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow));
        toolbar.setTitle("Eventos");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(toolbar);
    }

    private void setupRecycler(){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void clickBtnAdd(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarEvento();
            }
        });
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


    @Override
    public void onClick(Evento evento) {
        Intent intent = new Intent(this, DetalhesDoEventoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(EVENTO_KEY, evento);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
