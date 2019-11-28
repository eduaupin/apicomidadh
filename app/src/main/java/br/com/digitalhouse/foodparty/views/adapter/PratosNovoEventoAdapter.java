package br.com.digitalhouse.foodparty.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.views.interfaces.ClickPratoAdicionar;

import com.example.login.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PratosNovoEventoAdapter extends RecyclerView.Adapter<PratosNovoEventoAdapter.ViewHolder> {
    private List<Prato> pratosList;
    private TextView mensagemListaVazia;
    private ClickPratoAdicionar listener;

    public PratosNovoEventoAdapter(List<Prato> pratosList, TextView mensagemListaVazia, ClickPratoAdicionar listener) {
        this.pratosList = pratosList;
        this.mensagemListaVazia = mensagemListaVazia;
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
        Prato prato = pratosList.get(position);
        holder.onBind(prato);
    }

    @Override
    public int getItemCount() {
        mensagemListaVazia.setVisibility(pratosList.size() > 0 ? View.GONE : View.VISIBLE);
        return pratosList == null ? 0 : pratosList.size();
    }

    public void atualizaLista(List<Prato> listaAtualizada) {
        if (this.pratosList.isEmpty()) {
            this.pratosList = listaAtualizada;
        } else {
            this.pratosList.addAll(listaAtualizada);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagemPrato;
        private TextView nomePrato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagemPrato = itemView.findViewById(R.id.img_prato_home);
            nomePrato = itemView.findViewById(R.id.txt_nome_prato);
        }

        public void onBind(Prato prato) {
            nomePrato.setText(prato.getStrMeal());
            Picasso.get().load(prato.getStrMealThumb()).into(imagemPrato);
        }
    }
}
