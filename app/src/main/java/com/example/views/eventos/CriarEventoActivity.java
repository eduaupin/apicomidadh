package com.example.views.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.login.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class CriarEventoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextInputLayout inputTitulo;
    private TextInputLayout inputData;
    private TextInputLayout inputHora;
    private TextInputLayout inputLocal;
    private Button buttonAdicionarParticipantes;
    private Button buttonAdicionarPratos;
    private MaterialButton buttonSalvarEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);

        initViews();

        //TODO: Será que não conseguimos usar um datepicker para data e hora?

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Novo Evento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        buttonAdicionarParticipantes.setOnClickListener(view -> {
            startActivity(new Intent(CriarEventoActivity.this, AdicionarPessoaAoEventoActivity.class));
        });

        buttonAdicionarPratos.setOnClickListener(view -> {
            //TODO: Adicionar tela de adicionar prato em evento (searchview com recycler)
            Snackbar.make(buttonSalvarEvento, "Função não disponível", Snackbar.LENGTH_SHORT).show();
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar_novo_evento);
        inputTitulo = findViewById(R.id.input_novo_evento_titulo);
        inputData = findViewById(R.id.input_novo_evento_data);
        inputHora = findViewById(R.id.input_novo_evento_hora);
        inputLocal = findViewById(R.id.input_novo_evento_local);
        buttonAdicionarParticipantes = findViewById(R.id.button_novo_participante);
        buttonAdicionarPratos = findViewById(R.id.button_novo_pratos);
        buttonSalvarEvento = findViewById(R.id.button_novo_evento_salvar);
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
}
