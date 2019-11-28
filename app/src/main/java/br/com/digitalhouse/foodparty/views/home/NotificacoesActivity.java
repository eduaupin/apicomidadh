package br.com.digitalhouse.foodparty.views.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.digitalhouse.foodparty.model.ModelNotificacao;
import br.com.digitalhouse.foodparty.views.adapter.NotificacaoAdapter;

import com.example.login.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NotificacoesActivity extends AppCompatActivity {

    public TextView txtVer;
    private RecyclerView recyclerView;
    private NotificacaoAdapter adapter;
    private List<ModelNotificacao> listaNotificacoes = new ArrayList<>();

    public static final String NOTIFICACAO_KEY = "Notificacao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);

        txtVer = findViewById(R.id.txtVerMais);
        recyclerView = findViewById(R.id.recyclerViewNotificacao);

        Toolbar notToolbar = findViewById(R.id.toolbar_notificacoes);
        setSupportActionBar(notToolbar);

        if (notToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
            notToolbar.setTitle("Notificações");
        }

        //TODO: Implementar layout do item e lógica para notificações

        adapter = new NotificacaoAdapter(getNotificacoes());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        txtVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(txtVer, "Função Não Disponível!", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    public List<ModelNotificacao> getNotificacoes() {
        listaNotificacoes.add(new ModelNotificacao("Notificação 1", "01/01/2019"));
        listaNotificacoes.add(new ModelNotificacao("Notificação 2", "01/02/2019"));
        listaNotificacoes.add(new ModelNotificacao("Notificação 3", "01/03/2019"));
        listaNotificacoes.add(new ModelNotificacao("Notificação 4", "01/04/2019"));
        listaNotificacoes.add(new ModelNotificacao("Notificação 5", "01/05/2019"));
        listaNotificacoes.add(new ModelNotificacao("Notificação 6", "01/06/2019"));

        return listaNotificacoes;

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
