package br.com.digitalhouse.foodparty.views.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.viewmodel.AdicionarPratosViewModel;
import br.com.digitalhouse.foodparty.views.adapter.AdicionarPratoAdapter;
import br.com.digitalhouse.foodparty.views.interfaces.ClickPratoAdicionar;
import br.com.digitalhouse.foodparty.views.pratos.DetalhesDoPratoActivity;

import static br.com.digitalhouse.foodparty.views.home.HomeFragment.PRATO_KEY;

public class AdicionarPratosAoEventoActivity extends AppCompatActivity implements ClickPratoAdicionar {
    public static final String PRATOS_KEY = "participantes";
    private Toolbar toolbar;
    private TextInputLayout queryPrato;
    private SearchView searchPrato;
    private RecyclerView recyclerPrato;
    private AdicionarPratoAdapter adapter;
    private AdicionarPratosViewModel viewModel;
    private ProgressBar loadingPratos;
    private String termoBusca = "";

    private List<Prato> pratosBuscados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_pratos_ao_evento);

        toolbar = findViewById(R.id.toolbar_prato_add);
        setSupportActionBar(toolbar);

        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Pratos");
        }

        initViews();

        viewModel.getPratosFromAPI(termoBusca);

        getPratosEncontrados();
        getError();
        getLoading();

        searchPrato.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                termoBusca = query;
                adapter.clear();
                viewModel.getPratosFromAPI(termoBusca);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 3) {
                    termoBusca = newText;
                    adapter.clear();
                    viewModel.getPratosFromAPI(termoBusca);
                }
                return false;
            }
        });
    }

    private void initViews() {
        searchPrato = findViewById(R.id.search_prato_add);
        recyclerPrato = findViewById(R.id.recycler_prato_add);
        loadingPratos = findViewById(R.id.progress_prato_add);
        viewModel = ViewModelProviders.of(this).get(AdicionarPratosViewModel.class);
        adapter = new AdicionarPratoAdapter(pratosBuscados, this);
        recyclerPrato.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerPrato.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        setResult(RESULT_CANCELED);
        finish();
        return true;
    }

    @Override
    public void onClickAdicionarPrato(Prato prato) {
        Intent intent = new Intent(this, DetalhesDoPratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRATO_KEY, prato);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        startActivity(intent);
        finish();
    }

    private void getPratosEncontrados() {
        viewModel.getPratosRetornados().observe(this, pratos -> {
            adapter.atualizaLista(pratos);
        });
    }

    private void getLoading() {
        viewModel.getLoading().observe(this, loading -> {
            if (loading) {
                loadingPratos.setVisibility(View.VISIBLE);
            } else {
                loadingPratos.setVisibility(View.GONE);
            }
        });
    }

    private void getError() {
        viewModel.getError().observe(this, s -> {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        });
    }
}
