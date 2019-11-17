package com.example.views.eventos;

import android.content.Intent;
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
import com.example.model.Evento;
import com.example.model.Participante;
import com.example.model.Prato;
import com.example.model.PratosPopulares;
import com.example.views.adapter.ParticipantesDetalheEventoAdapter;
import com.example.views.adapter.PratosPopularesAdapter;
import com.example.views.interfaces.ClickPratos;
import com.example.views.pratos.DetalhesDoPratoActivity;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.views.home.HomeFragment.EVENTO_KEY;
import static com.example.views.home.HomeFragment.PRATO_KEY;

public class DetalhesDoEventoActivity extends AppCompatActivity implements ClickPratos {
    private ImageView imagemEvento;
    private TextView nomeEvento;
    private TextView dataEvento;
    private TextView enderecoEvento;
    private RecyclerView recyclerParticipantes;
    private RecyclerView recyclerViewPratos;
    private Evento evento;
    private List<Participante> participantes;
    private List<Prato> pratos;

    private PratosPopularesAdapter pratosAdapter;
    private ParticipantesDetalheEventoAdapter participantesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_evento);

        initViews();

        Toolbar favToolbar = findViewById(R.id.toolbarEvento);
        setSupportActionBar(favToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        getDetalhesEvento();

        pratosAdapter = new PratosPopularesAdapter(pratos, this);
        recyclerViewPratos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewPratos.setAdapter(pratosAdapter);

        participantesAdapter = new ParticipantesDetalheEventoAdapter(participantes);
        recyclerParticipantes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerParticipantes.setAdapter(participantesAdapter);

    }

    private void initViews() {
        imagemEvento = findViewById(R.id.image_detalhe_evento_foto);
        recyclerParticipantes = findViewById(R.id.recycler_detalhe_evento_participantes);
        recyclerViewPratos = findViewById(R.id.recycler_detalhe_evento_pratos);
        nomeEvento = findViewById(R.id.text_detalhe_evento_nome);
        dataEvento = findViewById(R.id.text_detalhe_evento_data);
        enderecoEvento = findViewById(R.id.text_detalhe_evento_endereco);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalhe_evento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_evento_compartilhar) {
            Snackbar.make(recyclerViewPratos, "Função Não Disponível!", Snackbar.LENGTH_LONG).show();
            //TODO: Colocar Dynamic link do Firebase [COMPARTILHAR]
//            Intent sendIntent = new Intent();
//            sendIntent.setAction(Intent.ACTION_SEND);
//            sendIntent.putExtra(Intent.EXTRA_TEXT, String.format("%s", prato.getStrMeal(), prato.getListaIngredientes().toString(), prato.getStrInstructions()));
//            sendIntent.setType("text/plain");
//
//            Intent shareIntent = Intent.createChooser(sendIntent, null);
//            startActivity(shareIntent);
            return true;
        } else if (id == R.id.menu_evento_editar) {
            Snackbar.make(recyclerViewPratos, "Função Não Disponível!", Snackbar.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getDetalhesEvento() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            evento = getIntent().getParcelableExtra(EVENTO_KEY);
            Picasso.get().load(evento.getImgEvento()).into(imagemEvento);
            nomeEvento.setText(evento.getNomeEvento());
            dataEvento.setText(evento.getDataEvento());
            enderecoEvento.setText(evento.getEnderecoEvento());
            participantes = evento.getParticipantes();
            pratos = evento.getPratos();
        }
    }

    @Override
    public void onClick(Prato prato) {
        Intent intent = new Intent(DetalhesDoEventoActivity.this, DetalhesDoPratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRATO_KEY, prato);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
