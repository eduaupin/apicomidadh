package com.example.Adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Interface.RecyclerDetalhe;
import com.example.Modelo.Detalhe;
import com.example.login.R;

import java.util.List;

public class DetalheAdapter extends RecyclerView.Adapter<DetalheAdapter.ViewHolder> {

    private List<Detalhe> listaDetalhes;
    private RecyclerDetalhe listenerDetalhe;

    public DetalheAdapter(List<Detalhe> listaDetalhes, RecyclerDetalhe listenerDetalhe) {
        this.listaDetalhes = listaDetalhes;
        this.listenerDetalhe = listenerDetalhe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerdetalheevento,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int posicao) {

        final Detalhe detalhe = listaDetalhes.get(posicao);
        viewHolder.onBind(detalhe);

        viewHolder.itemView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerDetalhe.onClick(detalhe);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listaDetalhes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewX;
        private TextView txtNomeDetalhe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewX =itemView.findViewById(R.id.imgCardDetalher);
            txtNomeDetalhe = itemView.findViewById(R.id.txtCardDetalhe);
        }

        public void onBind(Detalhe detalhe) {
            txtNomeDetalhe.setText(detalhe.getTxtDetalhe());
            Drawable drawable = itemView.getResources().getDrawable(detalhe.getImgViewDtlhe());
            imageViewX.setImageDrawable(drawable);
        }
    }
}
