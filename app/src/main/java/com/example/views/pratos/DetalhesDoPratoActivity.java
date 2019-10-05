package com.example.views.pratos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.login.R;

public class DetalhesDoPratoActivity extends AppCompatActivity {

    private Button adicionarPrato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_do_prato);
    }
}
