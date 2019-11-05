package com.example.views.pratos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.google.android.material.snackbar.Snackbar;

public class DetalhesDoPratoActivity extends AppCompatActivity {

    private static final String PRATOS_KEY = "pratos";
    private Button adicionarPrato;
    private ImageButton botaoVoltar;
    private ImageButton botaoCompartilhar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_prato);

        botaoVoltar = findViewById(R.id.botaoVoltarReceita);
        botaoCompartilhar = findViewById(R.id.botaoShare);


        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        botaoCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(botaoCompartilhar, "Função Não Disponível!", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
