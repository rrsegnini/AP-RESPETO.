package cr.ac.tec.ec.respeto;

import android.text.format.DateUtils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class RespetoSistema {

    private ArrayList<Denuncia> denuncias = new ArrayList<>();
    DatabaseReference databaseReference;
    DatabaseReference eventsReference;



    public RespetoSistema() {

    }


    private void obtenerReportes() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference eventsReference = databaseReference.child("events");

        //obtener fecha hora actual para poder hacer la consulta
        Date fechaHoraActual = new Date();
        //Timestamp ts=new Timestamp(fechaHoraActual.getTime());
        //System.out.println(ts);

        //Query query = eventsReference.orderByChild("timestamp").startAt(ts);

        //modifica la fecha para obtener todos los reportes en un rango de 6 horas
        //Date newDate = DateUtils.addHours(fechaHoraActual, 3);

    }





}
