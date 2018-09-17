package cr.ac.tec.ec.respeto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private RespetoSistema sistema;

    @Override
    protected void onStart() {
        super.onStart();
        sistema = new RespetoSistema();
        //sistema.databaseController.writeONEuser(LoginActivity.this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Usuario administrador = new Usuario("1", 3, "Administrador", Genero.MASCULINO, 21, "admin", "admin@admin.com", "Adminadmin123");

        EditText campoEmail = findViewById(R.id.login_txtEmail);
        EditText campoClave = findViewById(R.id.login_txtPassword);
        Button botonIngreso = findViewById(R.id.botonIngresar);

        //almacenar temporalmente el usuario mientras se hace lo de meter a la BD.
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
        listaUsuario.add(administrador);

        botonIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = campoEmail.getText().toString();
                String contrasena = campoClave.getText().toString();

                if(correo.equals(administrador.getEmail()) && contrasena.equals(administrador.getContrasenna())){
                    Toast toast = Toast.makeText(getApplicationContext(), "Está ingresando como administrador", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(LoginActivity.this, MainFeedActivity.class);
                    Bundle usuarioActual = new Bundle();

                    //Aquí paso los datos que le voy a enviar a MainFeedActivity
                    usuarioActual.putString("nombre",administrador.getNombre());
                    usuarioActual.putString("correo",administrador.getEmail());
                    usuarioActual.putString("alias",administrador.getAlias());
                    intent.putExtras(usuarioActual);

                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Correo/contraseña incorrecto", Toast.LENGTH_SHORT);
                    toast.show();
                }



            }
        });





    }


}
