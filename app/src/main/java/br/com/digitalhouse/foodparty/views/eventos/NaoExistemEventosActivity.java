package br.com.digitalhouse.foodparty.views.eventos;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.views.home.HomeActivity;

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
                startActivity(new Intent(NaoExistemEventosActivity.this, HomeActivity.class));
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
