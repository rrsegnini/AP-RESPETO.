package cr.ac.tec.ec.respeto;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Pantalla_registrar extends AppCompatActivity {

    private static final String TAG = "PRegistrar";

    Controller controladorBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registrar);
        String[] generos = {"Masculino","Femenino"};
        Button boton_Registrar = findViewById(R.id.boton_reg);
        EditText nombre = findViewById(R.id.campoNombre);
        EditText email = findViewById(R.id.campoEmail);
        EditText cedula = findViewById(R.id.campoCedula);
        Spinner genero = findViewById(R.id.spinnerGenero);
        EditText fecha_nac = findViewById(R.id.campoFechaNac);
        EditText contrasenia = findViewById(R.id.campoNombreUsuario);
        genero.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, generos));

        boton_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoNombre = nombre.getText().toString();
                String textoEmail = email.getText().toString();
                String textoCedula = cedula.getText().toString();
                String textoGenero = genero.getSelectedItem().toString();
                String textoNacimiento = fecha_nac.getText().toString();
                String textoContrasenna = contrasenia.getText().toString();

                try {
                    int cedula = Integer.parseInt(textoCedula);
                    int edad = Integer.parseInt(textoNacimiento);
                    registrar(cedula,textoNombre,textoGenero,edad,"Alias",textoEmail,Pantalla_registrar.this,textoContrasenna);

                    Intent intent = new Intent(Pantalla_registrar.this, MainFeedActivity.class);
                    startActivity(intent);

                }catch (Exception e) {
                    Toast.makeText(Pantalla_registrar.this, "Ha ocurrido un error",
                            Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onClick: ", e);
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        controladorBD = new Controller();
    }

    private void registrar(int cedula, String nombreCompleto, String genero, int edad, String alias,
                           String email, Activity context, String contrasenna) {
        controladorBD.writeNewUser(cedula,nombreCompleto,genero,edad,alias,email,context,contrasenna);
    }


}
