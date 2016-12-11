package ph.edu.apc.mytenant.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ph.edu.apc.mytenant.R;
import ph.edu.apc.mytenant.model.Tenant;

import static android.content.ContentValues.TAG;

/**
 * Created by Gail on 12/5/2016.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Tenant> tenants;


    public CustomAdapter(Context c, ArrayList<Tenant> tenants) {
        this.c = c;
        this.tenants = tenants;
    }

    @Override
    public int getCount() {
        return tenants.size();
    }

    @Override
    public Object getItem(int i) {
        return tenants.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.list_item, viewGroup, false);
        }

        TextView txtRoomNum = (TextView) convertView.findViewById(R.id.textView_roomNum);
        TextView txtRentalFee = (TextView) convertView.findViewById(R.id.textView_rentalFee);
        TextView txtWMeter = (TextView) convertView.findViewById(R.id.textView_waterMeter);
        TextView txtEMeter = (TextView) convertView.findViewById(R.id.textView_electricMeter);
        TextView txtName = (TextView) convertView.findViewById(R.id.textView_name);
        TextView txtDate = (TextView) convertView.findViewById(R.id.textView_dateEntered);

        final Tenant t = (Tenant) this.getItem(i);

        txtRoomNum.setText(t.getRoom_number());
        txtRentalFee.setText(t.getRental_fee());
        txtWMeter.setText(t.getWater_meter());
        txtEMeter.setText(t.getElectricity_meter());
        txtName.setText(t.getName());
        txtDate.setText(t.getSelect_date());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailActivity(t.getRoom_number(),t.getRental_fee(),t.getWater_meter(),t.getElectricity_meter(),t.getName(),t.getSelect_date());
                //Toast.makeText(c,t.getRoom_number(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }


    private void openDetailActivity(String...details) {
        Intent i = new Intent(c,Detail_Activity.class);
        i.putExtra("ROOM_NUM",details[0]);
        i.putExtra("RENTAL_FEE",details[1]);
        i.putExtra("W_METER",details[2]);
        i.putExtra("E_METER",details[3]);
        i.putExtra("NAME",details[4]);
        i.putExtra("SELECT_DATE",details[5]);

        c.startActivity(i);

    }



}
