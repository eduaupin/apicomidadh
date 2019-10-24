package com.example.views.eventos;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Adapter.DetalheAdapter;
import com.example.Interface.RecyclerDetalhe;
import com.example.Modelo.Detalhe;
import com.example.login.R;
import com.example.views.home.MenuActivity;
import com.example.views.pratos.DetalhesDoPratoActivity;

import java.util.ArrayList;
import java.util.List;

public class DetalhesDoEventoActivity extends AppCompatActivity implements RecyclerDetalhe {

    private FloatingActionButton btnVoltarEventos;
    private FloatingActionButton btnEditEventos;
    private FloatingActionButton btnShareEventos;

    private RecyclerView recyclerViewsXD;
    private GridLayoutManager gridLayoutManager;
    private DetalheAdapter adapters;
    private List<Detalhe> LstDetalhe = new ArrayList<>();
    private ImageView imageViewXDTL;
    private TextView txtNomeXDTL;

    public static final String DETALHE_KEY = "Detalhe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_evento);

        btnEditEventos = findViewById(R.id.btnfloateditEventos);
        btnVoltarEventos = findViewById(R.id.BtnfloatvoltarEventos);
        btnShareEventos = findViewById(R.id.btnfloatShareEventos);

        recyclerViewsXD=findViewById(R.id.recyclerViewX);
        adapters = new DetalheAdapter(retornarComidasXX(), this);
        recyclerViewsXD.setAdapter(adapters);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewsXD.setLayoutManager(gridLayoutManager);



        btnVoltarEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetalhesDoEventoActivity.this, MenuActivity.class));
            }
        });

        btnEditEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetalhesDoEventoActivity.this, CriarEventoActivity.class));
            }
        });


        btnShareEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(btnShareEventos, "Função Não Disponível!", Snackbar.LENGTH_LONG).show();
            }
        });


    }


    public List<Detalhe> retornarComidasXX() {
        LstDetalhe.add(new Detalhe(R.drawable.churros,"Churros"));
        LstDetalhe.add(new Detalhe(R.drawable.bolocenoura,"Bolo de Cenoura"));


        return LstDetalhe;
    }

    @Override
    public void onClick(Detalhe detralhes) {

        Intent intent = new Intent(DetalhesDoEventoActivity.this, DetalhesDoPratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DETALHE_KEY,detralhes);
        intent.putExtras(bundle);
        startActivity(intent);



    }
}
