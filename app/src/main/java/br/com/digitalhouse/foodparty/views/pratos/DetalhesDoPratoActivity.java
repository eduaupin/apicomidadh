package br.com.digitalhouse.foodparty.views.pratos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Ingrediente;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.util.AppUtil;
import br.com.digitalhouse.foodparty.viewmodel.FavoritosViewModel;
import br.com.digitalhouse.foodparty.views.adapter.IngredientesAdapter;
import br.com.digitalhouse.foodparty.views.eventos.CriarEventoActivity;

import static br.com.digitalhouse.foodparty.R.drawable.favoritetrue;
import static br.com.digitalhouse.foodparty.R.drawable.ic_favorite_black_24dp;
import static br.com.digitalhouse.foodparty.R.drawable.ic_favorite_borde;
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
    private ImageButton btn_back;
    private ImageButton btn_favorite;
    private ImageButton btn_share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_prato);

        initViews();

        getDetalhesPrato();
        ativarBotaoAdicionar();

        adapter = new IngredientesAdapter(listaIngredientes);
        recyclerIngredientes.setLayoutManager(new LinearLayoutManager(this));
        recyclerIngredientes.setAdapter(adapter);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();

            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, String.format("%s \n\nIngredientes:\n\n%s\n\nPreparo:\n\n%s", prato.getStrMeal(), prato.getListaIngredientes().toString(), prato.getStrInstructions()));
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(teste){
                    excluirFavorito2(prato);
                    btn_favorite.setBackgroundResource(ic_favorite_borde);
                    teste = false;
                }else {
                    favoritosViewModel.salvarFavorito(prato);
                    btn_favorite.setBackgroundResource(ic_favorite_black_24dp);
                    teste = true;
                }
            }
        });


    }

    private void initViews() {
        btn_back = findViewById(R.id.btn_back_detalhe_prato);
        btn_share = findViewById(R.id.btn_share_detalhe_prato);
        btn_favorite = findViewById(R.id.btn_favorite_detalhe_prato);
        imagemPrato = findViewById(R.id.image_detalhe_prato_foto);
        nomePrato = findViewById(R.id.text_detalhe_prato_nome);
        categoriaPrato = findViewById(R.id.text_detalhe_prato_categoria);
        recyclerIngredientes = findViewById(R.id.recycler_ingredientes);
        preparoPrato = findViewById(R.id.text_detalhe_prato_preparo);
        buttonAdicionarPrato = findViewById(R.id.botao_detalhe_prato_adicionar);
        favoritosViewModel = ViewModelProviders.of(this).get(FavoritosViewModel.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    private void getDetalhesPrato() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            prato = getIntent().getParcelableExtra(PRATO_KEY);
            Picasso.get().load(prato.getStrMealThumb()).into(imagemPrato);
            nomePrato.setText(prato.getStrMeal());
            categoriaPrato.setText(prato.getStrCategory());
            preparoPrato.setText(prato.getStrInstructions());
            if (prato.getListaIngredientes().size() > 0) {
                listaIngredientes = prato.getListaIngredientes();
            }
            ehFavorito();
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

    public Boolean getTeste() {
        return teste;
    }

    public void setTeste(Boolean teste) {
        this.teste = teste;
    }

    public void excluirFavorito2(Prato prato){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(AppUtil.getIdUsuario(this) + "/favorites");

        Query queryRemoverPrato2 = reference.limitToLast(1);
        queryRemoverPrato2.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot removerPratoSnapshot : dataSnapshot.getChildren()) {
                    Prato resultFirebase = removerPratoSnapshot.getValue(Prato.class);
                    removerPratoSnapshot.getRef().removeValue();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public boolean ehFavorito(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(AppUtil.getIdUsuario(this) + "/favorites");
        reference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@io.reactivex.annotations.NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {

                    Prato resultFirebase = resultSnapshot.getValue(Prato.class);

                    String idPrato = prato.getIdMeal();
                    String idFirebaseFavorite = resultFirebase.getIdMeal();

                    if (idPrato.equals(idFirebaseFavorite)) {
                        btn_favorite.setBackgroundResource(ic_favorite_black_24dp);
                        setTeste(true);
                    }
                }
            }

            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
                btn_favorite.setBackgroundResource(ic_favorite_borde);
                setTeste(false);
            }
        });
        Toast.makeText(DetalhesDoPratoActivity.this, "get Teste "+ getTeste(), Toast.LENGTH_LONG).show();

        return getTeste();

    }

}