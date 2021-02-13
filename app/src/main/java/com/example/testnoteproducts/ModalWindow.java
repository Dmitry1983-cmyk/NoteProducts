package com.example.testnoteproducts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ModalWindow extends DialogFragment {
    private Removeable removable;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        removable = (Removeable) context;
    }

    @NonNull
    public Dialog onCreateDialog( Bundle savedInstanceState) {

        final String product = getArguments().getString("product");

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Вычеркнуть из списка")
                .setMessage("продукт " + product + "?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removable.remove(product);
                    }
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}
