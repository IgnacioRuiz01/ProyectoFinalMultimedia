package com.vedruna.proyectofinalmultimedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Esta clase representa la actividad principal de la aplicación una vez que el usuario ha iniciado
 * sesión correctamente. Proporciona una interfaz de usuario con una barra de navegación inferior
 * y fragmentos asociados para las diferentes funcionalidades de la aplicación.
 */
public class HomeActivity extends AppCompatActivity {

    // Botón para cerrar sesión
    Button mButtonCerrarSesion;

    // Instancia de Firebase Auth
    FirebaseAuth mAuth;

    /**
     * Método que se ejecuta al crear la actividad. Configura la interfaz de usuario con la barra
     * de navegación inferior y los fragmentos asociados.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        mAuth = FirebaseAuth.getInstance();

        mButtonCerrarSesion = findViewById(R.id.logoutButton);

        mButtonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                navController.navigate(R.id.FragmentHome);
            } else if (item.getItemId() == R.id.navigation_crear) {
                navController.navigate(R.id.FragmentCrear);
            } else if (item.getItemId() == R.id.navigation_modificar) {
                navController.navigate(R.id.FragmentModificar);
            } else if (item.getItemId() == R.id.navigation_eliminar) {
                navController.navigate(R.id.FragmentEliminar);
            }
            return true;
        });
    }

    /**
     * Método que se ejecuta al iniciar la actividad. Verifica si hay un usuario autenticado.
     * Si no hay ninguno, redirige al usuario a la pantalla de inicio de sesión.
     */
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            irMain();
        }
    }

    /**
     * Método que maneja el evento de cierre de sesión. Desautentica al usuario y lo redirige
     * a la pantalla de inicio de sesión.
     */
    private void logout() {
        mAuth.signOut();
        irMain();
    }

    /**
     * Método que redirige al usuario a la pantalla de inicio de sesión.
     */
    private void irMain() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
