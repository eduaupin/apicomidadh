package br.com.digitalhouse.foodparty.views.pratos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.login.R;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.viewmodel.HomeFragmentViewModel;
import br.com.digitalhouse.foodparty.views.adapter.CardFavoritosAdapter;
import br.com.digitalhouse.foodparty.views.adapter.PratosPopularesAdapter;
import br.com.digitalhouse.foodparty.views.interfaces.ClickEvento;
import br.com.digitalhouse.foodparty.views.interfaces.ClickPratos;

import static br.com.digitalhouse.foodparty.views.home.HomeFragment.PRATO_KEY;

public class ListaDePratosActivity extends AppCompatActivity implements ClickPratos {
    private Toolbar toolbar;
    private RecyclerView recyclerViewPratos;
    private PratosPopularesAdapter pratosAdapter;
    private List<Prato> listaPratosPopulares = new ArrayList<>();
    private HomeFragmentViewModel viewModel;
    private ProgressBar loadingPratos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_pratos);

        initViews();
        setupToolbar();
        setupRecycler();
        buscaDados();

    }

    private void initViews() {
        toolbar = findViewById(R.id.my_toolbar);
        recyclerViewPratos = findViewById(R.id.recycler_lista_de_pratos);
        pratosAdapter = new PratosPopularesAdapter(listaPratosPopulares, this);
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        loadingPratos = findViewById(R.id.progress_bar_home);
    }

    private void setupToolbar(){
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow));
        toolbar.setTitle("Pratos Populares");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(toolbar);
    }

    private void setupRecycler(){
        recyclerViewPratos.setAdapter(pratosAdapter);
        GridLayoutManager gdManager = new GridLayoutManager(this, 2);
        recyclerViewPratos.setLayoutManager(gdManager);
    }

    private void buscaDados(){
        viewModel.getPratos();
        viewModel.getPratosLiveData().observe(this, pratos -> {
            pratosAdapter.atualizaLista(pratos);
        });

        viewModel.getLoading().observe(this, loading -> {
            if (loading) {
                loadingPratos.setVisibility(View.VISIBLE);
            } else {
                loadingPratos.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Prato prato) {
        //Envio do objeto para a tela de detalhe
        Intent intent = new Intent(this, DetalhesDoPratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRATO_KEY, prato);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
