package com.example.views.pratos;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.login.R;

public class DetalhesDoPratoActivity extends AppCompatActivity {

    private static final String PRATOS_KEY = "pratos";
    private Button adicionarPrato;
    private ImageButton botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_prato);

        botaoVoltar = findViewById(R.id.botaoVoltarReceita);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(DetalhesDoPratoActivity.this,
                        ListaDePratosActivity.class);
            }
        });
    }

}
