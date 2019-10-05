package com.example.views.home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.login.R;
import com.example.views.eventos.NaoExistemEventosActivity;

public class NotificacoesActivity extends AppCompatActivity {

    public FloatingActionButton btnVoltaNota;
    public TextView txtVer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);

        btnVoltaNota = findViewById(R.id.BtnFloatVoltarNotifica);
        txtVer = findViewById(R.id.txtVerMais);

        btnVoltaNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificacoesActivity.this, MenuActivity.class));
            }
        });

        txtVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(txtVer, "Função Não Disponível!", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}
