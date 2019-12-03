package br.com.digitalhouse.foodparty.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.ModelNotificacao;

import java.util.List;

public class NotificacaoAdapter extends RecyclerView.Adapter<NotificacaoAdapter.ViewHolder> {


    private List<ModelNotificacao> listaNotificacao;


    public NotificacaoAdapter(List<ModelNotificacao> listaNotificacao) {
        this.listaNotificacao = listaNotificacao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificacoes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModelNotificacao notificacao = listaNotificacao.get(position);
        holder.onBind(notificacao);

    }

    @Override
    public int getItemCount() {
        return listaNotificacao.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notification_title);
            date = itemView.findViewById(R.id.notification_date);
        }

        public void onBind(ModelNotificacao notificacao){
        title.setText(notificacao.getTitle());
        date.setText(notificacao.getDate());
        }
    }
}
