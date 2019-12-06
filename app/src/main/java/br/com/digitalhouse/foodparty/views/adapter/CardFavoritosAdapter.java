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
import br.com.digitalhouse.foodparty.views.interfaces.FavoritosClick;

public class CardFavoritosAdapter extends RecyclerView.Adapter<CardFavoritosAdapter.ViewHolder> {

    private List<Prato> pratosFavoritos;
    private FavoritosClick listener;

    public CardFavoritosAdapter(List<Prato> pratosFavoritos, FavoritosClick listener) {
        this.pratosFavoritos = pratosFavoritos;
        this.listener = listener;
    }

    //método que atualiza a lista do adapter
    public void update(List<Prato> pratosFavoritos) {
        this.pratosFavoritos = pratosFavoritos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_prato_favorito, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Prato prato = pratosFavoritos.get(i);
        viewHolder.bind(prato);
        viewHolder.itemView.setOnClickListener(v -> {
            listener.favOnClick(prato);
        });
    }

    @Override
    public int getItemCount() {
        return pratosFavoritos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPrato;
        private TextView txtPrato;
        private ImageView imgFavorito;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFavorito = itemView.findViewById(R.id.imgCardPrato);
            txtPrato = itemView.findViewById(R.id.txtCardFavoritos);
            imgPrato = itemView.findViewById(R.id.imgCardPrato);
        }

        public void bind(Prato prato) {

            Picasso.get().load(prato.getStrMealThumb()).into(imgPrato);
            txtPrato.setText(prato.getStrMeal());
        }
    }
}
