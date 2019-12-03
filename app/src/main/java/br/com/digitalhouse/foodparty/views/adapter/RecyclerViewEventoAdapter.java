package br.com.digitalhouse.foodparty.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.views.eventos.ListaEventosActivity;
import br.com.digitalhouse.foodparty.views.interfaces.ClickEvento;

public class RecyclerViewEventoAdapter extends RecyclerView.Adapter<RecyclerViewEventoAdapter.ViewHolder> {

    private List<Evento> listaEventos;
    private ClickEvento listener;

    public RecyclerViewEventoAdapter(List<Evento> listaEventos, ListaEventosActivity listener) {
        this.listaEventos = listaEventos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewEventoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_card_evento, viewGroup, false);
        return new RecyclerViewEventoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewEventoAdapter.ViewHolder viewHolder, int i) {
        final Evento eventos = listaEventos.get(i);
        viewHolder.bind(eventos);
        viewHolder.itemView.setOnClickListener(v -> {
            listener.onClick(eventos);
        });
    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFavorito;
        private TextView txtFavorito;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFavorito = itemView.findViewById(R.id.img_event_home);
            txtFavorito = itemView.findViewById(R.id.txt_nome_evento_home);
        }

        public void bind(Evento evento) {

            Picasso.get().load(new File(evento.getImgEvento())).into(imgFavorito);
            txtFavorito.setText(evento.getNomeEvento());

        }
    }
}
