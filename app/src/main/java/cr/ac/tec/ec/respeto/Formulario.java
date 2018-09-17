package cr.ac.tec.ec.respeto;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Formulario extends AppCompatActivity {

    private static final String TAG = "Formulario";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    private RespetoSistema sistema;

    private String placeID = "";

    DatabaseReference databaseDenuncias;
//    ListView listViewDenuncia;
    Controller databaseController;


    public String obtener_localización() {
        try {
            placeID = Objects.requireNonNull(getIntent().getExtras()).getString("localización");
        } catch (NullPointerException e) {
            Log.e(TAG, "instance initializer: ", e);
        }
        return placeID;
    }

    private long obtener_fecha() throws ParseException {
        Date date = new Date();
        return date.getTime();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        if (isServicesOK()) {
            init();
        }
    }

    private void init() {


        Button btnMap = (Button) findViewById(R.id.btn_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Formulario.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        TextView location = (TextView) findViewById(R.id.txt_location);

        Spinner location_spinner = (Spinner) findViewById(R.id.spinnerCategoria);

        location_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    btnMap.setEnabled(true);
                    location.setText(String.format("Ubicación seleccionada: %s", obtener_localización()));
                } else {
                    btnMap.setEnabled(false);
                    location.setText(String.format("Ubicación seleccionada: %s",
                            location_spinner.getSelectedItem().toString()));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                btnMap.setEnabled(true);
                location.setText(String.format("Ubicación seleccionada: %s", obtener_localización()));
            }
        });


        FloatingActionButton btnAgregar = (FloatingActionButton) findViewById(R.id.form_fabAdd);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText descripcion = (EditText) findViewById(R.id.txtDenuncia);
                Switch privacidad = (Switch) findViewById(R.id.switchPrivacidad);
                String mDescripcion = descripcion.getText().toString();
                String mUsuario = "Prueba";
                String mAlias = (privacidad.isChecked()) ? "Anonimo" : "Prueba";



                try {
                    String mLocation = location_spinner.getSelectedItem().toString();
                    if(mLocation.equals("Usar ubicación del mapa")){
                        mLocation = obtener_localización();
                    }
                    Denuncia denuncia = new Denuncia(mDescripcion, mLocation,
                            obtener_fecha(), mUsuario, mAlias );

                    sistema = new RespetoSistema();
                    //fetch database data
                    databaseDenuncias = FirebaseDatabase.getInstance().getReference("denuncias");

                    databaseDenuncias.push().setValue(denuncia);

                    Toast.makeText(Formulario.this,
                            "Publicación exitosa", Toast.LENGTH_SHORT).show();

                    Intent mainFeed = new Intent(Formulario.this, MainFeedActivity.class);
                    startActivity(mainFeed);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Formulario.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Formulario.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
