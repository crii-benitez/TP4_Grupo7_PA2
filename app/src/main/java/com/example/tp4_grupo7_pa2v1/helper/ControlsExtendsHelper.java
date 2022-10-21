package com.example.tp4_grupo7_pa2v1.helper;

import android.graphics.Color;
import android.widget.EditText;

public class ControlsExtendsHelper {

    // Método encargado de validar los requeridos de los controles EditText enviados por parametro.
    public static boolean ValidateRequireds(EditText editTexts[])
    {
        boolean notErrors = true;

        for (EditText control: editTexts) {

            if (control.getText().toString().equals("")) {
                control.setHintTextColor(Color.RED);
                control.setHint("Requerido");
                notErrors = false;
            } else {
                control.setHint("");
                notErrors = true;
            }
        }

        return notErrors;
    }

    // Método encargado de blanquear los campos enviados por parametros.
    public static void ClearTextControls(EditText editTexts[])
    {
        for (EditText control: editTexts) {
                control.setHint("");
                control.setText("");
        }
    }
}
