package cr.ac.tec.ec.respeto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Error";
//    private RespetoSistema sistema;
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        //sistema.databaseController.writeONEuser(LoginActivity.this);
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        EditText campoEmail = findViewById(R.id.login_txtEmail);
        EditText campoClave = findViewById(R.id.login_txtPassword);
        Button botonIngreso = findViewById(R.id.botonIngresar);
        Button botonReg = findViewById(R.id.botonRegistrar);

        botonIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = campoEmail.getText().toString();
                String contrasena = campoClave.getText().toString();

                if(!correo.equals("") || !contrasena.equals("")){
                    sign_in(correo, contrasena);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Debe rellenar todos los campos",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

        botonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Pantalla_registrar.class);
                startActivity(intent);
            }
        });





    }

    private void sign_in(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Ha ocurrido un error",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(LoginActivity.this, MainFeedActivity.class);
                        int pos = email.indexOf('@');
                        intent.putExtra("username", email.substring(0, pos));
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Ingreso exitoso",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }



    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
