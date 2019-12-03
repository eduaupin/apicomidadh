package br.com.digitalhouse.foodparty.views.home;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.digitalhouse.foodparty.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PratosPopularesFragment extends Fragment {


    public PratosPopularesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_pratos, container, false);
    }

}
