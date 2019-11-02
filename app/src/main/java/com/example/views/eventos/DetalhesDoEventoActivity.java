package com.example.views.eventos;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.login.R;
import com.example.views.home.HomeActivity;

public class DetalhesDoEventoActivity extends AppCompatActivity {

    private FloatingActionButton btnVoltarEventos;
    private FloatingActionButton btnEditEventos;
    private FloatingActionButton btnShareEventos;



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
                startActivity(new Intent(DetalhesDoEventoActivity.this, HomeActivity.class));
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
