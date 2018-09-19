package cr.ac.tec.ec.respeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Pantalla_registrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registrar);

        Button boton_Registrar = findViewById(R.id.boton_reg);
        EditText nombre = findViewById(R.id.campoNombre);
        EditText email = findViewById(R.id.campoEmail);
        EditText cedula = findViewById(R.id.campoCedula);
        Spinner genero = findViewById(R.id.spinnerGenero);
        EditText fecha_nac = findViewById(R.id.campoFechaNac);
        EditText nombre_usuario = findViewById(R.id.campoNombreUsuario);

        boton_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoNombre = nombre.getText().toString();
                String textoEmail = email.getText().toString();
                String textoCedula = cedula.getText().toString();
                String textoGenero = genero.getSelectedItem().toString();
                String textoNacimiento = fecha_nac.getText().toString();
                String textoNombreUsuario = nombre_usuario.getText().toString();


            }
        });

    }
}
