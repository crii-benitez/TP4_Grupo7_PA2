package com.example.tp4_grupo7_pa2v1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp4_grupo7_pa2v1.dao.AltaArticulo;
import com.example.tp4_grupo7_pa2v1.dao.BuscarArticulo;
import com.example.tp4_grupo7_pa2v1.helper.ControlsExtendsHelper;
import com.example.tp4_grupo7_pa2v1.dao.CargarSpinner;
import com.example.tp4_grupo7_pa2v1.entidad.Articulo;

public class fragmentAlta extends Fragment {

    EditText etId, etNombreProducto, etStock, etCategoria;
    private Spinner spinnerCat;

    public fragmentAlta() {

    }

    public static fragmentAlta newInstance() {
        // Recibo info del que lo invoque.
        fragmentAlta fragment = new fragmentAlta();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_alta, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        etId = view.findViewById(R.id.etId);
        etNombreProducto = view.findViewById(R.id.etName);
        etStock = view.findViewById(R.id.etStock);
        spinnerCat = (Spinner) view.findViewById(R.id.spinner_categorias);

        Button btnAdd = view.findViewById(R.id.btnAdd);

        CargarSpinner cargarSpinner = new CargarSpinner(spinnerCat, view.getContext());
        cargarSpinner.execute();

        btnAdd.setOnClickListener(view1 -> {
            if (ControlsExtendsHelper.ValidateRequireds(new EditText[] { etId, etNombreProducto, etStock }))
            {
                try {
                    Integer id = Integer.parseInt(etId.getText().toString());

                    BuscarArticulo busqueda = new BuscarArticulo();
                    busqueda.setId(id);

                    Articulo art = busqueda.execute().get();
                    if (art != null) {
                        Toast.makeText(getActivity(), "El ID ingresado ya existe", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Articulo a = new Articulo(id,etNombreProducto.getText().toString(),
                            Integer.parseInt(etStock.getText().toString()), spinnerCat.getSelectedItemPosition() +1);

                    AltaArticulo alta = new AltaArticulo();
                    alta.setArticulo(a);

                    String resultado = alta.execute().get();
                    Toast.makeText(getActivity(), resultado, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error al insertar", Toast.LENGTH_SHORT).show();
                }

                // Limpio los EditText.
                ControlsExtendsHelper.ClearTextControls(new EditText[] { etId, etNombreProducto, etStock });
            }
            else
            {
                Toast.makeText(view1.getContext(), "Complete todos los datos para agregar el producto.", Toast.LENGTH_LONG).show();
            }
        });
    }
}