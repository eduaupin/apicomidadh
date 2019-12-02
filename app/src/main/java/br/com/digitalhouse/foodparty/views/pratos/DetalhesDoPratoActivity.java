package br.com.digitalhouse.foodparty.views.pratos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

import br.com.digitalhouse.foodparty.viewmodel.FavoritosViewModel;
import br.com.digitalhouse.foodparty.views.adapter.IngredientesAdapter;
import br.com.digitalhouse.foodparty.views.eventos.CriarEventoActivity;
import br.com.digitalhouse.foodparty.model.Ingrediente;
import br.com.digitalhouse.foodparty.model.Prato;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static br.com.digitalhouse.foodparty.views.home.HomeFragment.PRATO_KEY;

public class DetalhesDoPratoActivity extends AppCompatActivity {

    private ImageView imagemPrato;
    private TextView nomePrato;
    private TextView categoriaPrato;
    private RecyclerView recyclerIngredientes;
    private IngredientesAdapter adapter;
    private List<Ingrediente> listaIngredientes = new ArrayList<>();
    private TextView preparoPrato;
    private Prato prato;
    private Button buttonAdicionarPrato;
    private FavoritosViewModel favoritosViewModel;
    private Boolean teste = false;
    private Boolean teste2 = teste;


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
        ativarBotaoAdicionar();

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
        buttonAdicionarPrato = findViewById(R.id.botao_detalhe_prato_adicionar);
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(teste==true) {
            invalidateOptionsMenu();
            menu.findItem(R.id.menu_prato_favoritar).setIcon(R.drawable.favoritetrue);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_prato_compartilhar) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, String.format("%s \n\nIngredientes:\n\n%s\n\nPreparo:\n\n%s", prato.getStrMeal(), prato.getListaIngredientes().toString(), prato.getStrInstructions()));
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return true;
        } else if (id == R.id.menu_prato_favoritar) {
            if(teste2 == true){
                this.teste2 = false;
                item.setIcon(R.drawable.ic_favorite_outline);
            }else{
                this.teste2 = true;
                item.setIcon(R.drawable.favoritetrue);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void getDetalhesPrato() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            prato = getIntent().getParcelableExtra(PRATO_KEY);
            Picasso.get().load(prato.getStrMealThumb()).into(imagemPrato);
            nomePrato.setText(prato.getStrMeal());
            categoriaPrato.setText(prato.getStrCategory());
            preparoPrato.setText(prato.getStrInstructions());
            listaIngredientes = prato.getListaIngredientes();
        }
    }

    private void ativarBotaoAdicionar() {
        if (CriarEventoActivity.ADICIONAR_PRATO_REQUEST == 1) {
            buttonAdicionarPrato.setVisibility(View.VISIBLE);

            buttonAdicionarPrato.setOnClickListener(view -> {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable(PRATO_KEY, prato);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            });
        }
    }

}
