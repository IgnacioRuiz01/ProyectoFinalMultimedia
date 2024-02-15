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

import com.vedruna.proyectofinalmultimedia.DTO.RapperDTO;
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
 * Este fragmento permite al usuario crear un nuevo rapero. Presenta un formulario con campos para
 * ingresar el nombre, la ciudad, el álbum y la URL de la imagen del rapero. Cuando el usuario hace
 * clic en el botón de crear, se envían los datos ingresados al servidor a través de una solicitud
 * HTTP POST para agregar un nuevo rapero a la base de datos.
 */
public class FragmentCrear extends Fragment {

    // EditText para ingresar el ID, nombre, ciudad, álbum y URL de la imagen del rapero
    EditText idText;
    EditText nameText;
    EditText cityText;
    EditText albumText;
    EditText editTextUrlImagen;

    // Botón para crear un nuevo rapero
    Button button;

    // Interfaz para realizar operaciones CRUD
    CRUDinterfaces crudInterfaces;

    /**
     * Constructor vacío requerido por la clase Fragment.
     */
    public FragmentCrear() {
    }

    /**
     * Método que se ejecuta al crear la vista del fragmento. Aquí se infla el diseño de la interfaz
     * de usuario y se inicializan los componentes.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_crear, container, false);

        // Inicializar los EditText
        idText = rootView.findViewById(R.id.editTextId);
        nameText = rootView.findViewById(R.id.editTextNombre);
        cityText = rootView.findViewById(R.id.editTextCiudad);
        albumText = rootView.findViewById(R.id.editTextAlbum);
        editTextUrlImagen = rootView.findViewById(R.id.editTextUrlImagen);

        // Inicializar el botón
        button = rootView.findViewById(R.id.buttonCrearRapero);

        // Agregar un listener al botón para manejar clics
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario
                String id = idText.getText().toString();
                String nombre = nameText.getText().toString();
                String ciudad = cityText.getText().toString();
                String album = albumText.getText().toString();
                String urlImagen = editTextUrlImagen.getText().toString();

                // Verificar si alguno de los campos está vacío
                if (id.isEmpty() || nombre.isEmpty() || ciudad.isEmpty() || album.isEmpty() || urlImagen.isEmpty()) {
                    // Mostrar Toast indicando que todos los campos son obligatorios
                    mostrarToast("Todos los campos son obligatorios");
                } else {
                    // Crear un objeto RapperDTO con los datos ingresados
                    RapperDTO rapper = new RapperDTO(nombre, ciudad, album, urlImagen);
                    // Llamar al método para crear un nuevo rapero en el servidor
                    create(rapper);
                }
            }
        });

        return rootView;
    }

    /**
     * Método que realiza una solicitud HTTP POST al servidor para crear un nuevo rapero con los datos
     * proporcionados por el usuario.
     */
    private void create(RapperDTO rapper){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de la interfaz CRUDinterfaces utilizando Retrofit
        crudInterfaces = retrofit.create(CRUDinterfaces.class);
        // Realizar una solicitud HTTP POST al servidor para crear un nuevo rapero
        Call<Rapper> call = crudInterfaces.create(rapper);

        // Manejar la respuesta del servidor
        call.enqueue(new Callback<Rapper>() {
            @Override
            public void onResponse(Call<Rapper> call, Response<Rapper> response) {
                if(!response.isSuccessful()){
                    // Mostrar un mensaje de error en caso de que la solicitud no sea exitosa
                    Log.e("Response err ",response.message());
                    return;
                }
                // Obtener el rapero creado desde la respuesta del servidor
                Rapper rapper = response.body();
                // Mostrar un mensaje indicando que el rapero se añadió correctamente
                mostrarToast("Rapero añadido correctamente: " + rapper.getName());
            }

            @Override
            public void onFailure(Call<Rapper> call, Throwable t) {
                // Mostrar un mensaje de error en caso de que la solicitud falle
                Log.e("Throw err:",t.getMessage());
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
