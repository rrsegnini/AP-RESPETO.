package cr.ac.tec.ec.respeto;

import com.google.firebase.database.DatabaseReference;

public class Controller {
    DatabaseReference databaseReference;
    DatabaseReference eventsReference;

    public Controller(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    private void writeNewUser(String userId, String name, String email) {
        Usuario user = new Usuario();
        databaseReference.child("Usuarios").child(userId).setValue(user);
    }

    private void readDenuncias() {

    }
}
