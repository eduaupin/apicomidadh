package br.com.digitalhouse.foodparty.views.eventos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.example.login.R;

public class AdicionarPessoaAoEventoActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_pessoa_ao_evento);

        //TODO: Melhorar layout
        //TODO: Adicionar lógica/ viewmodel

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow));
        toolbar.setTitle("Participantes");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.holo_red_dark));
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}


