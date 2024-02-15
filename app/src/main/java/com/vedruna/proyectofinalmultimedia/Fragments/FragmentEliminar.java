package com.vedruna.proyectofinalmultimedia.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedruna.proyectofinalmultimedia.Interfaces.CRUDinterfaces;
import com.vedruna.proyectofinalmultimedia.R;
import com.vedruna.proyectofinalmultimedia.Utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Este fragmento permite al usuario eliminar un rapero existente de la base de datos. Presenta un campo
 * EditText donde se puede ingresar el ID del rapero que se desea eliminar y un botón para confirmar la eliminación.
 * Cuando el usuario hace clic en el botón, se envía una solicitud HTTP DELETE al servidor con el ID del rapero
 * a eliminar. Si la eliminación es exitosa, se muestra un mensaje indicando que el rapero fue eliminado
 * correctamente.
 */
public class FragmentEliminar extends Fragment {

    // Interfaz para realizar operaciones CRUD
    CRUDinterfaces crudInterfaces;

    // Botón para eliminar el rapero
    Button button;

    // EditText para ingresar el ID del rapero a eliminar
    EditText idEditText;

    // Constructor vacío requerido por la clase Fragment
    public FragmentEliminar() {
    }

    /**
     * Método que se ejecuta al crear la vista del fragmento. Aquí se infla el diseño de la interfaz
     * de usuario y se inicializan los componentes.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_eliminar, container, false);

        // Inicializar el EditText para el ID
        idEditText = view.findViewById(R.id.editTextID);

        // Configurar el botón de borrado con el clic
        setupDeleteButton(view);

        return view;
    }

    /**
     * Método auxiliar para configurar el botón de borrado.
     */
    private void setupDeleteButton(View view) {
        button = view.findViewById(R.id.buttonBorrarRapero);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el ID ingresado por el usuario
                String idString = idEditText.getText().toString().trim();

                // Verificar si el ID no está vacío
                if (!idString.isEmpty()) {
                    // Convertir el ID de String a int
                    int rapperId = Integer.parseInt(idString);
                    // Llamar al método de borrado con el ID del rapero
                    delete(rapperId);
                } else {
                    // Manejar el caso en el que el ID esté vacío
                    mostrarToast("El Id no puede estar vacío");
                }
            }
        });
    }

    /**
     * Método para enviar una solicitud HTTP DELETE al servidor para eliminar un rapero de la base de datos.
     */
    private void delete(int id) {
        // Construir la instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear la interfaz CRUDInterface
        crudInterfaces = retrofit.create(CRUDinterfaces.class);

        // Llamar al método de borrado con el ID del rapero
        Call<Void> call = crudInterfaces.delete(id);

        // Manejar la respuesta del servidor
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Verificar si la respuesta del servidor fue exitosa
                if (!response.isSuccessful()) {
                    // Mostrar un mensaje de error si la solicitud no fue exitosa
                    Log.e("Response err ", response.message());
                    return;
                }
                // Mostrar un mensaje indicando que el rapero fue eliminado correctamente
                mostrarToast("Rapero eliminado");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Mostrar un mensaje de error en caso de que la solicitud falle
                Log.e("Throw err:", t.getMessage());
            }
        });
    }

    /**
     * Método auxiliar para mostrar un Toast en la actividad actual.
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
