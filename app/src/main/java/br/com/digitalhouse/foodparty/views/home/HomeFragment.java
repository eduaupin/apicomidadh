package br.com.digitalhouse.foodparty.views.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.viewmodel.HomeFragmentViewModel;
import br.com.digitalhouse.foodparty.views.adapter.CardEventoAdapter;
import br.com.digitalhouse.foodparty.views.adapter.PratosPopularesAdapter;
import br.com.digitalhouse.foodparty.views.eventos.DetalhesDoEventoActivity;
import br.com.digitalhouse.foodparty.views.eventos.ListaEventosActivity;
import br.com.digitalhouse.foodparty.views.interfaces.ClickEvento;
import br.com.digitalhouse.foodparty.views.interfaces.ClickPratos;
import br.com.digitalhouse.foodparty.views.pratos.DetalhesDoPratoActivity;
import br.com.digitalhouse.foodparty.views.pratos.ListaDePratosActivity;

public class HomeFragment extends Fragment implements ClickEvento, ClickPratos {
    public static final String EVENTO_KEY = "evento";
    public static final String PRATO_KEY = "pratos";

    private RecyclerView recyclerViewPratos;
    private PratosPopularesAdapter pratosAdapter;
    private List<Prato> listaPratosPopulares = new ArrayList<>();
    private HomeFragmentViewModel viewModel;
    private ProgressBar loadingPratos;
    private List<Evento> principaisEventos = new ArrayList<>();

    private TextView txtVerTodos;
    private TextView txtMaisPratos;

    private CardEventoAdapter eventoAdapter;
    private FragmentActivity contextoViewPager;
    private ViewPager viewPager;

    public HomeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        contextoViewPager = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);

        recyclerViewPratos.setAdapter(pratosAdapter);

        viewModel.getPratos();
        viewModel.getPratosLiveData().observe(this, pratos -> {
            pratosAdapter.atualizaLista(pratos);
        });

        viewModel.getLoading().observe(this, loading -> {
            if (loading) {
                loadingPratos.setVisibility(View.VISIBLE);
            } else {
                loadingPratos.setVisibility(View.GONE);
            }
        });

        viewModel.getErro().observe(this, s -> {
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            Log.i("HOMEERRORS", "Erros: " + s);
        });

        recyclerViewPratos.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPratos.setLayoutManager(layoutManager);

        //PagerView

        viewModel.getPrincipaisEventosLocal();

        viewModel.getPrincipaisEventos().observe(this, eventos -> {
            if (eventos.size() > 0) {
                for (Evento evento : eventos) {
                    principaisEventos.add(new Evento(evento.getImgEvento(), evento.getNomeEvento(), evento.getDataEvento(), evento.getHoraEvento(),
                            evento.getEnderecoEvento(), evento.getPratos(), evento.getParticipantes(), CardEventoFragment.novaInstancia(evento)));
                }
                eventoAdapter.atualizaViewPager(principaisEventos);
            } else {
                principaisEventos.add(new Evento(new NaoExistemEventosFragment()));
                eventoAdapter.atualizaViewPager(principaisEventos);
            }
        });

        FragmentManager fragManager = contextoViewPager.getSupportFragmentManager();
        eventoAdapter = new CardEventoAdapter(fragManager, principaisEventos);

        viewPager.setAdapter(eventoAdapter);

        viewPager.setOffscreenPageLimit(principaisEventos.size());

        txtVerTodos.setOnClickListener(view1 -> verTodos());
        txtMaisPratos.setOnClickListener(view12 -> verMais());

        return view;
    }

    private void initViews(View view) {
        txtMaisPratos = view.findViewById(R.id.txt_ver_mais);
        txtVerTodos = view.findViewById(R.id.txt_ver_todos);
        recyclerViewPratos = view.findViewById(R.id.recycler_home);
        viewPager = view.findViewById(R.id.viewPager_home);
        pratosAdapter = new PratosPopularesAdapter(listaPratosPopulares, this);
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        loadingPratos = view.findViewById(R.id.progress_bar_home);
    }

    private void verTodos() {
        startActivity(new Intent(getContext(), ListaEventosActivity.class));
    }

    private void detalhesDoEvento() {
        startActivity(new Intent(getContext(), DetalhesDoEventoActivity.class));
    }

    private void verMais() {
        startActivity(new Intent(getContext(), ListaDePratosActivity.class));
    }

    private void detalhesDoPrato() {
        startActivity(new Intent(getContext(), DetalhesDoPratoActivity.class));
    }

    @Override
    public void onClick(Evento evento) {
        Intent intent = new Intent(getContext(), DetalhesDoEventoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(EVENTO_KEY, evento);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(Prato prato) {
        Intent intent = new Intent(getContext(), DetalhesDoPratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRATO_KEY, prato);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
