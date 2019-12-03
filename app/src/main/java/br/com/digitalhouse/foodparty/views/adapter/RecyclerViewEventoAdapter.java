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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
        Evento evento = listaEventos.get(i);
        viewHolder.bind(evento);
        viewHolder.itemView.setOnClickListener(v -> {
            listener.onClick(evento);
        });
    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }

    public void atualizaLista(List<Evento> eventos) {
        this.listaEventos.clear();
        listaEventos = eventos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgEvento;
        private TextView txtFavorito;
        private TextView txtData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEvento = itemView.findViewById(R.id.img_event_home);
            txtFavorito = itemView.findViewById(R.id.txt_nome_evento_home);
            txtData = itemView.findViewById(R.id.data_evento_home);
        }

        public void bind(Evento evento) {

            Picasso.get().load(new File(evento.getImgEvento())).fit().centerCrop().into(imgEvento);
            txtFavorito.setText(evento.getNomeEvento());
            txtData.setText(evento.getDataEvento());
        }
    }
}
