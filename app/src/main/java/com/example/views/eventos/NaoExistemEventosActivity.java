package com.example.views.eventos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.login.R;
import com.example.views.home.MenuActivity;

public class NaoExistemEventosActivity extends AppCompatActivity {

    private FloatingActionButton BtnVoltarEventos;
    private FloatingActionButton BtnAddEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nao_existem_eventos);


        BtnVoltarEventos = findViewById(R.id.BtnFloatVoltarNotifica);
        BtnAddEventos = findViewById(R.id.BtnFloatAddEventos);

        BtnAddEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NaoExistemEventosActivity.this, MenuActivity.class));
            }
        });

        BtnVoltarEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NaoExistemEventosActivity.this, CriarEventoActivity.class));
            }
        });



    }



}
