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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Executor;

import static android.support.constraint.Constraints.TAG;

public class Controller {
    DatabaseReference databaseReference;
    DatabaseReference databaseDenuncias;
    DatabaseReference databaseUsuarios;


    private FirebaseAuth mAuth;

    private boolean BANDERA_REGISTRO = false;





    public Controller() {
        mAuth = FirebaseAuth.getInstance();
    }



    public Controller(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void writeONEuser(Activity context) {
        int cedula = 101110111;
        String nombreCompleto = "AdministradorUSERTEST";
        Genero genero = Genero.FEMENINO;
        int edad = 100;
        String alias = "Apodo del Administrador";
        String email = "admin@admin.com";
        String contrasenna = "admin123";
        writeNewUser(cedula,nombreCompleto,genero,edad,alias,email,context,contrasenna);





    }


    public void writeNewUser(int cedula, String nombreCompleto, Genero genero, int edad, String alias,
                             String email, Activity context, String contrasenna) {

        String genStr;
        if (genero == Genero.FEMENINO) {
            genStr = "f";

        } else {
            genStr = "m";
        }

        mAuth.createUserWithEmailAndPassword(email, contrasenna)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            //updateUI(null);

                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                int a = 0;
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                //mTxtEmail.setError(getString(R.string.error_invalid_email));
                                //mTxtEmail.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e) {
                                //mTxtEmail.setError(getString(R.string.error_user_exists));
                                //mTxtEmail.requestFocus();
                            } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                            }

                        }
                    }
                });
        String id = mAuth.getCurrentUser().getUid();
        Usuario user = new Usuario( alias,  cedula,  edad,  email,  genStr,  id,  nombreCompleto);

        //public Usuario(String id,int cedula, String nombreCompleto, String genero, int edad, String alias, String email)

        databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");

        databaseReference.push().setValue(user);


        /*
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

        */




        //String id = mAuth.getCurrentUser().getUid();

        /*
        if (writeUser) {
            Log.d(TAG, "createUserWithEmail:success");
        } else {
            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
        }

        * */


        //Usuario user = new Usuario(id, cedula,nombreCompleto,genStr,edad,alias,email);
        //databaseReference.child("usuarios").push().setValue(user);



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

                Collections.reverse(Arrays.asList(denuncias));
                ListaDenuncia adapter = new ListaDenuncia(context, denuncias);
                adapter.notifyDataSetChanged();
                listViewDenuncia.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private ArrayList<Denuncia> sortDenuncias(ArrayList<Denuncia> denuncias) {
        ArrayList<Denuncia> sorted = new ArrayList<>();




        return denuncias;
    }


    public String readUsuarios(FirebaseAuth mAuth,ArrayList<Usuario> usuarios) {

        String id  = mAuth.getCurrentUser().getUid();
        usuarios.clear();
        databaseUsuarios = FirebaseDatabase.getInstance().getReference("usuarios");
        databaseUsuarios.orderByChild("id").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });;


        return " ";
    }

    public String readUsuario() {
        mAuth = FirebaseAuth.getInstance();
        String id  = mAuth.getCurrentUser().getUid();
        databaseUsuarios = FirebaseDatabase.getInstance().getReference("usuarios");
        databaseUsuarios.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        return " ";
    }

}
