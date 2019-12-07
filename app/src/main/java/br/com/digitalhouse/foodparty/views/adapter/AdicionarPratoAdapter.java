package br.com.digitalhouse.foodparty.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.views.interfaces.ClickPratoAdicionar;

public class AdicionarPratoAdapter extends RecyclerView.Adapter<AdicionarPratoAdapter.ViewHolder> {
    private List<Prato> pratoSearchResult;
    private ClickPratoAdicionar listener;

    public AdicionarPratoAdapter(List<Prato> pratoSearchResult, ClickPratoAdicionar listener) {
        this.pratoSearchResult = pratoSearchResult;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pratos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prato prato = pratoSearchResult.get(position);
        holder.onBind(prato);
    }

    @Override
    public int getItemCount() {
        return pratoSearchResult == null ? 0 : pratoSearchResult.size();
    }

    public void atualizaLista(List<Prato> listaAtualizada) {
        this.pratoSearchResult.clear();
        pratoSearchResult = listaAtualizada;
        notifyDataSetChanged();
    }

    public void clear() {
        this.pratoSearchResult.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagePrato;
        private TextView nomePrato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePrato = itemView.findViewById(R.id.img_prato_home);
            nomePrato = itemView.findViewById(R.id.txt_nome_prato);
        }

        public void onBind(Prato prato) {
            nomePrato.setText(prato.getStrMeal());
            Picasso.get().load(prato.getStrMealThumb()).into(imagePrato);

            itemView.setOnClickListener(view -> {
                listener.onClickAdicionarPrato(prato);
            });
        }
    }
}
