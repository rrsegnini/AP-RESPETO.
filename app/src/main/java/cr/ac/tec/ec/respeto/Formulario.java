package cr.ac.tec.ec.respeto;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.Objects;

public class Formulario extends AppCompatActivity{

    private static final String TAG = "Formulario";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    public String obtener_localización() {
        String placeID = "";
        try {
            placeID = Objects.requireNonNull(getIntent().getExtras()).getString("localización");
        } catch (NullPointerException e){
            Log.e(TAG, "instance initializer: ", e);
        }
        if(placeID == null){

        }
        return placeID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        if(isServicesOK()){
            init();
        }
        addReportButton();
    }

    private void init(){
        Button btnMap = (Button) findViewById(R.id.btn_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Formulario.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        TextView location = (TextView) findViewById(R.id.txt_location);
        location.setText(String.format("Ubicación seleccionada: %s", obtener_localización()));
    }

    private void addReportButton(){
        findViewById(R.id.form_fabAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Formulario.this,
                        "Publicación exitosa", Toast.LENGTH_SHORT).show();

                Intent mainFeed = new Intent(Formulario.this, MainFeedActivity.class);
                startActivity(mainFeed);

            }
        });
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Formulario.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Formulario.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
