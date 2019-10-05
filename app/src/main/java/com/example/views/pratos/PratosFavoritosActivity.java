package com.example.views.pratos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.login.R;
import com.example.views.home.HomeActivity;

public class PratosFavoritosActivity extends AppCompatActivity {

    private ImageButton botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos_favoritos);

        botaoVoltar = findViewById(R.id.botaoVoltarFav);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltar = new Intent(PratosFavoritosActivity.this,
                        HomeActivity.class);
            }
        });


    }
}
