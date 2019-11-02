package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.interfaces.ClickPratos;
import com.example.login.R;
import com.example.model.ModelCardPratosHome;

import java.util.List;

public class CardPratoAdapter  extends RecyclerView.Adapter<CardPratoAdapter.ViewHolderPrato> {

        private List<ModelCardPratosHome> pratos;
        private ClickPratos listener;


        public CardPratoAdapter(List<ModelCardPratosHome> pratos, ClickPratos listener) {
            this.pratos = pratos;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ViewHolderPrato onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_pratos_populares, viewGroup, false);
            return new ViewHolderPrato(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderPrato viewHolderPrato, int i) {
            final ModelCardPratosHome prato = pratos.get(i);
            viewHolderPrato.bind(prato);

            //Seta a função de click no itemView(que é um parametro passado no construtor da classe
            viewHolderPrato.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //chamada do método da interface através do atributo
                    listener.onClick(prato);
                }
            });

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
