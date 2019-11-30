package br.com.digitalhouse.foodparty.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.digitalhouse.foodparty.views.interfaces.ClickPratos;

import com.example.login.R;
import br.com.digitalhouse.foodparty.model.Prato;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PratosPopularesAdapter extends RecyclerView.Adapter<PratosPopularesAdapter.ViewHolderPrato> {

    private List<Prato> pratos;
    private ClickPratos listener;


    public PratosPopularesAdapter(List<Prato> pratos, ClickPratos listener) {
        this.pratos = pratos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderPrato onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pratos, viewGroup, false);
        return new ViewHolderPrato(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPrato viewHolderPrato, int i) {
        final Prato prato = pratos.get(i);
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
        return pratos == null ? 0 : pratos.size();
    }

    public void atualizaLista(List<Prato> listaAtualizada) {
        this.pratos.clear();
        pratos = listaAtualizada;
        notifyDataSetChanged();
    }

    class ViewHolderPrato extends RecyclerView.ViewHolder {
        ImageView imgPrato;
        TextView txtPrato;


        public ViewHolderPrato(@NonNull View itemView) {
            super(itemView);

            imgPrato = itemView.findViewById(R.id.img_prato_home);
            txtPrato = itemView.findViewById(R.id.txt_nome_prato);
        }


        public void bind(Prato prato) {
            txtPrato.setText(prato.getStrMeal());
            Picasso.get().load(prato.getStrMealThumb()).into(imgPrato);
        }
    }

}
