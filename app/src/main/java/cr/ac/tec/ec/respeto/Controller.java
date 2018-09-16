package cr.ac.tec.ec.respeto;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    DatabaseReference databaseReference;
    DatabaseReference databaseDenuncias;


    public Controller() {}

    public Controller(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    private void writeNewUser(String userId, String name, String email) {
        Usuario user = new Usuario();
        databaseReference.child("Usuarios").child(userId).setValue(user);
    }

    /**
     * metodo utilizado para que escuche las colecciones de denuncias
     * @param context
     * @param denuncias
     * @param listViewDenuncia
     */
    public void readDenuncias(Activity context, ArrayList<Denuncia> denuncias,
                               ListView listViewDenuncia) {

        databaseDenuncias = FirebaseDatabase.getInstance().getReference("denuncias");
        databaseDenuncias.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                denuncias.clear();

                for (DataSnapshot denunciaSnapshot : dataSnapshot.getChildren()) {
                    Denuncia denuncia = denunciaSnapshot.getValue(Denuncia.class);


                    //query para obtener el user nombre del usuario para mandarlo a la clase

                    denuncias.add(denuncia);
                }


                ListaDenuncia adapter = new ListaDenuncia(context, denuncias);
                adapter.notifyDataSetChanged();
                listViewDenuncia.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
