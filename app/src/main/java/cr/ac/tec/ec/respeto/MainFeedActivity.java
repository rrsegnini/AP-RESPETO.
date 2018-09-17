package cr.ac.tec.ec.respeto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainFeedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DatabaseReference databaseDenuncias;
    private ListView listViewDenuncia;
    private Controller databaseController;
    private TableLayout feedTable;
    private Button search;
    private EditText criteriaText;
    private RespetoSistema sistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent form = new Intent(MainFeedActivity.this, Formulario.class);
                startActivity(form);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        criteriaText = (EditText) findViewById(R.id.searchCriteriaTextEdit);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //setting attributes
        //this.feedTable = findViewById(R.id.feedTableLayout);


        //database
        sistema = new RespetoSistema();
        //fetch database data
        databaseDenuncias = FirebaseDatabase.getInstance().getReference("denuncias");

        listViewDenuncia = (ListView) findViewById(R.id.listViewDenuncia);

        listViewDenuncia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent comments = new Intent(MainFeedActivity.this, PantallaComentarios.class);
                startActivity(comments);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


        databaseController = sistema.databaseController;


        //leer denuncias
        databaseController.readDenuncias(MainFeedActivity.this, sistema.getDenuncias(), listViewDenuncia);
        //metodo utilizado para que escuche las colecciones de denuncias

        /*
        databaseDenuncias.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                sistema.getDenuncias().clear();

                for (DataSnapshot denunciaSnapshot : dataSnapshot.getChildren()) {
                    Denuncia denuncia = denunciaSnapshot.getValue(Denuncia.class);


                    //query para obtener el user nombre del usuario para mandarlo a la clase

                    sistema.getDenuncias().add(denuncia);
                }


                ListaDenuncia adapter = new ListaDenuncia(MainFeedActivity.this, sistema.getDenuncias());
                adapter.notifyDataSetChanged();
                listViewDenuncia.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_emergency) {
            Intent emergency_contacts = new Intent(MainFeedActivity.this, PantallaNumEmergencia.class);
            startActivity(emergency_contacts);

        } else if (id == R.id.nav_slideshow) {
            Intent help = new Intent(MainFeedActivity.this, PantallaAyuda.class);
            startActivity(help);

        } else if (id == R.id.nav_manage) {

            //} else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private TextView setNewTextView(int i) {
        //Movie m = movies.get(i);
        TextView new_element = new TextView(this);
        new_element.setId(i);

        new_element.setTag(i);
        new_element.setText("Nombre: ");
        new_element.setOnClickListener((View.OnClickListener) this);
        new_element.setLayoutParams(new TableRow.LayoutParams(300, 400));
        return new_element;
    }


    //Debe de recibir un arreglo de denuncias
    private void populateTable() {
        //clean table
        this.feedTable.removeAllViews();


        TableRow newRow = new TableRow(this);
        //Collections.reverse(_movies);

        int i = 0;
        TextView new_element1 = null;
        new_element1 = setNewTextView(i);
    }
}