package br.com.digitalhouse.foodparty.views.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.viewmodel.ListaEventosViewModel;
import br.com.digitalhouse.foodparty.views.adapter.CardEventoAdapter;
import br.com.digitalhouse.foodparty.views.adapter.RecyclerViewEventoAdapter;
import br.com.digitalhouse.foodparty.views.interfaces.ClickEvento;

import static br.com.digitalhouse.foodparty.views.home.HomeFragment.EVENTO_KEY;

public class ListaEventosActivity extends AppCompatActivity implements ClickEvento {
    private Toolbar toolbar;
    private FloatingActionButton btnAdd;
    private RecyclerView recyclerView;
    private List<Evento> eventos = new ArrayList<>();
    private RecyclerViewEventoAdapter adapter;
    private ListaEventosViewModel listaEventosViewModel;
    private List<Prato> listaPratosPopulares = new ArrayList<>();
    private CardEventoAdapter eventoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);
        initViews();
        setupRecycler();
        listaEventosViewModel.getAllEventosLocal();
        setupToolbar();
        clickBtnAdd();
    }

    private void initViews() {
        toolbar = findViewById(R.id.my_toolbar);
        btnAdd = findViewById(R.id.floatingActionButton3);
        recyclerView = findViewById(R.id.recycler_eventos);
        recyclerView = findViewById(R.id.recycler_eventos);
        adapter = new RecyclerViewEventoAdapter(eventos, this);
        listaEventosViewModel = ViewModelProviders.of(this).get(ListaEventosViewModel.class);
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow));
        toolbar.setTitle("Eventos");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(toolbar);
    }

    private void setupRecycler() {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void clickBtnAdd() {
        btnAdd.setOnClickListener(view -> criarEvento());
    }

    private void criarEvento() {
        startActivity(new Intent(ListaEventosActivity.this, CriarEventoActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
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
