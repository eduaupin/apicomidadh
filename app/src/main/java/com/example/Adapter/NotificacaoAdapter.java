package com.example.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Interface.RecyclerDetalhe;
import com.example.Interface.RecyclerNotificacao;
import com.example.Modelo.Detalhe;
import com.example.Modelo.Notifica;
import com.example.login.R;

import java.util.List;

public class NotificacaoAdapter extends RecyclerView.Adapter<NotificacaoAdapter.ViewHolder>{

    private List<Notifica> listaNotifica;
    private RecyclerNotificacao listenerNotifica;

    public NotificacaoAdapter(List<Notifica> listaNotifica, RecyclerNotificacao listenerNotifica) {
        this.listaNotifica = listaNotifica;
        this.listenerNotifica = listenerNotifica;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notificacao_card,viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Notifica notifica = listaNotifica.get(i);
        viewHolder.onBind(notifica);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerNotifica.onClicks(notifica);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaNotifica.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNotificaCard;
        private TextView txtdataNotifCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdataNotifCard.findViewById(R.id.txtDataCardNotif);
            txtNotificaCard.findViewById(R.id.txtnotificacaoCard);
        }

        public void onBind(Notifica notifica) {
        }
    }
}
