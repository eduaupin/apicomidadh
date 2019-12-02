package br.com.digitalhouse.foodparty.views.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.digitalhouse.foodparty.model.PratosFavoritos;
import br.com.digitalhouse.foodparty.views.interfaces.FavoritosClick;

import com.example.login.R;
import br.com.digitalhouse.foodparty.model.ModelCardPratosHome;
import br.com.digitalhouse.foodparty.views.pratos.PratosFavoritosActivity;

import java.util.List;

public class CardFavoritosAdapter extends RecyclerView.Adapter<CardFavoritosAdapter.ViewHolder> {

    private List<ModelCardPratosHome> listaPratos;
    private List<PratosFavoritos> pratosFavoritos;
    private FavoritosClick listener;

    public CardFavoritosAdapter(List<ModelCardPratosHome> listaPratos,
                                PratosFavoritosActivity listener) {
        this.listaPratos = listaPratos;
        this.listener = listener;
    }

    //método que atualiza a lista do adapter
    public void update(List<PratosFavoritos> pratosFavoritos) {
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
        final ModelCardPratosHome prato = listaPratos.get(i);
        viewHolder.bind(prato);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.favOnClick(prato);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFavorito;
        private TextView txtFavorito;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFavorito = itemView.findViewById(R.id.imgCardFavoritos);
            txtFavorito = itemView.findViewById(R.id.txtCardFavoritos);
        }

        public void bind(ModelCardPratosHome prato) {

            Drawable drawable = itemView.getResources().getDrawable(prato.getImgPrato());
            imgFavorito.setImageDrawable(drawable);
            txtFavorito.setText(prato.getTxtPrato());

        }
    }
}
