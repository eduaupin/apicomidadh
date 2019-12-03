package br.com.digitalhouse.foodparty.views.home;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.digitalhouse.foodparty.views.eventos.DetalhesDoEventoActivity;
import br.com.digitalhouse.foodparty.views.interfaces.ClickEvento;

import br.com.digitalhouse.foodparty.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardEventoFragment extends Fragment {

    private static final String IMAGEM = "imagem";
    private static final String NOME = "nome";
    private static final String DATA = "data";

    private ImageView imgEvento;
    private TextView txtNomeEvento;
    private TextView txtDataEvento;
    private CardView cardViewEvento;
    private ClickEvento clickEvento;

    public CardEventoFragment() {
        // Required empty public constructor
    }

    public static Fragment novaInstancia(int imagem, String nomeEvento, String dataEvento) {
        CardEventoFragment cardFragment = new CardEventoFragment();
        Bundle bundle = new Bundle();

        bundle.putInt(IMAGEM, imagem);
        bundle.putString(NOME, nomeEvento);
        bundle.putString(DATA, dataEvento);

        cardFragment.setArguments(bundle);

        return cardFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_evento, container, false);

        initViews(view);

        if (getArguments() != null) {
            int imagemRecebida = getArguments().getInt(IMAGEM);
            String nomeRecebido = getArguments().getString(NOME);
            String dataRecebida = getArguments().getString(DATA);

            Drawable drawable = getResources().getDrawable(imagemRecebida);

            imgEvento.setImageDrawable(drawable);
            txtNomeEvento.setText(nomeRecebido);
            txtDataEvento.setText(dataRecebida);
        }

        cardViewEvento.setOnClickListener(view1 -> {
//            clickEvento.onClick(evento);
            //TODO: solução temporária até comunicarmos do BD com as activities
            startActivity(new Intent(getContext(), DetalhesDoEventoActivity.class));
        });

        return view;
    }

    private void initViews(View view) {
        txtNomeEvento = view.findViewById(R.id.txt_nome_evento_home);
        txtDataEvento = view.findViewById(R.id.data_evento_home);
        imgEvento = view.findViewById(R.id.img_event_home);
        cardViewEvento = view.findViewById(R.id.card_view_evento_home);
    }
}
