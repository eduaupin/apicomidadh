package br.com.digitalhouse.foodparty.views.pratos;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.model.PratosFavoritos;
import br.com.digitalhouse.foodparty.util.AppUtil;
import br.com.digitalhouse.foodparty.views.adapter.CardFavoritosAdapter;
import br.com.digitalhouse.foodparty.views.interfaces.FavoritosClick;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.digitalhouse.foodparty.model.ModelCardPratosHome;
import io.reactivex.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

import static br.com.digitalhouse.foodparty.views.home.HomeFragment.PRATO_KEY;

public class PratosFavoritosActivity extends AppCompatActivity implements FavoritosClick {

    private RecyclerView recyclerView;
    private List<Prato> pratosFavoritos = new ArrayList<>();
    private CardFavoritosAdapter adapter;

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

        adapter = new CardFavoritosAdapter(pratosFavoritos, this);
        GridLayoutManager gdManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gdManager);
        recyclerView.setAdapter(adapter);

        carregarFavorites();

    }

    private void carregarFavorites() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(AppUtil.getIdUsuario(this) + "/favorites");
        reference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Prato> results = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Prato result = child.getValue(Prato.class);
                    results.add(result);

                }
                adapter.update(results);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void favOnClick(Prato prato) {
        Intent intent = new Intent(PratosFavoritosActivity.this,
                DetalhesDoPratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRATO_KEY, prato);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
