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
import com.vedruna.proyectofinalmultimedia.Model.Rapper;
import com.vedruna.proyectofinalmultimedia.R;
import com.vedruna.proyectofinalmultimedia.Utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Este fragmento permite al usuario modificar los datos de un rapero existente. Presenta un formulario
 * con campos para ingresar el ID del rapero a modificar, así como los nuevos valores para el nombre,
 * la ciudad, el álbum y la URL de la imagen del rapero. Cuando el usuario hace clic en el botón de
 * actualizar, se envían los datos ingresados al servidor a través de una solicitud HTTP PUT para modificar
 * los datos del rapero en la base de datos.
 */

public class FragmentModificar extends Fragment {

    EditText nameText;
    EditText cityText;
    EditText albumText;
    EditText editTextUrlImagen;
    Button button;
    EditText idText;  // Nuevo EditText para el ID
    private Retrofit retrofit;
    CRUDinterfaces crudInterfaces;


    public FragmentModificar() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * Método que se ejecuta al crear la vista del fragmento. Aquí se infla el diseño de la interfaz
     * de usuario y se inicializan los componentes.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_modificar, container, false);

        // Inicializar los EditText
        idText = rootView.findViewById(R.id.editTextID);  // Nuevo EditText para el ID
        nameText = rootView.findViewById(R.id.editTextNombre);
        cityText = rootView.findViewById(R.id.editTextCiudad);
        albumText = rootView.findViewById(R.id.editTextAlbum);
        editTextUrlImagen = rootView.findViewById(R.id.editTextUrlImagen);

        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Inicializar el botón
        button = rootView.findViewById(R.id.buttonActualizarRapero);

        // Agregar un listener al botón para manejar clics
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

        return rootView;
    }


    /**
     * Método que se encarga de enviar una solicitud HTTP PUT al servidor para actualizar los datos
     * de un rapero existente en la base de datos.
     */
    private void actualizar() {
        String id = idText.getText().toString().trim();
        String nombre = nameText.getText().toString().trim();
        String ciudad = cityText.getText().toString().trim();
        String album = albumText.getText().toString().trim();
        String urlImagen = editTextUrlImagen.getText().toString().trim();

        // Verificar si alguno de los campos está vacío
        if (id.isEmpty() || nombre.isEmpty() || ciudad.isEmpty() || album.isEmpty() || urlImagen.isEmpty()) {
            // Mostrar Toast indicando que todos los campos son obligatorios
            mostrarToast("Todos los campos son obligatorios");
        } else {
            // Crear un rapero
            Rapper rapero = new Rapper(Integer.parseInt(id), nombre, ciudad, album, urlImagen);

            crudInterfaces = retrofit.create(CRUDinterfaces.class);

            // Llamar al método actualizar
            Call<Rapper> call = crudInterfaces.actualizar(Integer.parseInt(id), rapero);

            call.enqueue(new Callback<Rapper>() {
                @Override
                public void onResponse(Call<Rapper> call, Response<Rapper> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Response err ", response.message());
                        return;
                    }
                    Rapper raperoActualizado = response.body();
                    mostrarToast("Rapero actualizado: " + raperoActualizado.getName());
                }

                @Override
                public void onFailure(Call<Rapper> call, Throwable t) {
                    Log.e("Throw err:", t.getMessage());
                    mostrarToast("Error al actualizar el rapero");
                }
            });
        }
    }


    /**
     * Método auxiliar para mostrar un Toast en la actividad actual.
     */
    // Método para mostrar un Toast
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }

}