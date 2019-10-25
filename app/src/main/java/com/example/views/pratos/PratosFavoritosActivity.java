package com.example.views.pratos;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.example.Interface.FavoritosClick;
import com.example.adapter.CardFavoritosAdapter;
import com.example.login.R;
import com.example.model.ModelCard;
import com.example.model.ModelCardPratosHome;
import com.example.views.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class PratosFavoritosActivity extends AppCompatActivity implements FavoritosClick {

    private static final String PRATO_KEY = "prato";
    private RecyclerView recyclerView;
    private List<ModelCardPratosHome> lista = new ArrayList<>();
    private ImageButton btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos_favoritos);

        btnVoltar = findViewById(R.id.btnVoltarFavoritos);
        recyclerView = findViewById(R.id.recyclerFavoritos);

        CardFavoritosAdapter adapter = new CardFavoritosAdapter(testarRecycler(), this);
        GridLayoutManager gdManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gdManager);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PratosFavoritosActivity.this,
                        HomeActivity.class));
            }
        });


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
}
