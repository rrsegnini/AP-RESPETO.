package cr.ac.tec.ec.respeto;

import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class RespetoSistema {

    private ArrayList<Denuncia> denuncias = new ArrayList<>();
    private DatabaseReference databaseReference;
    private DatabaseReference denunciasReference;



    public RespetoSistema() {

    }


    private void obtenerReportes() {
         databaseReference = FirebaseDatabase.getInstance().getReference();
         denunciasReference = databaseReference.child("cenuncias");



        denunciasReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                denuncias.clear();
                for (DataSnapshot denunciaSnapshot: dataSnapshot.getChildren()){

                    Denuncia denuncia = denunciaSnapshot.getValue(Denuncia.class);
                    denuncias.add(denuncia);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }


    public ArrayList<Denuncia> getDenuncias() {
        return denuncias;
    }

    public void setDenuncias(ArrayList<Denuncia> denuncias) {
        this.denuncias = denuncias;
    }
}
