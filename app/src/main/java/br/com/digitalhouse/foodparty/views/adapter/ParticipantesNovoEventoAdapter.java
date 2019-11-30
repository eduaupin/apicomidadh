package br.com.digitalhouse.foodparty.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import br.com.digitalhouse.foodparty.model.Participante;

import java.util.List;

public class ParticipantesNovoEventoAdapter extends RecyclerView.Adapter<ParticipantesNovoEventoAdapter.ViewHolder> {
    private List<Participante> participantesList;
    private TextView mensagemListaVazia;

    public ParticipantesNovoEventoAdapter(List<Participante> participantesList, TextView mensagemListaVazia) {
        this.participantesList = participantesList;
        this.mensagemListaVazia = mensagemListaVazia;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participantes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Participante participante = participantesList.get(position);
        holder.onBind(participante);
    }

    @Override
    public int getItemCount() {
//        mensagemListaVazia.setVisibility(participantesList.size() > 0 ? View.GONE : View.VISIBLE);
        return 0;
        //participantesList == null ? 0 : participantesList.size();
    }

    public void atualizaLista(List<Participante> listaAtualizada) {
        if (this.participantesList.isEmpty()) {
            this.participantesList = listaAtualizada;
        } else {
            this.participantesList.addAll(listaAtualizada);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeParticipante;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeParticipante = itemView.findViewById(R.id.item_participante_nome);
        }

        public void onBind(Participante participante) {
            nomeParticipante.setText(participante.getNome());
        }
    }
}
