package cr.ac.tec.ec.respeto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class PantallaComentarios extends AppCompatActivity {

    private ListView listViewComentario;
    private DatabaseReference databaseComentarios;
    private Controller databaseController;
    private Button search;
    private EditText criteriaText;
    private RespetoSistema sistema;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_comentarios);
        EditText cComent;
        Button bPub;

        bPub = (Button) findViewById(R.id.publicar);
        cComent = findViewById(R.id.writeNewComment);

        bPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String commentario = cComent.getText().toString();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


        databaseController = sistema.databaseController;

        ArrayList<Comentario> comentariosLista = new ArrayList<>();

        //leer denuncias
        databaseController.readComentarios(PantallaComentarios.this, comentariosLista,listViewComentario);

    }



}
