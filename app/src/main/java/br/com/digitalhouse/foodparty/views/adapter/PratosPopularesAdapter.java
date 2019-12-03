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
import br.com.digitalhouse.foodparty.views.interfaces.ClickPratos;

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

        viewHolderPrato.itemView.setOnClickListener(v -> {
            listener.onClick(prato);
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
