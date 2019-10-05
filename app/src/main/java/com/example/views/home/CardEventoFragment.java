package com.example.views.home;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.R;

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



    public CardEventoFragment() {
        // Required empty public constructor
    }

    public static Fragment novaInstancia(int imagem, String nomeEvento, String dataEvento){
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
        View view =  inflater.inflate(R.layout.fragment_card_evento, container, false);

        initViews(view);

        if(getArguments() != null){
            int imagemRecebida = getArguments().getInt(IMAGEM);
            String nomeRecebido = getArguments().getString(NOME);
            String dataRecebida = getArguments().getString(DATA);

            Drawable drawable = getResources().getDrawable(imagemRecebida);

            imgEvento.setImageDrawable(drawable);
            txtNomeEvento.setText(nomeRecebido);
            txtDataEvento.setText(dataRecebida);

        }

        return view;
    }

    private void initViews(View view){
        txtNomeEvento = view.findViewById(R.id.txt_nome_evento_home);
        txtDataEvento = view.findViewById(R.id.data_evento_home);
        imgEvento = view.findViewById(R.id.img_event_home);

    }
}
