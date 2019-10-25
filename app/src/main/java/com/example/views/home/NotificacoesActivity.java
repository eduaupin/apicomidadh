package com.example.views.home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Adapter.DetalheAdapter;
import com.example.Adapter.NotificacaoAdapter;
import com.example.Interface.RecyclerDetalhe;
import com.example.Interface.RecyclerNotificacao;
import com.example.Modelo.Detalhe;
import com.example.Modelo.Notifica;
import com.example.login.R;
import com.example.views.eventos.DetalhesDoEventoActivity;
import com.example.views.eventos.ListaEventosActivity;
import com.example.views.eventos.NaoExistemEventosActivity;
import com.example.views.pratos.DetalhesDoPratoActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificacoesActivity extends AppCompatActivity implements RecyclerNotificacao{

    public FloatingActionButton btnVoltaNota;
    public TextView txtVer;

    private RecyclerView recyclerViewsTT;
    private NotificacaoAdapter adapters;
    private List<Notifica> LstNotifica = new ArrayList<>();


    public static final String NOTIFICA_KEY = "Notifica";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);

        btnVoltaNota = findViewById(R.id.BtnFloatVoltarNotifica);
        txtVer = findViewById(R.id.txtVerMais);

        recyclerViewsTT=findViewById(R.id.recyclerViewNotificacao);
        adapters = new NotificacaoAdapter(retornarNotificaTT(), this);
        recyclerViewsTT.setAdapter(adapters);
        recyclerViewsTT.setLayoutManager(new LinearLayoutManager(this));




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

    public List<Notifica> retornarNotificaTT(){
        LstNotifica.add(new Notifica("Notificação 1","01/01/2019"));
        LstNotifica.add(new Notifica("Notificação 2","01/02/2019"));
        LstNotifica.add(new Notifica("Notificação 3","01/03/2019"));
        LstNotifica.add(new Notifica("Notificação 4","01/04/2019"));
        LstNotifica.add(new Notifica("Notificação 5","01/05/2019"));
        LstNotifica.add(new Notifica("Notificação 6","01/06/2019"));

        return LstNotifica;

    }


    @Override
    public void onClicks(Notifica notifica) {
        Intent intent = new Intent(NotificacoesActivity.this, ListaEventosActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(NOTIFICA_KEY,notifica);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
