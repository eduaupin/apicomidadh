package br.com.digitalhouse.foodparty.views.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Participante;
import br.com.digitalhouse.foodparty.views.adapter.AdicionarParticipanteAdapter;

public class AdicionarPessoaAoEventoActivity extends AppCompatActivity {
    public static final String PARTICIPANTES_KEY = "participantes";
    private Toolbar toolbar;
    private TextInputLayout inputParticipante;
    private RecyclerView recyclerParticipantes;
    private AdicionarParticipanteAdapter adapter;
    private List<Participante> participantes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_pessoa_ao_evento);

        toolbar = findViewById(R.id.toolbar_participante_add);
        setSupportActionBar(toolbar);

        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Participantes");
        }

        initViews();

        inputParticipante.getEditText().setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String nomeParticipante = inputParticipante.getEditText().getText().toString();

                if (!nomeParticipante.isEmpty()) {
                    Participante novoParticipante = new Participante(nomeParticipante);
                    adapter.adicionarItem(novoParticipante);
                    inputParticipante.getEditText().getText().clear();
                }
                return true;
            }
            return false;
        });
    }

    private void initViews() {
        inputParticipante = findViewById(R.id.input_participante_add);
        recyclerParticipantes = findViewById(R.id.recycler_participante_add);
        recyclerParticipantes.setLayoutManager(new LinearLayoutManager(this));
        recyclerParticipantes.addItemDecoration(new DividerItemDecoration(recyclerParticipantes.getContext(),
                DividerItemDecoration.VERTICAL));
        adapter = new AdicionarParticipanteAdapter(participantes);
        recyclerParticipantes.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        setResult(RESULT_CANCELED);
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_adicionar) {
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra(PARTICIPANTES_KEY, (ArrayList<? extends Parcelable>) participantes);
            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}


