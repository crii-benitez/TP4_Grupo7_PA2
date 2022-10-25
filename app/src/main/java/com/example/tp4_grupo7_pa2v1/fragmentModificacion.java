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

import com.example.tp4_grupo7_pa2v1.dao.AltaArticulo;
import com.example.tp4_grupo7_pa2v1.dao.BuscarArticulo;
import com.example.tp4_grupo7_pa2v1.dao.CargarSpinner;
import com.example.tp4_grupo7_pa2v1.dao.ModifyArticulo;
import com.example.tp4_grupo7_pa2v1.dao.ObtenerArticulo;
import com.example.tp4_grupo7_pa2v1.entidad.Articulo;
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

        CargarSpinner cargarSpinner = new CargarSpinner(spCategoria, view.getContext());
        cargarSpinner.execute();

        Button btnModify = view.findViewById(R.id.btnModify);

        btnModify.setOnClickListener(view1 -> {
            if (ControlsExtendsHelper.ValidateRequireds(new EditText[] { etIdSeach, etNombreProducto, etStock }))
            {
                try {
                    Integer id = Integer.parseInt(etIdSeach.getText().toString());

                    BuscarArticulo busqueda = new BuscarArticulo();
                    busqueda.setId(id);

                    Articulo a = new Articulo(id,etNombreProducto.getText().toString(),
                            Integer.parseInt(etStock.getText().toString()), spCategoria.getSelectedItemPosition() +1);

                    ModifyArticulo modify = new ModifyArticulo();
                    modify.setArticulo(a);
                    String resultado = modify.execute().get();
                    Toast.makeText(getActivity(), resultado, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error al modificar", Toast.LENGTH_SHORT).show();
                }
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