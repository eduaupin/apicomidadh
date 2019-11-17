package com.example.views.pratos;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.model.Ingrediente;
import com.example.model.Prato;
import com.example.views.adapter.IngredientesAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.views.home.HomeFragment.PRATO_KEY;

public class DetalhesDoPratoActivity extends AppCompatActivity {

    private ImageView imagemPrato;
    private TextView nomePrato;
    private TextView categoriaPrato;
    private RecyclerView recyclerIngredientes;
    private IngredientesAdapter adapter;
    private List<Ingrediente> listaIngredientes = new ArrayList<>();
    private TextView preparoPrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_prato);

        initViews();

        Toolbar favToolbar = findViewById(R.id.toolbarPrato);
        setSupportActionBar(favToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        getDetalhesPrato();

        adapter = new IngredientesAdapter(listaIngredientes);
        recyclerIngredientes.setLayoutManager(new LinearLayoutManager(this));
        recyclerIngredientes.setAdapter(adapter);
    }

    private void initViews() {
        imagemPrato = findViewById(R.id.image_detalhe_prato_foto);
        nomePrato = findViewById(R.id.text_detalhe_prato_nome);
        categoriaPrato = findViewById(R.id.text_detalhe_prato_categoria);
        recyclerIngredientes = findViewById(R.id.recycler_ingredientes);
        preparoPrato = findViewById(R.id.text_detalhe_prato_preparo);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalhe_prato, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_prato_compartilhar) {
            Snackbar.make(preparoPrato, "Função Não Disponível!", Snackbar.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_prato_favoritar) {
            Snackbar.make(preparoPrato, "Função Não Disponível!", Snackbar.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getDetalhesPrato() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Prato prato = getIntent().getParcelableExtra(PRATO_KEY);
            Picasso.get().load(prato.getStrMealThumb()).into(imagemPrato);
            nomePrato.setText(prato.getStrMeal());
            categoriaPrato.setText(prato.getStrCategory());
            preparoPrato.setText(prato.getStrInstructions());
            listaIngredientes = prato.getListaIngredientes();
        }
    }

}
