package com.example.tp4_grupo7_pa2v1;

import android.os.Bundle;

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

import com.example.tp4_grupo7_pa2v1.dao.ObtenerArticulo;
import com.example.tp4_grupo7_pa2v1.helper.ControlsExtendsHelper;

public class fragmentModificacion extends Fragment {
    public fragmentModificacion() {
    }

    public static fragmentModificacion newInstance() {
        fragmentModificacion fragment = new fragmentModificacion();
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
        return inflater.inflate(R.layout.fragment_modificacion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Button btnSeach = view.findViewById(R.id.btnSeach);
        EditText etIdSeach = view.findViewById(R.id.etIdSeach);
        EditText etNombreProducto = view.findViewById(R.id.etNameModify);
        EditText etStock = view.findViewById(R.id.etStockModify);
        Spinner spCategoria = view.findViewById(R.id.spinner_categoriasModify);

        Button btnModify = view.findViewById(R.id.btnModify);

        btnModify.setOnClickListener(view1 -> {
            if (ControlsExtendsHelper.ValidateRequireds(new EditText[] { etNombreProducto, etStock }))
            {
                // TODO: FALTA COMPLETAR, HAY QUE MODIFICAR EN LA BD.
                Toast.makeText(view1.getContext(), "Tengo que modificar en la BD.", Toast.LENGTH_SHORT).show();

                // Limpio los EditText.
                ControlsExtendsHelper.ClearTextControls(new EditText[] { etNombreProducto, etStock, etIdSeach });
            }
            else
            {
                Toast.makeText(view1.getContext(), "Complete todos los datos para modificar el producto.", Toast.LENGTH_SHORT).show();
            }
        });

        btnSeach.setOnClickListener(view12 -> {
            String id = etIdSeach.getText().toString();

            if (!id.equals(""))
            {
                ObtenerArticulo obtenerArticulo = new ObtenerArticulo(etIdSeach.getText().toString(),
                        etNombreProducto, etStock, spCategoria, view12.getContext());
                obtenerArticulo.execute();
            }
            else
            {
                Toast.makeText(view12.getContext(), "Debe ingresar un id para poder buscar el artìculo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}