package ph.edu.apc.mytenant.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.FirebaseError;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import ph.edu.apc.mytenant.R;
import ph.edu.apc.mytenant.activity.recyclerView.recyclerAdapter;
import ph.edu.apc.mytenant.model.Tenant;
import ph.edu.apc.mytenant.activity.FirebaseHelper;

import static android.R.id.list;

public class Content_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private Button bSignout;
    private TextView tvUserEmail;
    ListView lv;

    Calendar calendar = Calendar.getInstance();
    EditText roomNum, rentalFee, eMeter, wMeter, tName, selectDate;
    Button btnSave, btnDate;

    //ArrayAdapter<String> adapter;
    //  CustomAdapter adapter;        for custom only
    DatabaseReference db;
    FirebaseHelper helper;
    ProgressBar progressBar;
    //for recyclerView
    recyclerAdapter adapter;
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // lv = (ListView) findViewById(R.id.listView);      comment out for recycler
        //   registerForContextMenu(lv);

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        registerForContextMenu(rv);


        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, Signin_Activity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        tvUserEmail = (TextView) findViewById(R.id.tvUserEmail);
        tvUserEmail.setText("Welcome " + user.getEmail());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //SETUP FIREBASE
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);


        //ADAPTER
        // adapter = new CustomAdapter(this, helper.retrieve());
        //  lv.setAdapter(adapter);
        adapter = new recyclerAdapter(this, helper.retrieve());
        rv.setAdapter(adapter);
      /*  lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Content_Activity.this, helper.retrieve().get(position), Toast.LENGTH_SHORT).show();
            }
        }); */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogue();
                // Intent i = new Intent(Content_Activity.this, tenant_form.class);
                // startActivity(i);
                //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //          .setAction("Action", null).show();
            }
        });
    }


    private void showDialogue() {
        Dialog d = new Dialog(this);
        d.setTitle("Add Tenant");
        d.setContentView(R.layout.form);
        roomNum = (EditText) d.findViewById(R.id.editText_room);
        rentalFee = (EditText) d.findViewById(R.id.editText_rentalFee);
        eMeter = (EditText) d.findViewById(R.id.editText_EMeter);
        wMeter = (EditText) d.findViewById(R.id.editText_WMeter);
        tName = (EditText) d.findViewById(R.id.editText_TenantName);
        selectDate = (EditText) d.findViewById(R.id.editText_SelectDate);
        btnDate = (Button) d.findViewById(R.id.button_date);
        btnSave = (Button) d.findViewById(R.id.button_save);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Content_Activity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Getting values to store
                String room_number = roomNum.getText().toString();
                String rental_fee = rentalFee.getText().toString();
                String electricity_meter = eMeter.getText().toString();
                String water_meter = wMeter.getText().toString();
                String name = tName.getText().toString();
                String select_date = selectDate.getText().toString();
                //Creating Person object
                Tenant t = new Tenant();

                //Adding values
                t.setWater_meter(water_meter);
                t.setElectricity_meter(electricity_meter);
                t.setRental_fee(rental_fee);
                t.setRoom_number(room_number);
                t.setName(name);
                t.setSelect_date(select_date);
                //Storing values to firebase
                // ref.setValue(t); */
                //VALIDATE
                if (room_number.length() > 0 && room_number != null) {
                    if (helper.save(t)) {
                        roomNum.setText("");

                        //   db = FirebaseDatabase.getInstance().getReference();
                        //   helper = new FirebaseHelper(db);

                        //adapter = new CustomAdapter(Content_Activity.this, helper.retrieve());
                        //lv.setAdapter(adapter);

                        adapter = new recyclerAdapter(Content_Activity.this, helper.retrieve());
                        rv.setAdapter(adapter);

                    }
                } else {
                    Toast.makeText(Content_Activity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        d.show();
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            selectDate.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
        }
    };

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v,
                                     ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        switch(item.getItemId())
        {
            case R.id.delete:

                //   helper.db.getKey(FirebaseHelper.class, ).removeValue;
                // FirebaseHelper.


                //  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //   String clickedKey = .get(position);
                //  db.child(c,t.getRoom_number().removeValue();
        }

        // db.getRef().removeValue();

        // db.removeValue();

        //db.child.removeValue();



        return super.onContextItemSelected(item);

    }

    @Override
    public void onBackPressed () {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.content_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
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
    public boolean onNavigationItemSelected (MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Signin_Activity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}