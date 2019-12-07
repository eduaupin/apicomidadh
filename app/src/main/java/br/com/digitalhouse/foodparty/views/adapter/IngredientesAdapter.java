package br.com.digitalhouse.foodparty.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Ingrediente;

import java.util.List;

public class IngredientesAdapter extends RecyclerView.Adapter<IngredientesAdapter.ViewHolder> {
    private List<Ingrediente> ingredientes;

    public IngredientesAdapter(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredientes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingrediente ingrediente = ingredientes.get(position);
        holder.onBind(ingrediente);
    }

    @Override
    public int getItemCount() {
        return ingredientes == null ? 0 : ingredientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredienteNome;
        private TextView ingredienteMedida;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredienteNome = itemView.findViewById(R.id.item_ingrediente_nome);
            ingredienteMedida = itemView.findViewById(R.id.item_ingrediente_medida);
        }

        public void onBind(Ingrediente ingrediente) {
            ingredienteNome.setText(ingrediente.getNome());
            ingredienteMedida.setText(ingrediente.getMedida());
        }
    }
}
