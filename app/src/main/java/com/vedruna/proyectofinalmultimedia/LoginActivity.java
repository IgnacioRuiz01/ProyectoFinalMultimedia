package com.vedruna.proyectofinalmultimedia;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;



/**
 * Esta clase maneja la funcionalidad de inicio de sesión en la aplicación, incluyendo la autenticación
 * mediante Google, así como la autenticación local con nombre de usuario y contraseña.
 */
public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;

    // Cliente para el inicio de sesión con Google
    private GoogleSignInClient mGoogleSignInClient;
    // Botón de inicio de sesión con Google
    SignInButton mSignInButtonGoogle;
    // Instancia de Firebase Auth
    private FirebaseAuth mAuth;
    // Campos de entrada para el nombre de usuario y contraseña
    private EditText name;
    private EditText password;
    // TextView para mostrar mensajes de error durante el inicio de sesión
    private TextView loginFallo;

    /**
     * Método que se ejecuta al crear la actividad. Configura los elementos de interfaz de usuario,
     * como los campos de nombre de usuario y contraseña, y los botones de inicio de sesión con Google.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        loginFallo = findViewById(R.id.loginFallo);

        // Configurar inicio de sesión con Google
        mAuth = FirebaseAuth.getInstance();
        mSignInButtonGoogle = findViewById(R.id.btnGoogle);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mSignInButtonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    /**
     * Método que se ejecuta al iniciar la actividad. Verifica si hay un usuario actualmente
     * autenticado.
     */
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /**
     * Método que inicia el proceso de inicio de sesión con Google.
     */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * Método que maneja el resultado de la actividad de inicio de sesión con Google.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                loginFallo.setVisibility(View.VISIBLE);
                loginFallo.setText(e.getMessage());
            }
        }
    }

    /**
     * Método que autentica al usuario en Firebase usando las credenciales de Google.
     */
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            irAlHome();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            loginFallo.setVisibility(View.VISIBLE);
                            loginFallo.setText(task.getException().toString());
                            updateUI(null);
                        }
                    }
                });
    }

    /**
     * Método que actualiza la interfaz de usuario según el estado de autenticación del usuario.
     */
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            irAlHome();
        }
    }

    /**
     * Método que inicia la actividad HomeActivity y finaliza la actividad actual.
     */
    private void irAlHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Método que maneja el evento de clic en el botón de inicio de sesión local.
     */
    public void login(View view) {
        String textoName = name.getText().toString();
        String textoPassword = password.getText().toString();

        if (textoName.equals("admin") && textoPassword.equals("admin")) {
            loginFallo.setVisibility(View.VISIBLE);
            loginFallo.setText("Usuario y Contraseña correctos");
            // Iniciar la HomeActivity
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            loginFallo.setVisibility(View.VISIBLE);
            loginFallo.setText("Usuario o Contraseña erróneos");
        }
    }
}
