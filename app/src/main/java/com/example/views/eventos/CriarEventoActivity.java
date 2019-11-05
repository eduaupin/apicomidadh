package com.example.views.eventos;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.login.R;

public class CriarEventoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);

        btnSalvar = findViewById(R.id.button_salvar);

        //TODO: Será que não conseguimos usar um datepicker para data e hora?

        //TODO: Fazer ligação de pratos e participantes para telas correspondentes

        toolbar = findViewById(R.id.toolbarNovoEvento);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow));
        toolbar.setTitle("Criar Evento");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
