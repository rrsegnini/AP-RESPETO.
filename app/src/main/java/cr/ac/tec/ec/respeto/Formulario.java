package cr.ac.tec.ec.respeto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class Formulario extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        addReportButton();
    }

    private void  addReportButton(){
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

}
