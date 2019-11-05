package com.example.views.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.google.android.material.snackbar.Snackbar;

public class DetalhesDoEventoActivity extends AppCompatActivity {

    private ImageButton btnVoltarEventos;
    private ImageButton btnEditEventos;
    private ImageButton btnShareEventos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_evento);

        btnEditEventos = findViewById(R.id.btnfloateditEventos);
        btnVoltarEventos = findViewById(R.id.BtnfloatvoltarEventos);
        btnShareEventos = findViewById(R.id.btnfloatShareEventos);


        btnVoltarEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEditEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetalhesDoEventoActivity.this, CriarEventoActivity.class));
            }
        });


        btnShareEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(btnShareEventos, "Função Não Disponível!", Snackbar.LENGTH_LONG).show();
            }
        });


    }



}
