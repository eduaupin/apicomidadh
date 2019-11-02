package com.example.views.eventos;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.login.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalhesDoEventoFragment extends Fragment {


    public DetalhesDoEventoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalhes_do_evento, container, false);
    }

}
