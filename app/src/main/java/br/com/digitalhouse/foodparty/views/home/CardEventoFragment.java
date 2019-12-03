package br.com.digitalhouse.foodparty.views.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.io.File;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.views.interfaces.ClickEvento;

public class CardEventoFragment extends Fragment {

    private static final String EVENTO_CARD_KEY = "evento";

    private ImageView imgEvento;
    private TextView txtNomeEvento;
    private TextView txtDataEvento;
    private CardView cardViewEvento;
    private ClickEvento clickEvento;

    public CardEventoFragment() {
    }

    public static Fragment novaInstancia(Evento evento) {
        CardEventoFragment cardFragment = new CardEventoFragment();
        Bundle bundle = new Bundle();

        bundle.putParcelable(EVENTO_CARD_KEY, evento);

        cardFragment.setArguments(bundle);

        return cardFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_evento, container, false);

        initViews(view);

        if (getArguments() != null) {
            Evento evento = getArguments().getParcelable(EVENTO_CARD_KEY);

            Picasso.get().load(new File(evento.getImgEvento())).into(imgEvento);
            txtNomeEvento.setText(evento.getNomeEvento());
            txtDataEvento.setText(evento.getDataEvento());

            cardViewEvento.setOnClickListener(view1 -> {
                clickEvento.onClick(evento);
            });
        }

        return view;
    }

    private void initViews(View view) {
        txtNomeEvento = view.findViewById(R.id.txt_nome_evento_home);
        txtDataEvento = view.findViewById(R.id.data_evento_home);
        imgEvento = view.findViewById(R.id.img_event_home);
        cardViewEvento = view.findViewById(R.id.card_view_evento_home);
    }
}
