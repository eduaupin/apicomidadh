package com.example.views.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.login.R;
import com.google.android.material.snackbar.Snackbar;

public class CriarEventoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnSalvar;
    private Button btnAdicionarParticipante;
    private Button btnAdicionarPrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);

        btnSalvar = findViewById(R.id.button_salvar);
        btnAdicionarParticipante = findViewById(R.id.btnAddParticipante);
        btnAdicionarPrato = findViewById(R.id.btnAddPratos);

        //TODO: Será que não conseguimos usar um datepicker para data e hora?

        toolbar = findViewById(R.id.toolbarNovoEvento);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow));
        toolbar.setTitle("Criar Evento");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(toolbar);

        btnAdicionarParticipante.setOnClickListener(view -> {
            startActivity(new Intent(CriarEventoActivity.this, AdicionarPessoaAoEventoActivity.class));
        });

        btnAdicionarPrato.setOnClickListener(view -> {
            //TODO: Adicionar tela de adicionar prato em evento (searchview com recycler)
            Snackbar.make(btnAdicionarParticipante, "Função não disponível", Snackbar.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
