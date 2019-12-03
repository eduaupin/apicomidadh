package br.com.digitalhouse.foodparty.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Participante;

public class AdicionarParticipanteAdapter extends RecyclerView.Adapter<AdicionarParticipanteAdapter.ViewHolder> {
    private List<Participante> participanteList;

    public AdicionarParticipanteAdapter(List<Participante> participanteList) {
        this.participanteList = participanteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participante_add, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Participante participante = participanteList.get(position);
        holder.onBind(participante);

        holder.removeName.setOnClickListener(view -> {
            removerItem(position);
        });
    }

    @Override
    public int getItemCount() {
        return participanteList == null ? 0 : participanteList.size();
    }

    private void removerItem(int position) {
        participanteList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, participanteList.size());
    }

    public void adicionarItem(Participante participante) {
        participanteList.add(participante);
        notifyItemInserted(getItemCount());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textName;
        private TextView removeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.item_nome_participante);
            removeName = itemView.findViewById(R.id.item_remove_participante);
        }

        public void onBind(Participante participante) {
            textName.setText(participante.getNome());
        }
    }
}
