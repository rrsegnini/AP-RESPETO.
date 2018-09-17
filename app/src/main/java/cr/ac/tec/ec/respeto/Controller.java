package cr.ac.tec.ec.respeto;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import static android.support.constraint.Constraints.TAG;

public class Controller {
    DatabaseReference databaseReference;
    DatabaseReference databaseDenuncias;

    private FirebaseAuth mAuth;






    public Controller() {
        mAuth = FirebaseAuth.getInstance();
    }



    public Controller(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void wirteONEuser(int cedula, String nombreCompleto, Genero genero, int edad, String alias,
                             String email, Activity context, String contrasenna) {


    }


    public void writeNewUser(int cedula, String nombreCompleto, Genero genero, int edad, String alias, String email, Activity context, String contrasenna) {
        String genStr;
        if (genero == Genero.FEMENINO) {
            genStr = "f";

        } else {
            genStr = "m";
        }


        mAuth.createUserWithEmailAndPassword(email, contrasenna)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

        String id = mAuth.getCurrentUser().getUid();

        /*
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

        * */


        Usuario user = new Usuario(id, cedula,nombreCompleto,genStr,edad,alias,email);
        databaseReference.child("usuarios").push().setValue(user);



    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    /**
     * metodo utilizado para que escuche las colecciones de denuncias
     *
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
