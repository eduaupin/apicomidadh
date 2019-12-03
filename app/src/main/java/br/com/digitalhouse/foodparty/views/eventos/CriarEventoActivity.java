package br.com.digitalhouse.foodparty.views.eventos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.digitalhouse.foodparty.R;

import br.com.digitalhouse.foodparty.views.interfaces.ClickPratoAdicionar;
import br.com.digitalhouse.foodparty.model.Participante;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.views.adapter.ParticipantesNovoEventoAdapter;
import br.com.digitalhouse.foodparty.views.adapter.PratosNovoEventoAdapter;
import br.com.digitalhouse.foodparty.views.pratos.DetalhesDoPratoActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static br.com.digitalhouse.foodparty.views.home.HomeFragment.PRATO_KEY;

public class CriarEventoActivity extends AppCompatActivity implements ClickPratoAdicionar {
    public static final int ADICIONAR_PRATO_REQUEST = 1;
    public static final int ADICIONAR_PARTICIPANTE_REQUEST = 2;
    private Toolbar toolbar;
    private TextInputLayout inputTitulo;
    private TextInputLayout inputData;
    private TextInputLayout inputHora;
    private TextInputLayout inputLocal;
    private TextView semParticipantes;
    private TextView semPratos;
    private Button buttonAdicionarParticipantes;
    private Button buttonAdicionarPratos;
    private MaterialButton buttonSalvarEvento;
    private RecyclerView recyclerParticipantes;
    private RecyclerView recyclerPratos;
    private ParticipantesNovoEventoAdapter participantesAdapter;
    private PratosNovoEventoAdapter pratosAdapter;
    private List<Participante> participantes;
    private List<Prato> pratos;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
    int currentMinute = calendar.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);

        initViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Novo Evento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        buttonAdicionarParticipantes.setOnClickListener(view -> {
            Intent intent = new Intent(CriarEventoActivity.this, AdicionarPessoaAoEventoActivity.class);
            startActivityForResult(intent, ADICIONAR_PARTICIPANTE_REQUEST);
        });

        buttonAdicionarPratos.setOnClickListener(view -> {
            //TODO: Adicionar tela de adicionar prato em evento (searchview com recycler)
            Snackbar.make(buttonSalvarEvento, "Função não disponível", Snackbar.LENGTH_SHORT).show();
        });

        displayData();

        inputData.getEditText().setOnClickListener(view -> {
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        });

        inputHora.getEditText().setOnClickListener(view -> {
            timePickerDialog.show();
        });

        buttonSalvarEvento.setOnClickListener(view -> {
            String titulo = inputTitulo.getEditText().getText().toString();
            if (titulo.isEmpty()) {
                inputTitulo.setError(getString(R.string.validacao_evento_nome).toString());
            }
            //TODO:Adicionar intent que leve para a página de Detalhes do evento recém criado
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar_novo_evento);
        inputTitulo = findViewById(R.id.input_novo_evento_titulo);
        inputData = findViewById(R.id.input_novo_evento_data);
        inputHora = findViewById(R.id.input_novo_evento_hora);
        inputLocal = findViewById(R.id.input_novo_evento_local);
        semParticipantes = findViewById(R.id.text_novo_evento_sem_participantes);
        semPratos = findViewById(R.id.text_novo_evento_sem_pratos);
        buttonAdicionarParticipantes = findViewById(R.id.button_novo_participante);
        buttonAdicionarPratos = findViewById(R.id.button_novo_pratos);
        buttonSalvarEvento = findViewById(R.id.button_novo_evento_salvar);
        recyclerParticipantes = findViewById(R.id.recycler_novo_evento_participantes);
        recyclerPratos = findViewById(R.id.recycler_novo_evento_pratos);
        participantesAdapter = new ParticipantesNovoEventoAdapter(participantes, semParticipantes);
        recyclerParticipantes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerParticipantes.setAdapter(participantesAdapter);
        pratosAdapter = new PratosNovoEventoAdapter(pratos, semPratos, this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayData() {
        datePickerDialog = new DatePickerDialog(CriarEventoActivity.this,
                (datePicker, year, month, day) -> {
                    inputData.getEditText().setText(String.format(Locale.GERMAN, "%d/%d/%d", day, month + 1, year));
                }, year, month, dayOfMonth);

        timePickerDialog = new TimePickerDialog(CriarEventoActivity.this, (timePicker, hourOfDay, minutes) -> {
            inputHora.getEditText().setText(String.format(Locale.GERMAN, "%02d:%02d", hourOfDay, minutes));
        }, currentHour, currentMinute, true);
    }

    @Override
    public void onClickAdicionarPrato(Prato prato) {
        Intent intent = new Intent(CriarEventoActivity.this, DetalhesDoPratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRATO_KEY, prato);
        intent.putExtras(bundle);
        startActivityForResult(intent, ADICIONAR_PRATO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Prato adicionado", Toast.LENGTH_SHORT).show();
            Prato prato = data.getParcelableExtra(PRATO_KEY);
            //TODO:Implementar a lógica de adicionar/remover prato, em conjunto com a tela do botão AdicionarPrato e do botão de Adicionar em DetalhePrato
            //pratos.add(prato);
            pratosAdapter.atualizaLista(pratos);
        }

        if (requestCode == 2 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Participante adicionado", Toast.LENGTH_SHORT).show();
//            Participante participante = data.getParcelableExtra();
            //TODO:Implementar a lógica de adicionar/remover participante, em conjunto com a tela de Adicionar Participante
//            participantes.add(participante);
            participantesAdapter.atualizaLista(participantes);
        }
    }
}
