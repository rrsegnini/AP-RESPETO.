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
    DatabaseReference databaseReference;
    DatabaseReference denunciasReference;



    public RespetoSistema() {
        //testingDatabase();
        obtenerReportes();
        boolean bandera = true;
    }




    private void obtenerReportes() {
         databaseReference = FirebaseDatabase.getInstance().getReference();
         denunciasReference = databaseReference.child("Denuncias");

        /**
        //obtener fecha hora actual para poder hacer la consulta
        Date fechaHoraActual = new Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(fechaHoraActual.getTime());
        //Query query = eventsReference.orderByChild("timestamp").startAt(ts);

        //modifica la fecha para obtener todos los reportes en un rango de 6 horas
        //Date newDate = DateUtils.addHours(fechaHoraActual, 3);


         * java.util.Date utilDate = new java.util.Date();
         java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
         */

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





}
