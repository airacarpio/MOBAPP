package ph.edu.apc.mytenant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ph.edu.apc.mytenant.R;
import ph.edu.apc.mytenant.model.Tenant;

/**
 * Created by Gail on 12/3/2016.
 */

public class tenant_form  extends AppCompatActivity{

    EditText roomNum, rentalFee, eMeter, wMeter, tName, selectDate;
    Button btnSave, btnDate;


    ListView lv;
   // ArrayAdapter<String> adapter;
    DatabaseReference db;
    FirebaseHelper helper;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.form);
        //Firebase.setAndroidContext(this);

        roomNum = (EditText) findViewById(R.id.editText_room);
        rentalFee = (EditText) findViewById(R.id.editText_rentalFee);
        eMeter = (EditText) findViewById(R.id.editText_EMeter);
        wMeter = (EditText) findViewById(R.id.editText_WMeter);
        tName = (EditText) findViewById(R.id.editText_TenantName);
        selectDate = (EditText) findViewById(R.id.editText_SelectDate);
        btnDate = (Button) findViewById(R.id.button_date);
        btnSave = (Button) findViewById(R.id.button_save);

/*
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Getting values to store
                String room_number = roomNum.getText().toString();
                String rental_fee = rentalFee.getText().toString();
                String electricity_meter = eMeter.getText().toString();
                String water_meter = wMeter.getText().toString();
                String name = tName.getText().toString();
                String date_entered = dateEntered.getText().toString();

                //Creating Person object
                Tenant t = new Tenant();

                //Adding values
                t.setDate_entered(date_entered);
                t.setWater_meter(water_meter);
                t.setElectricity_meter(electricity_meter);
                t.setRental_fee(rental_fee);
                t.setRoom_number(room_number);
                t.setName(name);

                //Storing values to firebase
               // ref.setValue(t); */
                //VALIDATE
      /*          if(room_number.length()>0 && room_number != null)
                {
                    if(helper.save(t))
                    {
                        roomNum.setText("");

                        db= FirebaseDatabase.getInstance().getReference();
                        helper=new FirebaseHelper(db);

                        adapter=new CustomAdapter(tenant_form.this,helper.retrieve());
                        lv = (ListView) findViewById(R.id.listView);
                        lv.setAdapter(adapter);

                    }
                }else
                {
                    Toast.makeText(tenant_form.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        }); */
    }
}
