package br.com.digitalhouse.foodparty.views.eventos;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.model.Participante;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.views.adapter.RecyclerViewEventoAdapter;
import br.com.digitalhouse.foodparty.views.home.HomeFragment;
import br.com.digitalhouse.foodparty.views.interfaces.ClickEvento;

import com.example.login.R;
<<<<<<< Updated upstream:app/src/main/java/com/example/views/eventos/ListaEventosActivity.java
=======
import br.com.digitalhouse.foodparty.viewmodel.ListaEventosViewModel;
import br.com.digitalhouse.foodparty.views.adapter.CardEventoAdapter;
import br.com.digitalhouse.foodparty.views.home.CardEventoFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
>>>>>>> Stashed changes:app/src/main/java/br/com/digitalhouse/foodparty/views/eventos/ListaEventosActivity.java

public class ListaEventosActivity extends AppCompatActivity {

<<<<<<< Updated upstream:app/src/main/java/com/example/views/eventos/ListaEventosActivity.java
=======
public class ListaEventosActivity extends AppCompatActivity implements ClickEvento {
>>>>>>> Stashed changes:app/src/main/java/br/com/digitalhouse/foodparty/views/eventos/ListaEventosActivity.java
    private Toolbar toolbar;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        initViews();

        //TODO: Implementar recyclerview

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarEvento();
            }
        });



        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow));
        toolbar.setTitle("Eventos");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(toolbar);

    }


    private void initViews(){
        btnAdd = findViewById(R.id.floatingActionButton3);
    }

    private void criarEvento(){
        startActivity(new Intent(ListaEventosActivity.this,CriarEventoActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

<<<<<<< Updated upstream:app/src/main/java/com/example/views/eventos/ListaEventosActivity.java
=======
    public List<Evento> testarRecycler() {
        List<Participante> exemploParticipantes = new ArrayList<>();
        exemploParticipantes.add(new Participante("Eduardo Pinheiro"));
        exemploParticipantes.add(new Participante("Nina Lofrese"));
        exemploParticipantes.add(new Participante("Thais Camargo"));
        exemploEventos.add(new Evento(R.drawable.eventos_noite_churros, "Noite do Churros", "10/10/2020", "Avenida Brasil, 200", listaPratosPopulares, exemploParticipantes, CardEventoFragment.novaInstancia(R.drawable.eventos_noite_churros, "Noite do Churros", "10/10/2020")));
        exemploEventos.add(new Evento(R.drawable.churras, "Churrasco dos Amigos", "20/10/2020", "Avenida Brasil, 200", listaPratosPopulares, exemploParticipantes, CardEventoFragment.novaInstancia(R.drawable.churras, "Churrasco dos Amigos", "20/10/2020")));
        exemploEventos.add(new Evento(R.drawable.sorvete, "Festa do Sorvete", "20/12/2020", "Avenida Brasil, 200", listaPratosPopulares, exemploParticipantes, CardEventoFragment.novaInstancia(R.drawable.sorvete, "Festa do Sorvete", "20/12/2020")));
        return exemploEventos;
    }

    @Override
    public void onClick(Evento evento) {
        Intent intent = new Intent(this, DetalhesDoEventoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(HomeFragment.EVENTO_KEY, evento);
        intent.putExtras(bundle);
        startActivity(intent);
    }
>>>>>>> Stashed changes:app/src/main/java/br/com/digitalhouse/foodparty/views/eventos/ListaEventosActivity.java
}
