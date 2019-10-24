package com.example.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Interface.FavoritosClick;
import com.example.login.R;
import com.example.model.ModelCard;
import com.example.model.ModelCardPratosHome;
import com.example.views.pratos.PratosFavoritosActivity;

import java.util.List;

public class CardFavoritosAdapter extends RecyclerView.Adapter<CardFavoritosAdapter.ViewHolder> {

    private List<ModelCardPratosHome> listaPratos;
    private FavoritosClick listener;

    public CardFavoritosAdapter(List<ModelCardPratosHome> listaPratos,
                                PratosFavoritosActivity listener) {
        this.listaPratos = listaPratos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_favoritos, viewGroup, false);
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
