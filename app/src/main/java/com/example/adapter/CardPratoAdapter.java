package com.example.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.R;
import com.example.model.ModelCardPratosHome;

import java.util.ArrayList;
import java.util.List;

public class CardPratoAdapter  extends RecyclerView.Adapter<CardPratoAdapter.ViewHolderPrato> {

        private List<ModelCardPratosHome> pratos;


        public CardPratoAdapter(List<ModelCardPratosHome> pratos) {
            this.pratos = pratos;
        }

        @NonNull
        @Override
        public ViewHolderPrato onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_pratos_populares, viewGroup, false);
            return new ViewHolderPrato(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderPrato viewHolderPrato, int i) {

            ModelCardPratosHome prato = pratos.get(i);

            viewHolderPrato.bind(prato);

        }

        @Override
        public int getItemCount() {
            return pratos.size();
        }

        class ViewHolderPrato extends RecyclerView.ViewHolder{
            ImageView imgPrato;
            TextView txtPrato;


            public ViewHolderPrato(@NonNull View itemView) {
                super(itemView);

                imgPrato = itemView.findViewById(R.id.img_prato_home);
                txtPrato = itemView.findViewById(R.id.txt_nome_prato);
            }


            public void bind(ModelCardPratosHome prato){
                txtPrato.setText(prato.getTxtPrato());
            }
        }

    }
