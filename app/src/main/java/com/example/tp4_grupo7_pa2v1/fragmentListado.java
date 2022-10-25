package com.example.tp4_grupo7_pa2v1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.tp4_grupo7_pa2v1.conexion.DataMainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentListado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentListado extends Fragment {
    private ListView lvArticulos;

    public fragmentListado() {
    }

    public static fragmentListado newInstance() {
        fragmentListado fragment = new fragmentListado();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lvArticulos = getView().findViewById(R.id.lvArticulos);
        Button btnActualizar = getView().findViewById(R.id.btnActualizar);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connect();
            }
        });
        Connect();
    }

    public void Connect() {
        DataMainActivity task = new DataMainActivity(lvArticulos, this.getContext());
        task.execute();
    }
}