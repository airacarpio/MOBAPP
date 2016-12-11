package ph.edu.apc.mytenant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ph.edu.apc.mytenant.R;

public class Detail_Activity extends AppCompatActivity {

    TextView txtroom, txtrental, txtemeter, txtwmeter, txtname, txtdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtroom = (TextView) findViewById(R.id.textView_detailRoom);
        txtrental = (TextView) findViewById(R.id.textView_detailRental);
        txtemeter = (TextView) findViewById(R.id.textView_detailEMeter);
        txtwmeter = (TextView) findViewById(R.id.textView_detailWMeter);
        txtname = (TextView) findViewById(R.id.textView_detailName);
        txtdate = (TextView) findViewById(R.id.textView_detailDate);

        //get intent
        Intent i = this.getIntent();

        //receive data
        String room = i.getExtras().getString("ROOM_NUM");
        String rental = i.getExtras().getString("RENTAL_FEE");
        String emeter = i.getExtras().getString("W_METER");
        String wmeter = i.getExtras().getString("E_METER");
        String name = i.getExtras().getString("NAME");
        String date = i.getExtras().getString("SELECT_DATE");

        //bind data
        txtroom.setText(room);
        txtrental.setText(rental);
        txtwmeter.setText(wmeter);
        txtemeter.setText(emeter);
        txtname.setText(name);
        txtdate.setText(date);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
