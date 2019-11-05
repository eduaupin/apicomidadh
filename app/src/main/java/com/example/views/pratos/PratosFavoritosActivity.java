package com.example.views.pratos;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.views.interfaces.FavoritosClick;
import com.example.views.adapter.CardFavoritosAdapter;
import com.example.login.R;
import com.example.model.ModelCardPratosHome;

import java.util.ArrayList;
import java.util.List;

public class PratosFavoritosActivity extends AppCompatActivity implements FavoritosClick {

    private static final String PRATO_KEY = "prato";
    private RecyclerView recyclerView;
    private List<ModelCardPratosHome> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos_favoritos);

        Toolbar favToolbar = findViewById(R.id.toolbarFavoritos);
        setSupportActionBar(favToolbar);

        if (favToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
        }

        recyclerView = findViewById(R.id.recyclerFavoritos);

        CardFavoritosAdapter adapter = new CardFavoritosAdapter(testarRecycler(), this);
        GridLayoutManager gdManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gdManager);

    }

    public List<ModelCardPratosHome> testarRecycler() {
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        lista.add(new ModelCardPratosHome(R.drawable.churras, "teste teste teste"));
        return lista;
    }

    @Override
    public void favOnClick(ModelCardPratosHome prato) {
        Intent intent = new Intent(PratosFavoritosActivity.this,
                DetalhesDoPratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRATO_KEY, prato);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
