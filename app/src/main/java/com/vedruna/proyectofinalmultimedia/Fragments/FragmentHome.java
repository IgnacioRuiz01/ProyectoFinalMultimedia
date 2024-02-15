package com.vedruna.proyectofinalmultimedia.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vedruna.proyectofinalmultimedia.Adapters.RapperAdapter;
import com.vedruna.proyectofinalmultimedia.Interfaces.CRUDinterfaces;
import com.vedruna.proyectofinalmultimedia.Model.Rapper;
import com.vedruna.proyectofinalmultimedia.R;
import com.vedruna.proyectofinalmultimedia.Utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Este fragmento representa la pantalla principal de la aplicación, donde se muestran los raperos
 * registrados. Al iniciar, se solicita al servidor la lista de raperos y se muestra en un ListView.
 */
public class FragmentHome extends Fragment {

    // Lista de raperos obtenida del servidor
    List<Rapper> rappers;

    // Interfaz para realizar operaciones CRUD
    CRUDinterfaces crudInterface;

    // ListView para mostrar la lista de raperos
    ListView listView;

    /**
     * Constructor vacío requerido por la clase Fragment.
     */
    public FragmentHome(){

    }

    /**
     * Método que se ejecuta al crear el fragmento. Aquí se inicializan los componentes y se
     * solicita la lista de raperos al servidor.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método que obtiene la lista completa de raperos desde el servidor utilizando Retrofit.
     * Una vez obtenida la lista, se muestra en el ListView.
     */
    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudInterface = retrofit.create(CRUDinterfaces.class);
        Call<List<Rapper>> call = crudInterface.getAll();

        call.enqueue(new Callback<List<Rapper>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Rapper>> call, Response<List<Rapper>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response error ", response.message());
                    // Aquí puedes agregar un mensaje de error o una acción de recuperación apropiada
                    return;
                }

                List<Rapper> rappersResponse = response.body();
                if (rappersResponse == null || rappersResponse.isEmpty()) {
                    Log.e("Response error ", "La lista de raperos está vacía");
                    // Aquí puedes agregar un mensaje de error o una acción de recuperación apropiada
                    return;
                }

                // La respuesta es exitosa y la lista de raperos no está vacía
                rappers = rappersResponse;
                RapperAdapter rapperAdapter = new RapperAdapter(requireContext(), rappers);
                listView.setAdapter(rapperAdapter);

                // Puedes agregar un registro para verificar si la lista se llenó correctamente
                rappers.forEach(p -> Log.i("Raperos: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Rapper>> call, Throwable t) {
                Log.e("Throw err:", t.getMessage());
                // Aquí puedes agregar un mensaje de error o una acción de recuperación apropiada
            }
        });
    }

    /**
     * Método que se ejecuta al crear la vista del fragmento. Aquí se infla el diseño de la interfaz
     * de usuario y se inicializa el ListView.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.listView);

        // Se obtiene la lista de raperos
        getAll();
        return view;
    }

}


