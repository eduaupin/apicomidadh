package com.example.views.home;

import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.login.R;

public class NotificacoesActivity extends AppCompatActivity {

    public TextView txtVer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);

        txtVer = findViewById(R.id.txtVerMais);

        Toolbar notToolbar = findViewById(R.id.toolbar_notificacoes);
        setSupportActionBar(notToolbar);

        if (notToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
            notToolbar.setTitle("Notificações");
        }

        //TODO: Implementar layout do item e lógica para notificações

        txtVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(txtVer, "Função Não Disponível!", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
